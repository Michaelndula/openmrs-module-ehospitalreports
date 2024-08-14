package org.openmrs.module.ehospitalreports.reporting.library.queries;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CommonQueries {
	
	public static String getPatientsInProgram(int programId) {
		
		String query = "SELECT p.patient_id FROM patient p "
		        + "INNER JOIN patient_program pp ON p.patient_id = pp.patient_id "
		        + "INNER JOIN program pg ON pg.program_id=pp.program_id "
		        + "WHERE p.voided=0 AND pp.voided=0 and pg.retired=0 "
		        + "AND pp.date_enrolled BETWEEN :startDate AND :endDate " + "AND pg.program_id=" + programId;
		
		return query;
	}
	
	public static String getBasePatientsBasedOnEncounter(List<Integer> encounterIds) {
		String inputs = StringUtils.join(encounterIds, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " WHERE p.voided=0 AND e.voided=0 AND e.encounter_type IN(" + inputs + ")"
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate";
		return query;
	}
	
	public static String getValueNumericValueCountsBasedOnQuestion(int question) {
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided=0 AND o.voided=0 AND o.concept_id=" + question
		        + " AND o.value_numeric IS NOT NULL " + " AND e.encounter_datetime BETWEEN :startDate AND :endDate ";
		return query;
	}
	
	public static String hasObs(Integer encounterId, List<Integer> question, List<Integer> ans) {
		String request = StringUtils.join(question, ',');
		String response = StringUtils.join(ans, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_type=" + encounterId
		        + " AND p.voided=0 AND e.voided=0 AND o.voided =0 "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate " + " AND o.concept_id IN(" + request + ")"
		        + " AND o.value_coded IN(" + response + ")";
		
		return query;
	}
	
	public static String hasObs(List<Integer> encounterIds, List<Integer> question, List<Integer> ans) {
		String request = StringUtils.join(question, ',');
		String response = StringUtils.join(ans, ',');
		String types = StringUtils.join(encounterIds, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_type IN(" + types + ")"
		        + " AND p.voided=0 AND e.voided=0 AND o.voided =0 "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate " + " AND o.concept_id IN(" + request + ")"
		        + " AND o.value_coded IN(" + response + ")";
		
		return query;
	}
	
	public static String hasObs(List<Integer> question, List<Integer> ans) {
		String request = StringUtils.join(question, ',');
		String response = StringUtils.join(ans, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE "
		        + " p.voided=0 AND e.voided=0 AND o.voided =0 "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate " + " AND o.concept_id IN(" + request + ")"
		        + " AND o.value_coded IN(" + response + ")";
		
		return query;
	}
	
	public static String hasObsIntersectedFromSameObsGroup(int encounterTypeId, List<Integer> question1, List<Integer> ans1,
	        List<Integer> question2, List<Integer> ans2) {
		String request1 = StringUtils.join(question1, ',');
		String response1 = StringUtils.join(ans1, ',');
		String request2 = StringUtils.join(question2, ',');
		String response2 = StringUtils.join(ans2, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " INNER JOIN obs oo ON o.obs_group_id=oo.obs_group_id " + " WHERE "
		        + " p.voided=0 AND e.voided=0 AND o.voided =0 "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate " + " AND e.encounter_type=" + encounterTypeId
		        + " AND o.concept_id IN(" + request1 + ")" + " AND o.value_coded IN(" + response1 + ") "
		        + " AND oo.concept_id IN(" + request2 + ")" + " AND oo.value_coded IN(" + response2 + ")";
		
		return query;
	}
	
	public static String getPatientsInProgramWithNullOutcomes(int programId) {
		
		String query = "SELECT p.patient_id " + "FROM patient p "
		        + " INNER JOIN patient_program pp ON p.patient_id = pp.patient_id "
		        + " INNER JOIN program pg ON pg.program_id=pp.program_id "
		        + " WHERE p.voided=0 AND pp.voided=0 and pg.retired=0 "
		        + " AND pp.date_enrolled BETWEEN :startDate AND :endDate " + " AND pg.program_id=" + programId
		        + " AND pp.outcome_concept_id IS NULL ";
		
		return query;
	}
	
	public static String getPatientsInProgramWithOutcomes(int programId, int outcomeConceptId) {
		
		String query = "SELECT p.patient_id " + "FROM patient p "
		        + " INNER JOIN patient_program pp ON p.patient_id = pp.patient_id "
		        + " INNER JOIN program pg ON pg.program_id=pp.program_id "
		        + " WHERE p.voided=0 AND pp.voided=0 and pg.retired=0 "
		        + " AND pp.date_enrolled BETWEEN :startDate AND :endDate " + " AND pg.program_id=" + programId
		        + " AND pp.outcome_concept_id=" + outcomeConceptId;
		
		return query;
	}
	
	public static String getMissedAppointmentPatientSetByDays(int days) {
		String query = "SELECT patient_id, MAX(start_date_time) AS latest_appointment FROM patient_appointment "
		        + " WHERE voided = 0 AND status = 'Missed' AND start_date_time BETWEEN :startDate AND :endDate "
		        + " GROUP BY patient_id, start_date_time HAVING DATEDIFF(CURDATE(), MAX(start_date_time)) >" + days;
		return query;
	}
	
	public static String hasObsByEndDate(List<Integer> question, List<Integer> ans) {
		String request = StringUtils.join(question, ',');
		String response = StringUtils.join(ans, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE "
		        + " p.voided=0 AND e.voided=0 AND o.voided =0 " + " AND e.encounter_datetime <=:endDate "
		        + " AND o.concept_id IN(" + request + ")" + " AND o.value_coded IN(" + response + ")";
		
		return query;
	}
	
	public static String getPatientsEverInProgramWithOutcomes(int programId, int outcomeConceptId) {
		
		String query = "SELECT p.patient_id " + "FROM patient p "
		        + " INNER JOIN patient_program pp ON p.patient_id = pp.patient_id "
		        + " INNER JOIN program pg ON pg.program_id=pp.program_id "
		        + " WHERE p.voided=0 AND pp.voided=0 and pg.retired=0 " + " AND pp.date_enrolled <= :endDate "
		        + " AND pg.program_id=" + programId + " AND pp.outcome_concept_id=" + outcomeConceptId;
		
		return query;
	}
	
	public static String getPatientsInProgramByEndOfReportingPeriod(int programId) {
		
		String query = "SELECT p.patient_id " + "FROM patient p "
		        + "INNER JOIN patient_program pp ON p.patient_id = pp.patient_id "
		        + "INNER JOIN program pg ON pg.program_id=pp.program_id "
		        + "WHERE p.voided=0 AND pp.voided=0 and pg.retired=0 " + "AND pp.date_enrolled <= :endDate "
		        + "AND pg.program_id=" + programId;
		
		return query;
	}
	
	public static String hasObs(List<Integer> question) {
		String request = StringUtils.join(question, ',');
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE "
		        + " p.voided=0 AND e.voided=0 AND o.voided =0 "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate " + " AND o.concept_id IN(" + request + ")";
		
		return query;
	}
	
	public static String hasAnyEncounter() {
		String query = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id " + " WHERE "
		        + " p.voided=0 AND e.voided=0 " + " AND e.encounter_datetime BETWEEN :startDate AND :endDate ";
		return query;
	}
	
	public static String getPatientsWithAppointments() {
		String query = "select t.patient_id from (select patient_id, appointment_service_id from openmrs.patient_appointment fp "
		        + " where fp.status = 'Scheduled' or fp.status = 'Missed' and fp.start_date_time BETWEEN :startDate AND :endDate"
		        + " group by patient_id, appointment_service_id) t";
		
		return query;
	}

	public static String getChildOpdVisits() {
		String query = "select t.patient_id from (select patient_id, appointment_service_id from openmrs.patient_appointment fp "
				+ " where fp.status = 'Scheduled' or fp.status = 'Missed' and fp.start_date_time BETWEEN :startDate AND :endDate"
				+ " group by patient_id, appointment_service_id) t";

		return query;
	}
}
