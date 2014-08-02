package org.sharecontent.search;

import java.util.List;
import java.util.Optional;

import org.sharecontent.search.response.Result;

public interface SearchService {
	<T extends Result> List<T> search(final String type);

	<T extends Result> Optional<T> search(final String type, final String id);

	<T extends Result> List<T> search(final String query, final String... params);
}
