package edu.ysu.onionuml.convert.srcml;

/**
 * Models a link between two srcml classes.
 */
public class RelationshipLink implements INamed {

	public enum LinkType{
		IMPLEMENTS,
		EXTENDS,
		ASSOCIATES,
		COMPOSES,
		AGGREGATES,
		DEPENDS;
	}
	
	
	
	private LinkType mType = null;
	private Name mHeadNameObject = new Name();
	private String mTailName = "";
	private boolean mIsDirected = false;
	
	
	/**
	 * Sets the link type to the specified type. If the type is ASSOCIATES, it is undirected.
	 */
	public void setLinkType(LinkType type){
		setLinkType(type, type != LinkType.ASSOCIATES);
	}
	
	/**
	 * Sets the link type to the specified type and if the type is ASSOCIATES, set whether
	 * it is directed.
	 */
	public void setLinkType(LinkType type, boolean isDirected){
		mType = type;
		mIsDirected = isDirected || type != LinkType.ASSOCIATES;
	}
	
	/**
	 * If the type is ASSOCIATES, set whether it is directed.
	 */
	public void setIsDirected(boolean isDirected){
		mIsDirected = isDirected || mType != LinkType.ASSOCIATES;
	}
	
	/**
	 * Sets the name of the head of the link (the 'to').
	 */
	public void setHeadName(String name){
		mHeadNameObject.setValue(name);
	}
	
	/**
	 * Sets the name of the tail of the link (the 'from').
	 */
	public void setTailName(String name){
		mTailName = name;
	}
	
	/**
	 * Gets the type of link represented by this object.
	 */
	public LinkType getLinkType(){
		return mType;
	}
	
	/**
	 * Gets whether the relationship is directed.
	 */
	public boolean isDirected(){
		return mIsDirected;
	}
	
	/**
	 * Gets the name of the head of the relationship.
	 */
	public String getHeadName(){
		return mHeadNameObject.toString();
	}
	
	/**
	 * Gets the name of the tail of the relationship.
	 */
	public String getTailName(){
		return mTailName;
	}

	@Override
	public Name getNameRef() {
		return mHeadNameObject;
	}

	@Override
	public void setName(Name n) {
		mHeadNameObject = n;
	}
}
