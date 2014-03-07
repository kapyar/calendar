package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import MYGUI.ButtonFactory;
import MYGUI.Label;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyCalendar extends MetroPanel {
	private JLabel lblMonth, lblYear;
	private MyButton btnPrev, btnNext;
	private JTable tblCalendar;
	private JComboBox cmbYear;
	private DefaultTableModel mtblCalendar; // Table model
	private JScrollPane stblCalendar; // The scrollpane
	private MetroPanel pnlCalendar;
	private int realYear, realMonth, realDay, currentYear, currentMonth;
	private MyButton btnBack;

	public MyCalendar() {

		this.setLayout(null); // Apply null layout
		this.setBackground(new Color(34, 77, 125));
		this.setSize(new Dimension(Config.C_WIDTH, Config.C_HEIGHT));

		// Create controls
		lblMonth = new JLabel("January");
		Label.decorateTitle(lblMonth);
		lblYear = new JLabel("Change year:");
		Label.decorateNormal(lblYear);
		cmbYear = new JComboBox();
		btnPrev = ButtonFactory.getNormalButton("...");
		btnNext = ButtonFactory.getNormalButton("...");
		mtblCalendar = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new MetroPanel();

		// // Set border
		// pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

		// Register action listeners
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());

		btnBack = ButtonFactory.getNormalButton("Back");
		btnBack.setBounds(35, Config.HEIGHT - 120, 80, 25);

		// Add controls to pane
		this.add(pnlCalendar);
		pnlCalendar.add(lblMonth);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(btnBack);
		pnlCalendar.add(stblCalendar);

		// Set bounds
		int midX = Config.WIDTH / 2;

		pnlCalendar.setBounds(0, 0, Config.WIDTH, Config.HEIGHT);
		lblMonth.setBounds(midX, 25, 100, 25);
		lblYear.setBounds(midX + 200, Config.HEIGHT - 120, 80, 20);
		cmbYear.setBounds(midX + 290, Config.HEIGHT - 120, 80, 20);
		btnPrev.setBounds(midX - lblMonth.getPreferredSize().width / 2 - 10,
				25, 50, 25);
		btnNext.setBounds(midX + lblMonth.getPreferredSize().width / 2 + 10,
				25, 50, 25);
		stblCalendar.setBounds(1, 50, Config.WIDTH - 2, Config.HEIGHT);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		// Add headers
		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }; // All
																				// headers
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);
		}

		tblCalendar.getParent().setBackground(Config.COLOR); // Set
																// background

		// No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		tblCalendar.setRowHeight(66);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		// Populate table
		for (int i = realYear - 100; i <= realYear + 100; i++) {
			cmbYear.addItem(String.valueOf(i));
		}

		// Refresh calendar
		refreshCalendar(realMonth, realYear); // Refresh calendar

	}

	public void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear - 10) {
			btnPrev.setEnabled(false);
		} // Too early
		if (month == 11 && year >= realYear + 100) {
			btnNext.setEnabled(false);
		} // Too late
		lblMonth.setText(months[month]); // Refresh the month label (at the top)
		lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25,
				180, 25); // Re-align label with calendar
		cmbYear.setSelectedItem(String.valueOf(year)); // Select the correct
														// year in the combo box

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);

		}

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0),
				new tblCalendarRenderer());
	}

	class tblCalendarRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean selected, boolean focused, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, selected,
					focused, row, column);
			if (column == 0 || column == 6) { // Week-end
				setBackground(new Color(255, 220, 220));
			} else { // Week
				setBackground(new Color(255, 255, 255));
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay
						&& currentMonth == realMonth && currentYear == realYear) { // Today
					setBackground(new Color(120, 220, 255));
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	class btnPrev_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0) { // Back one year
				currentMonth = 11;
				currentYear -= 1;
			} else { // Back one month
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	class btnNext_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) { // Foward one year
				currentMonth = 0;
				currentYear += 1;
			} else { // Foward one month
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	class cmbYear_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (cmbYear.getSelectedItem() != null) {
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}

	public void show() {
		this.setVisible(true);
	}

	public void hide() {
		this.setVisible(false);
	}

	public void addMyActionListener(ActionListener l) {
		btnBack.addActionListener(l);
	}

	// to dedicate which cell was choosen
	public void addListener(MouseListener l) {
		tblCalendar.addMouseListener(l);
	}

	public MyButton getBtnBack() {
		return btnBack;
	}

}