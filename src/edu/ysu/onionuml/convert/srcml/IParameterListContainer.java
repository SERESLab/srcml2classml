package edu.ysu.onionuml.convert.srcml;

import java.util.List;

/**
 * Specifies an object that can receive a list of parameters.
 */
public interface IParameterListContainer {

	/**
	 * Adds the specified list of parameters to this object.
	 */
	public void addParamList(List<Parameter> pList);
}
