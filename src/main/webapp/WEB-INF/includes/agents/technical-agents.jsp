<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section id="technical-agents" class="py-5">
    <div class="container-fluid">
        <div class="text-center mb-5">
            <h2 class="display-5 fw-bold mb-4">
                Technical Agents: Powering Your Code &<br>
                <span class="text-info">Data with AI</span>
            </h2>
            <p class="lead text-light mx-auto mb-5" style="max-width: 800px;">
                These specialized agents focus on code generation, front-end scaffolding, logic, and effect orchestration—automating the nuts and bolts of application development from SQL schemas to multi-step workflows.
            </p>
        </div>

        <div class="row g-4">
            <!-- Include Individual Agent Components -->
            <%@ include file="/WEB-INF/includes/agents/technical/sql-writer-agent.jsp" %>
            <%@ include file="/WEB-INF/includes/agents/technical/html-templater-agent.jsp" %>
            <%@ include file="/WEB-INF/includes/agents/technical/js-logic-agent.jsp" %>
            <%@ include file="/WEB-INF/includes/agents/technical/action-effects-agent.jsp" %>
        </div>

        <!-- Autonomous Workflows Section -->
        <div class="mt-5 pt-5">
            <div class="text-center mb-5">
                <h2 class="h3 fw-bold text-light mb-4">Creating Autonomous Workflows</h2>
                <p class="lead text-light mx-auto mb-5" style="max-width: 800px;">
                    These specialized agents work together to create powerful, self-driving workflows that minimize manual coding and maximize automation.
                </p>
            </div>

            <div class="card glass rounded-4 p-4">
                <div class="workflow-example">
                    <div class="workflow-step mb-4">
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="file-plus" class="text-info" style="width: 18px; height: 18px;"></i>
                            </div>
                            <h4 class="h6 text-light mb-0">1. Blueprint Definition</h4>
                        </div>
                        <p class="text-light ms-5 mb-0">Creator defines a new "Invoice" entity in the Blueprint</p>
                    </div>

                    <div class="workflow-step mb-4">
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="database" class="text-info" style="width: 18px; height: 18px;"></i>
                            </div>
                            <h4 class="h6 text-light mb-0">2. Database Setup</h4>
                        </div>
                        <p class="text-light ms-5 mb-0">SQL Writer automatically creates the "invoices" table with appropriate columns</p>
                    </div>

                    <div class="workflow-step mb-4">
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="layout" class="text-info" style="width: 18px; height: 18px;"></i>
                            </div>
                            <h4 class="h6 text-light mb-0">3. UI Generation</h4>
                        </div>
                        <p class="text-light ms-5 mb-0">HTML Templater creates invoice form and list view components</p>
                    </div>

                    <div class="workflow-step mb-4">
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="code" class="text-info" style="width: 18px; height: 18px;"></i>
                            </div>
                            <h4 class="h6 text-light mb-0">4. Logic Implementation</h4>
                        </div>
                        <p class="text-light ms-5 mb-0">JS Logic adds form validation and dynamic field behavior</p>
                    </div>

                    <div class="workflow-step">
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="activity" class="text-info" style="width: 18px; height: 18px;"></i>
                            </div>
                            <h4 class="h6 text-light mb-0">5. Workflow Automation</h4>
                        </div>
                        <p class="text-light ms-5 mb-0">Action Effects configures the complete approval workflow</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Explore Button -->
        <div class="text-center mt-5">
            <a href="#" class="btn btn-info btn-lg px-5">
                Start Building with AI
                <i data-feather="arrow-right" class="ms-2" style="width: 18px; height: 18px;"></i>
            </a>
        </div>
    </div>

    <style>
        /* Card Animations */
        .agent-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .agent-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 32px rgba(13, 202, 240, 0.1);
        }

        /* Icon Pulse Animation */
        .icon-pulse {
            position: relative;
        }

        .icon-pulse::after {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            border-radius: 50%;
            border: 2px solid var(--bs-info);
            animation: iconPulse 2s infinite;
        }

        @keyframes iconPulse {
            0% {
                transform: scale(1);
                opacity: 0.5;
            }
            100% {
                transform: scale(1.5);
                opacity: 0;
            }
        }

        /* Feature Item Hover */
        .feature-item {
            transition: transform 0.2s ease;
        }

        .feature-item:hover {
            transform: translateX(5px);
        }

        /* Agent Pattern Animation */
        .agent-overlay {
            position: absolute;
            top: 0;
            right: 0;
            pointer-events: none;
            opacity: 0.5;
            z-index: 0;
        }

        .agent-pattern {
            transform: rotate(45deg);
        }

        /* Glass Dark Cards */
        .glass-dark {
            background: rgba(15, 23, 42, 0.6);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 0.5rem;
        }

        /* Workflow Steps */
        .workflow-step {
            position: relative;
        }

        .workflow-step:not(:last-child)::after {
            content: '';
            position: absolute;
            left: 1.5rem;
            top: 3rem;
            bottom: -1rem;
            width: 2px;
            background: rgba(13, 202, 240, 0.2);
        }

        .step-number {
            width: 2rem;
            height: 2rem;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        /* Code Preview */
        pre {
            background: rgba(15, 23, 42, 0.6);
            border-radius: 0.5rem;
            padding: 1rem;
            margin: 0;
            overflow-x: auto;
        }

        code {
            font-family: 'JetBrains Mono', monospace;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .container-fluid {
                padding-left: 1rem;
                padding-right: 1rem;
            }

            pre {
                font-size: 0.75rem !important;
            }

            .workflow-step:not(:last-child)::after {
                left: 1rem;
            }
        }
    </style>
</section>
