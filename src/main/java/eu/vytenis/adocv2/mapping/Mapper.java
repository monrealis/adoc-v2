package eu.vytenis.adocv2.mapping;

import java.util.List;

public abstract class Mapper<F, T> {
	protected F from;

	public Mapper<F, T> from(F from) {
		this.from = from;
		return this;
	}

	public Mapper<List<F>, List<T>> fromList(List<F> from) {
		return new ListMapper<F, T>(this).from(from);
	}

	public abstract T map();
}
