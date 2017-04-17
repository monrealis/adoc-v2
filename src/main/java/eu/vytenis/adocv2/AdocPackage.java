package eu.vytenis.adocv2;

import java.util.HashMap;
import java.util.Map;

import eu.vytenis.adocv2.content.AdocByteContent;
import eu.vytenis.adocv2.content.AdocContent;
import eu.vytenis.adocv2.content.AdocStringContent;
import eu.vytenis.adocv2.manifest.AdocManifest;
import eu.vytenis.adocv2.relations.AdocRelationships;
import eu.vytenis.adocv2.relations.KnownRelationshipType;

public class AdocPackage {
	private final String mainFileName;
	private final Map<String, AdocContent> files = new HashMap<String, AdocContent>();
	private final AdocManifest manifest = new AdocManifest();
	private final AdocRelationships relationships = new AdocRelationships();

	public AdocPackage(String mainFileName, byte[] mainFileContent) {
		this.mainFileName = mainFileName;
		files.put(mainFileName, new AdocByteContent(mainFileContent));
		files.put(getMimeTypeFileName(), new AdocStringContent(getContentType()));
		files.put(getRelationsFileName(), relationships);
		files.put(getManifestFileName(), manifest);
		manifest.add("/", "application/vnd.etsi.asic-e+zip");
		relationships.add(mainFileName, KnownRelationshipType.Main);
	}

	public String getMainFileName() {
		return mainFileName;
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