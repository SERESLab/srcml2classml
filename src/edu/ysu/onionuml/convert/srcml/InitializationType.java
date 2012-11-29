package edu.ysu.onionuml.convert.srcml;

/**
 * Represents the object type of the initialization statement.
 */
public enum InitializationType{
	NEWOBJECT,
	OTHER,
	NULL,
	NONE;
	
	/**
	 * Returns the InitializationType represented by the specified string.
	 */
	public static InitializationType parseInitType(String s){
		
		if(s.equals("null")){
			return NULL;
		}
		else if(s.startsWith("new")){
			return NEWOBJECT;
		}
		else if(s.length() > 0){
			return OTHER;
		}
		
		return NONE;
	}
}