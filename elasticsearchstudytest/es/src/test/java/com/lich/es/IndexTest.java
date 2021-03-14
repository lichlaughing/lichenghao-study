package com.lich.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTest {

    private static final String NUMBER_OF_SHARDS = "number_of_shards";
    private static final String NUMBER_OF_REPLICAS = "number_of_replicas";
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 使用client创建索引
     */
    @Test
    public void testAddIndex() throws IOException, ExecutionException, InterruptedException {
        Client client = elasticsearchTemplate.getClient();
        Settings.Builder settings = Settings.builder()
                .put(NUMBER_OF_SHARDS, 5)
                .put(NUMBER_OF_REPLICAS, 1);
        // 构建JSON对象
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("title").field("type", "text").endObject()
                .startObject("author").field("type", "keyword").endObject()
                .startObject("price").field("type", "double").endObject()
                .startObject("release_date").field("type", "date").field("format", "yyyy-MM-dd").endObject()
                .endObject()
                .endObject();
        // 创建新建索引的请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("book")
                .settings(settings).mapping("popular", mappings);
        // 创建索引得到异步的结果
        CreateIndexResponse createIndexResponse =
                client.admin().indices().create(createIndexRequest).get();
        System.out.println("创建索引结果：" + createIndexResponse.isAcknowledged());
    }

    @Test
    public void testDeleteIndex() throws ExecutionException, InterruptedException {
        Client client = elasticsearchTemplate.getClient();
        DeleteIndexRequest article = new DeleteIndexRequest("book");
        DeleteIndexResponse deleteIndexResponse = client.admin().indices().delete(article).get();
        System.out.println("删除索引结果：" + deleteIndexResponse.isAcknowledged());
    }
}
