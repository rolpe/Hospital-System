package rlipkin.hospital.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import rlipkin.hospital.classes.Patient;
import rlipkin.hospital.classes.Visit;
import rlipkin.hospital.misc.Functions;

public class ViewPatientsFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPatientsFrame frame = new ViewPatientsFrame();
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
	public ViewPatientsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 99);
		contentPane.add(panel);
		
		JLabel lblAllPatients = new JLabel("All Patients:");
		panel.add(lblAllPatients);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 106, 440, 57);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setBounds(47, 6, 115, 16);
		panel_3.add(nameLabel);
		
		JLabel dateLabel = new JLabel("");
		dateLabel.setBounds(270, 6, 153, 16);
		panel_3.add(dateLabel);
		
		JLabel SSNLabel = new JLabel("");
		SSNLabel.setBounds(36, 35, 140, 16);
		panel_3.add(SSNLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		DefaultListModel<Patient> model = new DefaultListModel<Patient>();
		for(Patient p: GUI.data.getPatientList()) {
			model.addElement(p);
		}
		JList<Patient> patientList = new JList<Patient>(model);
		patientList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && !patientList.isSelectionEmpty()) {
					nameLabel.setText(patientList.getSelectedValue().getfName() + " " + patientList.getSelectedValue().getlName());
					dateLabel.setText(Functions.dateToString(patientList.getSelectedValue().getDOB(), "MM/dd/yyyy"));
					SSNLabel.setText(patientList.getSelectedValue().getSSN());
				}
			}
		});
		patientList.setVisibleRowCount(5);
		scrollPane.setViewportView(patientList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 236, 438, 36);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPatientsFrame.this.dispose();
			}
		});
		btnBackToMain.setBounds(6, 6, 147, 29);
		panel_1.add(btnBackToMain);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 188, 438, 36);
		contentPane.add(panel_2);
		
		JButton btnRemoveFromSystem = new JButton("Remove from System");
		btnRemoveFromSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Patient> itr = GUI.data.getPatientList().iterator();
				Iterator<Visit<Integer, Integer>> itr2 = GUI.data.getVisitList().iterator();
				Patient p = (Patient) patientList.getSelectedValue();
				while (itr.hasNext()) {
					Patient pat = itr.next();
					if (pat.equals(p)) {
						itr.remove();
					}
				}
				while (itr2.hasNext()) {
					Visit<Integer, Integer> v = itr2.next();
					if(p.getID() == v.getHost().intValue()) {
						itr2.remove();
					}
				}
				 int index = patientList.getSelectedIndex();
				    if (index != -1) {
				        model.remove(index);
				    }
			}
		});
		
		panel_2.add(btnRemoveFromSystem);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 6, 40, 16);
		panel_3.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(186, 6, 83, 16);
		panel_3.add(lblDateOfBirth);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(6, 35, 40, 16);
		panel_3.add(lblSsn);
	}
	
	

}
