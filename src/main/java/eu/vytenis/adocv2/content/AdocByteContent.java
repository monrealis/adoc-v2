package eu.vytenis.adocv2.content;

public class AdocByteContent implements AdocContent {
	private final byte[] content;

	public AdocByteContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String getAsString() {
		return new String(content);
	}
}
