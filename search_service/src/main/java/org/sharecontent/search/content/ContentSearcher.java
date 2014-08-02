package org.sharecontent.search.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.sharecontent.search.SearchService;
import org.sharecontent.search.response.content.Content;

//TODO: no validation atm
@SuppressWarnings("unchecked")
public class ContentSearcher implements SearchService {

	private Client elasticSearchClient;
	private String index;

	public ContentSearcher(final String indeParam, final Client elasticSearchClientParam) {
		this.index = indeParam;
		this.elasticSearchClient = elasticSearchClientParam;
	}

	@Override
	public Optional<Content> search(String type, String id) {
		GetResponse response = elasticSearchClient.prepareGet(index, type, id).execute().actionGet();
		Optional<Content> document = Optional.empty();
		if (response.isExists()) {
			Content c = new Content(type, id);
			c.setTitle(response.getField("content_title").getValue().toString());
			c.setPublisherId(response.getField("publisher_id").getValue().toString());
		}
		return document;
	}

	@Override
	public List<Content> search(String type) {
		List<Content> docs = new ArrayList<Content>();
		SearchResponse response = elasticSearchClient.prepareSearch(index).setTypes(type)
		// .setQuery(QueryBuilders.fuzzyQuery("_type", type))
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).execute().actionGet();

		SearchHit[] hits = response.getHits().hits();
		for (SearchHit hit : hits) {
			if (!hit.isSourceEmpty()) {
				Map<String, Object> source = hit.getSource();
				Content c = new Content(type, String.valueOf(source.get("content_id")));
				c.setTitle(String.valueOf(source.get("content_title")));
				c.setPublisherId(String.valueOf(source.get("publisher_id")));
				docs.add(c);
			}
		}
		return docs;
	}

	@Override
	public List<Content> search(String query, String... params) {

		return null;
	}

}
