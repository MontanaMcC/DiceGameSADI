package robot.ascii;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import control.RobotControl;
import robot.Robot;
import robot.ascii.impl.Block;
import robot.ascii.impl.Arm;
import robot.ascii.impl.Bar;
import robot.impl.RobotImpl;
import robot.impl.RobotInitException;

// ASCIIBot template code Programming 1 s2, 2017
// designed by Caspar, additional code by Ross
public class ASCIIBot extends AbstractASCIIBot implements Robot
{
	public int[] barHeights;
	public int[] blockHeights;
	public int height;
	public int width;
	public int depth;
	public int maxRow = terminalFrame.getTerminalSize().getRows()-1;
	public int maxCol = terminalFrame.getTerminalSize().getColumns() - 1;
	public int pickCount;
	public int dropCount;
	
	public static void main(String[] args)
	{
		new RobotControl().control(new ASCIIBot(), null, null);
	}

	// MUST CALL DEFAULT SUPERCLASS CONSTRUCTOR!
	public ASCIIBot()
	{
		super();
	}

	@Override
	public void init(int[] barHeights, int[] blockHeights, int height, int width, int depth)
	{
		// in addition to validating init params this also sets up keyboard support for the ASCIIBot!
		try
		{
			RobotImpl.validateInitParams(this, barHeights, blockHeights, height, width, depth);
		}
		catch (RobotInitException e)
		{
			System.err.println(e.getMessage());
			System.exit(0);
		}

		// init method
		initImpl();
		// must flush to display contents after each complete draw operation
		terminalFrame.flush();
	}

	public void armDraw() {
		terminalFrame.setForegroundColor(TextColor.ANSI.BLACK);
		terminalFrame.setBackgroundColor(TextColor.ANSI.WHITE);
		
		for (int rowPos = maxRow; rowPos >= maxRow - height; rowPos--) {
			terminalFrame.setCursorPosition(0, rowPos);
			terminalFrame.putCharacter(' ');
		}
		
		for (int widthPos = 0; widthPos <= width; widthPos++) {
			terminalFrame.setCursorPosition(widthPos, (maxRow - height));
			terminalFrame.putCharacter(' ');
		}
		
		for (int rowPos = 0; rowPos <= depth; rowPos++) {
			terminalFrame.setCursorPosition(width, rowPos);
			terminalFrame.putCharacter(' ');
		}
	}
	
	public void blockDraw() {
		int col1Height = 0;
		int col2Height = 0;
		int pickedBlock = ((blockHeights.length - pickCount) - 1);
		
		terminalFrame.setForegroundColor(TextColor.ANSI.BLACK);
		
		// if a block is picked, it will draw it under the arm
		if (pickCount >= 0 && pickCount > dropCount) {
			if (blockHeights[pickedBlock] == 1) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.YELLOW);
			}
			else if (blockHeights[pickedBlock] == 2) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.RED);
			}
			else if (blockHeights[pickedBlock] == 3) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.BLUE);
			}
			else
				terminalFrame.setBackgroundColor(TextColor.ANSI.CYAN);
			
			for (int rowPos = depth + 1; rowPos < ((depth + 1) + blockHeights[pickedBlock]); rowPos++) {
				terminalFrame.setCursorPosition(width, rowPos);
				terminalFrame.putCharacter(' ');
			}
		}
		
		// if less than 9 blocks have been dropped, draw them starting from the first bar to the last
		if (dropCount >= 0 && dropCount < 9) {
			for (int i = 0; i <= dropCount; i++) {
				if (blockHeights[((blockHeights.length - 1) - i)] == 1) {
					terminalFrame.setBackgroundColor(TextColor.ANSI.YELLOW);
				}
				else if (blockHeights[((blockHeights.length - 1) - i)] == 2) {
					terminalFrame.setBackgroundColor(TextColor.ANSI.RED);
				}
				else if (blockHeights[((blockHeights.length - 1) - i)] == 3) {
					terminalFrame.setBackgroundColor(TextColor.ANSI.BLUE);
				}
				else
					terminalFrame.setBackgroundColor(TextColor.ANSI.CYAN);
		
				for (int rowPos = maxRow - barHeights[i]; rowPos > maxRow - barHeights[i] - blockHeights[((blockHeights.length - 1) - i)]; rowPos--) {
					terminalFrame.setCursorPosition(2 + i, rowPos);
					terminalFrame.putCharacter(' ');
				}
			}
		}
		
		// if more than 8 blocks have been placed, draw them going forward while count is under 9
		if (dropCount >= 9) {
			int count = 0;
			do {
				int i = 0;
				for (i = 0; i <= dropCount; i++) {
					if (blockHeights[((blockHeights.length - 1) - i)] == 1) {
						terminalFrame.setBackgroundColor(TextColor.ANSI.YELLOW);
					}
					else if (blockHeights[((blockHeights.length - 1) - i)] == 2) {
						terminalFrame.setBackgroundColor(TextColor.ANSI.RED);
					}
					else if (blockHeights[((blockHeights.length - 1) - i)] == 3) {
						terminalFrame.setBackgroundColor(TextColor.ANSI.BLUE);
					}
					else
						terminalFrame.setBackgroundColor(TextColor.ANSI.CYAN);
			
					for (int rowPos = maxRow - barHeights[i]; rowPos > maxRow - barHeights[i] - blockHeights[((blockHeights.length - 1) - i)]; rowPos--) {
						terminalFrame.setCursorPosition(2 + i, rowPos);
						terminalFrame.putCharacter(' ');
					}
					// adds the block heights to the bars so when more blocks are placed on top, it knows the height
					barHeights[i] = barHeights[i] + blockHeights[i];
				}
			} while (count < 9);
			// draw them going back after the 8th block is placed
			for (int i = 8; i >= 0; i--) {
				if (blockHeights[((blockHeights.length - 1) - i)] == 1) {
					terminalFrame.setBackgroundColor(TextColor.ANSI.YELLOW);
				}
				else if (blockHeights[((blockHeights.length - 1) - i)] == 2) {
					terminalFrame.setBackgroundColor(TextColor.ANSI.RED);
				}
				else if (blockHeights[((blockHeights.length - 1) - i)] == 3) {
					terminalFrame.setBackgroundColor(TextColor.ANSI.BLUE);
				}
				else
					terminalFrame.setBackgroundColor(TextColor.ANSI.CYAN);
			
				for (int rowPos = maxRow - barHeights[i]; rowPos > maxRow - barHeights[i] - blockHeights[((blockHeights.length - 1) - i)]; rowPos--) {
					terminalFrame.setCursorPosition(2 + i, rowPos);
					terminalFrame.putCharacter(' ');
				}
			}
		}
		
		// closest column to arm
		// color coordinates the block heights
		for (int j = 1; j < blockHeights.length; j = j + 2) {
			if (blockHeights[j] == 1) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.YELLOW);
			}
			else if (blockHeights[j] == 2) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.RED);
			}
			else if (blockHeights[j] == 3) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.BLUE);
			}
			else
				terminalFrame.setBackgroundColor(TextColor.ANSI.CYAN);
			
			// if the block has been picked, don't draw it in the starting column
			if (pickedBlock > j)
				for (int rowPos = maxRow - col1Height; rowPos > ((maxRow - blockHeights[j]) - col1Height); rowPos--) {
					terminalFrame.setCursorPosition(1, rowPos);
					terminalFrame.putCharacter(' ');
				}
			
			// increments column height so block placed on top of another knows where to be placed
			col1Height = col1Height + blockHeights[j];
		}
		
		// furtherest column from arm
		for (int i = 0; i < blockHeights.length; i = i + 2) {
			if (blockHeights[i] == 1) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.YELLOW);
			}
			else if (blockHeights[i] == 2) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.RED);
			}
			else if (blockHeights[i] == 3) {
				terminalFrame.setBackgroundColor(TextColor.ANSI.BLUE);
			}
			else
				terminalFrame.setBackgroundColor(TextColor.ANSI.CYAN);
			
			if (pickedBlock > i) {
				for (int rowPos = maxRow - col2Height; rowPos > ((maxRow - blockHeights[i]) - col2Height); rowPos--) {
					terminalFrame.setCursorPosition(maxCol, rowPos);
					terminalFrame.putCharacter(' ');
				}
			}
			
			col2Height = col2Height + blockHeights[i];
		}
	}
	
	public void barDraw()
	{
		// sets color of bars to green and numbers drawn on bars as black
		terminalFrame.setForegroundColor(TextColor.ANSI.BLACK);
		terminalFrame.setBackgroundColor(TextColor.ANSI.GREEN);
		
		// loops through barHeights array in initImpl and draws them
		for (int i = 0; i < barHeights.length; i++) {
			int count = 0;
			for (int rowPos = maxRow; rowPos > maxRow - barHeights[i]; rowPos--) {
				terminalFrame.setCursorPosition(2 + i, rowPos);
				terminalFrame.putCharacter(count==0 ? (char)('0'+ barHeights[i]) : ' ');
				count++;
			}
		}
	} 
	
	public void initImpl() {
		// initializing the heights of bars, blocks and starting dimensions of arm then calls methods to draw them
		barHeights = new int[] {4, 5, 6, 2, 1, 6, 4, 8}; 
		blockHeights = new int[] {2, 3, 1, 3};
		pickCount = -1;
		dropCount = -1;
		height = maxRow;
		width = 1;
		depth = 0;
		barDraw();
		armDraw();
		blockDraw();
	}
	
	@Override
	public void pick()
	{
		// implement methods to draw robot environment using your implementation of Arm.draw(), Bar.draw() etc.
		System.out.println("pick()");
		pickCount++;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}

	@Override
	public void drop()
	{
		System.out.println("drop()");
		dropCount++;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
		
	}

	@Override
	public void up()
	{
		System.out.println("up()");
		// changes variable then redraws robot with new variable added to drawing algorithm
		// similar thing is down for all parts of the arm
		height--;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}

	@Override
	public void down()
	{
		System.out.println("down()");
		height++;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}

	@Override
	public void contract()
	{
		System.out.println("contract()");
		width--;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}

	@Override
	public void extend()
	{
		System.out.println("extend()");
		width++;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}

	@Override
	public void lower()
	{
		System.out.println("lower()");
		depth++;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}

	@Override
	public void raise()
	{
		System.out.println("raise()");
		depth--;
		delayAnimation();
		armDraw();
		blockDraw();
		barDraw();
		terminalFrame.flush();
	}
}
