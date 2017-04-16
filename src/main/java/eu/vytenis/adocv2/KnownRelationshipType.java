package eu.vytenis.adocv2;

public enum KnownRelationshipType {
	Main("http://www.archyvai.lt/adoc/2008/relationships/content/main"), //
	Appendix("http://www.archyvai.lt/adoc/2008/relationships/content/appendix"), //
	Attachment("http://www.archyvai.lt/adoc/2008/relationships/content/attachment"), //
	Signable("http://www.archyvai.lt/adoc/2008/relationships/metadata/signable"), //
	Unsignable("http://www.archyvai.lt/adoc/2008/relationships/metadata/unsignable"), //
	Signatures("http://www.archyvai.lt/adoc/2008/relationships/signatures"), //
	Thumbnail("http://www.archyvai.lt/adoc/2008/relationships/thumbnail");
	private final String uri;

	private KnownRelationshipType(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}
}
