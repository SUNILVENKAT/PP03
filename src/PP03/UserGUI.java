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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	  
	  //Components for the Payroll Period
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
	  //private JTextArea textArea;
	  //private JComboBox combList;
	  private JScrollPane jp;
	  private PayRoll payRoll;
	  private String fileName = "PayRoll.txt";
	  private GridBagConstraints layoutConstraint;
	  
	  public UserGUI() {
		  
                 // prompt the user to input the number of pay records
                 int n; // is the number of pay records for employees
		// payRoll = new PayRoll(fileName,n);

	    initGUI();
	    doTheLayout();

	    /*TransferButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            transfer();
	            }
	    });*/
	    
	    CloseButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            close();
	            }
	    });
	    
	    /*combList.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            //transfer_actionPerformed(e);
	        	updateTextarea();
	            
	            }
	    });*/

	  } // end of constructor

	  private void initGUI(){
	      
		  this.labelId = new JLabel("ID:");
		  this.labelFirstName = new JLabel("First Name:");
		  this.labelLastName = new JLabel("Last Name:");
		  this.fieldId = new JTextField(5);
		  this.fieldFirstName = new JTextField(10);
		  this.fieldLastName = new JTextField(10);
	      	      
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
	      this.labelPayrollEndDate = new JLabel("End Date");
	      this.fieldPayrollId = new JTextField(5);
	      this.fieldPayrollStartDate = new JTextField(10);
	      this.fieldPayrollEndDate = new JTextField(10);
	      
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
	      this.TextAreaRecordStats = new JTextArea(10,5);
	      this.TextAreaRecordStats.setEditable(false);
	      jp = new JScrollPane(this.TextAreaRecordStats);
	     
	      
	     // TransferButton = new JButton("Transfer");
	      CloseButton = new JButton("Close");
	      
	      //field2.setEditable(false);

	  }// end of creating objects method

	  private void doTheLayout(){

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
	      panelEmployee.setLayout( new FlowLayout(FlowLayout.LEFT));
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
	      this.radioFullTime = new JRadioButton("Full Time");
	      this.radioHourly = new JRadioButton("Hourly");
	      panelEmpStatus.add(this.radioFullTime);
	      panelEmpStatus.add(this.radioHourly);
	      ButtonGroup group = new ButtonGroup();
	      group.add(this.radioFullTime);
	      group.add(this.radioHourly);
	      
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

	      String[] listStates = {"","Alabama","Alaska","American Samoa","Arizona","Arkansas","California","Colorado","Connecticut",
	    		  				 "Delaware", "District of Columbia","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa",
	    		  				 "Kansas","Kentucky","Louisiana","Maine","Marshall Islands", "Maryland","Massachusetts","Michigan",
	    		  				 "Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey",
	    		  				 "New Mexico","New York","North Carolina","North Dakota", "Ohio","Oklahoma","Oregon","Palau",
	    		  				 "Pennsylvania","Puerto Rico","Rhode Island","South Carolina","South Dakota","Tennessee","Texas",
	    		  				 "Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
	      this.comboEmpState = new JComboBox<String>(listStates);
	      panelEmpStateZip.add(this.comboEmpState);

	      panelEmpStateZip.add(this.labelEmpZipcode);
	      panelEmpStateZip.add(this.fieldEmpZipcode);
	      
	      JPanel panelButton = new JPanel();
	      //panelButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	      ButtonEmpAdd.setPreferredSize(new Dimension(200,50));
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
	      titlePayRecord.setTitleColor(new Color(255));
	      
	      JPanel panelIncomeNum = new JPanel();
	      panelIncomeNum.setLayout(new FlowLayout(FlowLayout.CENTER));
	      //panelIncomeNum.add(labelPayRecordId);
	      //panelIncomeNum.add(fieldPayRecordId);
	      panelIncomeNum.add(labelPayRecordMonthlyincome);
	      panelIncomeNum.add(fieldPayRecordMonthlyincome);
	      panelIncomeNum.add(labelPayRecordNoOfMonths);
	      panelIncomeNum.add(fieldPayRecordNoOfMonths);
	      
	      JPanel panelPayButton = new JPanel();
	      buttonAddPayRecord.setPreferredSize(new Dimension(200,50));
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
	      //bottom.setLayout( new GridLayout(3,1));
	      bottom.add(new JSeparator(SwingConstants.HORIZONTAL));
	      bottom.add(this.labelRecordStats);
	      bottom.add(Box.createRigidArea(new Dimension(0,5)));
	      bottom.add(this.TextAreaRecordStats, BorderLayout.CENTER);
	      this.TextAreaRecordStats.setSize(new Dimension(10,10));
	      bottom.add(jp);
	      bottom.add(new JSeparator(SwingConstants.HORIZONTAL));
	      
	      JPanel panelCloseButton = new JPanel();
	      panelCloseButton.setLayout( new FlowLayout(FlowLayout.CENTER));
	      this.CloseButton.setPreferredSize(new Dimension(200,50));
	      panelCloseButton.add(CloseButton);
	      bottom.add(panelCloseButton);

	      setLayout( new BorderLayout(5,5));
	      add(panelEmp, BorderLayout.PAGE_START);
	      add(panelPay, BorderLayout.CENTER);
	      add(bottom, BorderLayout.SOUTH);

	  }// end of Layout method

	 
	  void transfer(){
	        //String mytext = field1.getText();
	        //field2.setText(mytext);
	  }// end of transfer action event method
	  
	  void updateTextarea(){
		  
		 		 
		 /* if(combList.getSelectedItem().toString().compareToIgnoreCase("") == 0)
			  textArea.append("This is an editable JTextArea. " +
		    		    "A text area is a \"plain\" text component, " +
		    		    "which means that although it can display text " +
		    		    "in any font, all of the text is in the same font.");
			 else if (combList.getSelectedItem().toString().compareToIgnoreCase( "Laptops") == 0)
				 textArea.setText("You have selected Laptops item name");
			 else if (combList.getSelectedItem().toString().compareToIgnoreCase("Tablets") == 0)
				 textArea.setText("You have not selected Tablets item name");
			 else if (combList.getSelectedItem().toString().compareToIgnoreCase("Cell Phones") == 0)
				 textArea.setText("You have not selected Cell Phones item name");
				 */
		  
	  }

	  void close(){
	      System.exit(0);
	  }// end of transfer action event method


	public static void main(String[] args) {
	   JFrame f = new JFrame("Pay Roll");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        contentPane.add( new UserGUI());
        f.pack();
        //setSize(200, 200);
        f.setVisible(true);


	}

}
