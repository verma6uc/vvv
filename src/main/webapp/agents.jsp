<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>AI Agents - Yuvi</title>
    <%@ include file="/WEB-INF/includes/common/head.jsp" %>
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/includes/common/header.jsp" %>

    <!-- Hero Section -->
    <%@ include file="/WEB-INF/includes/agents/hero.jsp" %>

    <!-- Cognitive Agents -->
    <%@ include file="/WEB-INF/includes/agents/cognitive-agents.jsp" %>

    <!-- Technical Agents -->
    <%@ include file="/WEB-INF/includes/agents/technical-agents.jsp" %>

    <!-- Integration Section -->
    <section class="py-5">
        <div class="container-fluid">
            <div class="glass rounded-4 p-5">
                <div class="row align-items-center">
                    <div class="col-lg-6">
                        <h2 class="display-6 fw-bold mb-4">Seamless Integration</h2>
                        <p class="lead text-medium-contrast mb-4">
                            Our AI agents work together seamlessly through a unified platform, sharing context and collaborating to deliver exceptional results.
                        </p>
                        <div class="d-flex gap-3">
                            <a href="/docs/integration" class="btn btn-primary btn-lg px-4">
                                Integration Guide
                                <i data-feather="book-open" class="ms-2" style="width: 18px; height: 18px;"></i>
                            </a>
                            <a href="/api" class="btn btn-outline-light btn-lg px-4">
                                API Reference
                                <i data-feather="code" class="ms-2" style="width: 18px; height: 18px;"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card glass rounded-4 p-4 mt-4 mt-lg-0">
                            <pre class="mb-0 p-4 rounded-3 bg-dark"><code class="text-info">// Example Agent Integration
const workflow = new AgentWorkflow();

workflow
  .use(agents.calvin)    // Reading Agent
  .use(agents.olivaw)    // Writing Agent
  .use(agents.sqlWriter) // Database Agent
  .use(agents.templater) // Frontend Agent

// Execute workflow
const result = await workflow.execute({
  input: userRequest,
  context: projectData
});</code></pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- CTA Section -->
    <section class="py-5">
        <div class="container-fluid">
            <div class="glass rounded-4 p-5 text-center">
                <h2 class="display-6 fw-bold mb-4">Ready to Meet Your AI Team?</h2>
                <p class="lead text-medium-contrast mb-4 mx-auto" style="max-width: 700px;">
                    Experience the power of AI-driven development with our suite of specialized agents.
                </p>
                <div class="d-flex justify-content-center gap-3">
                    <a href="/signup" class="btn btn-primary btn-lg px-5">
                        Get Started Free
                        <i data-feather="arrow-right" class="ms-2" style="width: 18px; height: 18px;"></i>
                    </a>
                    <a href="/demo" class="btn btn-outline-light btn-lg px-5">
                        Request Demo
                        <i data-feather="play-circle" class="ms-2" style="width: 18px; height: 18px;"></i>
                    </a>
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
