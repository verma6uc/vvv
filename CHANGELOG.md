# Changelog

All notable changes to the Yuvi project will be documented in this file.

## [Unreleased]

### Enhanced Authentication System
- Updated auth servlets to use Jakarta EE 9+ namespace:
  - LoginServlet: Added remember-me functionality and session management
  - SignupServlet: Enhanced validation and error handling
  - ForgotPasswordServlet: Added rate limiting and token validation
  - EmailVerificationServlet: Added security checks and audit logging
- Improved dependency injection through AuthConfig singleton
- Added comprehensive error handling and logging
- Enhanced security with:
  - Token format validation
  - Rate limiting for sensitive operations
  - Improved audit logging
  - Better session management
  - Enhanced error messages
  - Secure response handling

### Added Database Schema Helper MCP Server
[Previous content remains unchanged...]
