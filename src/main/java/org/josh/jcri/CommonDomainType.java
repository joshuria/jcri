package org.josh.jcri;

/**Interface of method's parameter.
 <p>All method's parameter classes and types in every domain are required to implement this interface.
 </p>
 <ol><li>{@link #check()}: for checking if user given parameters are all valid.</li>
 <li>{@link #toJson(StringBuilder)}: convert the parameter object into json string.</li></ol>
 @author Joshua */
interface CommonDomainType {
    /**Check if parameter fields of method are all valid.
     @throws IllegalArgumentException if any of parameter is not valid. */
    void check() throws IllegalArgumentException;
    /**Convert method parameter object into json string and append into string builder.
     @return string builder instance that is given in parameter (for chaining coding style use.) */
    StringBuilder toJson(StringBuilder strBuilder);
}
