srcml2classml
=============
The srcml2classml program is a tool for generating a ClassML UML class model from a Java project represented in [srcML](http://www.sdml.info/projects/srcml/). Relationships are automatically inferred by analyzing the srcML.


License
-------
The project is released under the [Eclipse Public License (EPL)](http://www.eclipse.org/legal/epl-v10.html). See license.html for details.


Relationship Inference Rules
-----------------------------
* Generalization:
If the srcML has an `<extends>` element as the child of a `<class>` element, a generalization relationship is inferred.

* Realization:
If the srcML has an `<implements>` element as the child of a `<class>` element, a realization relationship is inferred.

* Dependence:
Class A depends on Class B if a function in Class A has at least one parameter of the type Class B and there is not any other relationship between Class A and Class B.

* Composition:
Class A is composed of Class B if Class A constructs a new instance of Class B as a member variable and there is not an aggregation relationship between Class A and Class B.

* Aggregation:
Class B is an aggregate of Class A if a function in Class A returns an object of the type Class B.

* Association:
Class A has an association with Class B if Class A has a member variable of type Class B and there is no relationship other than Dependency between Class A and Class B.

