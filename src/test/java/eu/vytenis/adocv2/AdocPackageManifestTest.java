package eu.vytenis.adocv2;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.junit.Test;

import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocPackageManifestTest {
	private AdocPackage adoc = new AdocPackage("file.pdf");

	@Test
	public void manifestIsAValidXml() {
		Manifest manifest = getManifest();
		FileEntry file = manifest.getFileEntry().get(0);
		assertEquals("/", file.getFullPath());
		assertEquals("application/vnd.etsi.asic-e+zip", file.getMediaType());
	}

	private Manifest getManifest() {
		String xml = adoc.getFileAsText("META-INF/manifest.xml");
		Manifest manifest = JAXB.unmarshal(new StringReader(xml), Manifest.class);
		return manifest;
	}
}
