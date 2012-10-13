package com.onionuml.convert.srcml;

import java.util.List;

/**
 * Models a srcml name object.
 */
public class Name implements INamed, IArgumentListContainer {
	
	private String mName = "";
	
	
	@Override
	public String toString(){
		return mName;
	}

	@Override
	public Name getNameRef() {
		return this;
	}

	@Override
	public void setName(Name n) {
		mName += n.toString();
	}
	
	/**
	 * Sets the value of the name object to the specified string.
	 */
	public void setValue(String s){
		mName = s;
	}

	@Override
	public void addArgList(List<Argument> argList) {
		String argStr = "<";
		for(Argument a : argList){
			if(argStr.length() > 1){
				argStr += ", ";
			}
			argStr += a.toString();
		}
		argStr += ">";
		
		if(argStr.length() == 2){
			argStr = "<?>";
		}
		
		mName += argStr;
	}
}
