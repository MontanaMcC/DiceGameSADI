package client;

import javax.swing.SwingUtilities;
import view.AppFrame;

//Main class
public class App
{

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				AppFrame frame =new AppFrame();
				frame.populate();
				frame.addCallBacks();
				frame.loadPlayer();
			}
		});
	
	}
}