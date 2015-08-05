package rlipkin.hospital.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import rlipkin.hospital.classes.Doctor;
import rlipkin.hospital.classes.Patient;
import rlipkin.hospital.classes.Visit;
import rlipkin.hospital.classes.VisitImp1;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddVisitFrame extends JFrame {

	private JPanel contentPane;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVisitFrame frame = new AddVisitFrame();
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
	public AddVisitFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel visitAdded = new JLabel("Visit Added");
		visitAdded.setBounds(301, 248, 89, 16);
		contentPane.add(visitAdded);
		visitAdded.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 440, 78);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSelectPatient = new JLabel("Select Patient:");
		panel.add(lblSelectPatient);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		JList patientList = new JList(GUI.data.getPatientList().toArray());
		patientList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visitAdded.setVisible(false);
			}
		});
		patientList.setVisibleRowCount(4);
		scrollPane.setViewportView(patientList);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 92, 439, 78);
		contentPane.add(panel_1);
		
		JLabel lblSelectDoctor = new JLabel("Select Doctor:");
		panel_1.add(lblSelectDoctor);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		JList doctorList = new JList(GUI.data.getDoctorList().toArray());
		doctorList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visitAdded.setVisible(false);
			}
		});
		doctorList.setVisibleRowCount(4);
		scrollPane_1.setViewportView(doctorList);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVisitFrame.this.dispose();
			}
		});
		btnBackToMain.setBounds(5, 243, 155, 29);
		contentPane.add(btnBackToMain);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 193, 439, 43);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEnterDate = new JLabel("Enter Date:");
		panel_2.add(lblEnterDate);
		
		yearField = new JTextField();
		yearField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visitAdded.setVisible(false);
			}
		});
		yearField.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		yearField.setText("Year");
		panel_2.add(yearField);
		yearField.setColumns(10);
		
		monthField = new JTextField();
		monthField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visitAdded.setVisible(false);
			}
		});
		monthField.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		monthField.setText("Month (Number)");
		panel_2.add(monthField);
		monthField.setColumns(10);
		
		dayField = new JTextField();
		dayField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visitAdded.setVisible(false);
			}
		});
		dayField.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		dayField.setText("Day");
		panel_2.add(dayField);
		dayField.setColumns(10);
		
		
		JButton btnAddVisit = new JButton("Add Visit");
		btnAddVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visitAdded.setVisible(true);
				Patient p = (Patient) patientList.getSelectedValue();
				Doctor d = (Doctor) doctorList.getSelectedValue();
				Visit<Integer, Integer> v = new VisitImp1<Integer, Integer>(p.getID(), d.getID(), yearField.getText() + "-" + monthField.getText() + "-" + dayField.getText());
				GUI.data.addVisit(v);
			}
		});
		
		btnAddVisit.setBounds(172, 243, 117, 29);
		contentPane.add(btnAddVisit);
		
		
	}
}
