package eu.vytenis.adocv2;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXB;

import org.junit.Test;

import com.google.common.base.Joiner;

import eu.vytenis.adocv2.mapping.Mapper;
import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocPackageManifestTest {
	private AdocPackage adoc = new AdocPackage("file.pdf", new byte[] {});

	@Test
	public void manifestIsAValidXml() {
		String manifest = getManifestAsString();
		assertThat(manifest, containsString("/ application/vnd.etsi.asic-e+zip"));
	}

	private String getManifestAsString() {
		List<String> lines = new ToString().fromList(getManifest().getFileEntry()).map();
		return Joiner.on("\n").join(lines);
	}

	private Manifest getManifest() {
		String xml = adoc.getFileAsText("META-INF/manifest.xml");
		Manifest manifest = JAXB.unmarshal(new StringReader(xml), Manifest.class);
		return manifest;
	}

	private class ToString extends Mapper<FileEntry, String> {
		@Override
		public String map() {
			return String.format("%s %s", from.getFullPath(), from.getMediaType());
		}
	}
}
