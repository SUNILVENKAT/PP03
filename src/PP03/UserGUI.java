package PP03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class UserGUI extends JPanel {

	// Components for the Add Employee GUI
	private JLabel labelId;
	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JTextField fieldId;
	private JTextField fieldFirstName;
	private JTextField fieldLastName;
	private JRadioButton radioFullTime;
	private JRadioButton radioHourly;
	private ButtonGroup group;
	private JLabel labelEmpStreet;
	private JLabel labelEmpAptNumber;
	private JLabel labelEmpCity;
	private JLabel labelEmpState;
	private JComboBox<String> comboEmpState;
	private JLabel labelEmpZipcode;
	private JTextField fieldEmpStreet;
	private JTextField fieldEmpAptNumber;
	private JTextField fieldEmpCity;
	private JTextField fieldEmpZipcode;
	private JButton ButtonEmpAdd;

	// Components for the Payroll Period
	private JLabel labelPayrollId;
	private JLabel labelPayrollStartDate;
	private JLabel labelPayrollEndDate;
	private JTextField fieldPayrollId;
	private JTextField fieldPayrollStartDate;
	private JTextField fieldPayrollEndDate;
	private JLabel labelPayRecordId;
	private JLabel labelPayRecordMonthlyincome;
	private JLabel labelPayRecordNoOfMonths;
	private JLabel labelPayRecordPayHours;
	private JLabel labelPayRecordPayRate;
	private JTextField fieldPayRecordId;
	private JTextField fieldPayRecordMonthlyincome;
	private JTextField fieldPayRecordNoOfMonths;
	private JTextField fieldPayRecordPayHours;
	private JTextField fieldPayRecordPayRate;
	private JButton buttonAddPayRecord;

	private JLabel labelRecordStats;
	private JTextArea TextAreaRecordStats;
	private JButton CloseButton;
	// private JTextArea textArea;
	// private JComboBox combList;
	private JScrollPane jp;
	private PayRoll payRoll;
	private String fileName = "PayRoll.txt";
	// private GridBagConstraints layoutConstraint;

	// Employee Field data types;
	private int eID;
	private String fName;
	private String lName;
	private Status empStatus;
	private String street;
	private String empState;
	private int houseNumber;
	private String city;
	private int zipCode;

	// Employee Pay Record Data Types:
	private int rID;
	private int monthlyIncome;
	private int noOfMonths;
	private int pID;
	private LocalDate pStartDate;
	private LocalDate pEndDate;
	private double payHour;
	private double payRate;

	public UserGUI() {

		// prompt the user to input the number of pay records
		int n = 0; // is the number of pay records for employees
		while (true) {
			try {
				// must hold the number
				String inputEmpNum = JOptionPane.showInputDialog("Enter the number of Employees:");
				if (inputEmpNum == null) {
					throw new NullPointerException();
				}

				n = Integer.parseInt(inputEmpNum);

				if (n <= 0) {
					throw new NumberFormatException();
				}
				break;
			} catch (NumberFormatException e) { // of customers based on
				// the user input
				JOptionPane.showMessageDialog(null, "Invalid Input", "PayRoll", JOptionPane.ERROR_MESSAGE);
				// System.exit(0);
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Application Closing", "PayRoll", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid Input", "PayRoll", JOptionPane.ERROR_MESSAGE);
			}
		}

		this.payRoll = new PayRoll(fileName, n);

		initGUI();
		doTheLayout();
		try {
			payRoll.readFromFile();
			updateTextarea();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		

		ButtonEmpAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee();
				updateTextarea();
			}
		});

		buttonAddPayRecord.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPayRecord();
				updateTextarea();
			}
		});

		CloseButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		/*
		 * this.comboEmpState.addActionListener( new java.awt.event.ActionListener() {
		 * public void actionPerformed(ActionEvent e){ //transfer_actionPerformed(e);
		 * 
		 * 
		 * } });
		 */
		this.radioFullTime.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlsEnableDisable();
			}
		});
		
		this.radioHourly.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlsEnableDisable();
			}
		});

	} // end of constructor

	private void initGUI() {

		this.labelId = new JLabel("ID:");
		this.labelFirstName = new JLabel("First Name:");
		this.labelLastName = new JLabel("Last Name:");
		this.fieldId = new JTextField(5);
		this.fieldFirstName = new JTextField(10);
		this.fieldLastName = new JTextField(10);

		this.radioFullTime = new JRadioButton("Full Time");
		this.radioFullTime.setName("FULLTIME");
		this.radioHourly = new JRadioButton("Hourly");
		this.radioHourly.setName("HOURLY");
		this.group = new ButtonGroup();

		this.labelEmpStreet = new JLabel("Street: ");
		this.labelEmpAptNumber = new JLabel("H/Apt Number: ");
		this.labelEmpCity = new JLabel("City: ");
		this.labelEmpZipcode = new JLabel("Zipcode: ");
		this.labelEmpState = new JLabel("State: ");
		this.fieldEmpStreet = new JTextField(15);
		this.fieldEmpAptNumber = new JTextField(10);
		this.fieldEmpCity = new JTextField(10);
		this.fieldEmpZipcode = new JTextField(10);
		this.ButtonEmpAdd = new JButton("Employee add");

		this.labelPayrollId = new JLabel("ID:");
		this.labelPayrollStartDate = new JLabel("Start Date:");
		this.labelPayrollEndDate = new JLabel("End Date:");

		this.fieldPayrollId = new JTextField(5);
		this.fieldPayrollStartDate = new JTextField(10);
		// this.fieldPayrollStartDate.setText("mm/dd/yyyy");
		this.fieldPayrollStartDate.setToolTipText("Enter Date in mm/dd/yyyy format");
		this.fieldPayrollEndDate = new JTextField(10);
		this.fieldPayrollEndDate.setToolTipText("Enter Date in mm/dd/yyyy format");

		this.labelPayRecordId = new JLabel("ID:");
		this.labelPayRecordMonthlyincome = new JLabel("Monthly Income:");
		this.labelPayRecordNoOfMonths = new JLabel("No Of Months:");
		this.labelPayRecordPayHours = new JLabel("Pay Hours:");
		this.labelPayRecordPayRate = new JLabel("Pay Rate:");
		this.fieldPayRecordId = new JTextField(5);
		this.fieldPayRecordMonthlyincome = new JTextField(10);
		this.fieldPayRecordNoOfMonths = new JTextField(10);
		this.fieldPayRecordPayHours = new JTextField(10);
		this.fieldPayRecordPayRate = new JTextField(10);
		this.buttonAddPayRecord = new JButton("Add Pay Record");

		this.labelRecordStats = new JLabel("Current Employee Record and Stat (Total & Average Pays):");
		this.labelRecordStats.setForeground(Color.BLUE);
		this.TextAreaRecordStats = new JTextArea(5,5);
		this.TextAreaRecordStats.setSize(getWidth() - 5, getHeight() - 5 );
		this.TextAreaRecordStats.setEditable(false);
		jp = new JScrollPane(this.TextAreaRecordStats);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// jp.setWheelScrollingEnabled(true);

		// TransferButton = new JButton("Transfer");
		CloseButton = new JButton("Close");

		// field2.setEditable(false);

	}// end of creating objects method

	private void doTheLayout() {

		JPanel panelEmp = new JPanel();
		JPanel panelEmployee = new JPanel();
		JPanel panelEmpStatus = new JPanel();
		JPanel panelEmpAddress = new JPanel();
		JPanel bottom = new JPanel();

		Border borderEmployee;
		borderEmployee = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titleEmployee;
		titleEmployee = BorderFactory.createTitledBorder(borderEmployee, "Employee");
		titleEmployee.setTitleColor(Color.BLUE);
		panelEmp.setBorder(titleEmployee);
		panelEmployee.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelEmployee.add(this.labelId);
		panelEmployee.add(this.fieldId);
		panelEmployee.add(this.labelFirstName);
		panelEmployee.add(this.fieldFirstName);
		panelEmployee.add(this.labelLastName);
		panelEmployee.add(this.fieldLastName);

		Border borderEmpStatus;
		TitledBorder titleEmpStatus;
		borderEmpStatus = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titleEmpStatus = BorderFactory.createTitledBorder(borderEmpStatus, "Employee Status");
		panelEmpStatus.setBorder(titleEmpStatus);

		panelEmpStatus.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelEmpStatus.add(this.radioFullTime);
		//this.radioFullTime.setSelected(true);  // Setting the default Radio button selection
		panelEmpStatus.add(this.radioHourly);
		this.group.add(this.radioFullTime);
		this.group.add(this.radioHourly);

		JPanel panelBoxEmpAddr = new JPanel();
		panelBoxEmpAddr.setLayout(new BoxLayout(panelBoxEmpAddr, BoxLayout.PAGE_AXIS));
		panelBoxEmpAddr.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		Border borderEmpAddress;
		TitledBorder titleEmpAddress;
		borderEmpAddress = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titleEmpAddress = BorderFactory.createTitledBorder(borderEmpAddress, "Employee Address");
		panelBoxEmpAddr.setBorder(titleEmpAddress);

		panelEmpAddress.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelEmpAddress.add(this.labelEmpStreet);
		panelEmpAddress.add(this.fieldEmpStreet);
		panelEmpAddress.add(this.labelEmpAptNumber);
		panelEmpAddress.add(this.fieldEmpAptNumber);
		panelEmpAddress.add(this.labelEmpCity);
		panelEmpAddress.add(this.fieldEmpCity);

		JPanel panelEmpStateZip = new JPanel();

		panelEmpStateZip.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelEmpStateZip.add(this.labelEmpState);

		String[] listStates = { "Select...", "Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California",
				"Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho",
				"Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Marshall Islands",
				"Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
				"Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
				"Ohio", "Oklahoma", "Oregon", "Palau", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina",
				"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
				"Wisconsin", "Wyoming" };
		this.comboEmpState = new JComboBox<String>(listStates);
		panelEmpStateZip.add(this.comboEmpState);

		panelEmpStateZip.add(this.labelEmpZipcode);
		panelEmpStateZip.add(this.fieldEmpZipcode);

		JPanel panelButton = new JPanel();
		// panelButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		ButtonEmpAdd.setPreferredSize(new Dimension(200, 50));
		panelButton.add(this.ButtonEmpAdd, Box.CENTER_ALIGNMENT);

		panelBoxEmpAddr.add(panelEmpAddress);
		panelBoxEmpAddr.add(panelEmpStateZip);

		panelEmp.setLayout(new BoxLayout(panelEmp, BoxLayout.PAGE_AXIS));
		panelEmp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelEmp.add(panelEmployee);
		panelEmp.add(panelEmpStatus);
		panelEmp.add(panelBoxEmpAddr);
		panelEmp.add(panelButton);

		JPanel panelPay = new JPanel();

		Border borderPayrollPeriod;
		TitledBorder titlePayrollPeriod;
		borderPayrollPeriod = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titlePayrollPeriod = BorderFactory.createTitledBorder(borderPayrollPeriod, "Payroll Period");
		titlePayrollPeriod.setTitleColor(Color.BLUE);

		JPanel panelPayrollPreiod = new JPanel();
		panelPayrollPreiod.setBorder(titlePayrollPeriod);
		panelPayrollPreiod.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelPayrollPreiod.add(this.labelPayrollId);
		panelPayrollPreiod.add(this.fieldPayrollId);
		panelPayrollPreiod.add(this.labelPayrollStartDate);
		panelPayrollPreiod.add(this.fieldPayrollStartDate);
		panelPayrollPreiod.add(this.labelPayrollEndDate);
		panelPayrollPreiod.add(this.fieldPayrollEndDate);

		JPanel panelRecord = new JPanel();
		panelRecord.setLayout(new BoxLayout(panelRecord, BoxLayout.PAGE_AXIS));

		Border borderPayRecord;
		TitledBorder titlePayRecord;
		borderPayRecord = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titlePayRecord = BorderFactory.createTitledBorder(borderPayRecord, "Payroll Record");
		panelRecord.setBorder(titlePayRecord);
		titlePayRecord.setTitleColor(Color.BLUE);

		JPanel panelIncomeNum = new JPanel();
		panelIncomeNum.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panelIncomeNum.add(labelPayRecordId);
		// panelIncomeNum.add(fieldPayRecordId);
		panelIncomeNum.add(labelPayRecordMonthlyincome);
		panelIncomeNum.add(fieldPayRecordMonthlyincome);
		panelIncomeNum.add(labelPayRecordNoOfMonths);
		panelIncomeNum.add(fieldPayRecordNoOfMonths);

		JPanel panelPayButton = new JPanel();
		buttonAddPayRecord.setPreferredSize(new Dimension(200, 50));
		panelPayButton.add(this.buttonAddPayRecord, Box.CENTER_ALIGNMENT);
		panelPayButton.add(new JSeparator());

		JPanel panelId = new JPanel();
		panelId.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelId.add(labelPayRecordId);
		panelId.add(fieldPayRecordId);

		JPanel panelPayRate = new JPanel();
		panelPayRate.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelPayRate.add(labelPayRecordPayHours);
		panelPayRate.add(fieldPayRecordPayHours);
		panelPayRate.add(labelPayRecordPayRate);
		panelPayRate.add(fieldPayRecordPayRate);

		panelRecord.add(panelIncomeNum);
		panelRecord.add(panelId);
		panelRecord.add(panelPayRate);

		panelPay.setLayout(new BoxLayout(panelPay, BoxLayout.PAGE_AXIS));
		panelPay.add(new JSeparator(SwingConstants.HORIZONTAL));
		panelPay.add(panelPayrollPreiod);
		panelPay.add(panelRecord);
		panelPay.add(panelPayButton);

		bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
		// bottom.setLayout( new GridLayout(3,1));
		bottom.add(new JSeparator(SwingConstants.HORIZONTAL));
		bottom.add(this.labelRecordStats);
		bottom.add(Box.createRigidArea(new Dimension(0, 5)));
		bottom.add(this.TextAreaRecordStats);
		this.TextAreaRecordStats.setSize(new Dimension(10, 10));
		// bottom.add(jp);
		bottom.add(new JSeparator(SwingConstants.HORIZONTAL));

		JPanel panelCloseButton = new JPanel();
		panelCloseButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.CloseButton.setPreferredSize(new Dimension(200, 50));
		panelCloseButton.add(CloseButton);
		bottom.add(panelCloseButton);

		setLayout(new BorderLayout(5, 5));
		add(panelEmp, BorderLayout.PAGE_START);
		add(panelPay, BorderLayout.CENTER);
		add(jp, BorderLayout.SOUTH);
		add(bottom, BorderLayout.SOUTH);
		

	}// end of Layout method
	
	private void controlsEnableDisable() {
		if(this.radioFullTime.isSelected()) {
			this.fieldPayRecordPayHours.setEnabled(false);
			this.fieldPayRecordPayRate.setEnabled(false);
		}
		else {
			this.fieldPayRecordPayHours.setEnabled(true);
			this.fieldPayRecordPayRate.setEnabled(true);
		}
	}

	private boolean validateEmployeeFields() {
		boolean fieldValidation = true;
		// Employee ID
		try {
			this.eID = Integer.parseInt(this.fieldId.getText());
			if (this.eID <= 0) {
				throw new NumberFormatException();
			}
			this.labelId.setForeground(Color.BLACK);

		} catch (NumberFormatException e) { // of customers based on
			// the user input
			// JOptionPane.showMessageDialog(null, "Input Error on the Employee ID",
			// "Error", JOptionPane.ERROR_MESSAGE);
			this.fieldId.requestFocusInWindow();
			this.labelId.setForeground(Color.RED);
			fieldValidation = false;
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldId.requestFocusInWindow();
			this.labelId.setForeground(Color.RED);
			fieldValidation = false;
		}

		// First Name
		try {
			this.fName = this.fieldFirstName.getText();
			if (this.fName.isBlank() || this.fName.isEmpty()) {
				throw new NullPointerException();
			}
			this.labelFirstName.setForeground(Color.BLACK);

		} catch (NullPointerException e) {
			this.fieldFirstName.requestFocusInWindow();
			this.labelFirstName.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldFirstName.requestFocusInWindow();
			this.labelFirstName.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Last Name
		try {
			this.lName = this.fieldLastName.getText();
			if (this.lName.isBlank() || this.lName.isEmpty()) {
				throw new NullPointerException();
			}
			this.labelLastName.setForeground(Color.BLACK);
		} catch (NullPointerException e) {
			this.fieldLastName.requestFocusInWindow();
			this.labelLastName.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldLastName.requestFocusInWindow();
			this.labelLastName.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Street Field
		try {
			this.street = this.fieldEmpStreet.getText();
			if (this.street.isBlank() || this.street.isEmpty()) {
				throw new NullPointerException();
			}
			this.labelEmpStreet.setForeground(Color.BLACK);
		} catch (NullPointerException e) {
			this.fieldEmpStreet.requestFocusInWindow();
			this.labelEmpStreet.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldEmpStreet.requestFocusInWindow();
			this.labelEmpStreet.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Apartment and Housing Number
		try {
			this.houseNumber = Integer.parseInt(this.fieldEmpAptNumber.getText());
			if (this.houseNumber <= 0) {
				throw new NullPointerException();
			}
			this.labelEmpAptNumber.setForeground(Color.BLACK);
		} catch (NumberFormatException e) {
			this.fieldEmpAptNumber.requestFocusInWindow();
			this.labelEmpAptNumber.setForeground(Color.RED);
			fieldValidation = false;

		} catch (NullPointerException e) {
			this.fieldEmpAptNumber.requestFocusInWindow();
			this.labelEmpAptNumber.setForeground(Color.RED);
			fieldValidation = false;
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldEmpAptNumber.requestFocusInWindow();
			this.labelEmpAptNumber.setForeground(Color.RED);
			fieldValidation = false;
		}

		// City
		try {
			this.city = this.fieldEmpCity.getText();
			if (this.city.isBlank() || this.city.isEmpty()) {
				throw new NullPointerException();
			}
			this.labelEmpCity.setForeground(Color.BLACK);
		} catch (NullPointerException e) {
			this.fieldEmpCity.requestFocusInWindow();
			this.labelEmpCity.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldEmpCity.requestFocusInWindow();
			this.labelEmpCity.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Zip Code
		try {
			this.zipCode = Integer.parseInt(this.fieldEmpZipcode.getText());
			if (this.zipCode <= 0) {
				throw new NumberFormatException();
			}
			this.labelEmpZipcode.setForeground(Color.BLACK);
		} catch (NumberFormatException e) {
			this.fieldEmpZipcode.requestFocusInWindow();
			this.labelEmpZipcode.setForeground(Color.RED);
			fieldValidation = false;
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldEmpZipcode.requestFocusInWindow();
			this.labelEmpZipcode.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Employee Status
		if (this.group.getSelection() == null) {
			fieldValidation = false;
			this.radioFullTime.setForeground(Color.RED);
			this.radioHourly.setForeground(Color.RED);
		} else {
			if (this.radioFullTime.isSelected())
				this.empStatus = Status.FullTime;
			else
				this.empStatus = Status.Hourly;
			
			this.radioFullTime.setForeground(Color.BLACK);
			this.radioHourly.setForeground(Color.BLACK);
		}

		// State
		if (this.comboEmpState.getSelectedItem().equals("Select...")) {
			fieldValidation = false;
			this.comboEmpState.setForeground(Color.RED);
		} else {
			this.comboEmpState.setForeground(Color.BLACK);
			this.empState = this.comboEmpState.getSelectedItem().toString();
			this.comboEmpState.setSelectedItem(this.empState);

		}

		return fieldValidation;
	}
	
	private boolean validatePayRecordFields() {
		boolean fieldValidation = true;
		// payRecord ID
		try {
			String pID = this.fieldPayrollId.getText();
			if(pID.isBlank() && pID.isEmpty()) {
				throw new NullPointerException();
			}
			this.pID = Integer.parseInt(pID);
			if (this.pID <= 0) {
				throw new NumberFormatException();
			}
			this.labelPayrollId.setForeground(Color.BLACK);

		} catch (NumberFormatException e) { // of customers based on
			// the user input
			// JOptionPane.showMessageDialog(null, "Input Error on the Employee ID",
			// "Error", JOptionPane.ERROR_MESSAGE);
			this.fieldPayrollId.requestFocusInWindow();
			this.labelPayrollId.setForeground(Color.RED);
			fieldValidation = false;
		}catch (NullPointerException e ) {
			this.fieldPayrollId.requestFocusInWindow();
			this.labelPayrollId.setForeground(Color.RED);
			fieldValidation = false;
		}catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldPayrollId.requestFocusInWindow();
			this.labelPayrollId.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Start Date
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			this.pStartDate = LocalDate.parse(this.fieldPayrollStartDate.getText(), formatter);
			/*if (this.fName.isBlank() || this.fName.isEmpty()) {
				throw new NullPointerException();
			}*/
			this.labelPayrollStartDate.setForeground(Color.BLACK);

		} catch (DateTimeException e) {
			this.labelPayrollStartDate.requestFocusInWindow();
			this.labelPayrollStartDate.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			this.labelPayrollStartDate.requestFocusInWindow();
			this.labelPayrollStartDate.setForeground(Color.RED);
			fieldValidation = false;
		}

		// End Date
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			this.pEndDate = LocalDate.parse(this.fieldPayrollEndDate.getText(), formatter);
			this.labelPayrollEndDate.setForeground(Color.BLACK);
		} catch (DateTimeException e) {
			this.fieldPayrollEndDate.requestFocusInWindow();
			this.labelPayrollEndDate.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldPayrollEndDate.requestFocusInWindow();
			this.labelPayrollEndDate.setForeground(Color.RED);
			fieldValidation = false;
		}

		// PayRecordID
		try {
			String rID = this.fieldPayRecordId.getText();
			if (rID.isBlank() || rID.isEmpty()) {
				throw new NullPointerException();
			}
			this.rID = Integer.parseInt(rID);
			if (this.rID <= 0) {
				throw new NumberFormatException();
			}
			this.labelPayRecordId.setForeground(Color.BLACK);
		} catch (NumberFormatException e ) {
			this.fieldPayRecordId.requestFocusInWindow();
			this.labelPayRecordId.setForeground(Color.RED);
			fieldValidation = false;
		}
		catch (NullPointerException e) {
			this.fieldPayRecordId.requestFocusInWindow();
			this.labelPayRecordId.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldPayRecordId.requestFocusInWindow();
			this.labelPayRecordId.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Monthly Income
		try {
			this.monthlyIncome = Integer.parseInt(this.fieldPayRecordMonthlyincome.getText());
			if (this.monthlyIncome <= 0) {
				throw new NullPointerException();
			}
			this.labelPayRecordMonthlyincome.setForeground(Color.BLACK);
		} catch (NumberFormatException e) {
			this.fieldPayRecordMonthlyincome.requestFocusInWindow();
			this.labelPayRecordMonthlyincome.setForeground(Color.RED);
			fieldValidation = false;

		} catch (NullPointerException e) {
			this.fieldPayRecordMonthlyincome.requestFocusInWindow();
			this.labelPayRecordMonthlyincome.setForeground(Color.RED);
			fieldValidation = false;
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldPayRecordMonthlyincome.requestFocusInWindow();
			this.labelPayRecordMonthlyincome.setForeground(Color.RED);
			fieldValidation = false;
		}

		// No Of Months
		try {
			this.noOfMonths = Integer.parseInt(this.fieldPayRecordNoOfMonths.getText());
			/*if (this.street.isBlank() || this.street.isEmpty()) {
				throw new NullPointerException();
			}*/
			this.labelPayRecordNoOfMonths.setForeground(Color.BLACK);
		} catch (NullPointerException e) {
			this.fieldPayRecordNoOfMonths.requestFocusInWindow();
			this.labelPayRecordNoOfMonths.setForeground(Color.RED);
			fieldValidation = false;

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
			// JOptionPane.ERROR_MESSAGE);
			this.fieldPayRecordNoOfMonths.requestFocusInWindow();
			this.labelPayRecordNoOfMonths.setForeground(Color.RED);
			fieldValidation = false;
		}

		// Pay hours and Pay Rate depending on the Employee Status
		if((this.group.getSelection() != null) && this.radioHourly.isSelected()) {
			// pay Hours
			try {
				this.payHour = Double.parseDouble(this.fieldEmpZipcode.getText());
				if (this.payHour <= 0) {
					throw new NumberFormatException();
				}
				this.labelPayRecordPayHours.setForeground(Color.BLACK);
			} catch (NumberFormatException e) {
				this.fieldPayRecordPayHours.requestFocusInWindow();
				this.labelPayRecordPayHours.setForeground(Color.RED);
				fieldValidation = false;
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
				// JOptionPane.ERROR_MESSAGE);
				this.fieldPayRecordPayHours.requestFocusInWindow();
				this.labelPayRecordPayHours.setForeground(Color.RED);
				fieldValidation = false;
			}
			
			//Pay Rate
			try {
				this.payRate = Double.parseDouble(this.fieldPayRecordPayRate.getText());
				if (this.payRate <= 0) {
					throw new NumberFormatException();
				}
				this.labelPayRecordPayRate.setForeground(Color.BLACK);
			} catch (NumberFormatException e) {
				this.fieldPayRecordPayRate.requestFocusInWindow();
				this.labelPayRecordPayRate.setForeground(Color.RED);
				fieldValidation = false;
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "Invalid Input", "Error",
				// JOptionPane.ERROR_MESSAGE);
				this.fieldPayRecordPayRate.requestFocusInWindow();
				this.labelPayRecordPayRate.setForeground(Color.RED);
				fieldValidation = false;
			}
		}
		else {
			this.radioFullTime.setForeground(Color.RED);
			this.radioHourly.setForeground(Color.RED);
		}

		return fieldValidation;
	}

	private void addEmployee() {
		if (validateEmployeeFields()) {
			/*System.out.println("validation is completed");
			System.out.println(this.eID + "," + this.fName + "," + this.lName);
			System.out.println(this.empStatus);
			System.out.println(this.street + "," + this.houseNumber + "," + this.city);
			System.out.println(this.empState + "," + this.zipCode);*/
			this.fieldId.setText("");
			this.fieldPayRecordId.setText(Integer.toString(this.eID));
			this.fieldFirstName.setText("");
			this.fieldLastName.setText("");
			this.fieldEmpStreet.setText("");
			this.fieldEmpAptNumber.setText("");
			this.fieldEmpCity.setText("");
			this.fieldEmpZipcode.setText("");
			this.group.clearSelection();
			this.comboEmpState.setSelectedItem("Select...");
			this.fieldId.requestFocusInWindow();
			
			this.payRoll.createEmployee(this.eID, this.empStatus, this.fName, this.lName, this.street, this.houseNumber, this.city, this.empState, this.zipCode);
		}

	}// End of Add employee

	private void addPayRecord() {
		if(validatePayRecordFields()) {
			this.fieldPayRecordId.setText("");
			this.fieldPayrollId.setText("");
			this.fieldPayrollStartDate.setText("");
			this.fieldPayrollEndDate.setText("");
			this.fieldPayRecordMonthlyincome.setText("");
			this.fieldPayRecordNoOfMonths.setText("");
			this.radioFullTime.setForeground(Color.BLACK);
			this.radioHourly.setForeground(Color.BLACK);
			this.fieldPayrollId.requestFocusInWindow();
			if(this.radioHourly.isSelected()) {
				//this.payRoll.createPayRecord(eID,  , null, payHour, payRate);
				this.fieldPayRecordPayHours.setText("");
				this.fieldPayRecordPayRate.setText("");

			}
			else {
				//this.payRoll.createPayRecord(eID, null, null, monthlyIncome, noOfMonths);
			}
		}

	}// End of Add Pay Record

	private void updateTextarea() {

		TextAreaRecordStats.setText(payRoll.displayPayRecord());
		/*
		 * if(combList.getSelectedItem().toString().compareToIgnoreCase("") == 0)
		 * textArea.append("This is an editable JTextArea. " +
		 * "A text area is a \"plain\" text component, " +
		 * "which means that although it can display text " +
		 * "in any font, all of the text is in the same font."); else if
		 * (combList.getSelectedItem().toString().compareToIgnoreCase( "Laptops") == 0)
		 * textArea.setText("You have selected Laptops item name"); else if
		 * (combList.getSelectedItem().toString().compareToIgnoreCase("Tablets") == 0)
		 * textArea.setText("You have not selected Tablets item name"); else if
		 * (combList.getSelectedItem().toString().compareToIgnoreCase("Cell Phones") ==
		 * 0) textArea.setText("You have not selected Cell Phones item name");
		 */

	} // End of updateTextarea

	void close() {
		System.exit(0);
	}// end of Close action event method

	public static void main(String[] args) {
		JFrame f = new JFrame("Pay Roll");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();
		contentPane.add(new UserGUI());
		f.pack();
		// setSize(200, 200);
		f.setVisible(true);

	}

}
