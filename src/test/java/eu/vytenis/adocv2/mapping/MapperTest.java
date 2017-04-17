package eu.vytenis.adocv2.mapping;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MapperTest {
	private CountLetters countLetters = new CountLetters();

	@Test
	public void mapsOne() {
		assertEquals(2, countLetters.from("AB").map().intValue());
	}

	@Test
	public void mapsMany() {
		assertEquals(asList(2, 0), countLetters.fromList(asList("AA", "")).map());
	}

	private class CountLetters extends Mapper<String, Integer> {
		@Override
		public Integer map() {
			return from.length();
		}
	}
}
