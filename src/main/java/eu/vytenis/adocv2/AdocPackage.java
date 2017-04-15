package eu.vytenis.adocv2;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXB;

import lt.archyvai.adoc._2008.relationships.RelationshipsType;

public class AdocPackage {
	private final String fileName;
	private final Map<String, String> files = new HashMap<String, String>();

	public AdocPackage(String fileName) {
		this.fileName = fileName;
		files.put(getMimeTypeFileName(), getContentType());
		files.put(getRelationsFileName(), createRelationshipsXml());
	}

	private String createRelationshipsXml() {
		StringWriter w = new StringWriter();
		JAXB.marshal(new RelationshipsType(), w);
		String s = w.toString();
		return s;
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