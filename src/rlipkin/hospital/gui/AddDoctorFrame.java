package rlipkin.hospital.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import rlipkin.hospital.classes.Doctor;

import java.awt.GridLayout;

public class AddDoctorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField yearField;
	private JTextField SSNField;
	private JTextField fNameField;
	private JTextField monthField;
	private JTextField dayField;
	private JTextField lNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDoctorFrame frame = new AddDoctorFrame();
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
	public AddDoctorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 20, 438, 153);
		contentPane.add(panel);
		
		JLabel doctorAdded = new JLabel("Doctor Added");
		doctorAdded.setBounds(181, 226, 87, 16);
		contentPane.add(doctorAdded);
		doctorAdded.setVisible(false);
		
		fNameField = new JTextField();
		fNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doctorAdded.setVisible(false);
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
		
		JLabel lblNewLabel = new JLabel("Date of Birth:                                                                          ");
		panel.add(lblNewLabel);
		
		yearField = new JTextField();
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
		
		JLabel lblMedicalSpecialty = new JLabel("Medical Specialty:");
		panel.add(lblMedicalSpecialty);
		
		DefaultListModel<String> medSpecs = new DefaultListModel<String>();
		medSpecs.addElement("General_Medicine");
		medSpecs.addElement("Pediatrics");
		medSpecs.addElement("Oncology");
		JList<String> specList = new JList<String>(medSpecs);
		panel.add(specList);
		
		
			
		JButton btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor d = new Doctor(fNameField.getText() + " " + lNameField.getText(), SSNField.getText(), yearField.getText() + "-"+ monthField.getText() + "-" + dayField.getText(), specList.getSelectedValue().toUpperCase());
				GUI.data.addDoctor(d);
				doctorAdded.setVisible(true);
			}
		});
		btnAddDoctor.setBounds(171, 185, 117, 29);
		contentPane.add(btnAddDoctor);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDoctorFrame.this.dispose();
			}
		});
		btnBackToMain.setBounds(6, 243, 162, 29);
		contentPane.add(btnBackToMain);
		
		
	}
}
