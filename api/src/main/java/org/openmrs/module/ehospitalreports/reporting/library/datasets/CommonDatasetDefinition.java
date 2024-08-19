package org.openmrs.module.ehospitalreports.reporting.library.datasets;

import org.openmrs.Concept;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.patient.definition.EncountersForPatientDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ObsForPersonDataDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommonDatasetDefinition {
	
	/**
	 * Dataset that is generic accross all other definitions It should return the facility name and
	 * its corresponding MFL code
	 * 
	 * @return @{@link org.openmrs.module.reporting.dataset.definition.DataSetDefinition}
	 */
	public DataSetDefinition getFacilityMetadata() {
		SqlDataSetDefinition sqlDataSetDefinition = new SqlDataSetDefinition();
		sqlDataSetDefinition.setName("F");
		sqlDataSetDefinition
		        .setSqlQuery("SELECT l.name AS name,la.value_reference AS mfl_code FROM location l "
		                + " INNER JOIN location_attribute la ON l.location_id=la.location_id "
		                + " WHERE l.location_id = (SELECT property_value FROM global_property WHERE property='kenyaemr.defaultLocation')");
		return sqlDataSetDefinition;
	}
	
	public static DataDefinition getObservation(Concept question) {
		ObsForPersonDataDefinition obs = new ObsForPersonDataDefinition();
		obs.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		obs.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		obs.setWhich(TimeQualifier.LAST);
		obs.setQuestion(question);
		return obs;
		
	}
	
	public static DataDefinition getEncounterDate() {
		EncountersForPatientDataDefinition dsd = new EncountersForPatientDataDefinition();
		dsd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		dsd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		dsd.setWhich(TimeQualifier.LAST);
		return dsd;
	}
}
