package asgn2GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class asgn2GUI {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new MainFrame("Hello world", 1024, 728);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
