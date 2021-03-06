package View;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;

import WEB.User;
import WEB.DataBaseAPI;

import com.sun.mail.imap.protocol.Namespaces.Namespace;

public class Config {

	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;

	public static final int C_HEIGHT = 400;
	public static final int C_WIDTH = 400;

	public static final Color COLOR = ConfigColor._MAINcolor;

	public static final String[] MONTHS = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	public static final Font font = new Font("Segoe UI", Font.PLAIN, 11);

	public static final String[] DAYS_TITLE = { "Sun", "Mon", "Tue", "Wed",
			"Thu", "Fri", "Sat" };;

	// public DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(
	// new String[] { "00:00", "00:30", "01:00", "02:00", "02:30",
	// "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
	// "06:00", "06:30", "07:00", "07:30", "08:00", "08:30",
	// "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
	// "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
	// "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
	// "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
	// "21:00", "21:30", "22:00", "22:30", "23:00", "23:30",
	// "24:00" });

	public static int getIndex(String[] ar, String val) {

		for (int i = 0; i < ar.length; ++i) {
			if (ar[i].equals(val))
				return i;
		}

		return -1;
	}

	/** Font on numbers and <> on calendar */
	public static Font _gF = new Font("Segoe UI", Font.PLAIN, 20);

	/** Tupical height button */
	public static int _bH = 40;
	/** Tupical width button */
	public static int _bW = 100;
	/** X ordinate of editable panes in Register.java */
	public static int _EP_X = 160;

	public static DefaultComboBoxModel getTimeModel() {

		return new DefaultComboBoxModel(new String[] { "00:00", "00:30","01:30",
				"01:00", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30",
				"05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
				"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
				"15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
				"19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00",
				"22:30", "23:00", "23:30", "24:00" });

	}

}
