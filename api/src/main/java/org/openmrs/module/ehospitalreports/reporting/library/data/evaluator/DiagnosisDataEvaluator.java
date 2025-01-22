package org.openmrs.module.ehospitalreports.reporting.library.data.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.module.ehospitalreports.reporting.library.data.definition.DiagnosisDataDefinition;
import org.openmrs.module.reporting.data.person.EvaluatedPersonData;
import org.openmrs.module.reporting.data.person.definition.PersonDataDefinition;
import org.openmrs.module.reporting.data.person.evaluator.PersonDataEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.evaluation.querybuilder.SqlQueryBuilder;
import org.openmrs.module.reporting.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * Evaluates Diagnosis Data Definition
 */
@Handler(supports = DiagnosisDataDefinition.class, order = 50)
public class DiagnosisDataEvaluator implements PersonDataEvaluator {
	
	@Autowired
	private EvaluationService evaluationService;
	
	public EvaluatedPersonData evaluate(PersonDataDefinition definition, EvaluationContext context)
	        throws EvaluationException {
		EvaluatedPersonData c = new EvaluatedPersonData(definition, context);
		
		String qry = "SELECT client_id, " + "CASE "
		        + "    WHEN MID(MAX(CONCAT(encounter_datetime, impression_diagnosis)), 20) = 'Other' THEN "
		        + "        MID(MAX(CONCAT(encounter_datetime, other_diagnosis_specify)), 20) " + "    ELSE "
		        + "        MID(MAX(CONCAT(encounter_datetime, impression_diagnosis)), 20) " + "END AS diagnosis "
		        + "FROM ehospital_etl.ehospital_flat_encounter_findings_and_treatment_report "
		        + "WHERE date(encounter_datetime) <= date(:endDate) " + "GROUP BY client_id "
		        + "HAVING diagnosis IS NOT NULL " + "UNION " + "SELECT client_id, " + "CASE "
		        + "    WHEN MID(MAX(CONCAT(encounter_datetime, impression_diagnosis)), 20) = 'Other' THEN "
		        + "        MID(MAX(CONCAT(encounter_datetime, other_diagnosis_specify)), 20) " + "    ELSE "
		        + "        MID(MAX(CONCAT(encounter_datetime, impression_diagnosis)), 20) " + "END AS diagnosis "
		        + "FROM ehospital_etl.ehospital_flat_encounter_dental_report "
		        + "WHERE date(encounter_datetime) <= date(:endDate) " + "GROUP BY client_id "
		        + "HAVING diagnosis IS NOT NULL;";
		
		SqlQueryBuilder queryBuilder = new SqlQueryBuilder();
		queryBuilder.append(qry);
		Date startDate = (Date) context.getParameterValue("startDate");
		Date endDate = (Date) context.getParameterValue("endDate");
		queryBuilder.addParameter("endDate", endDate);
		queryBuilder.addParameter("startDate", startDate);
		
		Map<Integer, Object> data = evaluationService.evaluateToMap(queryBuilder, Integer.class, Object.class, context);
		c.setData(data);
		return c;
		
	}
}
