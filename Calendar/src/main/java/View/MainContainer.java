package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Model.Model;

public class MainContainer extends JFrame implements WindowListener {

	public MainContainer() {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		addWindowListener(this);

		setSize(new Dimension(Config.WIDTH, Config.HEIGHT));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X is
														// clicked
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("resources\\logo.png"));
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

	@Override
	public void windowClosing(WindowEvent e) {

		Model.MODEL.doLogOut();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * clean this shit up
	 * 
	 * Runtime.getRuntime().addShutdownHook(new Thread() {
	 * 
	 * @Override public void run() { updateZonas(); db.close(); } });
	 */
}
