package com.onionuml.convert.srcml;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a srcml function container.
 */
public class Function implements IParameterListContainer, INamed, ISpecified, ITyped {

	private boolean mIsPublic = false;
	private String mVisString = "private";
	private List<Parameter> mPList = new ArrayList<Parameter>();
	private Name mName = new Name();
	private Type mType = new Type();
	private boolean mIsAbstract = false;
	private boolean mIsStatic = false;
	
	
	@Override
	public void addParamList(List<Parameter> pList) {
		mPList = pList;
	}

	@Override
	public void addSpecifier(String specifier) {
		if(specifier.equals("public") || specifier.equals("protected") || specifier.equals("private")){
			mVisString = specifier;
			if(specifier.equals("public")){
				mIsPublic = true;
			}
		}
		else if(specifier.equals("abstract")){
			mIsAbstract = true;
		}
		else if(specifier.equals("static")){
			mIsStatic = true;
		}
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
		mVisString = t.getVisibilityString();
		if(mVisString.equals("public")){
			mIsPublic = true;
		}
	}

	@Override
	public Type getTypeRef() {
		return mType;
	}
	
	/**
	 * Gets whether the function is public.
	 */
	public boolean isPublic(){
		return mIsPublic;
	}
	
	/**
	 * Returns a reference to the function's parameter list.
	 */
	public List<Parameter> getParameterListRef(){
		return mPList;
	}
	
	/**
	 * Gets a string describing this function's visibility.
	 */
	public String getVisibilityString(){
		return mVisString;
	}
	
	/**
	 * Gets whether the function is static.
	 */
	public boolean isStatic(){
		return mIsStatic;
	}
	
	/**
	 * Gets whether the function is abstract.
	 */
	public boolean isAbstract(){
		return mIsAbstract;
	}
}
