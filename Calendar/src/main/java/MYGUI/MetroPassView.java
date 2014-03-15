package MYGUI;

import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import View.ConfigColor;

public class MetroPassView extends JPasswordField{
	
	
	public MetroPassView() {
		super();
		this.setBackground(ConfigColor._bgEDP);
	}

	@Override
	public void setBorder(Border border) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder());
	}
	

}
