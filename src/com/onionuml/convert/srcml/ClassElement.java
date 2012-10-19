package com.onionuml.convert.srcml;

import java.util.ArrayList;
import java.util.List;

import com.onionuml.convert.srcml.RelationshipLink.LinkType;
import com.onionuml.visplugin.core.UmlAttribute;
import com.onionuml.visplugin.core.UmlClassElement;
import com.onionuml.visplugin.core.UmlOperation;
import com.onionuml.visplugin.core.UmlOperationParameter;
import com.onionuml.visplugin.core.Visibility;

/**
 * Models a srcml class container.
 */
public class ClassElement implements INamed, ISpecified, IDeclarationContainer {
	
	private String mId;
	private String mPackageName;
	private String mStereotype;
	private boolean mIsPublic = true;
	private boolean mIsAbstract = false;
	private Name mName = new Name();
	private List<Declaration> mDeclarations = new ArrayList<Declaration>();
	private List<Function> mFunctions = new ArrayList<Function>();
	private List<RelationshipLink> mLinks = new ArrayList<RelationshipLink>();
	private List<String> mImports = new ArrayList<String>();
	
	/**
	 * Constructs a new class element container with the specified class id, package name,
	 * and stereotype.
	 */
	public ClassElement(String id, String packageName, String stereotype){
		mId = id;
		mPackageName = packageName;
		mStereotype = stereotype;
	}
	
	@Override
	public void addSpecifier(String specifier) {
		if(specifier.equals("protected") || specifier.equals("private")){
			mIsPublic = false;
		}
		else if(specifier.equals("abstract")){
			mIsAbstract = true;
		}
	}
	
	@Override
	public void setName(Name n) {
		mName = n;
	}
	
	@Override
	public Name getNameRef() {
		return mName;
	}
	
	@Override
	public void addDeclaration(Declaration d) {
		mDeclarations.add(d);
		if(d.getInitializationType() == InitializationType.NEWOBJECT){
			RelationshipLink l = new RelationshipLink();
			l.setHeadName(d.getTypeRef().getNameRef().toString());
			l.setLinkType(LinkType.COMPOSES);
			addLink(l);
		}
		else{
			RelationshipLink l = new RelationshipLink();
			l.setHeadName(d.getTypeRef().getNameRef().toString());
			l.setLinkType(LinkType.ASSOCIATES);
			addLink(l);
		}
	}
	
	/**
	 * Adds the specified function to the class. Creates dependency relationships
	 * with each of the function's parameter types.
	 */
	public void addFunction(Function f){
		mFunctions.add(f);
		
		String retType = f.getTypeRef().toString();
		if(retType.equals("void") || retType.length() > 0){
			RelationshipLink l = new RelationshipLink();
			l.setHeadName(retType);
			l.setLinkType(LinkType.AGGREGATES);
			addLink(l);
		}
		
		for(Parameter p : f.getParameterListRef()){
			RelationshipLink l = new RelationshipLink();
			l.setHeadName(p.getTypeRef().getNameRef().toString());
			l.setLinkType(LinkType.DEPENDS);
			addLink(l);
		}
	}
	
	/**
	 * Initializes a new UmlClassElement from this srcml class element.
	 */
	public UmlClassElement toUmlClassElement(){
		
		if(!mIsPublic || mName.toString().length() == 0){
			return null;
		}
		
		UmlClassElement e = new UmlClassElement(mName.toString(), mStereotype);
		e.setIsAbstract(mIsAbstract);
		
		for(Declaration d : mDeclarations){
			UmlAttribute a = new UmlAttribute(Visibility.parseVisibility(d.getTypeRef().getVisibilityString()),
					d.getNameRef().toString(), d.getTypeRef().toString());
			e.addAttribute(a);
		}
		
		for(Function f: mFunctions){
			UmlOperation o = new UmlOperation(Visibility.parseVisibility(f.getVisibilityString()),
					f.getNameRef().toString(), f.getTypeRef().toString(), f.isStatic(), f.isAbstract());
			
			for(Parameter p : f.getParameterListRef()){
				UmlOperationParameter op = new UmlOperationParameter(p.getNameRef().toString(),
						p.getTypeRef().toString());
				o.parameters.add(op);
			}
			
			// ignore default constructor
			if(!o.returnType.isEmpty() || !o.parameters.isEmpty()){
				e.addOperation(o);
			}
		}
		
		return e;
	}
	
	/**
	 * Gets the id of the class element.
	 */
	public String getId(){
		return mId;
	}
	
	/**
	 * Gets the name of the package containing the class element.
	 */
	public String getPackageName(){
		return mPackageName;
	}
	
	/**
	 * Adds the specified relationship link to this class.
	 */
	public void addLink(RelationshipLink l){
		l.setTailName(mName.toString());
		mLinks.add(l);
	}
	
	/**
	 * Sets the list of packages imported to this class.
	 */
	public void setImports(List<String> imports){
		mImports = imports;
	}
	
	/**
	 * Gets the fully qualified name of the class.
	 */
	public String getQualifiedName(){
		return mPackageName + "." + mName.toString();
	}
	
	/**
	 * Gets a list of relationship links with fully qualified names.
	 */
	public List<RelationshipLink> makeQualifiedRelationships(){
		List<RelationshipLink> qual = new ArrayList<RelationshipLink>();
		
		for(RelationshipLink linkUnqualified : mLinks){
			RelationshipLink linkQualified = new RelationshipLink();
			
			linkQualified.setLinkType(linkUnqualified.getLinkType(), linkUnqualified.isDirected());
			linkQualified.setTailName(getQualifiedName());
			
			String qHead = lookupQualifiedClassName(linkUnqualified.getHeadName());
			if(qHead != null){
				linkQualified.setHeadName(qHead);
				qual.add(linkQualified);
			}
		}
		
		return qual;
	}
	
	
	
	/*
	 * Searches the list of imported packages for imported classes having
	 * the specified class name.
	 */
	private String lookupQualifiedClassName(String className){
		
		String qual = null;
		String suffix = "." + className;
		
		for(String impString : mImports){
			if(impString.endsWith(suffix)){
				qual = impString;
			}
		}
		
		return qual;
	}
}
