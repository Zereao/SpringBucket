package com.zereao.es.service;

import com.alibaba.fastjson.JSONObject;
import com.zereao.es.pojo.po.Book;
import com.zereao.es.pojo.vo.BookAddVO;
import com.zereao.es.pojo.vo.BookUpdateVO;
import com.zereao.es.pojo.vo.BoolQueryVO;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author Zereao
 * @version 2019/05/14 15:09
 */
@Service
public class BookService {
    @Resource
    private RestHighLevelClient client;

    public Book findBookById(String id) {
        GetRequest request = new GetRequest("book", id);
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            Map<String, Object> resultMap = response.getSource();
            JSONObject resultJson = new JSONObject(resultMap);
            Book book = resultJson.toJavaObject(Book.class);
            book.setId(response.getId());
            return book;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBook(BookAddVO vo) {
        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("type", vo.getType())
                    .field("word_count", vo.getWordCount())
                    .field("author", vo.getAuthor())
                    .field("title", vo.getTitle())
                    .timeField("publish_date", vo.getPublishDate())
                    .endObject();
            IndexRequest request = new IndexRequest("book").source(content);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            DeleteRequest request = new DeleteRequest("book").id("PUPvtWoBd2QFj63oPVsT");
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(BookUpdateVO vo) {
        try {
            UpdateRequest request = new UpdateRequest("book", vo.getId());
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("type", vo.getType())
                    .field("word_count", vo.getWordCount())
                    .field("author", vo.getAuthor())
                    .field("title", vo.getTitle())
                    .timeField("publish_date", vo.getPublishDate())
                    .endObject();
            request.doc(content);
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String boolQuery(BoolQueryVO vo) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(vo.getAuthor())) {
            boolQuery.must(QueryBuilders.matchQuery("author", vo.getAuthor()));
        }
        if (!StringUtils.isEmpty(vo.getTitle())) {
            boolQuery.must(QueryBuilders.matchQuery("title", vo.getTitle()));
        }
        if (vo.getGtWordCount() != null && vo.getLtWordCount() != null) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count")
                    .from(vo.getGtWordCount()).to(vo.getLtWordCount());
            boolQuery.filter(rangeQuery);
        }
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
