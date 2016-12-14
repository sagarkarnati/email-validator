package com.vidya.tools.email.builder;

/**
 * Boolean builder,defaults to false 
 * 
 * @author vidya
 *
 */
public class BooleanBuilder {
	
	private boolean result = false;
	
	/**
	 * Performs and operation with the current result.
	 * 
	 * @param value
	 * @return
	 */
	public BooleanBuilder and(boolean value){
		
		result = result && value;
		return this;
	}
	
	/**
	 * Performs or operation with the current result
	 * 
	 * @param value
	 * @return
	 */
	public BooleanBuilder or(boolean value){
		
		result = result || value;
		return this;
	}
	
	/**
	 * Returns result till this point.
	 * 
	 * If this is called before any of the operation will give default value false.
	 * 
	 * @return
	 */
	public boolean build() {
		
		return result;
	}
}