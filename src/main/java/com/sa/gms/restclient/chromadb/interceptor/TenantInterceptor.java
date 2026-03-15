package com.sa.gms.restclient.chromadb.interceptor;


import com.sa.gms.restclient.chromadb.constants.VectorConstant;
import com.sa.gms.restclient.chromadb.context.TenantContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor that extracts tenant_id from the request header and stores it in TenantContextHolder.
 * This ensures tenant context is available throughout the request lifecycle.
 */
@Component
public class TenantInterceptor implements HandlerInterceptor {

    /**
     * Default constructor.
     */
    public TenantInterceptor(){
//
    }

    private static final Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);

    /**
     * Intercepts the request before it reaches the controller.
     * Extracts tenant_id from the request header and stores it in TenantContextHolder.
     *
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @param handler the chosen handler to execute
     * @return true to continue the request processing chain
     * @throws Exception if an error occurs during request processing
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // Extract tenant_id from request header
            String tenantIdHeader = request.getHeader(VectorConstant.TENANT_ID_HEADER);
            
            // Store tenant_id in TenantContextHolder
            if (tenantIdHeader != null && !tenantIdHeader.isEmpty()) {
                try {
                    Integer tenantId = Integer.parseInt(tenantIdHeader);
                    TenantContextHolder.setTenantId(tenantId);
                    logger.debug("Tenant ID {} set in context for request: {}", tenantId, request.getRequestURI());
                } catch (NumberFormatException e) {
                    logger.warn("Invalid tenant ID format in header: {} for request: {}", tenantIdHeader, request.getRequestURI());
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid tenant ID format in header: " + tenantIdHeader);
                }
            } else {
                logger.warn("Tenant ID header '{}' not found or empty for request: {}", VectorConstant.TENANT_ID_HEADER, request.getRequestURI());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Tenant ID header '" + VectorConstant.TENANT_ID_HEADER + "' is required");
            }
        } catch (Exception e) {
            logger.error("Error extracting tenant ID from request: {}", request.getRequestURI(), e);
            // Continue processing even if tenant extraction fails
            throw e;
        }
        
        return true;
    }

    /**
     * Intercepts the request after the complete request has finished processing.
     * Clears the tenant context from TenantContextHolder to prevent memory leaks.
     *
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @param handler the chosen handler to execute
     * @param ex any exception thrown on handler execution, if any
     * @throws Exception if an error occurs during completion processing
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Clear the tenant context after request processing is complete
        try {
            Integer tenantId = TenantContextHolder.getTenantId();
            if (tenantId != null) {
                logger.debug("Clearing tenant ID {} from context for request: {}", tenantId, request.getRequestURI());
            }
            TenantContextHolder.clear();
        } catch (Exception e) {
            logger.error("Error clearing tenant context for request: {}", request.getRequestURI(), e);
        }
    }
}
