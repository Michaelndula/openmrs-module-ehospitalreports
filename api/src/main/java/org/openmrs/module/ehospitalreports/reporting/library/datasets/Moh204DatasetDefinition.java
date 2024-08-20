package org.openmrs.module.ehospitalreports.reporting.library.datasets;

import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehospitalreports.reporting.calculation.WardAddressCalculation;
import org.openmrs.module.ehospitalreports.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.ehospitalreports.reporting.data.converter.EncounterDateConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.converter.BooleanToYesNoConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.converter.PersonAttributeDataConverter;
import org.openmrs.module.ehospitalreports.reporting.library.data.definition.CalculationDataDefinition;
import org.openmrs.module.ehospitalreports.reporting.library.data.definition.RevisitPatientCalculation;
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
public class Moh204DatasetDefinition extends eHospitalBaseDataSet {
	
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
		
		dsd.addColumn("id", new PatientIdDataDefinition(), "");
		dsd.addColumn("Date", CommonDatasetDefinition.getEncounterDate(),
		    "onOrAfter=${startDate},onOrBefore=${endDate+23h}", new EncounterDateConverter());
		dsd.addColumn("OPD No.", identifierDef, (String) null);
		dsd.addColumn("Revisit", getRevisit(), (String) null);
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Age", new AgeDataDefinition(), "", null);
		dsd.addColumn("Gender", new GenderDataDefinition(), "", null);
		dsd.addColumn("Ward", personWardAddress(), "", new CalculationResultConverter());
		dsd.addColumn("Telephone", new PersonAttributeDataDefinition("Phone Number", phoneNumber), "",
		    new PersonAttributeDataConverter());
		dsd.addColumn(
		    "Weight",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.WEIGHT_KG)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Height",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.HEIGHT_CM)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn("BMI",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(SharedReportConstants.BMI)),
		    "onOrAfter=${startDate},onOrBefore=${endDate+23h}", new ObsValueConverter());
		dsd.addColumn(
		    "Supplementation",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.SUPPLEMENTATION)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Visual Acuity right eye",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.VISUAL_ACUITY_RIGHT_EYE)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Visual Acuity left eye",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.VISUAL_ACUITY_LEFT_EYE)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Diagnosis",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.DIAGNOSIS)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Treatment",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.TREATMENT)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Outcome",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.OUTCOME)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}", new ObsValueConverter());
		dsd.addColumn(
		    "Referred From",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.REFFERED_FROM)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "Referred To",
		    CommonDatasetDefinition.getObservation(Context.getConceptService().getConceptByUuid(
		        SharedReportConstants.REFFERED_TO)), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		
		return dsd;
	}
	
	private DataDefinition getRevisit() {
		CalculationDataDefinition cd = new CalculationDataDefinition("Revisit", new RevisitPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return new ConvertedPatientDataDefinition(cd, new BooleanToYesNoConverter());
	}
	
}
