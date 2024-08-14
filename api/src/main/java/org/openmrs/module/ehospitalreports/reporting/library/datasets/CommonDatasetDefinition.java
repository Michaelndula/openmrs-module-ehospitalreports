package org.openmrs.module.ehospitalreports.reporting.library.datasets;

import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.springframework.stereotype.Component;

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
}
