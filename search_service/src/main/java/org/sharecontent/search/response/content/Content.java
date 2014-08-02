package org.sharecontent.search.response.content;

import org.sharecontent.search.response.Result;

public final class Content implements Result {
	private String id;
	private String type;
	private String title;
	private String publisherId;

	public Content(String typeParam, String idParam) {
		this.id = idParam;
		this.type = typeParam;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Content [id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", publisherId="
				+ this.publisherId + "]";
	}

}
