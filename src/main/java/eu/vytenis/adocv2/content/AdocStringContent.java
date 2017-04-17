package eu.vytenis.adocv2.content;

public class AdocStringContent implements AdocContent {
	private final String content;

	public AdocStringContent(String content) {
		this.content = content;
	}

	@Override
	public String getAsString() {
		return content;
	}
}
