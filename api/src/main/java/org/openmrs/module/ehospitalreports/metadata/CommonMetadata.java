package org.openmrs.module.ehospitalreports.metadata;

import org.openmrs.Form;
import org.openmrs.PersonAttributeType;
import org.openmrs.customdatatype.datatype.ConceptDatatype;
import org.openmrs.customdatatype.datatype.FreeTextDatatype;
import org.openmrs.module.coreapps.customdatatype.LocationDatatype;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.*;

public class CommonMetadata extends AbstractMetadataBundle {
	
	public static final class _EncounterType {
		
		public static final String CONSULTATION = "465a92f2-baf8-42e9-9612-53064be868e8";
		
		public static final String LAB_RESULTS = "17a381d1-7e29-406a-b782-aa903b963c28";
		
		public static final String REGISTRATION = "de1f9d67-b73e-4e1b-90d0-036166fc6995";
		
		public static final String TRIAGE = "d1059fb9-a079-4feb-a749-eedd709ae542";
	}
	
	public static final class _Form {
		
		public static final String BASIC_REGISTRATION = "add7abdc-59d1-11e8-9c2d-fa7ae01bbebc";
		
		public static final String DRUG_REGIMEN_EDITOR = "da687480-e197-11e8-9f32-f2801f1b9fd1";
	}
	
	public static final class _OrderType {
		
		public static final String DRUG = "131168f4-15f5-102d-96e4-000c29c2a5d7";
	}
	
	//	public static final class _PatientIdentifierType {
	//
	//		public static final String OLD_ID = Metadata.IdentifierType.OLD;
	//
	//		public static final String OPENMRS_ID = Metadata.IdentifierType.MEDICAL_RECORD_NUMBER;
	//
	//		public static final String OPD_NUMBER = Metadata.IdentifierType.OPD_NUMBER;
	//
	//		public static final String NATIONAL_ID = Metadata.IdentifierType.NATIONAL_ID;
	//	}
	
	public static final class _PersonAttributeType {
		
		public static final String NEXT_OF_KIN_ADDRESS = "7cf22bec-d90a-46ad-9f48-035952261294";
		
		public static final String NEXT_OF_KIN_CONTACT = "342a1d39-c541-4b29-8818-930916f4c2dc";
		
		public static final String NEXT_OF_KIN_NAME = "830bef6d-b01f-449d-9f8d-ac0fede8dbd3";
		
		public static final String NEXT_OF_KIN_RELATIONSHIP = "d0aa9fd1-2ac5-45d8-9c5e-4317c622c8f5";
		
		public static final String TELEPHONE_CONTACT = "b2c38640-2603-4629-aebd-3b54f33f1e3a";
		
		public static final String EMAIL_ADDRESS = "b8d0b331-1d2d-4a9a-b741-1816f498bdb6";
		
		public static final String ALTERNATE_PHONE_CONTACT = "94614350-84c8-41e0-ac29-86bc107069be";
		
		public static final String NEAREST_HEALTH_CENTER = "27573398-4651-4ce5-89d8-abec5998165c";
		
		public static final String GUARDIAN_FIRST_NAME = "8caf6d06-9070-49a5-b715-98b45e5d427b";
		
		public static final String GUARDIAN_LAST_NAME = "0803abbd-2be4-4091-80b3-80c6940303df";
	}
	
	public static final class _Provider {
		
		public static final String UNKNOWN = "ae01b8ff-a4cc-4012-bcf7-72359e852e14";
	}
	
	public static final class _ProviderAttributeType {
		
		public static final String PRIMARY_FACILITY = "5a53dddd-b382-4245-9bf1-03bce973f24b";
	}
	
	public static final class _RelationshipType {
		
		public static final String SPOUSE = "d6895098-5d8d-11e3-94ee-b35a4132a5e3";
		
		public static final String GUARDIAN_DEPENDANT = "5f115f62-68b7-11e3-94ee-6bef9086de92";
		
		public static final String PARTNER = "007b765f-6725-4ae9-afee-9966302bace4";
		
		public static final String CO_WIFE = "2ac0d501-eadc-4624-b982-563c70035d46";
		
		public static final String SNS = "76edc1fe-c5ce-4608-b326-c8ecd1020a73";
		
		public static final String CASE_MANAGER = "9065e3c6-b2f5-4f99-9cbf-f67fd9f82ec5";
		
		public static final String CARE_GIVER = "3667e52f-8653-40e1-b227-a7278d474020";
	}
	
	public static final class _VisitAttributeType {
		
		public static final String SOURCE_FORM = "8bfab185-6947-4958-b7ab-dfafae1a3e3d";
		
		public static final String VISIT_QUEUE_NUMBER = "c61ce16f-272a-41e7-9924-4c555d0932c5";
		
		public static final String PATIENT_TYPE_UUID = "3b9dfac8-9e4d-11ee-8c90-0242ac120002";
		
		public static final String PAYMENT_METHOD_UUID = "e6cb0c3b-04b0-4117-9bc6-ce24adbda802";
		
		public static final String POLICY_NUMBER = "0f4f3306-f01b-43c6-af5b-fdb60015cb02";
		
		public static final String INSURANCE_SCHEME = "2d0fa959-6780-41f1-85b1-402045935068";
		
		public static final String NON_PAYING_PATIENT_CATEGORY_UUID = "df0362f9-782e-4d92-8bb2-3112e9e9eb3c";
	}
	
	public static final class _VisitType {
		
		public static final String OUTPATIENT = "3371a4d4-f66f-4454-a86d-92c7b3da990c";
		
		public static final String INPATIENT = "a73e2ac6-263b-47fc-99fc-e0f2c09fc914";
	}
	
	/**
	 * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
	 */
	@Override
	public void install() {
		install(encounterType("Consultation", "Collection of clinical data during the main consultation",
		    _EncounterType.CONSULTATION));
		install(encounterType("Lab Results", "Collection of laboratory results", _EncounterType.LAB_RESULTS));
		install(encounterType("Registration", "Initial data collection for a patient, not specific to any program",
		    _EncounterType.REGISTRATION));
		install(encounterType("Triage", "Collection of limited data prior to a more thorough examination",
		    _EncounterType.TRIAGE));
		
		install(form("Registration Form", "Initial data collection for a patient/client, not specific to any program",
		    _EncounterType.REGISTRATION, "1", _Form.BASIC_REGISTRATION));
		
		install(globalProperty("order.drugDosingUnitsConceptUuid", "Drug dosing units concept",
		    "162384AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		install(globalProperty("client_number_label", "Label for Client Number", "Client Number"));
		install(globalProperty("clientNumber.enabled", "Switch to show client number", "false"));
		
		install(personAttributeType("Telephone contact", "Telephone contact number", String.class, null, true, 1.0,
		    _PersonAttributeType.TELEPHONE_CONTACT));
		install(personAttributeType("Email address", "Email address of person", String.class, null, false, 2.0,
		    _PersonAttributeType.EMAIL_ADDRESS));
		
		// Patient only person attributes..
		install(personAttributeType("Next of kin name", "Name of patient's next of kin", String.class, null, false, 4.0,
		    _PersonAttributeType.NEXT_OF_KIN_NAME));
		install(personAttributeType("Next of kin relationship", "Next of kin relationship to the patient", String.class,
		    null, false, 4.1, _PersonAttributeType.NEXT_OF_KIN_RELATIONSHIP));
		install(personAttributeType("Next of kin contact", "Telephone contact of patient's next of kin", String.class, null,
		    false, 4.2, _PersonAttributeType.NEXT_OF_KIN_CONTACT));
		install(personAttributeType("Next of kin address", "Address of patient's next of kin", String.class, null, false,
		    4.3, _PersonAttributeType.NEXT_OF_KIN_ADDRESS));
		install(personAttributeType("Alternate Phone Number", "Patient's alternate phone number", String.class, null, false,
		    4.3, _PersonAttributeType.ALTERNATE_PHONE_CONTACT));
		install(personAttributeType("Nearest Health Facility", "Patient's nearest Health Facility", String.class, null,
		    false, 4.3, _PersonAttributeType.NEAREST_HEALTH_CENTER));
		// guardian properties
		install(personAttributeType("Guardian First Name", "Guardian's first name", String.class, null, false, 4.3,
		    _PersonAttributeType.GUARDIAN_FIRST_NAME));
		install(personAttributeType("Guardian Last Name", "Guardian's last name", String.class, null, false, 4.3,
		    _PersonAttributeType.GUARDIAN_LAST_NAME));
		
		// Provider attribute types.
		install(providerAttributeType("Primary Facility", "Default facility for a provider", LocationDatatype.class, "", 0,
		    9999, _ProviderAttributeType.PRIMARY_FACILITY));
		
		install(relationshipType("Guardian", "Dependant", "One that guards, watches over, or protects",
		    _RelationshipType.GUARDIAN_DEPENDANT));
		install(relationshipType(
		    "Spouse",
		    "Spouse",
		    "A spouse is a partner in a marriage, civil union, domestic partnership or common-law marriage a male spouse is a husband and a female spouse is a wife",
		    _RelationshipType.SPOUSE));
		install(relationshipType("Partner", "Partner",
		    "Someone I had sex with for fun without commitment to a relationship", _RelationshipType.PARTNER));
		install(relationshipType("Co-wife", "Co-wife", "Female member spouse in a polygamist household",
		    _RelationshipType.CO_WIFE));
		install(relationshipType("SNS", "SNS", "Social Network Strategy", _RelationshipType.SNS));
		install(relationshipType("Case manager", "Case manager", "Case manager", _RelationshipType.CASE_MANAGER));
		install(relationshipType("Primary caregiver", "Primary caregiver", "Primary caregiver", _RelationshipType.CARE_GIVER));
		
		install(visitAttributeType("Visit queue number",
		    "The visit queue number assigned to a visit when they are added to the queue", FreeTextDatatype.class, null, 0,
		    1, _VisitAttributeType.VISIT_QUEUE_NUMBER));
		install(visitAttributeType("Patient Type", "To indicate whether the patient is paying for a service",
		    ConceptDatatype.class, null, 0, 1, _VisitAttributeType.PATIENT_TYPE_UUID));
		install(visitAttributeType("Payment Method", "The payment method used by the patient to settle payment",
		    ConceptDatatype.class, null, 0, 1, _VisitAttributeType.PAYMENT_METHOD_UUID));
		install(visitAttributeType("Policy Number", "The insurance policy number or member number", FreeTextDatatype.class,
		    null, 0, 1, _VisitAttributeType.POLICY_NUMBER));
		install(visitAttributeType("Insurance scheme",
		    "The insurance scheme the patient is using to settle payment for services e.g. NHIF, Old mutual.",
		    FreeTextDatatype.class, null, 0, 1, _VisitAttributeType.INSURANCE_SCHEME));
		install(visitAttributeType("Non-Paying patient category",
		    "If a patient isn't paying for service, The catergory the fall in.", ConceptDatatype.class, null, 0, 1,
		    _VisitAttributeType.NON_PAYING_PATIENT_CATEGORY_UUID));
		
		install(visitType("Outpatient", "Visit where the patient is not admitted to the hospital", _VisitType.OUTPATIENT));
		install(visitType("Inpatient", "Visit where the patient is admitted to the hospital", _VisitType.INPATIENT));
		uninstall(possible(PersonAttributeType.class, "73d34479-2f9e-4de3-a5e6-1f79a17459bb"), "Became patient identifier");
		
		//Retiring Lab results form
		uninstall(possible(Form.class, "7e603909-9ed5-4d0c-a688-26ecb05d8b6e"),
		    "Form deprecated with introduction of Lab orders");
	}
}
