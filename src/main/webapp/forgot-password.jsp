<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/includes/common/head.jsp">
        <jsp:param name="title" value="Forgot Password - Yuvi Platform" />
    </jsp:include>
    <style>
        .forgot-container {
            min-height: 100vh;
            background: linear-gradient(135deg, #121315 0%, #1C1D21 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .forgot-card {
            width: 100%;
            max-width: 420px;
            background: rgba(43, 45, 49, 0.5);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 1rem;
            padding: 2rem;
        }

        .forgot-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .forgot-logo {
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

        .btn-reset {
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

        .btn-reset:hover {
            background: #2D9BF0;
            transform: translateY(-1px);
        }

        .forgot-footer {
            text-align: center;
            margin-top: 1.5rem;
            color: #B0B0B0;
        }

        .forgot-footer a {
            color: #08A0F8;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .forgot-footer a:hover {
            color: #2D9BF0;
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

        .instructions {
            color: #B0B0B0;
            font-size: 0.875rem;
            margin-bottom: 1.5rem;
            text-align: center;
        }

        .back-to-login {
            display: inline-flex;
            align-items: center;
            color: #08A0F8;
            text-decoration: none;
            transition: color 0.3s ease;
            margin-bottom: 1.5rem;
        }

        .back-to-login:hover {
            color: #2D9BF0;
        }

        .back-to-login i {
            margin-right: 0.5rem;
        }
    </style>
</head>
<body class="bg-dark">
    <div class="forgot-container">
        <div class="forgot-card">
            <div class="forgot-header">
                <img src="${pageContext.request.contextPath}/assets/images/yuvi-logo.svg" alt="Yuvi Logo" class="forgot-logo">
                <h1 class="h4 text-light mb-3">Reset Your Password</h1>
                <p class="text-secondary mb-4">Enter your email to receive reset instructions</p>
            </div>

            <a href="${pageContext.request.contextPath}/login" class="back-to-login">
                <i data-feather="arrow-left" style="width: 16px; height: 16px;"></i>
                Back to Login
            </a>

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

            <form action="${pageContext.request.contextPath}/forgot-password" method="POST" class="needs-validation" novalidate>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com" required>
                    <label for="email">Email address</label>
                </div>

                <button type="submit" class="btn btn-reset">
                    Send Reset Instructions
                </button>
            </form>

            <div class="instructions mt-4">
                <p class="mb-0">
                    We'll send you an email with instructions to reset your password. 
                    If you don't receive the email within a few minutes, please check your spam folder.
                </p>
            </div>

            <div class="forgot-footer">
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
