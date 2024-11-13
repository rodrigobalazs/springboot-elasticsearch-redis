## Spring Boot Elasticsearch and Redis
This repository contains an Spring Boot Store Application which also implements Elasticsearch and Redis.<br/>
The app provides a REST API related to Products.<br/>
The goal is to show usage of Elasticsearch and Redis via Spring Boot =><br/>
1)Elasticsearch will be used as the primary engine for product search and indexing.<br/>
2)Redis will be used as a cache to store frequently accessed products (some API endpoints are cached), improving the 
app´s performance, reducing the load on Elasticsearch.
<br/><br/>

### 🔧 Technology Stack

```
Java 17
Spring Boot 3 ( REST API )
Spring Boot Elasticsearch
Spring Boot Redis
Misc Libraries (  Maven  /  Docker  /  SpringDoc OpenAPI  /  Apache Commons  /  Lombok  )
```

<br/>

### ⚒️ Getting Started

```bash
# clone the project
git clone https://github.com/rodrigobalazs/springboot-elasticsearch-redis.git;
cd springboot-elasticsearch-redis;

# start a redis container
docker run --name redis_container -p 6379:6379 -d redis:latest;

# start an elasticsearch container
docker run --name elasticsearch_container  \
       -p 9200:9200 -p 9300:9300 \
       -e "discovery.type=single-node" \
       -e "xpack.security.enabled=false" \
       elasticsearch:8.8.0;

# compile and start the spring boot server
<open a new terminal>
mvn clean install;
mvn spring-boot:run;
```

<br/>

### 💡 API Examples

#### 1. creates a new Product =>
```
curl -X POST http://localhost:8080/product \
	-H 'accept: application/json'  \
	-H "Content-Type: application/json" -d \
'{
	"id": "3",
	"name": "Sweater Tangle Essential",
	"description": "The Sweater Tangle Essential offers a perfect blend of comfort and style",
	"price": 90.8
}';
```

#### 2. Retrieves all Products ( non-cached API endpoint ) =>
-this particular API endpoints is non-cached, the results will be fetched from Elasticsearch.<br/>
-'redis-cli MONITOR' should not show any log trace so far.
```
curl -X GET http://localhost:8080/product/getProducts   -H 'accept: application/json';
```


#### 3. Retrieves a Product by Product ID ( cached API endpoint ) =>
-the first time this API endpoint is called the results are non-cached, the results are fetched from Elasticsearch,
and then stored into Redis Cache =>
```
curl -X GET "http://localhost:8080/product/id/2"   -H 'accept: application/json';
```
-'redis-cli MONITOR' should show a log trace similar to this one, the 'SET' statement means Redis will store the result
into the Cache =>
```
"GET" "products::2"
"SET" "products::2"
```
-the second (and subsequent) time the API endpoint is called the results will be fetched directly from the Redis Cache =>
```
curl -X GET "http://localhost:8080/product/id/2"   -H 'accept: application/json';
```
-'redis-cli MONITOR' should show a log trace similar to this one, a 'GET' statement without a 'SET' means Redis it´s
retrieving the result directly from the cache =>
```
"GET" "products::2"
```

<br/>

### 🔍 API Documentation / Swagger

the API documentation could be accessed from => http://localhost:8080/swagger-ui/index.html

![](https://github.com/rodrigobalazs/springboot-elasticsearch-redis/blob/main/src/main/resources/static/api_elasticsearch_redis_swagger.png)
