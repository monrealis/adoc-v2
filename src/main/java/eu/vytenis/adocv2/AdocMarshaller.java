package eu.vytenis.adocv2;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

public class AdocMarshaller {
	public String marshallToString(Object jaxbObject) {
		StringWriter w = new StringWriter();
		JAXB.marshal(jaxbObject, w);
		String s = w.toString();
		return s;
	}
}
