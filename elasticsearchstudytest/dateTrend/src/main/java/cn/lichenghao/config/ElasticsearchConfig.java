package cn.lichenghao.config;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;


/**
 * @author lichenghao
 */
@Configuration
public class ElasticsearchConfig {

    private static TransportClient client;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(initClient());
    }

    public Client initClient() {
        try {
            Collection<Class<? extends Plugin>> plugins = new ArrayList<>();
            Settings.Builder settingBuilder = Settings.builder().put("cluster.name", "elasticsearch");
            client = new PreBuiltTransportClient(settingBuilder.build(), plugins);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.16.127.2"), Integer.parseInt("9300")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
