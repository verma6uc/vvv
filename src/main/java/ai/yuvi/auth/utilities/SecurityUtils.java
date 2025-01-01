package ai.yuvi.auth.utilities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.regex.Pattern;

public class SecurityUtils {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 16;
    private static final int HASH_ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final int TOKEN_LENGTH = 32;

    // Password validation patterns
    private static final Pattern HAS_LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern HAS_UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern HAS_NUMBER = Pattern.compile("\\d");
    private static final Pattern HAS_SPECIAL = Pattern.compile("[^A-Za-z0-9]");
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Token expiration times
    private static final int EMAIL_VERIFICATION_HOURS = 24;
    private static final int PASSWORD_RESET_HOURS = 1;

    public static String hashPassword(String password) throws SecurityException {
        try {
            byte[] salt = new byte[SALT_LENGTH];
            SECURE_RANDOM.nextBytes(salt);

            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                HASH_ITERATIONS,
                KEY_LENGTH
            );

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // Format: iterations:base64(salt):base64(hash)
            return HASH_ITERATIONS + ":" +
                   Base64.getEncoder().encodeToString(salt) + ":" +
                   Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException("Error hashing password", e);
        }
    }

    public static boolean verifyPassword(String password, String storedHash) 
            throws SecurityException {
        try {
            String[] parts = storedHash.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] hash = Base64.getDecoder().decode(parts[2]);

            PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                iterations,
                hash.length * 8
            );

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] testHash = factory.generateSecret(spec).getEncoded();

            return MessageDigest.isEqual(hash, testHash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException("Error verifying password", e);
        }
    }

    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }

        return HAS_LOWERCASE.matcher(password).find() &&
               HAS_UPPERCASE.matcher(password).find() &&
               HAS_NUMBER.matcher(password).find() &&
               HAS_SPECIAL.matcher(password).find();
    }

    public static String generateToken() {
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        SECURE_RANDOM.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public static LocalDateTime getEmailVerificationExpiry() {
        return LocalDateTime.now().plusHours(EMAIL_VERIFICATION_HOURS);
    }

    public static LocalDateTime getPasswordResetExpiry() {
        return LocalDateTime.now().plusHours(PASSWORD_RESET_HOURS);
    }

    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        // Remove any non-printable characters
        return input.replaceAll("[^\\x20-\\x7E]", "");
    }

    public static String sanitizeHtml(String html) {
        if (html == null) {
            return null;
        }
        // Basic HTML sanitization
        return html.replaceAll("<[^>]*>", "")
                  .replaceAll("&", "&amp;")
                  .replaceAll("<", "&lt;")
                  .replaceAll(">", "&gt;")
                  .replaceAll("\"", "&quot;")
                  .replaceAll("'", "&#x27;")
                  .replaceAll("/", "&#x2F;");
    }

    public static String generateCsrfToken() {
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        SECURE_RANDOM.nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }

    public static boolean isValidOrigin(String origin, String[] allowedOrigins) {
        if (origin == null || allowedOrigins == null) {
            return false;
        }
        for (String allowedOrigin : allowedOrigins) {
            if (allowedOrigin.equals("*") || allowedOrigin.equals(origin)) {
                return true;
            }
        }
        return false;
    }

    public static String maskEmail(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return email;
        }

        String name = parts[0];
        String domain = parts[1];

        if (name.length() <= 2) {
            return name + "@" + domain;
        }

        return name.charAt(0) + 
               "*".repeat(name.length() - 2) + 
               name.charAt(name.length() - 1) + 
               "@" + domain;
    }

    public static String maskIpAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            return ipAddress;
        }

        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            return ipAddress;
        }

        return parts[0] + "." + parts[1] + ".*.*";
    }

    public static class SecurityException extends RuntimeException {
        public SecurityException(String message) {
            super(message);
        }

        public SecurityException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
