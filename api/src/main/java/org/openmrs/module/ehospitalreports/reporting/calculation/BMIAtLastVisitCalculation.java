package org.openmrs.module.ehospitalreports.reporting.calculation;

import org.openmrs.Obs;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.ehospitalreports.Dictionary;
import org.openmrs.module.ehospitalreports.calculation.library.Calculations;
import org.openmrs.module.ehospitalreports.calculation.library.EmrCalculationUtils;
import org.openmrs.module.ehospitalreports.reporting.utils.constants.reports.shared.SharedReportConstants;
import org.openmrs.module.metadatadeploy.MetadataUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class BMIAtLastVisitCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues,
	        PatientCalculationContext context) {
		
		CalculationResultMap weightMap = Calculations.lastObs(Dictionary.getConcept(SharedReportConstants.WEIGHT_KG),
		    cohort, context);
		CalculationResultMap heightMap = Calculations.lastObs(Dictionary.getConcept(SharedReportConstants.HEIGHT_CM),
		    cohort, context);
		
		PersonService service = Context.getPersonService();
		
		CalculationResultMap ret = new CalculationResultMap();
		for (Integer ptId : cohort) {
			
			Double visitWeight = null;
			Double visitHeight = null;
			String bmiStr = null;
			
			Obs lastWeightObs = EmrCalculationUtils.obsResultForPatient(weightMap, ptId);
			Obs lastHeightObs = EmrCalculationUtils.obsResultForPatient(heightMap, ptId);
			
			if (lastHeightObs != null && lastWeightObs != null && service.getPerson(ptId).getAge() > 12) {
				visitHeight = lastHeightObs.getValueNumeric();
				visitWeight = lastWeightObs.getValueNumeric();
				Double bmi = visitWeight / ((visitHeight / 100) * (visitHeight / 100));
				bmiStr = String.format("%.2f", bmi);
				ret.put(ptId, new SimpleResult(bmiStr, this, context));
			}
		}
		
		return ret;
	}
}
