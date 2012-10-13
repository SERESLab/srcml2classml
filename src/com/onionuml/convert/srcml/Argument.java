package com.onionuml.convert.srcml;

/**
 * Models a srcml argument.
 */
public class Argument implements INamed {
	
	private Name mName = new Name();
	
	@Override
	public Name getNameRef() {
		return mName;
	}

	@Override
	public void setName(Name n) {
		mName = n;
	}
	
	@Override
	public String toString(){
		return mName.toString();
	}
}
