package eu.vytenis.adocv2;

public class AdocPackage {
	private final String fileName;

	public AdocPackage(String fileName) {
		this.fileName = fileName;
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
}