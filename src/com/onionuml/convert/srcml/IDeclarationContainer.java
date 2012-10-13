package com.onionuml.convert.srcml;

/**
 * Specifies an object that can accept srcml declaration objects.
 */
public interface IDeclarationContainer {

	/**
	 * Adds the specified declaration object to this object's declaration collection.
	 */
	public void addDeclaration(Declaration d);
}
