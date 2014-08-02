package org.sharecontent.search;

import org.elasticsearch.client.Client;
import org.sharecontent.search.content.ContentSearcher;
import org.sharecontent.search.users.UserSearcher;

public class Searcher {
	private String index;
	private Client client;

	private Searcher(final Client clientParam) {
		this.client = clientParam;
	}

	public static Searcher newBuilder(final Client clientParam) {
		return new Searcher(clientParam);
	}
	public enum SearcherType {
		Content, User
	}

	public Searcher link(final String indexParam) {
		this.index = indexParam;
		return this;
	}

	public SearchService andCreate(final SearcherType type) {
		switch (type) {
		case Content:
			return new ContentSearcher(index, client);
		case User:
			return new UserSearcher(index, client);
		default:
			throw new IllegalArgumentException("no matching searcher");

		}
	}
}
