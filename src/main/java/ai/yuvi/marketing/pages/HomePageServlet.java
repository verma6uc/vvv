package ai.yuvi.marketing.pages;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ai.yuvi.db.utilities.DatabaseUtils;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation for the home page
 */
@WebServlet("/")
public class HomePageServlet extends BaseMarketingServlet {
    
    @Override
    protected void setPageAttributes(HttpServletRequest request) throws Exception {
        // Set page title and description
        request.setAttribute("pageTitle", "Yuvi - AI-Powered Development Platform");
        request.setAttribute("pageDescription", 
            "Accelerate your development with Yuvi's AI agents. From code generation to testing, " +
            "our platform helps you build better software faster.");
        
        // Set hero section attributes
        Map<String, String> heroContent = new HashMap<>();
        heroContent.put("title", "Meet the Agents Powering Yuvi's Intelligent Workflows");
        heroContent.put("subtitle", 
            "Because in Yuvi, your vision becomes our mission—and our AI Agents make that " +
            "transformation faster, smarter, and more seamless than ever.");
        request.setAttribute("hero", heroContent);
        
        // Set feature highlights
        request.setAttribute("features", getFeatureHighlights());
        
        // Set process steps
        request.setAttribute("processSteps", getProcessSteps());
        
        // Set agent showcase
        request.setAttribute("agents", getAgentShowcase());
        
        // Set platform metrics if database is available
        try {
            if (DatabaseUtils.testConnection()) {
                request.setAttribute("metrics", getPlatformMetrics());
            }
        } catch (SQLException e) {
            // Log error but continue rendering the page
            getServletContext().log("Error fetching platform metrics", e);
        }
    }
    
    @Override
    protected String getViewPath() {
        return "/index.jsp";
    }
    
    private Map<String, Object> getFeatureHighlights() {
        Map<String, Object> features = new HashMap<>();
        
        features.put("cognitive", Map.of(
            "title", "Cognitive Intelligence",
            "description", "AI agents that understand your code and business logic",
            "icon", "brain"
        ));
        
        features.put("automation", Map.of(
            "title", "Workflow Automation",
            "description", "Automate repetitive tasks and complex processes",
            "icon", "settings"
        ));
        
        features.put("integration", Map.of(
            "title", "Seamless Integration",
            "description", "Works with your existing tools and workflows",
            "icon", "git-merge"
        ));
        
        return features;
    }
    
    private Map<String, Object> getProcessSteps() {
        Map<String, Object> steps = new HashMap<>();
        
        steps.put("step1", Map.of(
            "title", "Connect Your Stack",
            "description", "Integrate Yuvi with your development environment",
            "icon", "plug"
        ));
        
        steps.put("step2", Map.of(
            "title", "Configure Agents",
            "description", "Set up AI agents for your specific needs",
            "icon", "sliders"
        ));
        
        steps.put("step3", Map.of(
            "title", "Start Building",
            "description", "Let AI accelerate your development process",
            "icon", "play"
        ));
        
        return steps;
    }
    
    private Map<String, Object> getAgentShowcase() {
        Map<String, Object> agents = new HashMap<>();
        
        agents.put("cognitive", Map.of(
            "title", "Cognitive Agents",
            "description", "AI-powered agents that understand and process complex information",
            "items", new String[]{"Dr. Susan Calvin", "R. Daneel Olivaw"}
        ));
        
        agents.put("technical", Map.of(
            "title", "Technical Agents",
            "description", "Specialized agents for specific development tasks",
            "items", new String[]{"SQL Writer", "HTML Templater"}
        ));
        
        return agents;
    }
    
    private Map<String, Object> getPlatformMetrics() throws SQLException {
        Map<String, Object> metrics = new HashMap<>();
        
        // These would typically come from the database
        metrics.put("totalProjects", 1000);
        metrics.put("activeUsers", 5000);
        metrics.put("tasksCompleted", 100000);
        metrics.put("averageSpeed", "5x faster");
        
        return metrics;
    }
}
