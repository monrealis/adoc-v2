package eu.vytenis.adocv2.relations;

import eu.vytenis.adocv2.AdocContent;
import eu.vytenis.adocv2.AdocMarshaller;
import eu.vytenis.adocv2.KnownRelationshipType;
import lt.archyvai.adoc._2008.relationships.RelationshipType;
import lt.archyvai.adoc._2008.relationships.RelationshipsType;
import lt.archyvai.adoc._2008.relationships.SourcePartType;

public class AdocRelationships implements AdocContent {
	private final AdocMarshaller marshaller = new AdocMarshaller();
	private final RelationshipsType relationships = new RelationshipsType();

	@Override
	public String getAsString() {
		return marshaller.marshallToString(relationships);
	}

	public void add(String fileName, KnownRelationshipType type) {
		SourcePartType source = new SourcePartType();
		source.setFullPath("/");
		relationships.getSourcePart().add(source);
		source.getRelationship().add(createRelationship(fileName, type));
	}

	private RelationshipType createRelationship(String fileName, KnownRelationshipType type) {
		RelationshipType r = new RelationshipType();
		r.setType(type.getUri());
		r.setFullPath(fileName);
		return r;
	}
}
