package org.openmrs.module.ehospitalreports.reporting.library.datasets;

import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehospitalreports.reporting.calculation.WardAddressCalculation;
import org.openmrs.module.ehospitalreports.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.ehospitalreports.reporting.data.converter.EncounterDateConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.converter.AgeFormatterConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.converter.BooleanToYesNoConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.converter.PersonAttributeDataConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.definition.*;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.reports.shared.SharedReportConstants;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.*;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh204ADatasetDefinition extends eHospitalBaseDataSet {
	
	private DataDefinition personWardAddress() {
		CalculationDataDefinition cd = new CalculationDataDefinition("Ward", new WardAddressCalculation());
		return cd;
	}
	
	public DataSetDefinition constructMoh204ADatasetDefinition() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("MOH204A");
		dsd.addParameters(getParameters());
		dsd.setDescription("Out Patient Report for Children");
		dsd.addSortCriteria("Psn", SortCriteria.SortDirection.ASC);
		dsd.addParameter(new Parameter("location", "Location", Location.class));
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		DataConverter nameFormatter = new ObjectFormatter("{familyName} {givenName} {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		
		PatientIdentifierType opdNumber = Context.getPatientService().getPatientIdentifierTypeByUuid(
		    SharedReportConstants.UNIQUE_OPD_NUMBER);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        opdNumber.getName(), opdNumber), identifierFormatter);
		
		PersonAttributeType phoneNumber = Context.getPersonService().getPersonAttributeTypeByUuid(
		    SharedReportConstants.PHONE_NUMBER_ATTRIBUTE_TYPE_UUID);
		
		WeightDataDefinition weightDataDefinition = new WeightDataDefinition();
		weightDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		HeightDataDefinition heightDataDefinition = new HeightDataDefinition();
		heightDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		BMIDataDefinition bmiDataDefinition = new BMIDataDefinition();
		bmiDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		SupplementationDataDefinition supplementationDataDefinition = new SupplementationDataDefinition();
		supplementationDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		VisualAcuityRightEyeDataDefinition visualAcuityRightEyeDataDefinition = new VisualAcuityRightEyeDataDefinition();
		visualAcuityRightEyeDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		VisualAcuityLeftEyeDataDefinition visualAcuityLeftEyeDataDefinition = new VisualAcuityLeftEyeDataDefinition();
		visualAcuityLeftEyeDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		DiagnosisDataDefinition diagnosisDataDefinition = new DiagnosisDataDefinition();
		diagnosisDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		TreatmentDataDefinition treatmentDataDefinition = new TreatmentDataDefinition();
		treatmentDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		OutcomeDataDefinition outcomeDataDefinition = new OutcomeDataDefinition();
		outcomeDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		ReferredFromDataDefinition referredFromDataDefinition = new ReferredFromDataDefinition();
		referredFromDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		ReferredToDataDefinition referredToDataDefinition = new ReferredToDataDefinition();
		referredToDataDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		dsd.addColumn("id", new PatientIdDataDefinition(), "");
		dsd.addColumn("Date", CommonDatasetDefinition.getEncounterDate(),
		    "onOrAfter=${startDate},onOrBefore=${endDate+23h}", new EncounterDateConverter());
		dsd.addColumn("OPD No.", identifierDef, (String) null);
		dsd.addColumn("Revisit", getRevisit(), (String) null);
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Age", new AgeDataDefinition(), "", new AgeFormatterConverter());
		dsd.addColumn("Gender", new GenderDataDefinition(), "", null);
		dsd.addColumn("Ward", personWardAddress(), "", new CalculationResultConverter());
		dsd.addColumn("Telephone", new PersonAttributeDataDefinition("Phone Number", phoneNumber), "",
		    new PersonAttributeDataConverter());
		dsd.addColumn("Weight", weightDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Height", heightDataDefinition, "endDate=${endDate}");
		dsd.addColumn("BMI", bmiDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Supplementation", supplementationDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Visual Acuity right eye", visualAcuityRightEyeDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Visual Acuity left eye", visualAcuityLeftEyeDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Diagnosis", diagnosisDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Treatment", treatmentDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Outcome", outcomeDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Referred From", referredFromDataDefinition, "endDate=${endDate}");
		dsd.addColumn("Referred To", referredToDataDefinition, "endDate=${endDate}");
		
		return dsd;
	}
	
	private DataDefinition getRevisit() {
		CalculationDataDefinition cd = new CalculationDataDefinition("Revisit", new RevisitPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return new ConvertedPatientDataDefinition(cd, new BooleanToYesNoConverter());
	}
	
}
