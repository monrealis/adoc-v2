package eu.vytenis.adocv2;

import java.util.HashMap;
import java.util.Map;

import lt.archyvai.adoc._2008.relationships.RelationshipsType;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocPackage {
	private final String fileName;
	private final Map<String, String> files = new HashMap<String, String>();
	private final AdocMarshaller marshaller = new AdocMarshaller();

	public AdocPackage(String fileName) {
		this.fileName = fileName;
		files.put(getMimeTypeFileName(), getContentType());
		files.put(getRelationsFileName(), createRelationshipsXml());
		files.put(getManifestFileName(), createManifestXml());
	}

	private String createRelationshipsXml() {
		return marshaller.marshallToString(new RelationshipsType());
	}

	private String createManifestXml() {
		return marshaller.marshallToString(new Manifest());
	}

	public String getMainFileName() {
		return fileName;
	}

	public String getMetaDirectoryName() {
		return "META";
	}

	public String getManifestFileName() {
		return "META-INF/manifest.xml";
	}

	public String getRelationsFileName() {
		return "META-INF2/relations.xml";
	}

	public String getMimeTypeFileName() {
		return "mimetype";
	}

	public String getContentType() {
		return "application/vnd.etsi.asic-e+zip";
	}

	public String getFileAsText(String path) {
		String content = files.get(path);
		if (content == null)
			throw new FileNotFoundInPackageException(path);
		return content;
	}
}