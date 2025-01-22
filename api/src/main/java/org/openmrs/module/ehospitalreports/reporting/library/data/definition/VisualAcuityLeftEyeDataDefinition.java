package org.openmrs.module.ehospitalreports.reporting.library.data.definition;

import org.openmrs.module.reporting.data.BaseDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;

/**
 * Visual Acuity Left Eye Data Definition
 */
public class VisualAcuityLeftEyeDataDefinition extends BaseDataDefinition implements PersonDataDefinition {
	
	public static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor
	 */
	public VisualAcuityLeftEyeDataDefinition() {
		super();
	}
	
	/**
	 * Constructor to populate name only
	 */
	public VisualAcuityLeftEyeDataDefinition(String name) {
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
