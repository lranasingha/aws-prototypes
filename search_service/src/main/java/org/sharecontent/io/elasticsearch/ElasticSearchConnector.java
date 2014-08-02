package org.sharecontent.io.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticSearchConnector {
	@SuppressWarnings("resource")
	public static Client tranportClient(final String host, final int port, final Settings settings) {
		return new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(host, port));
	}

	public static void closeClient(final Client client) {
		client.close();
	}
}
