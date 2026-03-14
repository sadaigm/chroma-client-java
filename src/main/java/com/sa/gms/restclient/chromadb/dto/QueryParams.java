package com.sa.gms.restclient.chromadb.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for building query parameters for Chroma DB API calls.
 * Provides type-safe methods to construct query parameter strings.
 */
public class QueryParams {

    private final Map<String, String> params;

    /**
     * Creates a new QueryParams instance.
     */
    public QueryParams() {
        this.params = new HashMap<>();
    }

    /**
     * Adds a query parameter.
     *
     * @param key the parameter name
     * @param value the parameter value
     * @return this QueryParams instance for method chaining.
     */
    public QueryParams add(String key, String value) {
        params.put(key, value);
        return this;
    }

    /**
     * Adds a query parameter with integer value.
     *
     * @param key the parameter name
     * @param value the parameter value
     * @return this QueryParams instance for method chaining.
     */
    public QueryParams add(String key, int value) {
        params.put(key, String.valueOf(value));
        return this;
    }

    /**
     * Adds a query parameter with boolean value.
     *
     * @param key the parameter name
     * @param value the parameter value
     * @return this QueryParams instance for method chaining.
     */
    public QueryParams add(String key, boolean value) {
        params.put(key, String.valueOf(value));
        return this;
    }

    /**
     * Builds the query parameter string.
     *
     * @return the query parameter string (e.g., "limit=10&amp;offset=0").
     */
    public String build() {
        if (params.isEmpty()) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!first) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            first = false;
        }
        
        return sb.toString();
    }

    // ==================== Database Query Parameters ====================

    /**
     * Creates query parameters for listing databases.
     *
     * @param limit the maximum number of results to return (optional)
     * @param offset the number of results to skip (optional)
     * @return QueryParams instance.
     */
    public static QueryParams forListDatabases(Integer limit, Integer offset) {
        QueryParams qp = new QueryParams();
        if (limit != null) {
            qp.add("limit", limit);
        }
        if (offset != null) {
            qp.add("offset", offset);
        }
        return qp;
    }

    // ==================== Collection Query Parameters ====================

    /**
     * Creates query parameters for listing collections.
     *
     * @param limit the maximum number of results to return (optional)
     * @param offset the number of results to skip (optional)
     * @return QueryParams instance.
     */
    public static QueryParams forListCollections(Integer limit, Integer offset) {
        QueryParams qp = new QueryParams();
        if (limit != null) {
            qp.add("limit", limit);
        }
        if (offset != null) {
            qp.add("offset", offset);
        }
        return qp;
    }

    /**
     * Creates query parameters for getting collections with tenant and database filters.
     *
     * @param tenant the tenant ID to filter by (optional)
     * @param database the database ID to filter by (optional)
     * @param limit the maximum number of results to return (optional)
     * @param offset the number of results to skip (optional)
     * @return QueryParams instance.
     */
    public static QueryParams forGetCollections(String tenant, String database, Integer limit, Integer offset) {
        QueryParams qp = new QueryParams();
        if (tenant != null) {
            qp.add("tenant", tenant);
        }
        if (database != null) {
            qp.add("database", database);
        }
        if (limit != null) {
            qp.add("limit", limit);
        }
        if (offset != null) {
            qp.add("offset", offset);
        }
        return qp;
    }

    // ==================== Document Query Parameters ====================

    /**
     * Creates query parameters for querying documents.
     *
     * @param nResults the number of results to return (optional)
     * @return QueryParams instance.
     */
    public static QueryParams forQueryDocuments(Integer nResults) {
        QueryParams qp = new QueryParams();
        if (nResults != null) {
            qp.add("n_results", nResults);
        }
        return qp;
    }

    /**
     * Creates query parameters for getting documents.
     *
     * @param ids comma-separated document IDs to retrieve (optional)
     * @param limit the maximum number of results to return (optional)
     * @param offset the number of results to skip (optional)
     * @return QueryParams instance.
     */
    public static QueryParams forGetDocuments(String ids, Integer limit, Integer offset) {
        QueryParams qp = new QueryParams();
        if (ids != null) {
            qp.add("ids", ids);
        }
        if (limit != null) {
            qp.add("limit", limit);
        }
        if (offset != null) {
            qp.add("offset", offset);
        }
        return qp;
    }

    /**
     * Creates query parameters for counting records in a collection.
     *
     * @param readLevel the read level for consistency vs performance tradeoffs (optional)
     *                  Valid values: "index_and_wal", "index_only"
     * @return QueryParams instance.
     */
    public static QueryParams forCountRecords(String readLevel) {
        QueryParams qp = new QueryParams();
        if (readLevel != null) {
            qp.add("read_level", readLevel);
        }
        return qp;
    }

    /**
     * Creates query parameters for querying a collection.
     *
     * @param limit the maximum number of results to return (optional)
     * @param offset the number of results to skip (optional)
     * @return QueryParams instance.
     */
    public static QueryParams forQueryCollection(Integer limit, Integer offset) {
        QueryParams qp = new QueryParams();
        if (limit != null) {
            qp.add("limit", limit);
        }
        if (offset != null) {
            qp.add("offset", offset);
        }
        return qp;
    }
}