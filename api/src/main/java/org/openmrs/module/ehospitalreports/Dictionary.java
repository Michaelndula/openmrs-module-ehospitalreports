package org.openmrs.module.ehospitalreports;

import org.openmrs.Concept;
import org.openmrs.ConceptNumeric;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.MissingMetadataException;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	/**
	 * Gets a concept by an identifier (mapping or UUID)
	 * 
	 * @param identifier the identifier
	 * @return the concept
	 * @throws org.openmrs.module.metadatadeploy.MissingMetadataException if the concept could not
	 *             be found
	 */
	public static Concept getConcept(String identifier) {
		Concept concept;
		
		if (identifier.contains(":")) {
			String[] tokens = identifier.split(":");
			concept = Context.getConceptService().getConceptByMapping(tokens[1].trim(), tokens[0].trim());
		} else {
			// Assume it's a UUID
			concept = Context.getConceptService().getConceptByUuid(identifier);
		}
		
		if (concept == null) {
			throw new MissingMetadataException(Concept.class, identifier);
		}
		
		// getConcept doesn't always return ConceptNumeric for numeric concepts
		if (concept.getDatatype().isNumeric() && !(concept instanceof ConceptNumeric)) {
			concept = Context.getConceptService().getConceptNumeric(concept.getId());
			
			if (concept == null) {
				throw new MissingMetadataException(ConceptNumeric.class, identifier);
			}
		}
		
		return concept;
	}
	
	/**
	 * Convenience method to fetch a list of concepts
	 * 
	 * @param identifiers the concept identifiers
	 * @return the concepts
	 * @throws org.openmrs.module.metadatadeploy.MissingMetadataException if a concept could not be
	 *             found
	 */
	public static List<Concept> getConcepts(String... identifiers) {
		List<Concept> concepts = new ArrayList<Concept>();
		for (String identifier : identifiers) {
			concepts.add(getConcept(identifier));
		}
		return concepts;
	}
}
