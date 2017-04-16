package eu.vytenis.adocv2;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.junit.Test;

import lt.archyvai.adoc._2008.relationships.RelationshipsType;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocPackageTest {
	private AdocPackage adoc = new AdocPackage("file.pdf");

	@Test
	public void minimalFileStructure() {
		assertEquals("file.pdf", adoc.getMainFileName());
		assertEquals("META", adoc.getMetaDirectoryName());
		assertEquals("META-INF/manifest.xml", adoc.getManifestFileName());
		assertEquals("META-INF2/relations.xml", adoc.getRelationsFileName());
		assertEquals("mimetype", adoc.getMimeTypeFileName());
	}

	@Test
	public void hasAsicContentType() {
		assertEquals("application/vnd.etsi.asic-e+zip", adoc.getContentType());
	}

	@Test
	public void getsFileAsText() {
		assertEquals("application/vnd.etsi.asic-e+zip", adoc.getFileAsText("mimetype"));
	}

	@Test(expected = FileNotFoundInPackageException.class)
	public void throwsExceptionIfFileNotFound() {
		adoc.getFileAsText("notExisting");
	}

	@Test
	public void relationsIsAnXmlFromRequiredNamespace() {
		String xml = adoc.getFileAsText("META-INF2/relations.xml");
		JAXB.unmarshal(new StringReader(xml), RelationshipsType.class);
	}
	
	@Test
	public void manifestIsAnXmlFromRequiredNamespace() {
		String xml = adoc.getFileAsText("META-INF/manifest.xml");
		JAXB.unmarshal(new StringReader(xml), Manifest.class);
	}
}
