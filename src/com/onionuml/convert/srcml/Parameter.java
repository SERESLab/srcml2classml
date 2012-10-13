package com.onionuml.convert.srcml;

/**
 * Models a srcml function parameter.
 */
public class Parameter implements IDeclarationContainer {

	private Declaration mDeclaration = new Declaration();
	
	@Override
	public void addDeclaration(Declaration d) {
		mDeclaration = d;
	}

	/**
	 * Returns a reference to the parameter's type.
	 */
	public Type getTypeRef(){
		return mDeclaration.getTypeRef();
	}
	
	/**
	 * Returns a reference to the parameter's name.
	 */
	public Name getNameRef(){
		return mDeclaration.getNameRef();
	}
}
