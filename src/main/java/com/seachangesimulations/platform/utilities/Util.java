package com.seachangesimulations.platform.utilities;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;

public class Util {

	public static final String lineTerminator = "\r\n";

	public static String packageIntoXML(Class class1, Object object) {

		String returnValue = "<empty />";

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(class1);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
			// true);
			StringWriter stringWriter = new StringWriter();

			jaxbMarshaller.marshal(object, stringWriter);

			returnValue = stringWriter.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnValue;
	}

	/**
	 * Attempts to save a file to disk. Returns true if it appears to have
	 * functioned.
	 * 
	 * @param filePathAndName
	 * @param bytes
	 * @return
	 */
	public static boolean saveFileContent(String filePathAndName, byte[] bytes) {
		try {
			File file = new File(filePathAndName);
			FileUtils.writeByteArrayToFile(file, bytes);
		} catch (Exception e) {
			System.out.println("XXX Unable to save image: " + e.getMessage());
			return false;
		}

		return true;

	}

	public static byte[] getFileContent(String filePathAndName) {

		byte[] returnBytes;

		try {
			File file = new File(filePathAndName);
			returnBytes = Files.readAllBytes(file.toPath());
		} catch (Exception e) {
			System.out.println("XXX Unable to save image: " + e.getMessage());
			return null;
		}

		return returnBytes;

	}
	
	public static void mark(String myMark) {
		System.out.println(myMark + "***************************************");
		System.out.println(myMark + "***************************************");
		System.out.println(myMark + "***************************************");
		System.out.println(myMark + "***************************************");
		System.out.println(myMark + "***************************************");
		System.out.println(myMark + "***************************************");
	}

	public static void mark() {
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("***************************************");
	}

	public static int string2Int(String parameter) {
		
		int returnValue = 0;
		
		try {
			returnValue = new Integer(parameter).intValue();
		} catch (Exception e){
			mark();
			System.out.println("Error converting parameter to int with input: " + parameter);
		}
		
		return returnValue;
	}

}
