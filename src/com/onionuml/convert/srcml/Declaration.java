package com.onionuml.convert.srcml;


/**
 * Models a srcml declaration container.
 */
public class Declaration implements ITyped, INamed {
	
	private Name mName = new Name();
	private Type mType = new Type();
	
	
	@Override
	public String toString(){
		return mName.toString() + " : " + mType.toString();
	}

	@Override
	public Name getNameRef() {
		return mName;
	}
	
	@Override
	public void setName(Name n) {
		mName = n;
	}
	
	@Override
	public void setType(Type t) {
		mType = t;
	}
	
	@Override
	public Type getTypeRef() {
		return mType;
	}
}
