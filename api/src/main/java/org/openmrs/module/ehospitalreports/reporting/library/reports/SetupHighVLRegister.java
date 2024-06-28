package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.HighVLDatasetDefinition;
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
public class SetupHighVLRegister extends eHospitalDataExportManager {
	
	private final HighVLDatasetDefinition highVLDatasetDefinition;
	
	private final BaseCohortQueries baseCohortQueries;
	
	@Autowired
	public SetupHighVLRegister(HighVLDatasetDefinition highVLDatasetDefinition, BaseCohortQueries baseCohortQueries) {
		this.highVLDatasetDefinition = highVLDatasetDefinition;
		this.baseCohortQueries = baseCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.HIGH_VL_LIST_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.HIGH_VL_LIST_UUID;
	}
	
	@Override
	public String getName() {
		return "Line list of clients with high viral load EAC sessions and Repeat viral load";
	}
	
	@Override
	public String getDescription() {
		return "Line list of clients with high viral load, EAC sessions and Repeat viral load";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(highVLDatasetDefinition.getParameters());
		rd.addDataSetDefinition("HVL", Mapped.mapStraightThrough(highVLDatasetDefinition.constructHighVLDatasetDefinition()));
		rd.setBaseCohortDefinition(eHospitalReportUtils.map(baseCohortQueries.getPatientsWhoHaveHighVL(),
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
			reportDesign = createXlsReportDesign(reportDefinition, "hvlRegister.xls", "Report for listing High VL clients",
			    getExcelDesignUuid(), null);
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:4,dataset:HVL");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
