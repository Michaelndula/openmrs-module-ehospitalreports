package org.openmrs.module.ehospitalreports.reporting.library.cohorts;

import java.util.Date;
import java.util.List;

import org.openmrs.Location;
import org.openmrs.module.ehospitalreports.reporting.library.queries.CommonQueries;
import org.openmrs.module.ehospitalreports.reporting.library.queries.PnsRegisterQueries;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

@Component
public class BaseCohortQueries {
	
	public CohortDefinition getPatientsWithTodaysAppointments() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Todays appointment patient set");
		cd.addParameter(new Parameter("startDate", "startDate", Date.class));
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		//cd.addParameter(new Parameter("location", "location", Location.class));
		cd.setQuery(CommonQueries.getPatientsWithAppointments());
		return cd;
	}
	
	public CohortDefinition getChildOpdPatients() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Outpatient Report for Children");
		cd.addParameter(new Parameter("startDate", "startDate", Date.class));
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.setQuery(CommonQueries.getChildOpdVisits());
		return cd;
	}
}
