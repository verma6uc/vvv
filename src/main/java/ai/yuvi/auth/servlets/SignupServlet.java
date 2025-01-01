package ai.yuvi.auth.servlets;

import ai.yuvi.auth.models.User;
import ai.yuvi.auth.services.AuthService;
import ai.yuvi.auth.services.SessionService;
import ai.yuvi.auth.services.AuditService;
import ai.yuvi.auth.config.AuthConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signup")
public class SignupServlet extends BaseAuthServlet {
    
    private AuthService authService;
    private SessionService sessionService;
    private AuditService auditService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Get service instances from AuthConfig
        AuthConfig config = AuthConfig.getInstance();
        this.authService = config.getAuthService();
        this.sessionService = config.getSessionService();
        this.auditService = config.getAuditService();
    }

    @Override
    protected String getJspPath() {
        return "/signup.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // If user is already authenticated, redirect to home
        if (sessionService.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Forward to signup page
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        Map<String, String> errors = validateInput(email, password, confirmPassword, 
                                                 firstName, lastName);
        
        if (!errors.isEmpty()) {
            // Add error messages and preserve form data
            preserveFormData(request, errors, email, firstName, lastName);
            request.getRequestDispatcher(getJspPath()).forward(request, response);
            return;
        }

        try {
            // Check if email is available
            if (!authService.isEmailAvailable(email)) {
                errors.put("email", "This email is already registered");
                preserveFormData(request, errors, email, firstName, lastName);
                request.getRequestDispatcher(getJspPath()).forward(request, response);
                return;
            }

            // Register user
            User user = authService.register(email, password, firstName, lastName, request);

            // Log successful registration
            auditService.logSecurityEvent(request, "REGISTRATION_SUCCESS", 
                "New user registered: " + email);

            // Track registration event
            auditService.trackUserAction(request, "REGISTER");

            // Set success message and redirect to login
            request.getSession().setAttribute("success", 
                "Registration successful! Please check your email to verify your account.");
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (AuthService.RegistrationException e) {
            // Log registration failure
            auditService.logSecurityEvent(request, "REGISTRATION_FAILED", 
                "Registration failed for email: " + email + " - " + e.getMessage());

            // Add error message and preserve form data
            errors.put("general", e.getMessage());
            preserveFormData(request, errors, email, firstName, lastName);
            request.getRequestDispatcher(getJspPath()).forward(request, response);
        } catch (Exception e) {
            handleException(request, response, e);
        }
    }

    private void preserveFormData(HttpServletRequest request, Map<String, String> errors,
                                String email, String firstName, String lastName) {
        request.setAttribute("errors", errors);
        request.setAttribute("email", email);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
    }

    private Map<String, String> validateInput(String email, String password, 
                                            String confirmPassword, String firstName, 
                                            String lastName) {
        Map<String, String> errors = new HashMap<>();

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email is required");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email", "Invalid email format");
        }

        // Validate password
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "Password is required");
        } else if (!authService.isPasswordStrong(password)) {
            errors.put("password", 
                "Password must be at least 8 characters long and contain at least " +
                "one uppercase letter, one lowercase letter, one number, and one special character");
        }

        // Validate password confirmation
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            errors.put("confirmPassword", "Please confirm your password");
        } else if (!password.equals(confirmPassword)) {
            errors.put("confirmPassword", "Passwords do not match");
        }

        // Validate first name
        if (firstName == null || firstName.trim().isEmpty()) {
            errors.put("firstName", "First name is required");
        } else if (firstName.length() > 50) {
            errors.put("firstName", "First name is too long (maximum 50 characters)");
        }

        // Validate last name
        if (lastName == null || lastName.trim().isEmpty()) {
            errors.put("lastName", "Last name is required");
        } else if (lastName.length() > 50) {
            errors.put("lastName", "Last name is too long (maximum 50 characters)");
        }

        return errors;
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) 
            throws ServletException, IOException {
        auditService.logSecurityEvent(request, "ERROR", 
            "Error during registration: " + e.getMessage());
        throw new ServletException("Registration error", e);
    }
}
