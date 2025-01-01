<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Features - Yuvi</title>
    <%@ include file="/WEB-INF/includes/common/head.jsp" %>
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/includes/common/header.jsp" %>

    <!-- Hero Section -->
    <section class="hero py-5">
        <div class="container-fluid">
            <div class="text-center">
                <h1 class="display-4 fw-bold mb-4">
                    Powerful Features for <span class="text-info">Modern Development</span>
                </h1>
                <p class="lead text-medium-contrast mb-4 mx-auto" style="max-width: 800px;">
                    Explore our comprehensive suite of tools and capabilities designed to streamline your software development process.
                </p>
            </div>
        </div>
    </section>

    <!-- Feature Set Discovery -->
    <section class="py-5">
        <div class="container-fluid">
            <div class="card glass rounded-4 p-5 text-center">
                <div class="mb-4">
                    <i data-feather="settings" class="text-info" style="width: 48px; height: 48px;"></i>
                </div>
                <h2 class="h3 fw-bold mb-4">Discover Yuvi's Powerful Feature Set</h2>
                <p class="text-medium-contrast mb-0 mx-auto" style="max-width: 800px;">
                    From multi-tenant control to AI-based workflows, Yuvi provides every tool you need to accelerate application development and bring your ideas to life. Here's an in-depth look at how our platform simplifies your build process while boosting creativity through our signature Memory→Blueprint→Visual PRD approach.
                </p>
            </div>
        </div>
    </section>

    <!-- Lifecycle Section -->
    <section class="py-5">
        <div class="container-fluid">
            <div class="text-center mb-5">
                <h2 class="display-5 fw-bold mb-4">From Idea to Interactive Prototype: The Yuvi Lifecycle</h2>
                <p class="lead text-medium-contrast mb-4 mx-auto" style="max-width: 800px;">
                    Yuvi's three-step process streamlines creation—from capturing raw ideas in Memory to systematically organizing them in the Blueprint, then generating an interactive Visual PRD for real-world feedback.
                </p>
            </div>

            <div class="row g-4">
                <!-- Memory -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="layers" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h3 class="h4 fw-bold mb-1">Memory</h3>
                                <p class="text-info mb-0">Step 1</p>
                            </div>
                        </div>
                        <p class="text-medium-contrast mb-4">
                            Collect and refine concepts, constraints, personas, or research notes using memory blocks. No more scattered notes—Yuvi stores everything in a structured, versionable format.
                        </p>
                        <div class="card glass bg-opacity-50">
                            <div class="card-body">
                                <h4 class="h6 fw-bold text-info mb-3">Example:</h4>
                                <p class="mb-0 text-medium-contrast">
                                    Define user personas, outline initial features, and capture key requirements in a structured format.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Blueprint -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="layout" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h3 class="h4 fw-bold mb-1">Blueprint</h3>
                                <p class="text-info mb-0">Step 2</p>
                            </div>
                        </div>
                        <p class="text-medium-contrast mb-4">
                            Convert raw ideas into an organized design. Define roles, pages, features, entity actions, and relationships. Ensure a coherent plan before coding and collaborate easily with stakeholders.
                        </p>
                        <div class="card glass bg-opacity-50">
                            <div class="card-body">
                                <h4 class="h6 fw-bold text-info mb-3">Example:</h4>
                                <p class="mb-0 text-medium-contrast">
                                    Assign roles like 'Audit Admin,' 'Billing Admin,' and map out pages: 'Dashboard,' 'Settings.'
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Visual PRD -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="monitor" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h3 class="h4 fw-bold mb-1">Visual PRD</h3>
                                <p class="text-info mb-0">Step 3</p>
                            </div>
                        </div>
                        <p class="text-medium-contrast mb-4">
                            Generate a real-feel UI representation with synthetic HTML so teams can see how the app will look and feel. Fewer surprises at dev time—gather feedback early and iterate quickly.
                        </p>
                        <div class="card glass bg-opacity-50">
                            <div class="card-body">
                                <h4 class="h6 fw-bold text-info mb-3">Example:</h4>
                                <p class="mb-0 text-medium-contrast">
                                    Stakeholders see a clickable UI mock instead of a vague document, enabling real-world feedback.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center mt-4">
                <a href="/docs/lifecycle" class="btn btn-outline-light px-4 py-2">
                    Learn more about Yuvi's lifecycle
                    <i data-feather="arrow-right" class="ms-2" style="width: 18px; height: 18px;"></i>
                </a>
            </div>
        </div>
    </section>

    <!-- Core Feature Categories -->
    <section class="py-5">
        <div class="container-fluid">
            <h2 class="text-center display-5 fw-bold mb-4">Core Feature Categories</h2>
            <p class="text-center lead text-medium-contrast mb-5 mx-auto" style="max-width: 800px;">
                Yuvi's capabilities span multiple domains—from AI-driven workflows to multi-tenant architecture. Explore the key pillars below to see how each addresses different stages of app creation.
            </p>

            <div class="row g-4">
                <!-- AI Agentic Capabilities -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="cpu" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">AI Agentic Capabilities</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Reading & Writing Agents</span></li>
                            <li><i data-feather="check-circle"></i><span>Planning & Orchestration</span></li>
                            <li><i data-feather="check-circle"></i><span>HTML Templating & SQL Writer</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Multi-Tenant & Role Management -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="users" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Multi-Tenant & Role Management</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Company-level roles vs. system roles</span></li>
                            <li><i data-feather="check-circle"></i><span>Spaces for hierarchical team structure</span></li>
                            <li><i data-feather="check-circle"></i><span>Audit logs for compliance</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Developer-Focused Tools -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="code" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Developer-Focused Tools</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Section queries for dynamic data</span></li>
                            <li><i data-feather="check-circle"></i><span>Action effects for complex workflows</span></li>
                            <li><i data-feather="check-circle"></i><span>API endpoints for integrations</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Audit & Compliance -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="shield" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Audit & Compliance</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Comprehensive audit trails</span></li>
                            <li><i data-feather="check-circle"></i><span>Activity monitoring & logging</span></li>
                            <li><i data-feather="check-circle"></i><span>Security & access controls</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Customization & Theming -->
                <div class="col-lg-4">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="droplet" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Customization & Theming</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Brand archetype integration</span></li>
                            <li><i data-feather="check-circle"></i><span>Dynamic color schemes</span></li>
                            <li><i data-feather="check-circle"></i><span>Custom styling & layouts</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Enterprise Multi-Tenancy -->
    <section class="py-5">
        <div class="container-fluid">
            <h2 class="text-center display-5 fw-bold mb-4">Enterprise-Grade Multi-Tenancy & Role Management</h2>
            <p class="text-center lead text-medium-contrast mb-5 mx-auto" style="max-width: 800px;">
                Yuvi's multi-tenant architecture lets you host multiple companies (tenants) under one platform, each with distinct branding, user roles, and data isolation. Perfect for agencies, enterprise groups, or large teams.
            </p>

            <div class="row g-4">
                <!-- Separate Data & Config -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="database" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Separate Data & Config</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Each tenant has its own settings, brand archetype, and can have different roles</span></li>
                            <li><i data-feather="check-circle"></i><span>No concerns about data or role leakage</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Role Management -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="users" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Role Management</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Combine system-wide and company-level roles</span></li>
                            <li><i data-feather="check-circle"></i><span>Define custom roles like 'Billing Admin' or 'Content Editor'</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Spaces & Hierarchies -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="git-branch" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Spaces & Hierarchies</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Create sub-teams or departmental divisions</span></li>
                            <li><i data-feather="check-circle"></i><span>Assign user statuses: ACTIVE, INACTIVE, PENDING, or SUSPENDED</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Audit & Compliance -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="shield" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Audit & Compliance</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Track changes across all companies with a global or per-tenant lens</span></li>
                            <li><i data-feather="check-circle"></i><span>Record user activity and system changes in real-time</span></li>
                            <li><i data-feather="check-circle"></i><span>Export audit logs for compliance reporting</span></li>
                            <li><i data-feather="check-circle"></i><span>Set custom retention policies per tenant</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Advanced Features -->
    <section class="py-5">
        <div class="container-fluid">
            <h2 class="text-center display-5 fw-bold mb-4">Going Further: Advanced Features & Next Steps</h2>
            <p class="text-center lead text-medium-contrast mb-5 mx-auto" style="max-width: 800px;">
                Beyond the core pillars, Yuvi's advanced options—like project plans, custom deployment schedules, and a data lineage system—give you an even deeper toolkit for specialized projects.
            </p>

            <div class="row g-4">
                <!-- Data Lineage & Visualization -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="git-merge" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Data Lineage & Visualization</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Visualize how data flows through your workflow</span></li>
                            <li><i data-feather="check-circle"></i><span>Track dependencies and relationships</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Project Planning -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="calendar" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Project Planning</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Manage tasks, dependencies, and agent assignments</span></li>
                            <li><i data-feather="check-circle"></i><span>Track progress and milestones</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Advanced Integration -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="box" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Advanced Integration</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Build plugins, third-party add-ons, and custom agents</span></li>
                            <li><i data-feather="check-circle"></i><span>Extension API support for external integrations</span></li>
                        </ul>
                    </div>
                </div>

                <!-- Custom Deployment -->
                <div class="col-lg-6">
                    <div class="card glass h-100 rounded-4 p-4">
                        <div class="d-flex align-items-center mb-4">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="upload-cloud" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <h3 class="h4 fw-bold mb-0">Custom Deployment</h3>
                        </div>
                        <ul class="feature-list mb-0">
                            <li><i data-feather="check-circle"></i><span>Flexible deployment options and schedules</span></li>
                            <li><i data-feather="check-circle"></i><span>Advanced configuration management</span></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="text-center mt-5">
                <a href="/signup" class="btn btn-primary btn-lg px-5">
                    Start Building with Yuvi
                    <i data-feather="arrow-right" class="ms-2" style="width: 18px; height: 18px;"></i>
                </a>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <%@ include file="/WEB-INF/includes/common/footer.jsp" %>

    <!-- Common Scripts -->
    <%@ include file="/WEB-INF/includes/common/scripts.jsp" %>
</body>
</html>
