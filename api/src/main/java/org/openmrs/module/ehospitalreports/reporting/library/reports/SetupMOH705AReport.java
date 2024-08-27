package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.Moh204BDatasetDefinition;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.Moh705aDatasetDefinition;
import org.openmrs.module.ehospitalreports.reporting.utils.EhospitalReportUtils;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.reports.shared.SharedReportConstants;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.templates.shared.SharedTemplatesConstants;
import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class SetupMOH705AReport extends eHospitalDataExportManager {

	private final Moh705aDatasetDefinition moh705aDatasetDefinition;

	private final BaseCohortQueries baseCohortQueries;

	@Autowired
	public SetupMOH705AReport(Moh705aDatasetDefinition moh705aDatasetDefinition, BaseCohortQueries baseCohortQueries) {
        this.moh705aDatasetDefinition = moh705aDatasetDefinition;
		this.baseCohortQueries = baseCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.MOH_705A_SUMMARY_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.MOH_705A_SUMMARY_REPORT_UUID;
	}
	
	@Override
	public String getName() {
		return "MOH 705A: UNDER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET";
	}
	
	@Override
	public String getDescription() {
		return "UNDER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET: MOH 705A";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(moh705aDatasetDefinition.getParameters());
		rd.addDataSetDefinition("MOH705A",
		    Mapped.mapStraightThrough(moh705aDatasetDefinition.constructMoh705ADatasetDefinition()));
		rd.setBaseCohortDefinition(EhospitalReportUtils.map(baseCohortQueries.getAdultOpdPatients(),
		    "startDate=${startDate},endDate=${endDate+23h}"));
		return rd;
	}
	
	@Override
	public String getVersion() {
		return "1.0.0-SNAPSHOT";
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		ReportDesign reportDesign = null;
		try {
			reportDesign = createXlsReportDesign(reportDefinition, "moh705a.xls",
			    "MOH 705A: UNDER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET", getExcelDesignUuid(), null);
			
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:5,dataset:MOH705A");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Collections.singletonList(reportDesign);
	}
}
