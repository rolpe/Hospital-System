package rlipkin.hospital.xmlutils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import rlipkin.hospital.classes.Doctor;
import rlipkin.hospital.classes.Patient;
import rlipkin.hospital.classes.SchedulerData;
import rlipkin.hospital.classes.Visit;
import rlipkin.hospital.classes.VisitImp1;


public class SchedulerXMLReaderUtils {
	
	public static String readCharacters(XMLEventReader eventReader, String elementName) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent(); 
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a " + elementName + " but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(elementName)) {
			throw new IllegalStateException("Attempting to read a " + elementName + " at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		String chars = eventReader.nextEvent().asCharacters().getData();
		return chars;
	}
	
	public static String readName(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent(); 
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a name but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals("name")) {
			throw new IllegalStateException("Attempting to read a name at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		String name = null;
		boolean finished = false;
		String firstName = null, lastName = null;
		while (!finished) {
			XMLEvent event = eventReader.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("firstName")) {
					event = eventReader.nextEvent();
					firstName = event.asCharacters().getData();
				}
				else if (startElement.getName().getLocalPart().equals("lastName")) {
					event = eventReader.nextEvent();
					lastName = event.asCharacters().getData();
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
				}
			}
			else if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("name")) {
					name = firstName + " " + lastName; 
					finished = true;
				}
			}
		}
		return name;
	}
	
	public static String readData(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent(); 
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read personal data but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals("data")) {
			throw new IllegalStateException("Attempting to read personal data at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		String data = null;
		boolean finished = false;
		String dob = null, SSN = null;
		while (!finished) {
			XMLEvent event = eventReader.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("dob")) {
					event = eventReader.nextEvent();
					dob = event.asCharacters().getData();
				}
				else if (startElement.getName().getLocalPart().equals("SSN")) {
					event = eventReader.nextEvent();
					SSN = event.asCharacters().getData();
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
				}
			}
			else if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("data")) {
					data = dob + " " + SSN;
					finished = true;
				}
			}
		}
		return data;
	}
	
	
	public static Doctor readDoctor(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a doctor but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals("doctor")) {
			throw new IllegalStateException("Attempting to read a doctor at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		Doctor doctor = null;
		String name = null;
		String data = null;
		String specialty = null;
		boolean finished = false;
		while (!finished) {
			XMLEvent event = eventReader.peek();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("name")) {
					name = readName(eventReader);
				}
				else if (startElement.getName().getLocalPart().equals("data")) {
					data = readData(eventReader);
				}
				else if (startElement.getName().getLocalPart().equals("specialty")) {
					specialty = readCharacters(eventReader, "specialty");
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent(); 
				}
			}
			else if (event.isEndElement()) {
				event = eventReader.nextEvent(); 
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("doctor")) {
					doctor = new Doctor(name, data.split(" ")[1], data.split(" ")[0], specialty);
					finished = true;
				}
			}
			else {
				event = eventReader.nextEvent();
			}
		}
		return doctor;
	}

	public static Patient readPatient(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a patient but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals("patient")) {
			throw new IllegalStateException("Attempting to read a patient at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		int id = 0;
		@SuppressWarnings("unchecked") 
		Iterator<Attribute> attributes = firstEvent.asStartElement().getAttributes();
		while (attributes.hasNext()) {
			Attribute attribute = attributes.next();
			if (attribute.getName().getLocalPart().equals("id")) {
				id = Integer.valueOf(attribute.getValue()); 
			}
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		Patient patient = null;
		String name = null;
		String data = null;
		boolean finished = false;
		while (!finished) {
			XMLEvent event = eventReader.peek();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("name")) {
					name = readName(eventReader);
				}
				else if (startElement.getName().getLocalPart().equals("data")) {
					data = readData(eventReader);
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent(); 
				}
			}
			else if (event.isEndElement()) {
				event = eventReader.nextEvent(); 
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("patient")) {
					patient = new Patient(name, data.split(" ")[1], data.split(" ")[0]);
					finished = true;
				}
			}
			else {
				event = eventReader.nextEvent();
			}
		}
		return patient;
	}
	
	public static Visit<Integer, Integer> readVisit(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a visit but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals("visit")) {
			throw new IllegalStateException("Attempting to read a patient at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		int patientId = 0;
		int doctorId = 0;
		String vDate = null;
		@SuppressWarnings("unchecked") 
		Iterator<Attribute> attributes = firstEvent.asStartElement().getAttributes();
		while (attributes.hasNext()) {
			Attribute attribute = attributes.next();
			if (attribute.getName().getLocalPart().equals("patientId")) {
				patientId = Integer.valueOf(attribute.getValue()); 
			}
			else if (attribute.getName().getLocalPart().equals("doctorId")) {
				doctorId = Integer.valueOf(attribute.getValue()); 
			}
			else if (attribute.getName().getLocalPart().equals("visitDate")) {
				vDate = String.valueOf(attribute.getValue()); 
			}
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		Visit<Integer, Integer> visit = null;
		boolean finished = false;
		while (!finished) {
			XMLEvent event = eventReader.peek();
			if (event.isEndElement()) {
				event = eventReader.nextEvent(); 
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("visit")) {
					visit = new VisitImp1<Integer, Integer>(patientId, doctorId, vDate);
					finished = true;
				}
			}
			else {
				event = eventReader.nextEvent();
			}
		}
		return visit;
	}
	
	
	public static SchedulerData readSchedulingXML(String fileName) throws XMLStreamException, IOException {
		SchedulerData systemData = new SchedulerData();
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		Path xmlFilePath = Paths.get(fileName);
		Reader in = Files.newBufferedReader(xmlFilePath, StandardCharsets.UTF_8);
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.peek();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == ("Data")) {
					event = eventReader.nextEvent(); 
				}
				else if (startElement.getName().getLocalPart() == ("doctor")) {
					Doctor d = readDoctor(eventReader);
					systemData.addDoctor(d);
				}
				else if (startElement.getName().getLocalPart() == ("patient")) {
					Patient p = readPatient(eventReader);
					systemData.addPatient(p);
				}
				else if (startElement.getName().getLocalPart() == ("visit")) {
					Visit<Integer, Integer> v = readVisit(eventReader);
					systemData.addVisit(v);
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent(); 
				}
			}
			else {
				event = eventReader.nextEvent(); 
			}
		}
		eventReader.close();
		return systemData;
	}
}
