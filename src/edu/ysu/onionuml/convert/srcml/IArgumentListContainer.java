package edu.ysu.onionuml.convert.srcml;

import java.util.List;

/**
 * Specifies an object that can receive a list of arguments.
 */
public interface IArgumentListContainer {

	/**
	 * Adds the specified list of arguments to this object.
	 */
	public void addArgList(List<Argument> argList);
}
