package ai.yuvi.marketing.pages;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ai.yuvi.db.utilities.DatabaseUtils;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation for the agents showcase page
 */
@WebServlet("/agents")
public class AgentsPageServlet extends BaseMarketingServlet {
    
    @Override
    protected void setPageAttributes(HttpServletRequest request) throws Exception {
        // Set page title and description
        request.setAttribute("pageTitle", "AI Agents - Yuvi Platform");
        request.setAttribute("pageDescription", 
            "Meet Yuvi's AI agents - from cognitive processing to technical implementation, " +
            "our agents work together to accelerate your development process.");
        
        // Set hero section attributes
        Map<String, String> heroContent = new HashMap<>();
        heroContent.put("title", "Meet the Agents Powering Yuvi's Intelligent Workflows");
        heroContent.put("subtitle", 
            "Because in Yuvi, your vision becomes our mission—and our AI Agents make that " +
            "transformation faster, smarter, and more seamless than ever.");
        request.setAttribute("hero", heroContent);
        
        // Set workflow stages
        request.setAttribute("workflow", getWorkflowStages());
        
        // Set cognitive agents
        request.setAttribute("cognitiveAgents", getCognitiveAgents());
        
        // Set technical agents
        request.setAttribute("technicalAgents", getTechnicalAgents());
        
        // Set agent metrics if database is available
        try {
            if (DatabaseUtils.testConnection()) {
                request.setAttribute("agentMetrics", getAgentMetrics());
            }
        } catch (SQLException e) {
            getServletContext().log("Error fetching agent metrics", e);
        }
    }
    
    @Override
    protected String getViewPath() {
        return "/agents.jsp";
    }
    
    private List<Map<String, Object>> getWorkflowStages() {
        List<Map<String, Object>> stages = new ArrayList<>();
        
        stages.add(Map.of(
            "name", "Input",
            "description", "Your code, documentation, or requirements",
            "icon", "file-text",
            "animation", "pulse"
        ));
        
        stages.add(Map.of(
            "name", "AI Agents",
            "description", "Processing and transformation",
            "icon", "cpu",
            "animation", "processing",
            "status", "active"
        ));
        
        stages.add(Map.of(
            "name", "Result",
            "description", "Optimized output ready for use",
            "icon", "check-circle",
            "animation", "pulse"
        ));
        
        return stages;
    }
    
    private Map<String, Object> getCognitiveAgents() {
        Map<String, Object> agents = new HashMap<>();
        
        // Dr. Susan Calvin (Reading Agent)
        Map<String, Object> calvin = new HashMap<>();
        calvin.put("name", "Dr. Susan Calvin");
        calvin.put("role", "Reading Agent");
        calvin.put("icon", "book-open");
        calvin.put("description", "Processes and summarizes text with human-like comprehension");
        calvin.put("features", List.of(
            "Document parsing & summarization",
            "Extract key insights",
            "Feed Memory blocks"
        ));
        calvin.put("metrics", Map.of(
            "accuracy", "99.8%",
            "speed", "500k words/sec",
            "languages", "95+"
        ));
        agents.put("calvin", calvin);
        
        // R. Daneel Olivaw (Writing Agent)
        Map<String, Object> olivaw = new HashMap<>();
        olivaw.put("name", "R. Daneel Olivaw");
        olivaw.put("role", "Writing Agent");
        olivaw.put("icon", "edit-3");
        olivaw.put("description", "Generates and refines text with precision and style");
        olivaw.put("features", List.of(
            "Match brand voice",
            "Generate documentation",
            "Polish content"
        ));
        olivaw.put("metrics", Map.of(
            "coherence", "98.5%",
            "style match", "96%",
            "formats", "25+"
        ));
        agents.put("olivaw", olivaw);
        
        return agents;
    }
    
    private Map<String, Object> getTechnicalAgents() {
        Map<String, Object> agents = new HashMap<>();
        
        // SQL Writer Agent
        Map<String, Object> sqlWriter = new HashMap<>();
        sqlWriter.put("name", "SQL Writer");
        sqlWriter.put("role", "Database Agent");
        sqlWriter.put("icon", "database");
        sqlWriter.put("description", "Generates and optimizes database queries");
        sqlWriter.put("features", List.of(
            "Smart query generation",
            "Performance optimization",
            "Schema suggestions"
        ));
        sqlWriter.put("codeExample", 
            "SELECT p.name, COUNT(t.id) as tasks\n" +
            "FROM projects p\n" +
            "LEFT JOIN tasks t ON p.id = t.project_id\n" +
            "WHERE p.status = 'active'\n" +
            "GROUP BY p.name\n" +
            "HAVING COUNT(t.id) > 5\n" +
            "ORDER BY tasks DESC;"
        );
        agents.put("sqlWriter", sqlWriter);
        
        // HTML Templater Agent
        Map<String, Object> htmlTemplater = new HashMap<>();
        htmlTemplater.put("name", "HTML Templater");
        htmlTemplater.put("role", "Frontend Agent");
        htmlTemplater.put("icon", "layout");
        htmlTemplater.put("description", "Builds structured front-end layouts");
        htmlTemplater.put("features", List.of(
            "Component generation",
            "Responsive layouts",
            "Accessibility built-in"
        ));
        htmlTemplater.put("codeExample",
            "<div class=\"component glass\">\n" +
            "  <div class=\"header\">\n" +
            "    <div class=\"icon-wrapper\">\n" +
            "      <i data-feather=\"star\"></i>\n" +
            "    </div>\n" +
            "    <h3>{{ title }}</h3>\n" +
            "  </div>\n" +
            "  <div class=\"content\">\n" +
            "    {{ content }}\n" +
            "  </div>\n" +
            "</div>"
        );
        agents.put("htmlTemplater", htmlTemplater);
        
        return agents;
    }
    
    private Map<String, Object> getAgentMetrics() throws SQLException {
        // In a real application, these would come from the database
        Map<String, Object> metrics = new HashMap<>();
        
        metrics.put("totalProcessed", 1000000);
        metrics.put("averageAccuracy", 99.5);
        metrics.put("responseTime", "50ms");
        metrics.put("activeAgents", 8);
        metrics.put("uptime", 99.99);
        
        // Agent collaboration stats
        Map<String, Integer> collaboration = new HashMap<>();
        collaboration.put("cognitive_technical", 5000);
        collaboration.put("reading_writing", 3500);
        collaboration.put("database_frontend", 2800);
        metrics.put("collaboration", collaboration);
        
        return metrics;
    }
}
