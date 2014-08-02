package org.sharecontent;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.sharecontent.io.elasticsearch.ElasticSearchConnector;
import org.sharecontent.search.SearchService;
import org.sharecontent.search.Searcher;
import org.sharecontent.search.Searcher.SearcherType;

public class DemoSearch {
	public static void main(String[] args) {
		Client client = ElasticSearchConnector.tranportClient("localhost", 9300,
				ImmutableSettings.builder().put("cluster.name", "share_content_search")
				.build());
		
		SearchService searcher = Searcher.newBuilder(client).link("contents").andCreate(SearcherType.Content);
		System.out.println("all pdfs --> " + searcher.search("pdf"));
		System.out.println("all videos --> " + searcher.search("video"));
		System.out.println("all audios --> " + searcher.search("audio"));
	}
}
