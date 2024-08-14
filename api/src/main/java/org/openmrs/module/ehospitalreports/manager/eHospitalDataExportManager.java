package org.openmrs.module.ehospitalreports.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehospitalreports.api.eHospitalReportsService;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;
import org.openmrs.util.OpenmrsClassLoader;

/** Excel Data Export Manager for eHospital reports */
public abstract class eHospitalDataExportManager extends eHospitalReportManager {
	
	/** @return the uuid for the report design for exporting to Excel */
	public abstract String getExcelDesignUuid();
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		List<ReportDesign> l = new ArrayList<ReportDesign>();
		ReportDesign excelDesign = ReportManagerUtil.createExcelDesign(getExcelDesignUuid(), reportDefinition);
		l.add(excelDesign);
		return l;
	}
	
	/**
	 * Auto generated method comment
	 * 
	 * @param reportDefinition the reportDesign to set
	 * @param resourceName
	 * @param reportDesignName
	 * @param properties
	 * @return
	 * @throws IOException
	 */
	public ReportDesign createXlsReportDesign(ReportDefinition reportDefinition, String resourceName,
	        String reportDesignName, String excelDesignUuid, Map<? extends Object, ? extends Object> properties)
	        throws IOException {
		
		eHospitalReportsService eHospitalReportsService = Context.getRegisteredComponent(
		    "ehospital.eHospitalReportsService", eHospitalReportsService.class);
		if (StringUtils.isNotBlank(excelDesignUuid)) {
			eHospitalReportsService.purgeReportDesignIfExists(excelDesignUuid);
		}
		
		ReportDesignResource resource = new ReportDesignResource();
		resource.setName(resourceName);
		resource.setExtension("xls");
		InputStream is = OpenmrsClassLoader.getInstance().getResourceAsStream(resourceName);
		resource.setContents(IOUtils.toByteArray(is));
		final ReportDesign design = new ReportDesign();
		design.setName(reportDesignName);
		design.setReportDefinition(reportDefinition);
		design.setRendererType(ExcelTemplateRenderer.class);
		design.addResource(resource);
		if (properties != null) {
			design.getProperties().putAll(properties);
		}
		if (excelDesignUuid != null && excelDesignUuid.length() > 1) {
			design.setUuid(excelDesignUuid);
		}
		resource.setReportDesign(design);
		
		return design;
	}
}
