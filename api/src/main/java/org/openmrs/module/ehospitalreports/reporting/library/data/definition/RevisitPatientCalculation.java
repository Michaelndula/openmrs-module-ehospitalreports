package org.openmrs.module.ehospitalreports.reporting.library.data.definition;

import org.openmrs.PatientIdentifierType;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.parameter.ParameterDefinitionSet;
import org.openmrs.calculation.patient.PatientCalculation;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.ListResult;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.ehospitalreports.calculation.library.CalculationUtils;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.reports.shared.SharedReportConstants;

import java.util.*;

public class RevisitPatientCalculation implements PatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues,
	        PatientCalculationContext context) {
		
		VisitsForPatientDataDefinition definition = new VisitsForPatientDataDefinition();
		CalculationResultMap data = CalculationUtils.evaluateWithReporting(definition, cohort, parameterValues, null,
		    context);
		CalculationResultMap ret = new CalculationResultMap();
		
		PatientIdentifierType opdNumber = Context.getPatientService().getPatientIdentifierTypeByUuid(
		    SharedReportConstants.UNIQUE_OPD_NUMBER);
		
		for (Integer ptid : cohort) {
			String opdNumnberValue = "";
			ListResult result = (ListResult) data.get(ptid);
			List<Visit> visits = CalculationUtils.extractResultValues(result);
			List<Visit> visitsWithinAyear = new ArrayList<Visit>();
			for (Visit visit : visits) {
				if (visit.getStartDatetime().compareTo(getAyearFromToday(context.getNow())) >= 0
				        && visit.getStartDatetime().compareTo(context.getNow()) <= 0) {
					visitsWithinAyear.add(visit);
				}
			}
			if (visitsWithinAyear.size() > 1
			        && Context.getPatientService().getPatient(ptid).getPatientIdentifier(opdNumber) != null) {
				opdNumnberValue = Context.getPatientService().getPatient(ptid).getPatientIdentifier(opdNumber)
				        .getIdentifier();
			}
			
			ret.put(ptid, new SimpleResult(opdNumnberValue, this));
		}
		
		return ret;
	}
	
	private Date getAyearFromToday(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.YEAR, -1);
		return calendar.getTime();
		
	}
	
	@Override
	public ParameterDefinitionSet getParameterDefinitionSet() {
		return null;
	}
}
