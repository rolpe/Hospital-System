package rlipkin.hospital.xmlutils;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

import rlipkin.hospital.classes.Doctor;
import rlipkin.hospital.classes.Patient;
import rlipkin.hospital.classes.Visit;
import rlipkin.hospital.gui.GUI;
import rlipkin.hospital.misc.Functions;

public class SchedulerXMLWriteUtils {
	
	private final static String NAMESPACE = "http://www.miami.edu/cis324/xml/scheduling";
	private final static String SCHEMA_INSTANCE_PREFIX = "xsi";
	private final static String SCHEMA_INSTANCE_NS = "http://www.w3.org/2001/XMLSchema-instance";
	private final static String SCHEMA_LOCATION_ATTRNAME = "schemaLocation";
	private final static String SCHEMA_FILE_NAME = "scheduling.xsd";
	
	
	public static Characters getIndentation(XMLEventFactory eventFactory, int level) {
		// returns an object with as many tabs as needed to indent to the value specified by the input parameter
		char[] tabs = new char[level];
		Arrays.fill(tabs, '\t'); // fill the number of tabs
		return eventFactory.createIgnorableSpace(String.valueOf(tabs)); // and create an ignorable space
	}

	public static void writeNode(XMLEventFactory eventFactory, XMLEventWriter eventWriter, 
			String name, String value, int level) throws XMLStreamException {
		// Create Start node
		eventWriter.add(getIndentation(eventFactory, level));
		StartElement startElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(startElement);
		// Create Content
		Characters charValue = eventFactory.createCharacters(value);
		eventWriter.add(charValue);
		// Create End node
		EndElement endElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(endElement);
		// line feed
		eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	}

	public static String xmlStandardDateFormat = "yyyy-MM-dd"; // ignore time zones for simplicity

	/*public static void writeDate(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String name, Calendar date, int level) throws XMLStreamException {
		// write the date in the specific date format required by XML Schema
		DateFormat df = new SimpleDateFormat(); // ignore time zones for simplicity
		String dateStr = df.format(date.getTime());
		writeNode(eventFactory, eventWriter, name, dateStr, level);
	}*/
	
	public static void writeName(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String name, int level) throws XMLStreamException {
		// first, write as many tabs as levels needed
		eventWriter.add(getIndentation(eventFactory, level));
		// start element
		eventWriter.add(eventFactory.createStartElement("", "", "name"));
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
		// first name
		writeNode(eventFactory, eventWriter, "firstName", name.split(" ")[0], level+1);
		// last name
		writeNode(eventFactory, eventWriter, "lastName", name.split(" ")[1], level+1);
		// end element
		eventWriter.add(getIndentation(eventFactory, level)); // also indent it
		eventWriter.add(eventFactory.createEndElement("", "", "name"));
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
	}
	
	public static void writeData(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String data, int level) throws XMLStreamException {
		// first, write as many tabs as levels needed
		eventWriter.add(getIndentation(eventFactory, level));
		// start element
		eventWriter.add(eventFactory.createStartElement("", "", "data"));
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
		// first name
		writeNode(eventFactory, eventWriter, "dob", data.split(" ")[0], level+1);
		// last name
		writeNode(eventFactory, eventWriter, "SSN", data.split(" ")[1], level+1);
		// end element
		eventWriter.add(getIndentation(eventFactory, level)); // also indent it
		eventWriter.add(eventFactory.createEndElement("", "", "data"));
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
	}

	public static void writeDoctor(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Doctor d, int level) throws XMLStreamException {
		// writes a single student through to the XML event writer
		// create the student start element
		eventWriter.add(getIndentation(eventFactory, level));
	    StartElement doctorStart = eventFactory.createStartElement("", "", "doctor");
	    eventWriter.add(doctorStart);
	    // create the id attribute
	    // note the use of Integer.toString to get a string representation
	    Attribute doctorId = eventFactory.createAttribute("id", Integer.toString(d.getID()));
	    eventWriter.add(doctorId);
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
	    // now create the nested elements
	    writeName(eventFactory, eventWriter, d.getfName() + " " + d.getlName(), level + 1);
	    writeData(eventFactory, eventWriter, Functions.dateToString(d.getDOB(), "yyyy-MM-dd") + " " + d.getSSN(), level + 1);
	    writeNode(eventFactory, eventWriter, "specialty", d.getSpecialty().toString(), level + 1);
	    // create the student end element
		eventWriter.add(getIndentation(eventFactory, level));
	    EndElement doctorEnd = eventFactory.createEndElement("", "", "doctor");
	    eventWriter.add(doctorEnd);
	}
	
	public static void writePatient(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Patient p, int level) throws XMLStreamException {
		// writes a single student through to the XML event writer
		// create the student start element
		eventWriter.add(getIndentation(eventFactory, level));
	    StartElement patientStart = eventFactory.createStartElement("", "", "patient");
	    eventWriter.add(patientStart);
	    // create the id attribute
	    // note the use of Integer.toString to get a string representation
	    Attribute patientId = eventFactory.createAttribute("id", Integer.toString(p.getID()));
	    eventWriter.add(patientId);
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
	    // now create the nested elements
	    writeName(eventFactory, eventWriter, p.getfName() + " " + p.getlName(), level + 1);
	    writeData(eventFactory, eventWriter, Functions.dateToString(p.getDOB(), "yyyy-MM-dd") + " " + p.getSSN(), level + 1);
	    // create the student end element
		eventWriter.add(getIndentation(eventFactory, level));
	    EndElement patientEnd = eventFactory.createEndElement("", "", "patient");
	    eventWriter.add(patientEnd);
	}
	
	public static void writeVisit(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Visit<Integer, Integer> v, int level) throws XMLStreamException {
		// writes a single student through to the XML event writer
		// create the student start element
		GUI.makePatientMap(GUI.data.getPatientList());
		GUI.makeDoctorMap(GUI.data.getDoctorList());
		eventWriter.add(getIndentation(eventFactory, level));
	    StartElement visitStart = eventFactory.createStartElement("", "", "visit");
	    eventWriter.add(visitStart);
	    // create the id attribute
	    // note the use of Integer.toString to get a string representation
	    Attribute doctorId = eventFactory.createAttribute("doctorId", Integer.toString(GUI.dMap.get(v.getHost()).getID()));
	    eventWriter.add(doctorId);
		Attribute patientId = eventFactory.createAttribute("patientId", Integer.toString(GUI.pMap.get(v.getVisitor()).getID()));
		eventWriter.add(patientId);
	    Attribute visitDate = eventFactory.createAttribute("visitDate", Functions.dateToString(v.getVisitDate(), "yyyy-MM-dd"));
	    eventWriter.add(visitDate);
	    // create the student end element
	    EndElement visitEnd = eventFactory.createEndElement("", "", "visit");
	    eventWriter.add(visitEnd);
	}
	
	public static void writeData(String outFile, List<Doctor> doctors, List<Patient> patients, List<Visit<Integer,Integer>> visits) throws XMLStreamException, IOException {
	    // Create a XMLOutputFactory
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	    // Create XMLEventWriter
	    Path outputFilePath = Paths.get(outFile);
	    Writer outputFile = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
	    XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputFile);
	    // Create an XMLEventFactory
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	    // Create and write Start Tag
	    StartDocument startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
	    eventWriter.add(startDocument);
	    // put a linefeed for readability
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	    // create the root element
	    StartElement root = eventFactory.createStartElement("", "", "Data");
		eventWriter.add(root);
	    eventWriter.setDefaultNamespace(NAMESPACE); // set the default namespace for the root before adding it
		// add any other namespaces to the root
	    eventWriter.add(eventFactory.createNamespace(NAMESPACE));
	    eventWriter.add(eventFactory.createNamespace(SCHEMA_INSTANCE_PREFIX, SCHEMA_INSTANCE_NS));
	    // add the schema attributes to the root element 
	    String schemaLocationArg = NAMESPACE + " " + SCHEMA_FILE_NAME;
	    eventWriter.add(eventFactory.createAttribute(SCHEMA_INSTANCE_PREFIX, SCHEMA_INSTANCE_NS, SCHEMA_LOCATION_ATTRNAME, schemaLocationArg));
	    // put a linefeed for readability
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		// iterate over the list of students and create an element for each
	    for(Doctor d: doctors) {
			writeDoctor(eventFactory, eventWriter, d, 1);
			eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
		for (Patient p : patients) {
			writePatient(eventFactory, eventWriter, p, 1); // write the student with one level of indentation
		    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
		for (Visit<Integer, Integer> v : visits) {
			writeVisit(eventFactory, eventWriter, v, 1); // write the student with one level of indentation
		    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

}
