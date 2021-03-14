package com.lich.es;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocsTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testCreateDocWithoutId() throws ExecutionException, InterruptedException {
        Client client = elasticsearchTemplate.getClient();
        Map<String, Object> doc = new HashMap<>();
        doc.put("title", "撒哈拉的故事");
        doc.put("description", "撒哈拉的故事");
        doc.put("author", "三毛");
        doc.put("release_date", "1976-05-01");
        IndexRequest indexRequest = new IndexRequest("book").type("popular").source(doc);
        IndexResponse indexResponse = client.index(indexRequest).get();
        System.out.println(indexResponse.getResult());
    }

    @Test
    public void testCreateDocWithId() throws ExecutionException, InterruptedException {
        Client client = elasticsearchTemplate.getClient();
        Map<String, Object> doc = new HashMap<>();
        doc.put("title", "撒哈拉的故事");
        doc.put("description", "撒哈拉的故事");
        doc.put("author", "三毛");
        doc.put("release_date", "1976-05-01");
        IndexRequest indexRequest = new IndexRequest("book").type("popular").id("1").source(doc);
        IndexResponse indexResponse = client.index(indexRequest).get();
        System.out.println(indexResponse.getResult());
    }
}
