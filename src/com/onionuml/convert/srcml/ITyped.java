package com.onionuml.convert.srcml;

/**
 * Specifies an object that can have a type.
 */
public interface ITyped {

	/**
	 * Sets the object's type to the given type object.
	 */
	public void setType(Type t);
	
	/**
	 * Returns a reference to this object's type object.
	 */
	public Type getTypeRef();
}
