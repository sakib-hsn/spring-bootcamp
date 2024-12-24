package com.codemaster.io.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.codemaster.io.models.User;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;

public class SearchManager {
    public static void main(String[] args) throws IOException {
        RestClient restClient = RestClient
                .builder(new HttpHost("localhost", 9200, "http"))
                .build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);

        User user1 = User.builder()
                .id(1)
                .name("John Smith Doe")
                .email("john@gmail.com")
                .age(30)
                .build();

        User user2 = User.builder()
                .id(2)
                .name("Alex Doe")
                .email("alex@gmail.com")
                .age(15)
                .build();

        IndexResponse response1 = client.index(indexRequestBuilder -> indexRequestBuilder
                .index("person")
                .id(user1.getId().toString())
                .document(user1));

        System.out.println("response = " + response1);

        IndexResponse response2 = client.index(indexRequestBuilder -> indexRequestBuilder
                .index("person")
                .id(user2.getId().toString())
                .document(user2));

        System.out.println("response = " + response2);

        String searchText = "doe";
        String from = "10";
        String end = "30";
        SearchResponse<User> searchResponse = client.search(searchIndexBuilder -> searchIndexBuilder
                        .index("person")
                        .query(queryBuilder -> queryBuilder
                                .match(matchBuilder -> matchBuilder
                                        .field("name")
                                        .boost(2.0F)
                                        .query(searchText)))
                        .query(queryBuilder -> queryBuilder
                                .range(rangeBuilder -> rangeBuilder
                                        .field("age")
                                        .from(from).to(end))),
                User.class);

        List<Hit<User>> hits = searchResponse.hits().hits();
        System.out.println("hits = " + hits);
    }
}
