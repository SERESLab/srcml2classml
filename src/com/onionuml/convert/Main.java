package com.onionuml.convert;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import com.onionuml.visplugin.core.UmlClassElement;
import com.onionuml.visplugin.core.UmlRelationshipElement;

public class Main {
	
	/**
	 * Main entry point of execution.
	 */
	public static void main(String[] args) {
		
		//String filename = "/Users/mike/srcml.xml";
		String filename = "/Users/mike/Downloads/srcML/xstream.xml";
		
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
	    
	    
	    String eol = System.getProperty("line.separator");
	    
	    Map<String,List<UmlClassElement>> pkgMap = saxHandler.getPackageClassMap();
	    
	    Iterator<Entry<String,List<UmlClassElement>>>  itNodes = pkgMap.entrySet().iterator();
		while (itNodes.hasNext()) {
			Entry<String,List<UmlClassElement>> pairs = (Entry<String,List<UmlClassElement>>)itNodes.next();
			
			System.out.println("Package: " + pairs.getKey() + ", " + pairs.getValue().size() + " classes");
			
			for(UmlClassElement c : pairs.getValue()){
				System.out.println(c.toString() + eol);
			}
			
			System.out.println(eol + "###############################" + eol);
		}
		
		List<UmlRelationshipElement> relationships = saxHandler.getRelationships();
		relationships.clear();
	}
}
