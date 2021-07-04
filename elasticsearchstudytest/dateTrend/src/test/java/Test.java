import cn.lichenghao.config.ElasticsearchConfig;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
import org.joda.time.DateTimeZone;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsearchConfig.class})
public class Test {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @org.junit.Test
    public void test1() {

        // 统计字段
        String[] field = {"create_time"};
        // 查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("create_time").gte("2020-07-01T00:00:00.000+08:00"));
        // 时间间隔
        DateHistogramInterval dateHistogramInterval = DateHistogramInterval.HOUR;
        // 指定时间范围,配合minDocCount(0),可以返回该时间范围的空桶
        ExtendedBounds extendedBounds =
                new ExtendedBounds("2021-07-02 00:00:00", "2021-07-02 23:59:59");
        // 桶配置
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
                AggregationBuilders.dateHistogram(field[0] + "_Agg")
                        .field(field[0])
                        .timeZone(DateTimeZone.getDefault())
                        .dateHistogramInterval(dateHistogramInterval)
                        .minDocCount(0)
                        .extendedBounds(extendedBounds)
                        .format("yyyy-MM-dd HH:mm:ss");
        // 查询条件
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("soc_alarm_info")
                .withTypes("base")
                .withQuery(boolQueryBuilder)
                .addAggregation(dateHistogramAggregationBuilder)
                .build();
        // 聚合结果
        Aggregations trendStats = elasticsearchTemplate.query(searchQuery, SearchResponse::getAggregations);
        // 整理聚合结果
        InternalDateHistogram dateHistogram = trendStats.get("create_time_Agg");
        List<InternalDateHistogram.Bucket> buckets = dateHistogram.getBuckets();
        for (InternalDateHistogram.Bucket bucket : buckets) {
            String keyAsString = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println(keyAsString + "-" + docCount);
        }
    }
}
