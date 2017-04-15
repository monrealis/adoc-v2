package eu.vytenis.adocv2;

public class FileNotFoundInPackageException extends RuntimeException {
	private static final long serialVersionUID = 1;
	private final String path;

	public FileNotFoundInPackageException(String path) {
		super(path);
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}