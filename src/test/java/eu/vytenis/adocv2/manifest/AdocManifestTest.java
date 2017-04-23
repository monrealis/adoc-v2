package eu.vytenis.adocv2.manifest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class AdocManifestTest {
	private Set<String> fileNames = new TreeSet<String>();

	@Test
	public void containsRootEntry() {
		assertThat(getString(), containsString("/ application/vnd.etsi.asic-e+zip"));
	}

	@Test
	public void containsDirectoriesOfAllLevels() {
		fileNames.add("a/b");
		fileNames.add("c/d/e");
		fileNames.add("c/d/e/f");
		assertThat(getString(), containsString("a/ "));
		assertThat(getString(), containsString("c/ "));
		assertThat(getString(), containsString("c/d/e/ "));
	}

	private String getString() {
		AdocManifest adocManifest = new AdocManifest(fileNames);
		String manifestXml = adocManifest.getAsString();
		String manifestString = Manifests.getManifestString(manifestXml);
		return manifestString;
	}
}
