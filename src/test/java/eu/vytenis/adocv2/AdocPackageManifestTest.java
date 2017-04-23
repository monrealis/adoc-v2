package eu.vytenis.adocv2;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import eu.vytenis.adocv2.manifest.Manifests;

public class AdocPackageManifestTest {
	private AdocPackage adoc = new AdocPackage("file.pdf", new byte[] {});

	@Test
	public void containsRootEntry() {
		String manifest = getManifestAsString();
		assertThat(manifest, containsString("/ application/vnd.etsi.asic-e+zip"));
	}

	@Test
	public void containsDirectories() {
		String manifest = getManifestAsString();
		assertThat(manifest, containsString("META-INF/ "));
		assertThat(manifest, containsString("META-INF2/ "));
	}

	private String getManifestAsString() {
		String xml = adoc.getFileAsText("META-INF/manifest.xml");
		return Manifests.getManifestString(xml);
	}
}
