package org.openmrs.module.ehospitalreports.reporting.library.data.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.module.ehospitalreports.reporting.library.data.definition.BMIDataDefinition;
import org.openmrs.module.ehospitalreports.reporting.library.data.definition.SupplementationDataDefinition;
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
 * Evaluates Supplementation Data Definition
 */
@Handler(supports = SupplementationDataDefinition.class, order = 50)
public class SupplementationDataEvaluator implements PersonDataEvaluator {
	
	@Autowired
	private EvaluationService evaluationService;
	
	public EvaluatedPersonData evaluate(PersonDataDefinition definition, EvaluationContext context)
	        throws EvaluationException {
		EvaluatedPersonData c = new EvaluatedPersonData(definition, context);
		
		String qry = "SELECT client_id, MID(MAX(CONCAT(encounter_datetime, nutrition_diatetics)), 20) as nutrition_diatetics FROM ehospital_etl.ehospital_flat_encounter_findings_and_treatment_report "
		        + " WHERE  date(encounter_datetime) <= date(:endDate) GROUP BY client_id HAVING nutrition_diatetics is not null;";
		
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
