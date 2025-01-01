<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/includes/common/head.jsp">
        <jsp:param name="title" value="Sign In - Yuvi Platform" />
    </jsp:include>
    <style>
        .login-container {
            min-height: 100vh;
            background: linear-gradient(135deg, #121315 0%, #1C1D21 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .login-card {
            width: 100%;
            max-width: 420px;
            background: rgba(43, 45, 49, 0.5);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 1rem;
            padding: 2rem;
        }

        .login-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .login-logo {
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

        .btn-login {
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

        .btn-login:hover {
            background: #2D9BF0;
            transform: translateY(-1px);
        }

        .login-footer {
            text-align: center;
            margin-top: 1.5rem;
            color: #B0B0B0;
        }

        .login-footer a {
            color: #08A0F8;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .login-footer a:hover {
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

        .login-divider {
            display: flex;
            align-items: center;
            text-align: center;
            margin: 1.5rem 0;
            color: #B0B0B0;
        }

        .login-divider::before,
        .login-divider::after {
            content: '';
            flex: 1;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }

        .login-divider span {
            padding: 0 1rem;
        }

        .social-login {
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
    </style>
</head>
<body class="bg-dark">
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <img src="${pageContext.request.contextPath}/assets/images/yuvi-logo.svg" alt="Yuvi Logo" class="login-logo">
                <h1 class="h4 text-light mb-3">Welcome Back</h1>
                <p class="text-secondary mb-4">Sign in to continue to Yuvi</p>
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

            <form action="${pageContext.request.contextPath}/login" method="POST" class="needs-validation" novalidate>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
                    <label for="email">Email address</label>
                </div>

                <div class="form-floating mb-3 position-relative">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                    <label for="password">Password</label>
                    <button type="button" class="password-toggle" onclick="togglePassword()">
                        <i data-feather="eye"></i>
                    </button>
                </div>

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="remember" name="remember">
                        <label class="form-check-label text-secondary" for="remember">Remember me</label>
                    </div>
                    <a href="${pageContext.request.contextPath}/forgot-password" class="text-info text-decoration-none">Forgot password?</a>
                </div>

                <button type="submit" class="btn btn-login">
                    Sign In
                </button>
            </form>

            <div class="login-divider">
                <span>or continue with</span>
            </div>

            <div class="social-login">
                <button type="button" class="btn-social" onclick="socialLogin('google')">
                    <i data-feather="chrome"></i>
                </button>
                <button type="button" class="btn-social" onclick="socialLogin('github')">
                    <i data-feather="github"></i>
                </button>
                <button type="button" class="btn-social" onclick="socialLogin('gitlab')">
                    <i data-feather="gitlab"></i>
                </button>
            </div>

            <div class="login-footer">
                <p class="mb-0">
                    Don't have an account? <a href="${pageContext.request.contextPath}/signup">Sign up</a>
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
        function togglePassword() {
            const password = document.getElementById('password');
            const icon = document.querySelector('.password-toggle i');
            if (password.type === 'password') {
                password.type = 'text';
                icon.setAttribute('data-feather', 'eye-off');
            } else {
                password.type = 'password';
                icon.setAttribute('data-feather', 'eye');
            }
            feather.replace();
        }

        // Social login handler
        function socialLogin(provider) {
            // TODO: Implement social login
            console.log(`Social login with ${provider}`);
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
