package rlipkin.hospital.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import rlipkin.hospital.classes.Doctor;
import rlipkin.hospital.classes.Visit;
import rlipkin.hospital.misc.Functions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewDoctorsFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDoctorsFrame frame = new ViewDoctorsFrame();
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
	public ViewDoctorsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 0, 440, 94);
		contentPane.add(panel);
		
		JLabel lblAllDoctors = new JLabel("All Doctors:");
		panel.add(lblAllDoctors);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		DefaultListModel<Doctor> model = new DefaultListModel<Doctor>();
		for(Doctor d: GUI.data.getDoctorList()) {
			model.addElement(d);
		}
		
		
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
		
		JLabel specLabel = new JLabel("");
		specLabel.setBounds(68, 35, 130, 16);
		panel_3.add(specLabel);
		
		JLabel SSNLabel = new JLabel("");
		SSNLabel.setBounds(252, 35, 140, 16);
		panel_3.add(SSNLabel);
		
		JList<Doctor> doctorList = new JList<Doctor>(model);
		doctorList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && !doctorList.isSelectionEmpty()) {
					nameLabel.setText(doctorList.getSelectedValue().getfName() + " " + doctorList.getSelectedValue().getlName());
					dateLabel.setText(Functions.dateToString(doctorList.getSelectedValue().getDOB(), "MM/dd/yyyy"));
					specLabel.setText(doctorList.getSelectedValue().getSpecialty().toString());
					SSNLabel.setText(doctorList.getSelectedValue().getSSN());
				}
			}
		});
		doctorList.setVisibleRowCount(5);
		scrollPane.setViewportView(doctorList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 175, 440, 50);
		contentPane.add(panel_1);
		
		JButton btnRemoveFromSystem = new JButton("Remove From System");
		btnRemoveFromSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator<Doctor> itr = GUI.data.getDoctorList().iterator();
				Iterator<Visit<Integer, Integer>> itr2 = GUI.data.getVisitList().iterator();
				Doctor d = (Doctor) doctorList.getSelectedValue();
				while (itr.hasNext()) {
					Doctor doc = itr.next();
					if (doc.equals(d)) {
						itr.remove();
					}
				}
				while (itr2.hasNext()) {
					Visit<Integer, Integer> v = itr2.next();
					if(d.getID() == v.getHost().intValue()) {
						itr2.remove();
					}
				}
				 int index = doctorList.getSelectedIndex();
				    if (index != -1) {
				        model.remove(index);
				    }
			}
		});
		
		panel_1.add(btnRemoveFromSystem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 237, 440, 35);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Back to Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDoctorsFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(6, 6, 152, 29);
		panel_2.add(btnNewButton);

		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 6, 40, 16);
		panel_3.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(186, 6, 83, 16);
		panel_3.add(lblDateOfBirth);
		
		JLabel lblSpecialty = new JLabel("Specialty:");
		lblSpecialty.setBounds(6, 35, 61, 16);
		panel_3.add(lblSpecialty);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(218, 35, 40, 16);
		panel_3.add(lblSsn);
		
	
		
	
		
		
		
		

	}

}
