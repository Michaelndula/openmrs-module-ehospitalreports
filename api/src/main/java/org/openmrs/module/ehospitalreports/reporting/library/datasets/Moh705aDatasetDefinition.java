package org.openmrs.module.ehospitalreports.reporting.library.datasets;

import org.openmrs.Location;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh705aDatasetDefinition extends eHospitalBaseDataSet {
    public DataSetDefinition constructMoh705ADatasetDefinition() {
        PatientDataSetDefinition dsd = new PatientDataSetDefinition();
        dsd.setName("MOH705A");
        dsd.addParameters(getParameters());
        dsd.setDescription("UNDER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET: MOH 705A");
        dsd.addSortCriteria("Psn", SortCriteria.SortDirection.ASC);
        dsd.addParameter(new Parameter("location", "Location", Location.class));
        dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        dsd.addParameter(new Parameter("endDate", "End Date", Date.class));


        return dsd;
    }
}
