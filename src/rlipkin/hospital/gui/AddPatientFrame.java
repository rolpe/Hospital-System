package rlipkin.hospital.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import rlipkin.hospital.classes.Patient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPatientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fNameField;
	private JTextField SSNField;
	private JTextField yearField;
	private JLabel patientAdded;
	private JTextField lNameField;
	private JButton btnBackToMain;
	private JTextField monthField;
	private JTextField dayField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPatientFrame frame = new AddPatientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddPatientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 6, 440, 117);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 156, 440, 117);
		contentPane.add(panel_1);
		
		patientAdded = new JLabel("Patient Added");
		patientAdded.setBounds(175, 59, 87, 16);
		panel_1.add(patientAdded);
		patientAdded.setVisible(false);
		
		
		fNameField = new JTextField();
		fNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				patientAdded.setVisible(false);
			}
		});
		fNameField.setText("First Name");
		panel.add(fNameField);
		fNameField.setColumns(10);
		
		lNameField = new JTextField();
		lNameField.setText("Last Name");
		panel.add(lNameField);
		lNameField.setColumns(10);
		
		SSNField = new JTextField();
		SSNField.setText("SSN");
		panel.add(SSNField);
		SSNField.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		panel.add(lblDateOfBirth);
		
		yearField = new JTextField();
		yearField.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		yearField.setText("Year");
		panel.add(yearField);
		yearField.setColumns(10);
		
		monthField = new JTextField();
		monthField.setText("Month (Number)");
		panel.add(monthField);
		monthField.setColumns(10);
		
		dayField = new JTextField();
		dayField.setText("Day");
		panel.add(dayField);
		dayField.setColumns(10);
		
		JButton addPatientButton = new JButton("Add Patient");
		addPatientButton.setBounds(165, 18, 116, 29);
		addPatientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient p = new Patient(fNameField.getText() + " " + lNameField.getText(), SSNField.getText(), yearField.getText() + "-"+ monthField.getText() + "-" + dayField.getText());
				GUI.data.addPatient(p);
				patientAdded.setVisible(true);
			}
		});
		panel_1.setLayout(null);
		panel_1.add(addPatientButton);
		
		btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPatientFrame.this.dispose();
			}
		});
		btnBackToMain.setBounds(6, 82, 146, 29);
		panel_1.add(btnBackToMain);
		
		
	}
}
