package eu.vytenis.adocv2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
