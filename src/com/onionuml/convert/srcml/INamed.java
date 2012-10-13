package com.onionuml.convert.srcml;

/**
 * Specifies an object that can have a name.
 */
public interface INamed {

	/**
	 * Returns a reference to the name object belonging to this object.
	 */
	public Name getNameRef();
	
	/**
	 * Sets this object's name to the specified name object.
	 */
	public void setName(Name n);
}
