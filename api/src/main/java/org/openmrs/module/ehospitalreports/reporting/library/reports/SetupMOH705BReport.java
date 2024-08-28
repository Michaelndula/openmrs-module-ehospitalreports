package org.openmrs.module.ehospitalreports.reporting.library.reports;

import org.openmrs.module.ehospitalreports.manager.eHospitalDataExportManager;
import org.openmrs.module.ehospitalreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ehospitalreports.reporting.library.datasets.Moh705bDatasetDefinition;
import org.openmrs.module.ehospitalreports.reporting.utils.EhospitalReportUtils;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.reports.shared.SharedReportConstants;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.templates.shared.SharedTemplatesConstants;
import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SetupMOH705BReport extends eHospitalDataExportManager {
    private final Moh705bDatasetDefinition moh705bDatasetDefinition;

    private final BaseCohortQueries baseCohortQueries;

    @Autowired
    public SetupMOH705BReport(Moh705bDatasetDefinition moh705bDatasetDefinition, BaseCohortQueries baseCohortQueries) {
        this.moh705bDatasetDefinition = moh705bDatasetDefinition;
        this.baseCohortQueries = baseCohortQueries;
    }

    @Override
    public String getExcelDesignUuid() {
        return SharedTemplatesConstants.MOH_705B_SUMMARY_TEMPLATE;
    }

    @Override
    public String getUuid() {
        return SharedReportConstants.MOH_705B_SUMMARY_REPORT_UUID;
    }

    @Override
    public String getName() {
        return "MOH 705B: OVER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET";
    }

    @Override
    public String getDescription() {
        return "OVER 5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET: MOH 705B";
    }

    @Override
    public ReportDefinition constructReportDefinition() {
        ReportDefinition rd = new ReportDefinition();
        rd.setUuid(getUuid());
        rd.setName(getName());
        rd.setDescription(getDescription());
        rd.addParameters(moh705bDatasetDefinition.getParameters());
        rd.addDataSetDefinition("MOH705B",
                Mapped.mapStraightThrough(moh705bDatasetDefinition.constructMoh705BDatasetDefinition()));
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
            reportDesign = createXlsReportDesign(reportDefinition, "moh705b.xls",
                    "MOH 705B: OVER  5 YEARS DAILY OUTPATIENT MORBIDITY SUMMARY SHEET", getExcelDesignUuid(), null);

            Properties props = new Properties();
            props.put("repeatingSections", "sheet:1,row:5,dataset:MOH705B");
            props.put("sortWeight", "5000");
            reportDesign.setProperties(props);
        }
        catch (IOException e) {
            throw new ReportingException(e.toString());
        }

        return Collections.singletonList(reportDesign);
    }
}
