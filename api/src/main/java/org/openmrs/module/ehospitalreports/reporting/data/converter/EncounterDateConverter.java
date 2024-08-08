package org.openmrs.module.ehospitalreports.reporting.data.converter;

import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EncounterDateConverter implements DataConverter {
	
	public Object convert(Object original) {
		Encounter encounter = (Encounter) original;
		
		if (encounter == null) {
			return null;
		} else if (encounter.getDateCreated() != null) {
			return formatdates(encounter.getDateCreated(), "dd-MM-yyyy HH:mm:ss");
		}
		return null;
	}
	
	public Class<?> getInputDataType() {
		return Obs.class;
	}
	
	public Class<?> getDataType() {
		return Object.class;
	}
	
	public static String formatdates(Date date, String format) {
		Format formatter = new SimpleDateFormat(format);
		String s = formatter.format(date);
		return s;
	}
}
