package edu.ysu.onionuml.convert.srcml;


/**
 * Models a srcml type container.
 */
public class Type implements ISpecified, INamed{
	
	private String mIndex = "";
	private Name mName = new Name();
	private String mVisString = "private";
	
	
	/**
	 * Sets the index string to be appended to the type's name.
	 */
	public void setIndex(String index){
		mIndex = index;
	}
	
	@Override
	public String toString(){
		return mName.toString() + mIndex;
	}
	
	@Override
	public void addSpecifier(String specifier) {
		if(specifier.equals("public") || specifier.equals("protected") || specifier.equals("private")){
			mVisString = specifier;
		}
	}
	
	public String getVisibilityString(){
		return mVisString;
	}
	
	@Override
	public Name getNameRef() {
		return mName;
	}


	@Override
	public void setName(Name n) {
		mName = n;
	}
}
