package org.openmrs.module.ehospitalreports.reporting.library.data.definition;

import org.openmrs.module.reporting.data.BaseDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;

/**
 * Outcome Data Definition
 */
public class OutcomeDataDefinition extends BaseDataDefinition implements PersonDataDefinition {
	
	public static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor
	 */
	public OutcomeDataDefinition() {
		super();
	}
	
	/**
	 * Constructor to populate name only
	 */
	public OutcomeDataDefinition(String name) {
		super(name);
	}
	
	//***** INSTANCE METHODS *****
	
	/**
	 * @see org.openmrs.module.reporting.data.DataDefinition#getDataType()
	 */
	public Class<?> getDataType() {
		return String.class;
	}
}
