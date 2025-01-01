package ai.yuvi.auth.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String fromEmail;
    private final boolean useTls;
    private final Session session;

    public EmailUtils(String host, int port, String username, String password, 
                     String fromEmail, boolean useTls) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.fromEmail = fromEmail;
        this.useTls = useTls;
        this.session = createSession();
    }

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        if (useTls) {
            props.put("mail.smtp.starttls.enable", "true");
        }

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendVerificationEmail(String toEmail, String token, String baseUrl) 
            throws MessagingException {
        String subject = "Verify Your Email Address";
        String verificationLink = baseUrl + "/verify-email?token=" + token;
        
        String content = 
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; line-height: 1.6; }" +
            ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
            ".button { " +
            "    display: inline-block; " +
            "    padding: 10px 20px; " +
            "    background-color: #007bff; " +
            "    color: white; " +
            "    text-decoration: none; " +
            "    border-radius: 5px; " +
            "    margin: 20px 0; " +
            "}" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='container'>" +
            "<h2>Welcome to Yuvi!</h2>" +
            "<p>Please verify your email address by clicking the button below:</p>" +
            "<a href='" + verificationLink + "' class='button'>Verify Email</a>" +
            "<p>Or copy and paste this link into your browser:</p>" +
            "<p>" + verificationLink + "</p>" +
            "<p>This link will expire in 24 hours.</p>" +
            "<p>If you didn't create an account, you can safely ignore this email.</p>" +
            "</div>" +
            "</body>" +
            "</html>";

        sendEmail(toEmail, subject, content);
    }

    public void sendPasswordResetEmail(String toEmail, String token, String baseUrl) 
            throws MessagingException {
        String subject = "Reset Your Password";
        String resetLink = baseUrl + "/reset-password?token=" + token;
        
        String content = 
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; line-height: 1.6; }" +
            ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
            ".button { " +
            "    display: inline-block; " +
            "    padding: 10px 20px; " +
            "    background-color: #007bff; " +
            "    color: white; " +
            "    text-decoration: none; " +
            "    border-radius: 5px; " +
            "    margin: 20px 0; " +
            "}" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='container'>" +
            "<h2>Password Reset Request</h2>" +
            "<p>We received a request to reset your password. Click the button below to create a new password:</p>" +
            "<a href='" + resetLink + "' class='button'>Reset Password</a>" +
            "<p>Or copy and paste this link into your browser:</p>" +
            "<p>" + resetLink + "</p>" +
            "<p>This link will expire in 1 hour.</p>" +
            "<p>If you didn't request a password reset, you can safely ignore this email.</p>" +
            "</div>" +
            "</body>" +
            "</html>";

        sendEmail(toEmail, subject, content);
    }

    public void sendLoginNotificationEmail(String toEmail, String ipAddress, 
                                         String userAgent, String location) 
            throws MessagingException {
        String subject = "New Login to Your Account";
        
        String content = 
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; line-height: 1.6; }" +
            ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
            ".info { " +
            "    background-color: #f8f9fa; " +
            "    padding: 15px; " +
            "    border-radius: 5px; " +
            "    margin: 20px 0; " +
            "}" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class='container'>" +
            "<h2>New Login Detected</h2>" +
            "<p>We detected a new login to your account with the following details:</p>" +
            "<div class='info'>" +
            "<p><strong>Time:</strong> " + LocalDateTime.now() + "</p>" +
            "<p><strong>Location:</strong> " + location + "</p>" +
            "<p><strong>IP Address:</strong> " + ipAddress + "</p>" +
            "<p><strong>Device:</strong> " + userAgent + "</p>" +
            "</div>" +
            "<p>If this wasn't you, please change your password immediately and contact support.</p>" +
            "</div>" +
            "</body>" +
            "</html>";

        sendEmail(toEmail, subject, content);
    }

    private void sendEmail(String toEmail, String subject, String content) 
            throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setContent(content, "text/html; charset=utf-8");
        Transport.send(message);
    }

    // Helper method to validate email format
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Basic email validation using regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Helper method to get a friendly location string from IP
    public static String getLocationFromIp(String ipAddress) {
        // In a real implementation, this would use a geolocation service
        // For now, just return the IP
        return ipAddress;
    }
}
