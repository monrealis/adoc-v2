package eu.vytenis.adocv2.manifest;

import eu.vytenis.adocv2.mapping.Mapper;
import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;

public class FileEntryToString extends Mapper<FileEntry, String> {
	@Override
	public String map() {
		return String.format("%s %s", from.getFullPath(), from.getMediaType());
	}
}