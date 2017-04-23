package eu.vytenis.adocv2.manifest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Test;

public class AdocManifestTest {
	private Set<String> fileNames = new TreeSet<String>();

	@Ignore
	@Test
	public void containsRootEntry() {
		AdocManifest adocManifest = new AdocManifest(fileNames);
		String manifestXml = adocManifest.getAsString();
		String manifestString = Manifests.getManifestString(manifestXml);
		assertThat(manifestString, containsString("/ application/vnd.etsi.asic-e+zip"));
	}
}
