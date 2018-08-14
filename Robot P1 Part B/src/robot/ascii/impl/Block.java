package robot.ascii.impl;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import robot.ascii.ASCIIBot;

public class Block extends ASCIIBot implements Drawable
{
	@Override
	public void draw(SwingTerminalFrame terminalFrame)
	{
		
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
}
