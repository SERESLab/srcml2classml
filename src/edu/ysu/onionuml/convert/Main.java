package edu.ysu.onionuml.convert;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import edu.ysu.onionuml.core.UmlClassModel;

public class Main {
	
	/**
	 * Main entry point of execution.
	 */
	public static void main(String[] args) {
		
		if(args.length < 2){
			System.out.println("Usage: srcml2classml srcml_file out_file [model_name]");
			System.exit(0);
		}
		
		String inFile = args[0];
		String outFile = args[1];
	    String name = (args.length > 2 ? args[2] : "Untitled Model");
	    
	    try{
	    	System.out.println("Reading " + inFile + "...");
		    SrcmlSaxHandler saxHandler = readSrcmlFile(inFile);
		    
		    UmlClassModel model = new UmlClassModel(name, null,
		    		saxHandler.buildUmlPackages(), saxHandler.buildUmlRelationships());
		    
		    System.out.println("Read " + model.getPackages().size() + " packages, "
		    		+ String.valueOf(saxHandler.getNumClasses()) + " classes, and "
		    		+ model.getRelationships().size() + " relationships...");
		    
		    System.out.print("Writing ClassML file to " + outFile + "...");
		    model.toFile(outFile);
		    System.out.println("Done.");
	    }
	    catch(Exception e){
	    	System.err.println("Error parsing file: ");
	    	System.err.println(e.getMessage());
	    }
	}
	
	
	/*
	 * Reads and parses the specified srcml file.
	 */
	private static SrcmlSaxHandler readSrcmlFile(String filename){
		SAXParser saxParser = null;
	    try {
	    	SAXParserFactory spf = SAXParserFactory.newInstance();
		    saxParser = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("A parser for the specified file could not be created.");
		} catch (SAXException e) {
			throw new RuntimeException("SAX error: " + e.getMessage());
		}
	    
	    SrcmlSaxHandler saxHandler = new SrcmlSaxHandler();
	    try {
			XMLReader reader = saxParser.getXMLReader();
			reader.setErrorHandler(new ErrorHandler(){

				@Override
				public void error(SAXParseException e) throws SAXException {
					throw new RuntimeException("Parse error: " + e.getMessage());
				}

				@Override
				public void fatalError(SAXParseException e)
						throws SAXException {
					throw new RuntimeException("Parse error: " + e.getMessage());
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
					e.printStackTrace();
				}
				
			});
			reader.setContentHandler(saxHandler);
			reader.parse(filename);
		} catch (SAXException e) {
			throw new RuntimeException("Error reading the specified file: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Error reading the specified file.");
		}
	    
	    return saxHandler;
	}
}
