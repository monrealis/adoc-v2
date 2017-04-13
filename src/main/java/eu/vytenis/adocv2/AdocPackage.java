package eu.vytenis.adocv2;

public class AdocPackage {
	private final String fileName;

	public AdocPackage(String fileName) {
		this.fileName = fileName;
	}

	public String getMainFileName() {
		return fileName;
	}
}