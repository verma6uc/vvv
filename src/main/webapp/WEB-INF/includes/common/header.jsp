<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img src="${pageContext.request.contextPath}/assets/images/yuvi-logo.svg" alt="YUVI" style="height: 32px;">
        </a>
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="/features">Features</a></li>
                <li class="nav-item"><a class="nav-link" href="/solutions">Solutions</a></li>
                <li class="nav-item"><a class="nav-link" href="/agents">Agents</a></li>
                <li class="nav-item"><a class="nav-link" href="/docs">Docs</a></li>
            </ul>
            <div class="d-flex gap-3">
                <a href="/signin" class="btn btn-outline-light px-4">Sign In</a>
                <a href="/signup" class="btn btn-primary px-4">Sign Up</a>
            </div>
        </div>
    </div>
</header>
