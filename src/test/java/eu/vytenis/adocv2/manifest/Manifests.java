package eu.vytenis.adocv2.manifest;

import java.util.List;

import com.google.common.base.Joiner;

import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;

public class Manifests {
	public static String getManifestAsString(List<FileEntry> entries) {
		List<String> lines = new FileEntryToString().fromList(entries).map();
		return Joiner.on("\n").join(lines);
	}
}
