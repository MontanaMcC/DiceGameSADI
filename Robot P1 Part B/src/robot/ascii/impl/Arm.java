package robot.ascii.impl;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import robot.RobotMovement;
import robot.ascii.ASCIIBot;

public class Arm extends ASCIIBot implements RobotMovement, Drawable
{

	@Override
	public void draw(SwingTerminalFrame terminalFrame)
	{
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

	@Override
	public void pick()
	{
		
	}

	@Override
	public void drop()
	{

	}

	@Override
	public void up()
	{
		
	}

	@Override
	public void down()
	{

	}

	@Override
	public void contract()
	{

	}

	@Override
	public void extend()
	{

	}

	@Override
	public void lower()
	{

	}

	@Override
	public void raise()
	{

	}
}
