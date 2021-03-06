package eu.vytenis.adocv2.manifest;

import static java.util.Collections.unmodifiableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import eu.vytenis.adocv2.AdocMarshaller;
import eu.vytenis.adocv2.content.AdocContent;
import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocManifest implements AdocContent {
	private final AdocMarshaller marshaller = new AdocMarshaller();
	private final List<FileEntry> fileEntries = new ArrayList<FileEntry>();
	private final Set<String> fileNames;

	public AdocManifest(Set<String> fileNames) {
		this.fileNames = unmodifiableSet(fileNames);
		add("/", "application/vnd.etsi.asic-e+zip");
	}

	@Override
	public String getAsString() {
		Manifest manifest = createManifest();
		return marshaller.marshallToString(manifest);
	}

	private Manifest createManifest() {
		Manifest manifest = new Manifest();
		manifest.getFileEntry().addAll(fileEntries);
		manifest.getFileEntry().addAll(getEntriesForDirectories());
		return manifest;
	}

	private List<FileEntry> getEntriesForDirectories() {
		List<FileEntry> entries = new ArrayList<FileEntry>();
		for (String directory : getDirectories())
			entries.add(createEntry(directory, ""));
		return entries;
	}

	private SortedSet<String> getDirectories() {
		SortedSet<String> directories = new TreeSet<String>();
		for (String fileName : fileNames)
			if (fileName.contains("/"))
				directories.addAll(getParentsOfAllLevels(fileName));
		// TODO 2017-04-23 sort paths
		return directories;
	}

	private List<String> getParentsOfAllLevels(String fileName) {
		List<String> parents = new ArrayList<String>();
		String[] parts = fileName.split("/");
		String parent = "";
		for (int i = 0; i < parts.length - 1; ++i)
			parents.add(parent += parts[i] + "/");
		return parents;
	}

	private void add(String path, String contentType) {
		fileEntries.add(createEntry("/", "application/vnd.etsi.asic-e+zip"));
	}

	private FileEntry createEntry(String path, String contentType) {
		FileEntry entry = new FileEntry();
		entry.setFullPath(path);
		entry.setMediaType(contentType);
		return entry;
	}
}
