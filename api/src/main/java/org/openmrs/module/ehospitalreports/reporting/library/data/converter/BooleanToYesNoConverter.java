package org.openmrs.module.ehospitalreports.reporting.library.data.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

public class BooleanToYesNoConverter implements DataConverter {
	
	@Override
	public Object convert(Object original) {
		if (original instanceof Boolean) {
			return ((Boolean) original) ? "Yes" : "No";
		}
		return "No";
	}
	
	@Override
	public Class<?> getInputDataType() {
		return Boolean.class;
	}
	
	@Override
	public Class<?> getDataType() {
		return String.class;
	}
}
