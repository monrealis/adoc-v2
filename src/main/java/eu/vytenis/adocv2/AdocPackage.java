package eu.vytenis.adocv2;

import java.util.HashMap;
import java.util.Map;

public class AdocPackage {
	private final String fileName;
	private final Map<String, String> files = new HashMap<String, String>();

	public AdocPackage(String fileName) {
		this.fileName = fileName;
		files.put(getMimeTypeFileName(), getContentType());
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
			throw new FileNotFoundInPackageException();
		return content;
	}
}