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
				JFrame frame = new MainFrame("Departing Train gooey (gee, you, eye) - Bryce Keifer n8040486, Daniel Rablin n8038848", 1024, 728);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
