package org.sharecontent.search.response.user;

import org.sharecontent.search.response.Result;

public final class User implements Result {
	private String id;
	private String type;
	private String name;
	private String email;

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
