package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.TPTCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.EligibleForTPTDatasetDefinition;
import org.openmrs.module.ehospitalreports.reporting.utils.eHospitalReportUtils;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.reports.shared.SharedReportConstants;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.templates.shared.SharedTemplatesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class SetupEligibleForTPTRegister extends eHospitalDataExportManager {
	
	private final EligibleForTPTDatasetDefinition eligibleForTPTDatasetDefinition;
	
	private final TPTCohortQueries tptCohortQueries;
	
	@Autowired
	public SetupEligibleForTPTRegister(EligibleForTPTDatasetDefinition eligibleForTPTDatasetDefinition,
	    TPTCohortQueries tptCohortQueries) {
		this.eligibleForTPTDatasetDefinition = eligibleForTPTDatasetDefinition;
		this.tptCohortQueries = tptCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.ELIGIBLE_FOR_TPT_LIST_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.ELIGIBLE_FOR_TPT_LIST_UUID;
	}
	
	@Override
	public String getName() {
		return "Line list for clients eligible started and completed TPT on Date";
	}
	
	@Override
	public String getDescription() {
		return "Line list for clients eligible, started and completed TPT";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(eligibleForTPTDatasetDefinition.getParameters());
		rd.addDataSetDefinition("ETPT",
		    Mapped.mapStraightThrough(eligibleForTPTDatasetDefinition.constructEligibleForTPTDatasetDefinition()));
		rd.setBaseCohortDefinition(eHospitalReportUtils.map(tptCohortQueries.getPatientsWhoAreEligibleForTPT(),
		    "startDate=${startDate},endDate=${endDate+23h}"));
		return rd;
	}
	
	@Override
	public String getVersion() {
		return "1.0-SNAPSHOT";
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		ReportDesign reportDesign = null;
		try {
			reportDesign = createXlsReportDesign(reportDefinition, "eligibleTPTRegister.xls",
			    "Report for clients eligible for TPT", getExcelDesignUuid(), null);
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:2,dataset:ETPT");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
