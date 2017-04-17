package eu.vytenis.adocv2.manifest;

import eu.vytenis.adocv2.AdocMarshaller;
import eu.vytenis.adocv2.content.AdocContent;
import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocManifest implements AdocContent {
	private final AdocMarshaller marshaller = new AdocMarshaller();
	private final Manifest manifest = new Manifest();

	public void add(String path, String contentType) {
		FileEntry fe = new FileEntry();
		fe.setFullPath("/");
		fe.setMediaType("application/vnd.etsi.asic-e+zip");
		manifest.getFileEntry().add(fe);
	}

	@Override
	public String getAsString() {
		return marshaller.marshallToString(manifest);
	}
}
