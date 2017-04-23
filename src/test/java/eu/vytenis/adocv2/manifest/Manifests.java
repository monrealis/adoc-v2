package eu.vytenis.adocv2.manifest;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXB;

import com.google.common.base.Joiner;

import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class Manifests {
	// Returns String representation for comparisons in tests
	public static String getManifestString(String manifestXml) {
		Manifest manifest = JAXB.unmarshal(new StringReader(manifestXml), Manifest.class);
		String manifestString = getManifestAsString(manifest.getFileEntry());
		return manifestString;
	}

	private static String getManifestAsString(List<FileEntry> entries) {
		List<String> lines = new FileEntryToString().fromList(entries).map();
		return Joiner.on("\n").join(lines);
	}
}
