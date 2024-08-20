package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.Moh204DatasetDefinition;
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
public class SetupMOH204AReportRegister extends eHospitalDataExportManager {
	
	private final Moh204DatasetDefinition moh204DatasetDefinition;
	
	private final BaseCohortQueries baseCohortQueries;
	
	@Autowired
	public SetupMOH204AReportRegister(Moh204DatasetDefinition moh204DatasetDefinition, BaseCohortQueries baseCohortQueries) {
		this.moh204DatasetDefinition = moh204DatasetDefinition;
		this.baseCohortQueries = baseCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.MOH_204A_REGISTER_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.MOH_204A_REGISTER_REPORT_UUID;
	}
	
	@Override
	public String getName() {
		return "Outpatient Register: Under 5 years MOH 204A";
	}
	
	@Override
	public String getDescription() {
		return "Out Patient Report for Children: MOH 204 A";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(moh204DatasetDefinition.getParameters());
		rd.addDataSetDefinition("MOH204A",
		    Mapped.mapStraightThrough(moh204DatasetDefinition.constructMoh204ADatasetDefinition()));
		rd.setBaseCohortDefinition(EhospitalReportUtils.map(baseCohortQueries.getChildOpdPatients(),
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
			reportDesign = createXlsReportDesign(reportDefinition, "moh204a.xls",
			    "Outpatient Register: Under 5 years MOH 204A", getExcelDesignUuid(), null);
			
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:22,dataset:MOH204A");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Collections.singletonList(reportDesign);
	}
}
