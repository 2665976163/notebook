> ## ElasticSearch Java IN Search

> 导入`pom`文件

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-elasticsearch</artifactId>
</dependency>
```

> 配置`application.yml`

```yaml
spring:
  data:
    elasticsearch:
      cluster-name: clusterName
      cluster-nodes: <IP>:<PORT>
      repositories:
        enabled: true
      properties:
        node:
          master: false
          name: master
```

> 编写索引类

```java
@Document(indexName = "myIndex", type = "index_info")
public class MyIndex {
    @Id
    private Integer id;
    private String name;
}
```

> 编写`Repository`

```java
@Repository
public interface MyRepository extends ElasticsearchRepository<MyIndex, Integer> {
}
```

> 编写`Java Search`

> 查询某个字段匹配多个值： `SELECT id, name FROM MyIndex WHERE field IN ('V1', 'V2')`

```java
BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
queryBuilder.should(QueryBuilders.matchQuery("field", Arrays.asList("V1","V2")).boost(1));
Iterable<Object> search = myRepository.search(customQq);
search.forEach(System.out::println);
```

---

> #### 通过 TransportClient 去 ES 查询

```java
BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

TermsQueryBuilder customQq = QueryBuilders.termsQuery("customQq", Arrays.asList("2845243653"));
query.must(customQq);

TermQueryBuilder fraudType = QueryBuilders.termQuery("fraudType", 0);
query.must(fraudType);

SearchResponse response = client
	.prepareSearch("IndexName")
    .setTypes("IndexType")
    .setQuery(queryBuilder)
    // .addAggregation(ValueCountAggregationBuilder)
    .setSize(1)
    .addSort("createTime", SortOrder.DESC)
    .execute().actionGet();

log.info("response: {}", response);
```

