<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/includes/common/head.jsp">
        <jsp:param name="title" value="Sign Up - Yuvi Platform" />
    </jsp:include>
    <style>
        .signup-container {
            min-height: 100vh;
            background: linear-gradient(135deg, #121315 0%, #1C1D21 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .signup-card {
            width: 100%;
            max-width: 480px;
            background: rgba(43, 45, 49, 0.5);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 1rem;
            padding: 2rem;
        }

        .signup-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .signup-logo {
            width: 120px;
            height: auto;
            margin-bottom: 1.5rem;
        }

        .form-floating {
            margin-bottom: 1rem;
        }

        .form-floating > label {
            color: #B0B0B0;
        }

        .form-control {
            background: rgba(15, 23, 42, 0.6);
            border: 1px solid rgba(255, 255, 255, 0.1);
            color: #F3F3F3;
        }

        .form-control:focus {
            background: rgba(15, 23, 42, 0.8);
            border-color: #08A0F8;
            box-shadow: 0 0 0 0.25rem rgba(8, 160, 248, 0.25);
            color: #F3F3F3;
        }

        .form-check-input {
            background-color: rgba(15, 23, 42, 0.6);
            border: 1px solid rgba(255, 255, 255, 0.1);
        }

        .form-check-input:checked {
            background-color: #08A0F8;
            border-color: #08A0F8;
        }

        .btn-signup {
            width: 100%;
            padding: 0.75rem;
            background: #08A0F8;
            border: none;
            border-radius: 0.5rem;
            color: white;
            font-weight: 500;
            margin-top: 1rem;
            transition: all 0.3s ease;
        }

        .btn-signup:hover {
            background: #2D9BF0;
            transform: translateY(-1px);
        }

        .signup-footer {
            text-align: center;
            margin-top: 1.5rem;
            color: #B0B0B0;
        }

        .signup-footer a {
            color: #08A0F8;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .signup-footer a:hover {
            color: #2D9BF0;
        }

        .password-toggle {
            position: absolute;
            right: 1rem;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            color: #B0B0B0;
            cursor: pointer;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .password-toggle:hover {
            color: #F3F3F3;
        }

        .alert {
            border: none;
            border-radius: 0.5rem;
            padding: 1rem;
            margin-bottom: 1rem;
            font-size: 0.875rem;
        }

        .alert-danger {
            background: rgba(255, 92, 92, 0.1);
            border: 1px solid rgba(255, 92, 92, 0.2);
            color: #FF5C5C;
        }

        .alert-success {
            background: rgba(49, 204, 116, 0.1);
            border: 1px solid rgba(49, 204, 116, 0.2);
            color: #31CC74;
        }

        .form-floating > .form-control:focus ~ label,
        .form-floating > .form-control:not(:placeholder-shown) ~ label {
            color: #08A0F8;
            transform: scale(.85) translateY(-0.75rem) translateX(0.15rem);
        }

        .signup-divider {
            display: flex;
            align-items: center;
            text-align: center;
            margin: 1.5rem 0;
            color: #B0B0B0;
        }

        .signup-divider::before,
        .signup-divider::after {
            content: '';
            flex: 1;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }

        .signup-divider span {
            padding: 0 1rem;
        }

        .social-signup {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 1rem;
            margin-bottom: 1.5rem;
        }

        .btn-social {
            background: rgba(15, 23, 42, 0.6);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 0.5rem;
            padding: 0.75rem;
            color: #F3F3F3;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: all 0.3s ease;
        }

        .btn-social:hover {
            background: rgba(15, 23, 42, 0.8);
            transform: translateY(-1px);
        }

        .btn-social i {
            font-size: 1.25rem;
        }

        .password-requirements {
            font-size: 0.875rem;
            color: #B0B0B0;
            margin-top: 0.5rem;
        }

        .password-requirements ul {
            list-style: none;
            padding-left: 0;
            margin-bottom: 0;
        }

        .password-requirements li {
            display: flex;
            align-items: center;
            margin-bottom: 0.25rem;
        }

        .password-requirements li i {
            margin-right: 0.5rem;
        }

        .requirement-met {
            color: #31CC74;
        }

        .requirement-unmet {
            color: #B0B0B0;
        }
    </style>
</head>
<body class="bg-dark">
    <div class="signup-container">
        <div class="signup-card">
            <div class="signup-header">
                <img src="${pageContext.request.contextPath}/assets/images/yuvi-logo.svg" alt="Yuvi Logo" class="signup-logo">
                <h1 class="h4 text-light mb-3">Create Your Account</h1>
                <p class="text-secondary mb-4">Join Yuvi and start building with AI</p>
            </div>

            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <i data-feather="alert-circle" class="me-2" style="width: 16px; height: 16px;"></i>
                    ${error}
                </div>
            </c:if>

            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    <i data-feather="check-circle" class="me-2" style="width: 16px; height: 16px;"></i>
                    ${success}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/signup" method="POST" class="needs-validation" novalidate>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="John" required>
                            <label for="firstName">First name</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Doe" required>
                            <label for="lastName">Last name</label>
                        </div>
                    </div>
                </div>

                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
                    <label for="email">Email address</label>
                </div>

                <div class="form-floating mb-3 position-relative">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" 
                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" required>
                    <label for="password">Password</label>
                    <button type="button" class="password-toggle" onclick="togglePassword('password')">
                        <i data-feather="eye"></i>
                    </button>
                </div>

                <div class="form-floating mb-3 position-relative">
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                    <label for="confirmPassword">Confirm Password</label>
                    <button type="button" class="password-toggle" onclick="togglePassword('confirmPassword')">
                        <i data-feather="eye"></i>
                    </button>
                </div>

                <div class="password-requirements">
                    <ul>
                        <li class="requirement-unmet" id="req-length">
                            <i data-feather="check-circle"></i>
                            At least 8 characters
                        </li>
                        <li class="requirement-unmet" id="req-uppercase">
                            <i data-feather="check-circle"></i>
                            One uppercase letter
                        </li>
                        <li class="requirement-unmet" id="req-lowercase">
                            <i data-feather="check-circle"></i>
                            One lowercase letter
                        </li>
                        <li class="requirement-unmet" id="req-number">
                            <i data-feather="check-circle"></i>
                            One number
                        </li>
                        <li class="requirement-unmet" id="req-special">
                            <i data-feather="check-circle"></i>
                            One special character
                        </li>
                        <li class="requirement-unmet" id="req-match">
                            <i data-feather="check-circle"></i>
                            Passwords match
                        </li>
                    </ul>
                </div>

                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" id="terms" name="terms" required>
                    <label class="form-check-label text-secondary" for="terms">
                        I agree to the <a href="${pageContext.request.contextPath}/terms" target="_blank">Terms of Service</a> and 
                        <a href="${pageContext.request.contextPath}/privacy" target="_blank">Privacy Policy</a>
                    </label>
                </div>

                <button type="submit" class="btn btn-signup">
                    Create Account
                </button>
            </form>

            <div class="signup-divider">
                <span>or sign up with</span>
            </div>

            <div class="social-signup">
                <button type="button" class="btn-social" onclick="socialSignup('google')">
                    <i data-feather="chrome"></i>
                </button>
                <button type="button" class="btn-social" onclick="socialSignup('github')">
                    <i data-feather="github"></i>
                </button>
                <button type="button" class="btn-social" onclick="socialSignup('gitlab')">
                    <i data-feather="gitlab"></i>
                </button>
            </div>

            <div class="signup-footer">
                <p class="mb-0">
                    Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in</a>
                </p>
            </div>
        </div>
    </div>

    <script>
        // Initialize Feather icons
        feather.replace();

        // Form validation
        (function() {
            'use strict';
            const forms = document.querySelectorAll('.needs-validation');
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();

        // Password visibility toggle
        function togglePassword(id) {
            const password = document.getElementById(id);
            const icon = password.nextElementSibling.nextElementSibling.querySelector('i');
            if (password.type === 'password') {
                password.type = 'text';
                icon.setAttribute('data-feather', 'eye-off');
            } else {
                password.type = 'password';
                icon.setAttribute('data-feather', 'eye');
            }
            feather.replace();
        }

        // Password requirements validation
        const password = document.getElementById('password');
        const confirmPassword = document.getElementById('confirmPassword');
        const requirements = {
            length: /.{8,}/,
            uppercase: /[A-Z]/,
            lowercase: /[a-z]/,
            number: /[0-9]/,
            special: /[@$!%*?&]/
        };

        function validatePassword() {
            const value = password.value;
            
            // Check each requirement
            document.getElementById('req-length').className = requirements.length.test(value) ? 'requirement-met' : 'requirement-unmet';
            document.getElementById('req-uppercase').className = requirements.uppercase.test(value) ? 'requirement-met' : 'requirement-unmet';
            document.getElementById('req-lowercase').className = requirements.lowercase.test(value) ? 'requirement-met' : 'requirement-unmet';
            document.getElementById('req-number').className = requirements.number.test(value) ? 'requirement-met' : 'requirement-unmet';
            document.getElementById('req-special').className = requirements.special.test(value) ? 'requirement-met' : 'requirement-unmet';
            
            // Check if passwords match
            document.getElementById('req-match').className = 
                (value && value === confirmPassword.value) ? 'requirement-met' : 'requirement-unmet';

            feather.replace();
        }

        password.addEventListener('input', validatePassword);
        confirmPassword.addEventListener('input', validatePassword);

        // Social signup handler
        function socialSignup(provider) {
            // TODO: Implement social signup
            console.log(`Social signup with ${provider}`);
        }

        // Auto-hide alerts after 5 seconds
        setTimeout(() => {
            const alerts = document.querySelectorAll('.alert');
            alerts.forEach(alert => {
                alert.style.transition = 'opacity 0.5s ease';
                alert.style.opacity = '0';
                setTimeout(() => alert.remove(), 500);
            });
        }, 5000);
    </script>
</body>
</html>
