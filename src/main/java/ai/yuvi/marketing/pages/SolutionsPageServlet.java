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
 * Servlet implementation for the solutions page
 */
@WebServlet("/solutions")
public class SolutionsPageServlet extends BaseMarketingServlet {
    
    @Override
    protected void setPageAttributes(HttpServletRequest request) throws Exception {
        // Set page title and description
        request.setAttribute("pageTitle", "Solutions - Yuvi AI Platform");
        request.setAttribute("pageDescription", 
            "Discover how Yuvi's AI solutions can transform your development process. " +
            "From startups to enterprises, we have the right solution for your needs.");
        
        // Set hero section attributes
        Map<String, String> heroContent = new HashMap<>();
        heroContent.put("title", "Intelligent Solutions for Every Business");
        heroContent.put("subtitle", 
            "From startups to enterprises, Yuvi provides tailored AI solutions to accelerate " +
            "development and drive innovation across all sectors.");
        request.setAttribute("hero", heroContent);
        
        // Set solution categories
        request.setAttribute("solutions", getSolutionCategories());
        
        // Set industry metrics
        request.setAttribute("metrics", getIndustryMetrics());
        
        // Set testimonials
        request.setAttribute("testimonials", getClientTestimonials());
        
        // Set compliance badges if database is available
        try {
            if (DatabaseUtils.testConnection()) {
                request.setAttribute("complianceBadges", getComplianceBadges());
            }
        } catch (SQLException e) {
            getServletContext().log("Error fetching compliance badges", e);
        }
    }
    
    @Override
    protected String getViewPath() {
        return "/solutions.jsp";
    }
    
    private Map<String, Object> getSolutionCategories() {
        Map<String, Object> solutions = new HashMap<>();
        
        // Enterprise Solutions
        Map<String, Object> enterprise = new HashMap<>();
        enterprise.put("title", "Enterprise Solutions");
        enterprise.put("description", "Scalable infrastructure for large teams");
        enterprise.put("icon", "building");
        enterprise.put("features", List.of(
            "Advanced team collaboration features",
            "Custom workflow automation",
            "Enterprise-grade security",
            "Dedicated support team"
        ));
        enterprise.put("metrics", Map.of(
            "uptime", "99.9%",
            "support", "24/7",
            "sla", "Enterprise SLA",
            "compliance", "SOC2 Compliant"
        ));
        solutions.put("enterprise", enterprise);
        
        // Startup Solutions
        Map<String, Object> startup = new HashMap<>();
        startup.put("title", "Startup Solutions");
        startup.put("description", "Fast deployment for growing teams");
        startup.put("icon", "rocket");
        startup.put("features", List.of(
            "Quick setup and deployment",
            "Pay-as-you-grow pricing",
            "Built-in best practices",
            "Startup-focused templates"
        ));
        startup.put("timeline", List.of(
            Map.of("stage", "Project Start", "duration", "Day 1"),
            Map.of("stage", "MVP Launch", "duration", "Week 4"),
            Map.of("stage", "Scale", "duration", "Month 3")
        ));
        solutions.put("startup", startup);
        
        // Agency Solutions
        Map<String, Object> agency = new HashMap<>();
        agency.put("title", "Agency Solutions");
        agency.put("description", "Multi-client management tools");
        agency.put("icon", "users");
        agency.put("features", List.of(
            "Multi-client dashboard",
            "White-label options",
            "Client collaboration tools",
            "Resource allocation"
        ));
        agency.put("metrics", Map.of(
            "clients", "12 Active",
            "projects", "50+",
            "delivery", "3x Faster",
            "satisfaction", "98%"
        ));
        solutions.put("agency", agency);
        
        return solutions;
    }
    
    private Map<String, Object> getIndustryMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("developmentSpeed", "5x faster development");
        metrics.put("codeQuality", "98% test coverage");
        metrics.put("teamEfficiency", "60% less meetings");
        metrics.put("costSavings", "40% cost reduction");
        return metrics;
    }
    
    private List<Map<String, String>> getClientTestimonials() {
        List<Map<String, String>> testimonials = new ArrayList<>();
        
        testimonials.add(Map.of(
            "company", "Global Tech Corp",
            "role", "Enterprise Client",
            "quote", "Yuvi's enterprise solutions have transformed our development workflow. " +
                    "The AI-powered tools have significantly reduced our time-to-market while " +
                    "maintaining high quality standards."
        ));
        
        testimonials.add(Map.of(
            "company", "InnovateTech",
            "role", "Startup Client",
            "quote", "As a startup, we needed a solution that could scale with us. Yuvi provided " +
                    "exactly that, with flexible pricing and powerful features that helped us " +
                    "launch faster."
        ));
        
        testimonials.add(Map.of(
            "company", "DigitalCraft Agency",
            "role", "Agency Client",
            "quote", "Managing multiple client projects has never been easier. Yuvi's agency tools " +
                    "give us the visibility and control we need to deliver exceptional results."
        ));
        
        return testimonials;
    }
    
    private List<Map<String, String>> getComplianceBadges() throws SQLException {
        // In a real application, these would come from the database
        List<Map<String, String>> badges = new ArrayList<>();
        badges.add(Map.of("name", "ISO 27001", "status", "Certified"));
        badges.add(Map.of("name", "SOC 2", "status", "Compliant"));
        badges.add(Map.of("name", "GDPR", "status", "Compliant"));
        badges.add(Map.of("name", "HIPAA", "status", "Ready"));
        return badges;
    }
}
