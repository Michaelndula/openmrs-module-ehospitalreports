package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.ContactsCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.SetupContactsWithUnknownHivStatusDatasetDefinition;
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
public class SetupContactsWithUnknownHivStatusRegister extends eHospitalDataExportManager {
	
	private final SetupContactsWithUnknownHivStatusDatasetDefinition setupContactsWithUnknownHivStatusDatasetDefinition;
	
	private final ContactsCohortQueries contactsCohortQueries;
	
	@Autowired
	public SetupContactsWithUnknownHivStatusRegister(
	    SetupContactsWithUnknownHivStatusDatasetDefinition setupContactsWithUnknownHivStatusDatasetDefinition,
	    ContactsCohortQueries contactsCohortQueries) {
		this.setupContactsWithUnknownHivStatusDatasetDefinition = setupContactsWithUnknownHivStatusDatasetDefinition;
		this.contactsCohortQueries = contactsCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.CONTACTS_WITH_UNKNOWN_HIV_STATUS_LIST_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.CONTACTS_WITH_UNKNOWN_HIV_STATUS_LIST_UUID;
	}
	
	@Override
	public String getName() {
		return "List the family members whose HIV result is not known at the time of listing";
	}
	
	@Override
	public String getDescription() {
		return "List the family members whose HIV result is not known at the time of listing";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(setupContactsWithUnknownHivStatusDatasetDefinition.getParameters());
		rd.addDataSetDefinition("CUHS", Mapped.mapStraightThrough(setupContactsWithUnknownHivStatusDatasetDefinition
		        .constructContactsWithUnknownHivStatusDatasetDefinition()));
		rd.setBaseCohortDefinition(eHospitalReportUtils.map(contactsCohortQueries.getPatientsWhoHaveUnknownHivStatus(),
		    "startDate=${startDate},endDate=${endDate}"));
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
			reportDesign = createXlsReportDesign(reportDefinition, "contactsUnknownHivStatusRegister.xls",
			    "List the family members whose HIV result is not known at the time of listing", getExcelDesignUuid(), null);
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:2,dataset:CUHS");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
