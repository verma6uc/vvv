<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>404 - Page Not Found - Yuvi</title>
    <%@ include file="/WEB-INF/includes/common/head.jsp" %>
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/includes/common/header.jsp" %>

    <!-- Error Section -->
    <section class="py-5">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-lg-6 text-center">
                    <div class="mb-4">
                        <i data-feather="map" class="text-info" style="width: 64px; height: 64px;"></i>
                    </div>
                    <h1 class="display-4 fw-bold mb-4">404</h1>
                    <p class="lead text-medium-contrast mb-4">
                        Oops! The page you're looking for seems to have wandered off.
                    </p>
                    <div class="d-flex justify-content-center gap-3">
                        <a href="/" class="btn btn-primary btn-lg px-4">
                            Go Home
                            <i data-feather="home" class="ms-2" style="width: 18px; height: 18px;"></i>
                        </a>
                        <a href="/contact" class="btn btn-outline-light btn-lg px-4">
                            Contact Support
                            <i data-feather="help-circle" class="ms-2" style="width: 18px; height: 18px;"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <%@ include file="/WEB-INF/includes/common/footer.jsp" %>

    <!-- Common Scripts -->
    <%@ include file="/WEB-INF/includes/common/scripts.jsp" %>
</body>
</html>
