# Chroma Client Java

A comprehensive Java client library for ChromaDB, an open-source vector database designed for AI applications. This library provides a clean, Spring Boot-based API for interacting with ChromaDB's REST API, supporting multi-tenant architecture with full CRUD operations for tenants, databases, collections, and documents.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Usage](#usage)
- [Main Connections](#main-connections)
- [Architecture](#architecture)
- [Examples](#examples)
- [Testing](#testing)
- [Building](#building)
- [License](#license)
- [Support](#support)
- [Contributing](#contributing)
- [Changelog](#changelog)
- [Roadmap](#roadmap)

## Features

- **Multi-tenant Support**: Full support for ChromaDB's multi-tenant architecture
- **Complete CRUD Operations**: Create, Read, Update, Delete for all resources
- **Vector Search**: Dense, sparse, and hybrid vector search capabilities
- **Document Management**: Add, update, upsert, delete, and query documents
- **Collection Management**: Create, fork, update, and manage collections
- **System Operations**: Health checks, heartbeat, version information
- **Type-Safe DTOs**: Builder pattern for request objects
- **Spring Boot Integration**: Seamless integration with Spring applications
- **REST Client**: Built on Spring's RestTemplate for HTTP operations
- **Query Parameters**: Support for pagination and filtering
- **Tenant Context**: Automatic tenant context management

## Prerequisites

- **Java 21** or higher
- **Maven 3.6+** or **Gradle 7+**
- **ChromaDB Server** running (local or remote)
- **Spring Boot 3.5.5** (managed dependency)

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/sadaigm/chroma-client-java.git
cd chroma-client-java
```

### 2. Build the Project

```bash
# Using Maven
mvn clean install

# Or using the provided build script
./build.sh
```

### 3. Add to Your Project

#### Maven

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.sa.gms.ksapp</groupId>
    <artifactId>chroma-client-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

#### Gradle

Add the dependency to your `build.gradle`:

```gradle
implementation 'com.sa.gms.ksapp:chroma-client-java:1.0-SNAPSHOT'
```

### 4. Start ChromaDB Server

If you don't have ChromaDB running, start it using Docker:

```bash
docker run -p 8000:8000 chromadb/chroma:latest
```

Or install locally:

```bash
pip install chromadb
chroma-server --host 0.0.0.0 --port 8000
```

## Dependencies

### Core Dependencies

The library uses the following main dependencies:

1. **Spring Boot Starter Data REST** (`spring-boot-starter-data-rest`)
   - Provides RESTful web services support
   - Includes Spring MVC, Jackson for JSON processing
   - Essential for HTTP client operations

2. **Spring Boot Dependencies** (`spring-boot-dependencies`)
   - Manages all Spring Boot dependency versions
   - Ensures compatibility across Spring ecosystem
   - Version: 3.5.5

### Testing Dependencies

1. **Spring Boot Starter Test** (`spring-boot-starter-test`)
   - Comprehensive testing support
   - Includes JUnit 5, Mockito, AssertJ
   - Provides Spring Test context framework

2. **JUnit Jupiter** (`junit-jupiter`)
   - Modern testing framework for Java
   - Supports parameterized tests, dynamic tests
   - Essential for unit and integration tests

3. **Mockito Core** (`mockito-core`)
   - Mocking framework for unit tests
   - Enables testing of components in isolation
   - Supports verification and stubbing

4. **Mockito JUnit Jupiter** (`mockito-junit-jupiter`)
   - Integration between Mockito and JUnit 5
   - Provides `@ExtendWith(MockitoExtension.class)`
   - Simplifies test setup

### Transitive Dependencies

The library also includes these transitive dependencies:

- **Jackson**: JSON serialization/deserialization
- **Spring Web**: HTTP client and server support
- **Spring Context**: Dependency injection and configuration
- **SLF4J**: Logging facade
- **Logback**: Logging implementation

## Configuration

### Application Configuration

Configure the ChromaDB connection in your `application.yml` or `application.properties`:

#### YAML Format (`application.yml`)

```yaml
chroma:
  host: http://localhost:8000
```

#### Properties Format (`application.properties`)

```properties
chroma.host=http://localhost:8000
```

### Custom Configuration

You can also configure the client programmatically:

```java
@Configuration
public class ChromaConfig {
    
    @Bean
    public ChromaProperties chromaProperties() {
        ChromaProperties properties = new ChromaProperties();
        properties.setHost("http://your-chroma-host:8000");
        return properties;
    }
}
```

### Environment Variables

For containerized deployments, use environment variables:

```bash
export CHROMA_HOST=http://chroma-server:8000
```

Then reference in your configuration:

```yaml
chroma:
  host: ${CHROMA_HOST:http://localhost:8000}
```

## Usage

### Basic Setup

Enable component scanning in your Spring Boot application:

```java
@SpringBootApplication
@ComponentScan(basePackages = "com.sa.gms.restclient.chromadb")
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### Injecting Clients

The library provides several client facades for different operations:

```java
@Service
public class MyService {
    
    @Autowired
    private ChromaTenantClient tenantClient;
    
    @Autowired
    private ChromaDBClient dbClient;
    
    @Autowired
    private ChromaCollectionClient collectionClient;
    
    @Autowired
    private ChromaDocumentClient documentClient;
    
    @Autowired
    private ChromaSystemClient systemClient;
}
```

### Using with Other Applications

#### 1. Spring Boot Application

```java
@RestController
@RequestMapping("/api/vectors")
public class VectorController {
    
    @Autowired
    private ChromaDocumentClient documentClient;
    
    @Autowired
    private ChromaCollectionClient collectionClient;
    
    @PostMapping("/search")
    public ResponseEntity<String> searchDocuments(
            @RequestParam String tenantId,
            @RequestParam String databaseName,
            @RequestParam String collectionId,
            @RequestBody SearchRecordsRequest request) {
        
        try {
            ResponseEntity<String> response = documentClient.searchRecords(
                tenantId, databaseName, collectionId, request);
            return ResponseEntity.ok(response.getBody());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Search failed");
        }
    }
}
```

#### 2. Standalone Java Application

```java
public class StandaloneApp {
    public static void main(String[] args) {
        // Create Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(
            ChromaConfig.class
        );
        
        // Get client beans
        ChromaTenantClient tenantClient = context.getBean(ChromaTenantClient.class);
        ChromaDBClient dbClient = context.getBean(ChromaDBClient.class);
        
        // Use the clients
        try {
            CreateTenantRequest request = new CreateTenantRequest("my-tenant");
            ResponseEntity<String> response = tenantClient.createTenant(request);
            System.out.println("Tenant created: " + response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
```

#### 3. Integration with Existing Spring Boot App

Add the library to your existing Spring Boot application:

```java
@Service
public class DocumentSearchService {
    
    private final ChromaDocumentClient documentClient;
    private final ChromaCollectionClient collectionClient;
    
    public DocumentSearchService(
            ChromaDocumentClient documentClient,
            ChromaCollectionClient collectionClient) {
        this.documentClient = documentClient;
        this.collectionClient = collectionClient;
    }
    
    public List<Document> searchSimilarDocuments(
            String query,
            int limit) throws JsonProcessingException {
        
        // Build search request
        SearchRecordsRequest request = SearchRecordsRequest.builder()
            .query(SearchRecordsRequest.SearchQuery.builder()
                .queryVector(embeddingService.embed(query))
                .nResults(limit)
                .build())
            .build();
        
        // Execute search
        ResponseEntity<String> response = documentClient.searchRecords(
            tenantId, databaseName, collectionId, request);
        
        // Parse and return results
        return parseSearchResults(response.getBody());
    }
}
```

## Main Connections

The library provides several main connection points to ChromaDB, each serving different purposes:

### 1. Tenant Connection (`ChromaTenantClient`)

**Purpose**: Manage multi-tenant isolation in ChromaDB

**Key Operations**:
- Create tenants for different organizations/users
- Retrieve tenant information
- Update tenant metadata
- Manage tenant lifecycle

**Use Cases**:
- SaaS applications with multiple customers
- Multi-organization data isolation
- Tenant-specific resource management

**Example**:
```java
// Create a new tenant
CreateTenantRequest request = new CreateTenantRequest("acme-corp");
ResponseEntity<String> response = tenantClient.createTenant(request);
String tenantId = extractTenantId(response.getBody());

// Get tenant info
ResponseEntity<String> tenantInfo = tenantClient.getTenant(tenantId);
```

### 2. Database Connection (`ChromaDBClient`)

**Purpose**: Manage databases within tenants

**Key Operations**:
- Create databases for different data domains
- List databases with pagination
- Retrieve database metadata
- Delete databases

**Use Cases**:
- Organizing data by domain (e.g., products, users, orders)
- Environment separation (dev, staging, prod)
- Data isolation within a tenant

**Example**:
```java
// Create a database
CreateDatabaseRequest request = new CreateDatabaseRequest("product-catalog");
ResponseEntity<String> response = dbClient.createDatabase(tenantId, request);

// List databases with pagination
String queryParams = QueryParams.forListDatabases(10, 0).build();
ResponseEntity<String> databases = dbClient.listDatabases(tenantId, queryParams);
```

### 3. Collection Connection (`ChromaCollectionClient`)

**Purpose**: Manage collections (vector stores) within databases

**Key Operations**:
- Create collections with custom metadata
- Query collection information
- Update collection properties
- Fork collections for experimentation
- Delete collections

**Use Cases**:
- Storing different types of embeddings (text, images, audio)
- A/B testing with collection forks
- Managing vector store lifecycle

**Example**:
```java
// Create a collection
CreateCollectionRequest request = CreateCollectionRequest.builder()
    .name("product-embeddings")
    .metadata(Map.of("dimension", "1536", "model", "text-embedding-ada-002"))
    .build();
ResponseEntity<String> response = collectionClient.createCollection(
    tenantId, databaseName, objectMapper.writeValueAsString(request));

// List collections
String queryParams = QueryParams.forListCollections(20, 0).build();
ResponseEntity<String> collections = collectionClient.listCollections(
    tenantId, databaseName, queryParams);
```

### 4. Document Connection (`ChromaDocumentClient`)

**Purpose**: Manage documents (vectors + metadata) within collections

**Key Operations**:
- Add documents with embeddings
- Query documents by vector similarity
- Search with dense, sparse, or hybrid search
- Update document metadata
- Delete documents
- Upsert documents (create or update)

**Use Cases**:
- Semantic search applications
- Recommendation systems
- Document retrieval
- Similarity matching

**Example**:
```java
// Add documents
AddRecordsRequest request = AddRecordsRequest.builder()
    .ids(List.of("doc1", "doc2"))
    .embeddings(List.of(
        List.of(0.1, 0.2, 0.3),
        List.of(0.4, 0.5, 0.6)
    ))
    .metadatas(List.of(
        Map.of("title", "Product A", "category", "electronics"),
        Map.of("title", "Product B", "category", "books")
    ))
    .documents(List.of("Description of Product A", "Description of Product B"))
    .build();
ResponseEntity<String> response = documentClient.addRecords(
    tenantId, databaseName, collectionId, request);

// Search documents
SearchRecordsRequest searchRequest = SearchRecordsRequest.builder()
    .query(SearchRecordsRequest.SearchQuery.builder()
        .queryVector(List.of(0.1, 0.2, 0.3))
        .nResults(5)
        .build())
    .build();
ResponseEntity<String> results = documentClient.searchRecords(
    tenantId, databaseName, collectionId, searchRequest);
```

### 5. System Connection (`ChromaSystemClient`)

**Purpose**: Monitor and manage ChromaDB system health

**Key Operations**:
- Health checks
- Heartbeat monitoring
- Version information
- Pre-flight checks
- System reset (with authorization)

**Use Cases**:
- Health monitoring in production
- Load balancer health checks
- System diagnostics
- Deployment verification

**Example**:
```java
// Health check
ResponseEntity<String> health = systemClient.healthcheck();
if (health.getStatusCode().is2xxSuccessful()) {
    System.out.println("ChromaDB is healthy");
}

// Get version
ResponseEntity<String> version = systemClient.getVersion();
System.out.println("ChromaDB version: " + version.getBody());

// Heartbeat
ResponseEntity<String> heartbeat = systemClient.heartbeat();
long timestamp = Long.parseLong(heartbeat.getBody());
System.out.println("Server timestamp: " + timestamp);
```

### 6. Authentication Connection (`ChromaAuthClient`)

**Purpose**: Manage authentication and user identity

**Key Operations**:
- Get current user identity
- Retrieve tenant and database access
- Manage authentication tokens

**Use Cases**:
- User authentication
- Access control
- Permission verification

**Example**:
```java
// Get user identity
ResponseEntity<String> identity = authClient.getIdentity();
UserIdentityResponse user = objectMapper.readValue(
    identity.getBody(), UserIdentityResponse.class);
System.out.println("User: " + user.getIdentity());
System.out.println("Tenants: " + user.getTenants());
```

## Architecture

### Layered Architecture

```
┌─────────────────────────────────────────┐
│   Your Application (Service Layer)     │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│   Client Facades (Service Layer)        │
│   - ChromaTenantClient                  │
│   - ChromaDBClient                      │
│   - ChromaCollectionClient              │
│   - ChromaDocumentClient                │
│   - ChromaSystemClient                  │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│   Core Interface (IChromaClient)        │
│   - ChromaClientImpl                    │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│   REST Client (IRestClient)             │
│   - ChromaRestClientImpl                │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│   ChromaDB REST API                     │
└─────────────────────────────────────────┘
```

### Key Components

1. **Client Facades**: High-level service classes that provide clean APIs
2. **Core Interface**: Defines all ChromaDB operations
3. **REST Client**: Handles HTTP communication with ChromaDB
4. **DTOs**: Type-safe request/response objects
5. **Configuration**: Manages connection settings
6. **Context Holder**: Manages tenant context

## Examples

### Complete Workflow Example

```java
@Service
public class ChromaWorkflowService {
    
    @Autowired
    private ChromaTenantClient tenantClient;
    
    @Autowired
    private ChromaDBClient dbClient;
    
    @Autowired
    private ChromaCollectionClient collectionClient;
    
    @Autowired
    private ChromaDocumentClient documentClient;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public void completeWorkflow() throws JsonProcessingException {
        // 1. Create a tenant
        CreateTenantRequest tenantRequest = new CreateTenantRequest("my-company");
        ResponseEntity<String> tenantResponse = tenantClient.createTenant(tenantRequest);
        String tenantId = extractId(tenantResponse.getBody());
        
        // 2. Create a database
        CreateDatabaseRequest dbRequest = new CreateDatabaseRequest("documents");
        ResponseEntity<String> dbResponse = dbClient.createDatabase(tenantId, dbRequest);
        String databaseName = "documents";
        
        // 3. Create a collection
        CreateCollectionRequest collectionRequest = CreateCollectionRequest.builder()
            .name("text-embeddings")
            .metadata(Map.of(
                "dimension", "1536",
                "model", "text-embedding-ada-002"
            ))
            .build();
        ResponseEntity<String> collectionResponse = collectionClient.createCollection(
            tenantId, databaseName, 
            objectMapper.writeValueAsString(collectionRequest)
        );
        String collectionId = extractId(collectionResponse.getBody());
        
        // 4. Add documents
        AddRecordsRequest addRequest = AddRecordsRequest.builder()
            .ids(List.of("doc1", "doc2", "doc3"))
            .embeddings(List.of(
                List.of(0.1, 0.2, 0.3),
                List.of(0.4, 0.5, 0.6),
                List.of(0.7, 0.8, 0.9)
            ))
            .metadatas(List.of(
                Map.of("title", "Document 1", "category", "tech"),
                Map.of("title", "Document 2", "category", "business"),
                Map.of("title", "Document 3", "category", "tech")
            ))
            .documents(List.of(
                "Content of document 1",
                "Content of document 2",
                "Content of document 3"
            ))
            .build();
        documentClient.addRecords(tenantId, databaseName, collectionId, addRequest);
        
        // 5. Search documents
        SearchRecordsRequest searchRequest = SearchRecordsRequest.builder()
            .query(SearchRecordsRequest.SearchQuery.builder()
                .queryVector(List.of(0.1, 0.2, 0.3))
                .nResults(3)
                .where(Map.of("category", "tech"))
                .build())
            .build();
        ResponseEntity<String> searchResults = documentClient.searchRecords(
            tenantId, databaseName, collectionId, searchRequest);
        
        System.out.println("Search results: " + searchResults.getBody());
    }
    
    private String extractId(String response) {
        // Parse response to extract ID
        return "extracted-id";
    }
}
```

### Pagination Example

```java
public void listWithPagination() {
    // List databases with pagination
    String queryParams = QueryParams.forListDatabases(10, 0).build();
    ResponseEntity<String> response = dbClient.listDatabases(tenantId, queryParams);
    
    // List collections with pagination
    String collectionParams = QueryParams.forListCollections(20, 0).build();
    ResponseEntity<String> collections = collectionClient.listCollections(
        tenantId, databaseName, collectionParams);
    
    // Query with pagination
    String queryParams = QueryParams.forQueryCollection(10, 0).build();
    QueryCollectionRequest request = QueryCollectionRequest.builder()
        .queryEmbeddings(List.of(List.of(0.1, 0.2, 0.3)))
        .nResults(10)
        .build();
    ResponseEntity<String> results = documentClient.queryCollection(
        tenantId, databaseName, collectionId, queryParams, request);
}
```

### Error Handling Example

```java
@Service
public class ChromaService {
    
    @Autowired
    private ChromaDocumentClient documentClient;
    
    public void safeOperation() {
        try {
            SearchRecordsRequest request = buildSearchRequest();
            ResponseEntity<String> response = documentClient.searchRecords(
                tenantId, databaseName, collectionId, request);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                // Process successful response
                processResults(response.getBody());
            } else {
                // Handle error response
                log.error("Search failed with status: {}", response.getStatusCode());
            }
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize request", e);
            throw new RuntimeException("Request serialization failed", e);
        } catch (RestClientException e) {
            log.error("HTTP request failed", e);
            throw new RuntimeException("ChromaDB communication failed", e);
        }
    }
}
```

## Testing

### Run Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ChromaClientImplTest

# Run with coverage
mvn test jacoco:report
```

### Test Structure

The project includes comprehensive tests:

- **Unit Tests**: Test individual components in isolation
- **Integration Tests**: Test client interactions with mocked ChromaDB
- **DTO Tests**: Verify request/response object serialization

### Example Test

```java
@SpringBootTest
class ChromaDocumentClientTest {
    
    @Autowired
    private ChromaDocumentClient documentClient;
    
    @MockBean
    private IChromaClient chromaClient;
    
    @Test
    void testAddRecords() throws JsonProcessingException {
        // Arrange
        AddRecordsRequest request = AddRecordsRequest.builder()
            .ids(List.of("doc1"))
            .embeddings(List.of(List.of(0.1, 0.2, 0.3)))
            .build();
        
        when(chromaClient.addRecords(any(), any(), any(), any(), any()))
            .thenReturn(ResponseEntity.ok("{\"success\": true}"));
        
        // Act
        ResponseEntity<String> response = documentClient.addRecords(
            "tenant1", "db1", "col1", request);
        
        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("success"));
    }
}
```

## Building

### Build from Source

```bash
# Clean and build
mvn clean install

# Skip tests
mvn clean install -DskipTests

# Build with specific profile
mvn clean install -P production
```

### Build Artifacts

After building, you'll find:

- **JAR File**: `target/chroma-client-java-1.0-SNAPSHOT.jar`
- **Sources JAR**: `target/chroma-client-java-1.0-SNAPSHOT-sources.jar`
- **Javadoc JAR**: `target/chroma-client-java-1.0-SNAPSHOT-javadoc.jar`

### Generate Javadoc

```bash
mvn javadoc:javadoc
```

Javadoc will be generated in the `target/site/apidocs` directory.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For issues, questions, or contributions:

- **Open an issue on GitHub**: If you encounter any problems or have questions, please create an issue at https://github.com/sadaigm/chroma-client-java/issues
- Check the [Javadoc](javadocs/index.html) for API documentation
- Review the test files for usage examples

## Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## Changelog

### Version 1.0-SNAPSHOT

- Initial release
- Full CRUD operations for tenants, databases, collections, and documents
- Support for dense, sparse, and hybrid vector search
- Multi-tenant architecture support
- Spring Boot integration
- Comprehensive test coverage
- Complete Javadoc documentation

## Roadmap

Future enhancements:

- [ ] **Automatic Data Chunking**: Intelligent chunking of large documents into smaller, manageable pieces for better embedding and retrieval
- [ ] **Flexible Embedding Model Integration**: Support for embedding content using any model (OpenAI, Hugging Face, local models, etc.) with pluggable architecture
- [ ] **Simplified Document Storage**: High-level API where users can simply send content/text, and the library handles chunking, embedding, and storage automatically
- [ ] **Connection pooling and retry logic**: Improved resilience with automatic retries and connection management
- [ ] **Metrics and monitoring integration**: Built-in metrics for monitoring performance and usage
- [ ] **Additional authentication methods**: Support for various authentication mechanisms (API keys, OAuth, JWT)
- [ ] **Batch operation optimizations**: Improved performance for bulk operations
- [ ] **Caching layer**: Optional caching for frequently accessed data to reduce latency

---

**Note**: This documentation was generated by AI.
