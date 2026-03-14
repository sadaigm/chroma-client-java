package com.sa.gms.restclient.chromadb.context;

/**
 * Thread-local holder for tenant context information.
 * This class stores the tenant ID for the current request thread.
 */
public class TenantContextHolder {

    /**
     * Default constructor.
     */
    public TenantContextHolder () {
//
    }

    private static final ThreadLocal<Integer> TENANT_ID = new ThreadLocal<>();

    /**
     * Set the tenant ID for the current thread.
     *
     * @param tenantId the tenant ID to set
     */
    public static void setTenantId(Integer tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * Get the tenant ID for the current thread.
     *
     * @return the tenant ID, or null if not set
     */
    public static Integer getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * Clear the tenant ID for the current thread.
     * This should be called at the end of request processing to prevent memory leaks.
     */
    public static void clear() {
        TENANT_ID.remove();
    }
}
