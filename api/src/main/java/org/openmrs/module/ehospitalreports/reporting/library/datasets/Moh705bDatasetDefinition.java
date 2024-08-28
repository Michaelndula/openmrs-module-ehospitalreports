package org.openmrs.module.ehospitalreports.reporting.library.datasets;

import org.openmrs.Location;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;

import java.util.Date;

public class Moh705bDatasetDefinition extends eHospitalBaseDataSet {
    public DataSetDefinition constructMoh705BDatasetDefinition() {
        PatientDataSetDefinition dsd = new PatientDataSetDefinition();
        dsd.setName("MOH705B");
        dsd.addParameters(getParameters());
        dsd.setDescription("OVER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET: MOH 705B");
        dsd.addSortCriteria("Psn", SortCriteria.SortDirection.ASC);
        dsd.addParameter(new Parameter("location", "Location", Location.class));
        dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        dsd.addParameter(new Parameter("endDate", "End Date", Date.class));


        return dsd;
    }
}
