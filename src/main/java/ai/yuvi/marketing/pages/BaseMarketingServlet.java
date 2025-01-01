package ai.yuvi.marketing.pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base servlet class for all marketing pages providing common functionality
 */
public abstract class BaseMarketingServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BaseMarketingServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Set common attributes
            setCommonAttributes(request);
            
            // Set page-specific attributes
            setPageAttributes(request);
            
            // Forward to the JSP view
            String viewPath = getViewPath();
            request.getRequestDispatcher(viewPath).forward(request, response);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing marketing page request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Set common attributes that are needed across all marketing pages
     */
    protected void setCommonAttributes(HttpServletRequest request) {
        request.setAttribute("currentYear", java.time.Year.now().getValue());
        request.setAttribute("version", getServletContext().getInitParameter("appVersion"));
        request.setAttribute("environment", getServletContext().getInitParameter("environment"));
        
        // Get current page for navigation highlighting
        String currentPage = request.getRequestURI();
        request.setAttribute("currentPage", currentPage);
    }
    
    /**
     * Set page-specific attributes. Override this in child servlets.
     */
    protected abstract void setPageAttributes(HttpServletRequest request) throws Exception;
    
    /**
     * Get the path to the JSP view. Override this in child servlets.
     */
    protected abstract String getViewPath();
    
    /**
     * Handle form submissions. Override this in child servlets that need form processing.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
    
    /**
     * Utility method to check if a request parameter exists and has a value
     */
    protected boolean hasParameter(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Utility method to get a request parameter with a default value
     */
    protected String getParameter(HttpServletRequest request, String paramName, String defaultValue) {
        String value = request.getParameter(paramName);
        return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
    }
    
    /**
     * Utility method to get an integer parameter with a default value
     */
    protected int getIntParameter(HttpServletRequest request, String paramName, int defaultValue) {
        String value = request.getParameter(paramName);
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid integer parameter: " + paramName + "=" + value);
            }
        }
        return defaultValue;
    }
    
    /**
     * Utility method to add an error message to the request
     */
    protected void addError(HttpServletRequest request, String message) {
        request.setAttribute("error", message);
    }
    
    /**
     * Utility method to add a success message to the request
     */
    protected void addSuccess(HttpServletRequest request, String message) {
        request.setAttribute("success", message);
    }
    
    /**
     * Utility method to check if the request is an AJAX request
     */
    protected boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
    
    /**
     * Utility method to send a JSON response
     */
    protected void sendJsonResponse(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
