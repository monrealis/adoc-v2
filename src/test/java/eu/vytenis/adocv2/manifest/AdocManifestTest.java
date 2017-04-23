package eu.vytenis.adocv2.manifest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.StringReader;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXB;

import org.junit.Ignore;
import org.junit.Test;

import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocManifestTest {
	private Set<String> fileNames = new TreeSet<String>();

	@Ignore
	@Test
	public void containsRootEntry() {
		AdocManifest adocManifest = new AdocManifest(fileNames);
		String manifestXml = adocManifest.getAsString();
		Manifest manifest = JAXB.unmarshal(new StringReader(manifestXml), Manifest.class);
		String manifestString = Manifests.getManifestAsString(manifest.getFileEntry());
		assertThat(manifestString, containsString("/ application/vnd.etsi.asic-e+zip"));
	}

}
