package org.sharecontent.search.users;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.client.Client;
import org.sharecontent.search.SearchService;
import org.sharecontent.search.response.user.User;

@SuppressWarnings("unchecked")
public final class UserSearcher implements SearchService {

	public UserSearcher(String index, Client client) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<User> search(String type, String id) {
		return null;
	}

	@Override
	public List<User> search(String query) {
		return null;
	}

	@Override
	public List<User> search(String query, String... params) {
		return null;
	}

}
