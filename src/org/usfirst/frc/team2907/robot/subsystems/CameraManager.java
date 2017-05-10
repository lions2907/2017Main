package org.usfirst.frc.team2907.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2907.robot.commands.ReadCommand;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraManager extends Subsystem
{
	public boolean aligned = false;
	/* GLOBAL CONSTANTS */
	/* image is 320 x 200 */
	public static final double IMAGE_WIDTH = 320.0;
	public static final double IMAGE_HEIGHT = 200.0;
	/* blocks of fields are 14 bytes */
	public static final int BLOCK_SIZE = 14;
	public static final int BUFFER_SIZE = 64;
	/* RESUSED FIELDS */
	private byte[] bytes;
	/* GEAR CAMERA CONSTANTS */
	/* Each pixy had a different lens, gear camera had FOV of 75 */
	public static final double PIXY_POV_GEAR = 75; 
	/* physical width of gear lift */
	public static final double GEAR_WIDTH_FT = 1.166;
	/* i2c address configured in pixymon */
	public static int PIXY_ADDRESS_GEAR = 0x54;
	/* GEAR CAMERA FIELDS */
	private I2C gearCamera;
	private boolean gearInRange;
	private double gearOffset;
	private ArrayList<PixyBlock> gearBlocks;
	private double gearArea;
	/* TOWER CAMERA CONSTANTS */
	/* tower camera lens fov was 42, longer range smaller fov */
	public static final double PIXY_POV_TOWER = 42;
	/* tower cam address registered in pixymon */ 
	public static int PIXY_ADDRESS_TOWER = 0x55;
	/* TOWER CAMERA FIELDS */
	private I2C towerCamera;
	private ArrayList<PixyBlock> towerBlocks;
	private double towerXOffset;
	private double towerYOffset;
	private boolean towerInRange;
	
	public CameraManager()
	{
		/* init i2c comminication with pixy camera */
		gearCamera = new I2C(I2C.Port.kOnboard/*RIO I2C*/, PIXY_ADDRESS_GEAR);
		towerCamera = new I2C(I2C.Port.kMXP/*NAVX MXP I2C*/, PIXY_ADDRESS_TOWER);
	}
	
	@Override
	public void initDefaultCommand()
	{
		/* the default command will be constantly called */
		setDefaultCommand(new ReadCommand());
	}
	
	/* called by the ReadCommand constantly */
	public void readCameras()
	{
		System.out.println("---------- READING GEAR CAMERA ----------");
		gearRead();
		System.out.println("---------- END GEAR CAMERA ---------- \n");
		System.out.println("---------- READING TOWER CAMERA ----------");
		towerRead();
		System.out.println("---------- END TOWER CAMERA ---------- \n");
	}
	
	private void towerRead()
	{
		if (towerBlocks == null) 	// lazy instantiation
			towerBlocks = new ArrayList<>();
			
		towerBlocks.clear(); 		// remove last read
		read(towerBlocks, towerCamera); // read from tower camera and store result into tower block array list
		
		if (towerBlocks.size() > 0)	// if any target
		{
			/* calculate larger target on tower */
			PixyBlock largerBlock;	
			if (towerBlocks.size() == 1)
			{
				largerBlock = towerBlocks.get(0);
			} else if ((towerBlocks.get(0).width * towerBlocks.get(0).height) > (towerBlocks.get(1).width * towerBlocks.get(1).height))
			{
				largerBlock = towerBlocks.get(0);
			} else 
			{
				largerBlock = towerBlocks.get(1);
			}
			
			/* save largest target coordinates */
			towerXOffset = largerBlock.centerX;
			towerYOffset = largerBlock.centerY;
			System.out.println("tower x " + towerXOffset);
			System.out.println("tower y " + towerYOffset);
			/* set tower found flag to true */
			towerInRange = true;
		} else /* not targets, tower not in range */
			towerInRange = false;
	}
	
	private void gearRead()
	{
		if (gearBlocks == null)
			gearBlocks = new ArrayList<>();
			
		gearBlocks.clear();				// clear last read
		gearBlocks = read(gearBlocks, gearCamera);	// read from gear camera and store result in gearblocks
		
		if (gearBlocks.size() > 0) 			// check for targets
		{
			System.out.println();
			if (gearBlocks.size() >= 2) 		// gear lift has 2 pieces of reflective tape
			{
				/* calculate left and right block */
				PixyBlock leftBlock;		
				PixyBlock rightBlock;
				if (gearBlocks.get(0).centerX > gearBlocks.get(1).centerX)
				{
					leftBlock = gearBlocks.get(1);
					rightBlock = gearBlocks.get(0);
				} else
				{
					leftBlock = gearBlocks.get(0);
					rightBlock = gearBlocks.get(1);
				}
				// calculate center between both targets
				double difference = (rightBlock.centerX + leftBlock.centerX) / 2;
				// calculate area
				gearArea = ((rightBlock.width + leftBlock.width) / 2) * ((rightBlock.height + leftBlock.height) / 2);
				// log results
				System.out.println("Center X : " + difference + ", targets : " + gearBlocks.size() + ", area : " + gearArea);
				setGearOffset(difference);
			} else
			{ /
				// only one target, likely at an angle
				gearArea = gearBlocks.get(0).width * gearBlocks.get(0).height;
				setGearOffset(gearBlocks.get(0).centerX);
			}
		} else
		{
			// no targets
			gearArea = 0;
			gearInRange = false;
		}
	}
	
	/* read function. reads buffered from specified i2c bus into given arraylist */
	private ArrayList<PixyBlock> read(ArrayList<PixyBlock> blocks, I2C camera)
	{
		// create new buffer
		bytes = new byte[BUFFER_SIZE];
		// read i2c bus
		camera.read(0x54, BUFFER_SIZE, bytes);
		// search for pixy header blocks as specified in docs. 
		// by default i2c must send data, so the buffer will be filled even without any targets
		int index = 0;
		for (; index < bytes.length - 1; ++index)
		{
			int b1 = bytes[index];
			if (b1 < 0)
				b1 += 256;
			
			int b2 = bytes[index + 1];
			if (b2 < 0)
				b2 += 256;
			
			if (b1 == 0x55 && b2 == 0xaa) // searching for header
				break;
		}
		
		if (index == BUFFER_SIZE - 1) // no header found
			return blocks;
		else if (index == 0) 		// header found at start index
			index += 2;
		
		System.out.println("-----------------");
		for (int byteOffset = index; byteOffset < bytes.length - BLOCK_SIZE - 1;)
		{
			// checking for sync block
			int b1 = bytes[byteOffset];
			if (b1 < 0)
				b1 += 256;
			
			int b2 = bytes[byteOffset + 1];
			if (b2 < 0)
				b2 += 256;
			
			if (b1 == 0x55 && b2 == 0xaa)
			{
				// copy block into temp buffer
				byte[] temp = new byte[BLOCK_SIZE];
				// StringBuilder sb = new StringBuilder("Data : ");
				for (int tempOffset = 0; tempOffset < BLOCK_SIZE; ++tempOffset)
				{
					temp[tempOffset] = bytes[byteOffset + tempOffset];
					// sb.append(temp[tempOffset] + ", ");
					// System.out.println("read byte : " + temp[tempOffset]);
				}
				// System.out.println(sb.toString());
				
				PixyBlock block = bytesToBlock(temp);
				if (block != null)
				{
					blocks.add(block);
//					System.out.println("Block width : " + block.width + ", block height : " + block.height);
//					System.out.println("Block x : " + block.centerX + ", block y : " + block.centerY);
//					System.out.println("Sig : " + block.signature);
//					System.out.println("checksum : " + block.checksum);
					byteOffset += BLOCK_SIZE - 1;
				} else
					++byteOffset;
			} else
				++byteOffset;
		}
		return blocks;
	}
	
	// convert bytes to block
	public PixyBlock bytesToBlock(byte[] bytes)
	{
		PixyBlock pixyBlock = new PixyBlock();
		pixyBlock.sync = bytesToInt(bytes[1], bytes[0]);
		pixyBlock.checksum = bytesToInt(bytes[3], bytes[2]);
		
		// if the checksum is 0 or the checksum is a sync byte, then there
		// are no more frames.
		// if (pixyBlock.checksum == 0 || pixyBlock.checksum == 0xaa55)
		// return null;
		
		// pixyBlock.signature = bytesToInt(bytes[5], bytes[4]);
		// pixyBlock.centerX = bytesToInt(bytes[7], bytes[6]);
		// pixyBlock.centerY = bytesToInt(bytes[9], bytes[8]);
		// pixyBlock.width = bytesToInt(bytes[11], bytes[10]);
		// pixyBlock.height = bytesToInt(bytes[13], bytes[12]);
		
		// System.out.println("centerx byte b1 : " + bytes[7] + ", b2 : "
		// + bytes[6]);
		
		pixyBlock.signature = orBytes(bytes[5], bytes[4]);
		pixyBlock.centerX = ((((int) bytes[7] & 0xff) << 8) | ((int) bytes[6] & 0xff));
		pixyBlock.centerY = ((((int) bytes[9] & 0xff) << 8) | ((int) bytes[8] & 0xff));
		pixyBlock.width = ((((int) bytes[11] & 0xff) << 8) | ((int) bytes[10] & 0xff));
		pixyBlock.height = ((((int) bytes[13] & 0xff) << 8) | ((int) bytes[12] & 0xff));
		return pixyBlock;
	}
	
	public int orBytes(byte b1, byte b2)
	{
		return (b1 & 0xff) | (b2 & 0xff);
	}
	
	// convert byte to int
	public int bytesToInt(int b1, int b2)
	{
		if (b1 < 0)
			b1 += 256;
		
		if (b2 < 0)
			b2 += 256;
		
		int intValue = b1 * 256;
		intValue += b2;
		return intValue;
	}
	
	// PixyBlock class for holding values
	public class PixyBlock
	{
		// 0, 1 0 sync (0xaa55)
		// 2, 3 1 checksum (sum of all 16-bit words 2-6)
		// 4, 5 2 signature number
		// 6, 7 3 x center of object
		// 8, 9 4 y center of object
		// 10, 11 5 width of object
		// 12, 13 6 height of object
		
		public int sync;
		public int checksum;
		public int signature;
		public int centerX;
		public int centerY;
		public int width;
		public int height;
	}
	
	public void setGearOffset(double offset)
	{
		gearOffset = offset;
		gearInRange = true;
	}
	
	public double getGearOffset()
	{
		return gearOffset;
	}
	
	public double getTowerXOffset()
	{
		return towerXOffset;
	}
	
	public double getTowerYOffset()
	{
		return towerYOffset;
	}
	
	public boolean isTowerInRange()
	{
		return towerInRange;
	}
	
	public double getGearArea()
	{
		return gearArea;
	}
	
	// pid wrapper class for gear camera
	public PIDSource gearCameraPID = new PIDSource()
	{

		@Override
		public void setPIDSourceType(PIDSourceType pidSource)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double pidGet()
		{
			// TODO Auto-generated method stub
			return gearOffset;
		}
		
	};
	
	//  pid wrapper class for tower camera, un-implemented
	public PIDSource towerCameraPID = new PIDSource()
	{

		@Override
		public void setPIDSourceType(PIDSourceType pidSource)
		{
		}

		@Override
		public PIDSourceType getPIDSourceType()
		{
			return null;
		}

		@Override
		public double pidGet()
		{
			return 0;
		}
		
	};
	
}
