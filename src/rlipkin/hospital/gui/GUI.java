package rlipkin.hospital.gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import rlipkin.hospital.classes.Doctor;
import rlipkin.hospital.classes.Patient;
import rlipkin.hospital.classes.SchedulerData;
import rlipkin.hospital.classes.Visit;
import rlipkin.hospital.misc.Functions;
import rlipkin.hospital.misc.vDateComparator;
import rlipkin.hospital.xmlutils.SchedulerXMLReaderUtils;
import rlipkin.hospital.xmlutils.SchedulerXMLWriteUtils;

import java.awt.Font;

public class GUI {
	
	public static SchedulerData data = new SchedulerData();
	
	private final static String OUTPUT_FILE = "/Users/Ron1/Documents/workspace/Assignment_4_GUI/resources/schedulerData.xml";
	private final static String INPUT_FILE = "/Users/Ron1/Documents/workspace/Assignment_4_GUI/resources/schedulerData.xml";
	
	public static Map<Integer, Patient> pMap = new HashMap<Integer, Patient>();
	public static Map<Integer, Doctor> dMap = new HashMap<Integer, Doctor>();
	
	public static void makePatientMap(List<Patient> pList){
		for(Patient p: pList){
			pMap.put(p.getID(), p);
		}
	}
	
	public static void makeDoctorMap(List<Doctor> dList){
		for(Doctor d: dList){
			dMap.put(d.getID(), d);
		}
	}
	
	public static void printAllVisits(List<Visit<Integer, Integer>> a){
		Collections.sort(a, new vDateComparator<Integer, Integer>());
		for(Visit<Integer, Integer> v: a){
			System.out.println("Visit Date:		" + Functions.dateToString(v.getVisitDate(), "MMMM dd, yyyy"));
			System.out.println("Doctor:			" + dMap.get(v.getHost()).getfName() + " " + dMap.get(v.getHost()).getlName());
			System.out.println("Specialty:		" + dMap.get(v.getHost()).getSpecialty());
			System.out.println("Days Until Visit:	" + Functions.daysUntil(v.getVisitDate()));
			System.out.println("Patient:");
			System.out.println("     First name:	   " + pMap.get(v.getVisitor()).getfName());
			System.out.println("     Last name:		   " + pMap.get(v.getVisitor()).getlName());
			System.out.println("     SSN:	 	   " + pMap.get(v.getVisitor()).getSSN());
			System.out.println("     Age:	 	   " + pMap.get(v.getVisitor()).getAge());
			System.out.println();
		}
	}
	
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws XMLStreamException, IOException {
		data = SchedulerXMLReaderUtils.readSchedulingXML(INPUT_FILE);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnAddVisit = new JButton("Add Visit");
		btnAddVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVisitFrame vFrame = new AddVisitFrame();
				vFrame.setVisible(true);
			}
		});
		btnAddVisit.setBounds(306, 73, 101, 29);
		panel.add(btnAddVisit);
		
		JButton btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.setBounds(41, 73, 116, 29);
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDoctorFrame dFrame = new AddDoctorFrame();
				dFrame.setVisible(true);
			}
		});
		panel.add(btnAddDoctor);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setBounds(177, 73, 116, 29);
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPatientFrame pFrame = new AddPatientFrame();
				pFrame.setVisible(true);
			}
		});
		panel.add(btnAddPatient);
		
		JLabel lblChangesSaved = new JLabel("Changes Saved");
		lblChangesSaved.setBounds(181, 244, 101, 16);
		panel.add(lblChangesSaved);
		lblChangesSaved.setVisible(false);
		
		JLabel lblHospitalSystem = new JLabel("Hospital System");
		lblHospitalSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHospitalSystem.setBounds(162, 21, 139, 29);
		panel.add(lblHospitalSystem);
		
		JButton btnShowVisitsIn = new JButton("Show Visits in Console");
		btnShowVisitsIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makePatientMap(data.getPatientList());
				makeDoctorMap(data.getDoctorList());
				printAllVisits(data.getVisitList());
			}
		});
		btnShowVisitsIn.setBounds(151, 166, 169, 29);
		panel.add(btnShowVisitsIn);
		
		JButton btnSaveAllChanges = new JButton("Save All Changes");
		btnSaveAllChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SchedulerXMLWriteUtils.writeData(OUTPUT_FILE, data.getDoctorList(), data.getPatientList(), data.getVisitList());
				} catch (XMLStreamException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				lblChangesSaved.setVisible(true);
			}
		});
		
		btnSaveAllChanges.setBounds(151, 218, 169, 29);
		panel.add(btnSaveAllChanges);
		
		JButton btnViewDoctors = new JButton("View Doctors");
		btnViewDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDoctorsFrame d = new ViewDoctorsFrame();
				d.setVisible(true);
			}
		});
		btnViewDoctors.setBounds(109, 114, 117, 29);
		panel.add(btnViewDoctors);
		
		JButton btnViewPatients = new JButton("View Patients");
		btnViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPatientsFrame p = new ViewPatientsFrame();
				p.setVisible(true);	
			}
		});
		btnViewPatients.setBounds(254, 114, 117, 29);
		panel.add(btnViewPatients);
		
		
	}
}
