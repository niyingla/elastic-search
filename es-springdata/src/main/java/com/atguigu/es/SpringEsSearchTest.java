package com.atguigu.es;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEsSearchTest {
    @Autowired
    private ProductDao productDao;
    /**
     * 类似jpa 操作
     */
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Test
    public void termQuery(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("titel", "小米");
        Iterable<Product> productIterable = productDao.search(termQueryBuilder);
        for (Product product : productIterable) {
            System.out.println(product);
        }

        //构建分页
        Pageable pageable = PageRequest.of(0, 999);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
                //QueryBuilders.termQuery("titel", "小米")
                //.should(termQueryBuilder)
                //.must(new MatchQueryBuilder("title", "很帅"));
        NativeSearchQuery query = builder.withQuery(boolQueryBuilder)
                .withPageable(pageable)
                .build();


        SearchHits<Product> searchHits = elasticsearchRestTemplate.search(query, Product.class);
        Stream<SearchHit<Product>> hitStream = searchHits.get();
        List<Product> collect = hitStream.map(SearchHit::getContent).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void termPageQuery(){

        Sort orders = Sort.by(Sort.Direction.DESC, "id");
        int curr = 0;
        int pageSize = 5;
        PageRequest pageRequest = PageRequest.of(curr, pageSize, orders);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("titel", "小米");
        Iterable<Product> productIterable = productDao.search(termQueryBuilder, pageRequest);
        for (Product product : productIterable) {
            System.out.println(product);
        }
    }
}
