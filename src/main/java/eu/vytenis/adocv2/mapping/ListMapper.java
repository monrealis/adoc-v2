package eu.vytenis.adocv2.mapping;

import java.util.ArrayList;
import java.util.List;

class ListMapper<F, T> extends Mapper<List<F>, List<T>> {
	private final Mapper<F, T> mapper;

	public ListMapper(Mapper<F, T> mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<T> map() {
		List<T> r = new ArrayList<T>();
		for (F f : from)
			r.add(mapper.from(f).map());
		return r;
	}
}
