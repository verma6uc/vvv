<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Transform ideas into intelligent applications with AI-powered development tools">
<meta name="theme-color" content="#0F172A">

<!-- SEO Tags -->
<meta property="og:title" content="${param.title}">
<meta property="og:description" content="Transform ideas into intelligent applications with AI-powered development tools">
<meta property="og:image" content="${pageContext.request.contextPath}/assets/images/yuvi-logo.svg">
<meta property="og:type" content="website">
<meta name="twitter:card" content="summary_large_image">

<!-- Security Headers -->
<meta http-equiv="X-Content-Type-Options" content="nosniff">
<meta http-equiv="X-Frame-Options" content="SAMEORIGIN">
<meta http-equiv="Strict-Transport-Security" content="max-age=31536000; includeSubDomains">

<!-- Favicon -->
<link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/assets/images/yuvi-logo.svg">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" rel="stylesheet">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/assets/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/components.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/layout.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/utilities.css" rel="stylesheet">

<!-- Title -->
<title>${param.title} | Yuvi Platform</title>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" defer></script>
<script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
<script>
    // Initialize Feather Icons
    document.addEventListener('DOMContentLoaded', function() {
        feather.replace();
    });

    // Global error handler
    window.onerror = function(msg, url, lineNo, columnNo, error) {
        console.error('Error: ' + msg + '\nURL: ' + url + '\nLine: ' + lineNo + '\nColumn: ' + columnNo + '\nError object: ' + JSON.stringify(error));
        return false;
    };

    // Global AJAX error handler
    window.addEventListener('unhandledrejection', function(event) {
        console.error('Unhandled promise rejection:', event.reason);
    });
</script>
