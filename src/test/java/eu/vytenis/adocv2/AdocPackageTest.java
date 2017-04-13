package eu.vytenis.adocv2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AdocPackageTest {
	private AdocPackage adoc = new AdocPackage("file.txt");

	@Test
	public void createdPackageHasFileName() {
		assertEquals("file.txt", adoc.getMainFileName());
	}
}
