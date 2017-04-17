package eu.vytenis.adocv2;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.junit.Test;

import eu.vytenis.adocv2.relations.KnownRelationshipType;
import lt.archyvai.adoc._2008.relationships.RelationshipType;
import lt.archyvai.adoc._2008.relationships.RelationshipsType;
import lt.archyvai.adoc._2008.relationships.SourcePartType;

public class AdocPackageRelationsTest {
	private AdocPackage adoc = new AdocPackage("file.pdf");

	@Test
	public void relationsIsAValidXml() {
		RelationshipsType relationships = getRelationships();
		SourcePartType sourcePart = relationships.getSourcePart().get(0);
		RelationshipType relationship = sourcePart.getRelationship().get(0);
		assertEquals("/", sourcePart.getFullPath());
		assertEquals("file.pdf", relationship.getFullPath());
		assertEquals(KnownRelationshipType.Main.getUri(), relationship.getType());
	}

	private RelationshipsType getRelationships() {
		String xml = adoc.getFileAsText("META-INF2/relations.xml");
		RelationshipsType relationships = JAXB.unmarshal(new StringReader(xml), RelationshipsType.class);
		return relationships;
	}
}
