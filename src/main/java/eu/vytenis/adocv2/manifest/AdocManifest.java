package eu.vytenis.adocv2.manifest;

import java.util.ArrayList;
import java.util.List;

import eu.vytenis.adocv2.AdocMarshaller;
import eu.vytenis.adocv2.content.AdocContent;
import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocManifest implements AdocContent {
	private final AdocMarshaller marshaller = new AdocMarshaller();
	private final List<FileEntry> fileEntries = new ArrayList<FileEntry>();

	public void add(String path, String contentType) {
		FileEntry entry = new FileEntry();
		entry.setFullPath("/");
		entry.setMediaType("application/vnd.etsi.asic-e+zip");
		fileEntries.add(entry);
		// TODO 2017-04-18 add directories
	}

	@Override
	public String getAsString() {
		Manifest manifest = createManifest();
		return marshaller.marshallToString(manifest);
	}

	private Manifest createManifest() {
		Manifest manifest = new Manifest();
		manifest.getFileEntry().addAll(fileEntries);
		return manifest;
	}
}
