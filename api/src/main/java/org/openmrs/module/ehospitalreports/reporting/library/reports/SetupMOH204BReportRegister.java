package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.Moh204BDatasetDefinition;
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
public class SetupMOH204BReportRegister extends eHospitalDataExportManager {
	
	private final Moh204BDatasetDefinition moh204BDatasetDefinition;
	
	private final BaseCohortQueries baseCohortQueries;
	
	@Autowired
	public SetupMOH204BReportRegister(Moh204BDatasetDefinition moh204BDatasetDefinition, BaseCohortQueries baseCohortQueries) {
		this.moh204BDatasetDefinition = moh204BDatasetDefinition;
		this.baseCohortQueries = baseCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.MOH_204B_REGISTER_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.MOH_204B_REGISTER_REPORT_UUID;
	}
	
	@Override
	public String getName() {
		return "Outpatient Register: Over 5 years MOH 204B";
	}
	
	@Override
	public String getDescription() {
		return "Out Patient Report for Adults: MOH 204B";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(moh204BDatasetDefinition.getParameters());
		rd.addDataSetDefinition("MOH204B",
		    Mapped.mapStraightThrough(moh204BDatasetDefinition.constructMoh204BDatasetDefinition()));
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
			reportDesign = createXlsReportDesign(reportDefinition, "moh204b.xls",
			    "Outpatient Register: Over 5 years MOH 204B", getExcelDesignUuid(), null);
			
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:22,dataset:MOH204B");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Collections.singletonList(reportDesign);
	}
}
