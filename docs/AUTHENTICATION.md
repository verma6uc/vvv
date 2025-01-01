# Authentication System Documentation

## Overview
The authentication system provides a secure, token-based authentication flow with JWT (JSON Web Tokens) for the Yuvi Creator platform. It includes features for user sign-in, password reset, and protected route access control.

## Architecture

### Core Components

1. **AuthContext (`src/context/AuthContext.tsx`)**
   - Manages authentication state
   - Handles token storage and refresh
   - Provides authentication status to the application
   - Exposes methods for login, logout, and token management

2. **Auth Service (`src/services/auth.ts`)**
   - Handles API communication for authentication operations
   - Implements password reset functionality
   - Provides comprehensive error handling
   - Manages token validation and refresh

3. **PrivateRoute Component (`src/components/auth/PrivateRoute.tsx`)**
   - Protects routes requiring authentication
   - Handles role-based access control
   - Manages token refresh and redirection
   - Provides loading states during authentication checks

## Features

### 1. Sign In
- Email and password authentication
- Remember me functionality
- Secure password input with show/hide toggle
- Comprehensive error handling for various failure scenarios
- Loading states for better UX
- Automatic redirection based on user role

### 2. Password Reset Flow
- **Request Password Reset**
  - Email validation
  - Rate limiting protection
  - Secure token generation
  - Email delivery status handling

- **Reset Password**
  - Token validation
  - Strong password requirements
  - Password confirmation
  - Real-time password strength validation

### 3. Token Management
- Access token for API authentication
- Refresh token for session maintenance
- Automatic token refresh
- Secure token storage in localStorage
- Token invalidation on logout

## Security Features

### Password Requirements
- Minimum 8 characters
- At least one uppercase letter
- At least one lowercase letter
- At least one number
- At least one special character
- Real-time validation feedback

### Error Handling
- Rate limiting protection
- Invalid credentials handling
- Account lockout detection
- Network error handling
- Token expiration handling

## API Endpoints

### Authentication
```
POST /api/auth/login
- Request: { email: string, password: string }
- Response: { accessToken: string, refreshToken: string, systemRole: string }
```

### Password Reset
```
POST /api/auth/forgot-password
- Request: { email: string }
- Response: { success: boolean, message: string }

GET /api/auth/validate-reset-token
- Query: token=string
- Response: { success: boolean, message?: string }

POST /api/auth/reset-password
- Request: { token: string, newPassword: string }
- Response: { success: boolean, message: string }
```

### Token Refresh
```
POST /api/auth/refresh
- Headers: { Authorization: Bearer <refresh_token> }
- Response: { accessToken: string, refreshToken: string, systemRole: string }
```

## User Roles
- **NONE**: Regular user access
- **SUPERADMIN**: Administrative access
  - Additional dashboard access
  - System management capabilities

## Protected Routes
Protected routes are wrapped with the `PrivateRoute` component:
```typescript
<PrivateRoute allowedRoles={['SUPERADMIN']}>
  <AdminDashboard />
</PrivateRoute>
```

## UI Components

### Sign In Page
- Clean, modern interface following design language
- Responsive layout
- Gradient background with blur effects
- Accessible form controls
- Loading states and error messages
- Links to password reset and sign up

### Password Reset Pages
- Step-by-step flow
- Clear instructions
- Real-time validation
- Progress indicators
- Success/error notifications

## Styling
- Follows Yuvi design language
- Dark mode optimized
- Consistent color scheme
- Responsive design
- Accessible contrast ratios
- Interactive states (hover, focus, active)

## Error Messages
- User-friendly error messages
- Clear action items
- Context-specific guidance
- Non-technical language
- Security-conscious information disclosure

## Best Practices

### Security
- HTTPS-only communication
- Secure token storage
- XSS protection
- CSRF protection
- Rate limiting
- Input validation

### UX/UI
- Immediate feedback
- Clear error messages
- Loading indicators
- Smooth transitions
- Responsive design
- Keyboard accessibility

### Code Organization
- TypeScript for type safety
- Component-based architecture
- Separation of concerns
- Consistent error handling
- Comprehensive documentation

## Future Improvements
1. OAuth integration for social login
2. Two-factor authentication
3. Session management improvements
4. Enhanced security features
5. Biometric authentication support
