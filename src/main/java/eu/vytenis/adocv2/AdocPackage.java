package eu.vytenis.adocv2;

import java.util.HashMap;
import java.util.Map;

import eu.vytenis.adocv2.manifest.AdocManifest;
import eu.vytenis.adocv2.relations.AdocRelationships;

public class AdocPackage {
	private final String fileName;
	private final Map<String, AdocContent> files = new HashMap<String, AdocContent>();
	private final AdocManifest manifest = new AdocManifest();
	private final AdocRelationships relationships = new AdocRelationships();

	public AdocPackage(String fileName) {
		this.fileName = fileName;
		files.put(getMimeTypeFileName(), new AdocStringContent(getContentType()));
		files.put(getRelationsFileName(), relationships);
		files.put(getManifestFileName(), manifest);
		manifest.add("/", "application/vnd.etsi.asic-e+zip");
		relationships.add(fileName, KnownRelationshipType.Main);
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