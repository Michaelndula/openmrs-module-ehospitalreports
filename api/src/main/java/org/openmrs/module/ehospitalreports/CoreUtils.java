package org.openmrs.module.ehospitalreports;

import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class CoreUtils {
	
	/**
	 * Calculates the earliest date of two given dates, ignoring null values
	 * 
	 * @param d1 the first date
	 * @param d2 the second date
	 * @return the earliest date value
	 * @should return null if both dates are null
	 * @should return non-null date if one date is null
	 * @should return earliest date of two non-null dates
	 */
	public static Date earliest(Date d1, Date d2) {
		return OpenmrsUtil.compareWithNullAsLatest(d1, d2) >= 0 ? d2 : d1;
	}
	
	/**
	 * Add days to an existing date
	 * 
	 * @param date the date
	 * @param days the number of days to add (negative to subtract days)
	 * @return the new date
	 * @should shift the date by the number of days
	 */
	public static Date dateAddDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	/**
	 * Sets an untyped global property
	 * 
	 * @param property the property name
	 * @param value the property value
	 */
	public static void setGlobalProperty(String property, String value) {
		GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(property);
		if (gp == null) {
			gp = new GlobalProperty();
			gp.setProperty(property);
		}
		gp.setPropertyValue(value);
		Context.getAdministrationService().saveGlobalProperty(gp);
	}
}
