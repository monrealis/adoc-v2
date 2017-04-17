package eu.vytenis.adocv2;

import java.util.HashMap;
import java.util.Map;

import lt.archyvai.adoc._2008.relationships.RelationshipType;
import lt.archyvai.adoc._2008.relationships.RelationshipsType;
import lt.archyvai.adoc._2008.relationships.SourcePartType;
import oasis.names.tc.opendocument.xmlns.manifest._1.FileEntry;
import oasis.names.tc.opendocument.xmlns.manifest._1.Manifest;

public class AdocPackage {
	private final String fileName;
	private final Map<String, AdocContent> files = new HashMap<String, AdocContent>();
	private final AdocMarshaller marshaller = new AdocMarshaller();

	public AdocPackage(String fileName) {
		this.fileName = fileName;
		files.put(getMimeTypeFileName(), new AdocStringContent(getContentType()));
		files.put(getRelationsFileName(), new AdocStringContent(createRelationshipsXml(fileName)));
		files.put(getManifestFileName(), new AdocStringContent(createManifestXml()));
	}

	private String createRelationshipsXml(String mainFileName) {
		RelationshipsType r = new RelationshipsType();
		SourcePartType sourcePart = new SourcePartType();
		RelationshipType relationship = new RelationshipType();
		relationship.setFullPath(mainFileName);
		relationship.setType(KnownRelationshipType.Main.getUri());
		sourcePart.setFullPath("/");
		sourcePart.getRelationship().add(relationship);
		r.getSourcePart().add(sourcePart);
		return marshaller.marshallToString(r);
	}

	private String createManifestXml() {
		Manifest manifest = new Manifest();
		FileEntry fe = new FileEntry();
		fe.setFullPath("/");
		fe.setMediaType("application/vnd.etsi.asic-e+zip");
		manifest.getFileEntry().add(fe);
		return marshaller.marshallToString(manifest);
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
		AdocContent content = files.get(path);
		if (content == null)
			throw new FileNotFoundInPackageException(path);
		return content.getAsString();
	}
}