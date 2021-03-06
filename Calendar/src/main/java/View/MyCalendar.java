package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
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

	
	private int midX = Config.WIDTH / 2;
	public MyCalendar() {

		
		
		this.setLayout(null); // Apply null layout
		this.setBackground(new Color(34, 77, 125));
		this.setSize(new Dimension(Config.C_WIDTH, Config.C_HEIGHT));
		
		// Create controls
		lblMonth = new JLabel("January");
		Decorator.decorateTitle(lblMonth);
		
		lblYear = new JLabel("Change year:");
		Decorator.decorateNormal(lblYear);
		
		cmbYear = new JComboBox();
		btnPrev = ButtonFactory.getNormalButton("<");
		btnNext = ButtonFactory.getNormalButton(">");
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
		btnBack.setLocation(new Point(45, Config.HEIGHT - 120));
		

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
		pnlCalendar.setBounds(0, 0, Config.WIDTH, Config.HEIGHT);
		lblMonth.setBounds(midX, 100, 100, 40);
		
		lblYear.setBounds(midX + 200, Config.HEIGHT - 120, 100, 40);
		cmbYear.setBounds(midX + 290, Config.HEIGHT - 120, 100, 40);
		
		
		btnPrev.setLocation(new Point(midX - lblMonth.getPreferredSize().width / 2 - 125,5));
		btnNext.setLocation(new Point(midX - lblMonth.getPreferredSize().width / 2 +120,5));
		
		stblCalendar.setBounds(1, 50, Config.WIDTH - 2, Config.HEIGHT);

		// Set btn text
		btnPrev.setFont(Config._gF);
		btnNext.setFont(Config._gF);
	
		
		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		// Add headers
		String[] headers = Config.DAYS_TITLE; // All
		// headers
		for (int i = 0; i < 6; i++) {
			mtblCalendar.addColumn(headers[i+1]);
		}
		mtblCalendar.addColumn(headers[0]);
		//mtblCalendar.setBackground(ConfigColor._bgEDP);
		
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
		String[] months = Config.MONTHS;
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
		lblMonth.setBounds(midX - lblMonth.getPreferredSize().width / 2, 5,
				200, 40); // Re-align label with calendar
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
			int row = new Integer((i + som - 3) / 7);
			
			int column = (i + som - 3) % 7;
			
			//початок костиля
			if (row == -1)
				row=6;
			if (column == -1)
				column=6;
			//кінець костиля
			
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
			if (column == 5 || column == 6) { // Week-end
				setBackground(ConfigColor._cnwkBG);
			} else { // Week
				setBackground(ConfigColor._cwkBG);
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay
						&& currentMonth == realMonth && currentYear == realYear) { // Today
					setBackground(ConfigColor._ctBG);
				}
			}
			setBorder(null);
			setForeground(Color.black);
			setFont(Config._gF);
			setForeground(new Color(255,255,255));
			
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

	public JLabel getLblMonth() {
		return lblMonth;
	}

	public JComboBox getCmbYear() {
		return cmbYear;
	}

}