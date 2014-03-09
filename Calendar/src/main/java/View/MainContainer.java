package View;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainContainer extends JFrame {

	public MainContainer() {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		setSize(new Dimension(Config.WIDTH, Config.HEIGHT));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X is
														// clicked
	}

	public void showPane(JPanel p) {
		System.out.println("ResetPanel");
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().revalidate();

		getContentPane().add(p);
		getContentPane().repaint();
		getContentPane().revalidate();
	}

	
	/*clean this shit up
	 * 
	  Runtime.getRuntime().addShutdownHook(new Thread()
{
    @Override
    public void run()
    {
        updateZonas();
        db.close();
    }
});
	 */
}
