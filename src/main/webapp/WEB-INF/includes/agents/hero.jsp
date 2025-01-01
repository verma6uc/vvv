<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="hero py-5 min-vh-100 d-flex align-items-center">
    <div class="container-fluid">
        <div class="text-center mb-5">
            <h1 class="display-3 fw-bold mb-4">
                Meet the Agents Powering Yuvi's<br>
                <span class="text-info">Intelligent Workflows</span>
            </h1>
            <p class="lead text-medium-contrast mx-auto mb-5" style="max-width: 800px;">
                "Because in Yuvi, your vision becomes our mission—and our AI Agents make that transformation faster, smarter, and more seamless than ever."
            </p>
        </div>

        <!-- Workflow Animation -->
        <div class="workflow-animation position-relative mx-auto" style="max-width: 800px;">
            <!-- Input Node -->
            <div class="workflow-node input-node position-relative d-inline-flex align-items-center justify-content-center rounded-circle bg-dark border border-info" style="width: 120px; height: 120px;">
                <div class="position-relative">
                    <div class="text-info fw-bold">Input</div>
                    <div class="workflow-pulse"></div>
                </div>
            </div>

            <!-- Connecting Line Left -->
            <div class="workflow-line position-relative d-inline-block mx-4" style="width: 100px;">
                <div class="workflow-dot-animation">
                    <div class="workflow-dot bg-info rounded-circle position-absolute" style="width: 6px; height: 6px;"></div>
                </div>
                <hr class="border-2 border-info opacity-25 position-absolute top-50 start-0 end-0 m-0">
            </div>

            <!-- AI Agents Node -->
            <div class="workflow-node agents-node position-relative d-inline-flex align-items-center justify-content-center rounded-circle bg-dark border border-info" style="width: 160px; height: 160px;">
                <div class="position-relative text-center">
                    <div class="text-info fw-bold">AI Agents</div>
                    <div class="text-info-50 small">Processing</div>
                    <div class="workflow-processing-animation"></div>
                </div>
            </div>

            <!-- Connecting Line Right -->
            <div class="workflow-line position-relative d-inline-block mx-4" style="width: 100px;">
                <div class="workflow-dot-animation">
                    <div class="workflow-dot bg-info rounded-circle position-absolute" style="width: 6px; height: 6px;"></div>
                </div>
                <hr class="border-2 border-info opacity-25 position-absolute top-50 start-0 end-0 m-0">
            </div>

            <!-- Result Node -->
            <div class="workflow-node result-node position-relative d-inline-flex align-items-center justify-content-center rounded-circle bg-dark border border-info" style="width: 120px; height: 120px;">
                <div class="position-relative">
                    <div class="text-info fw-bold">Result</div>
                    <div class="workflow-pulse"></div>
                </div>
            </div>
        </div>

        <!-- Background Glow -->
        <div class="position-absolute top-50 start-50 translate-middle" style="z-index: -1;">
            <div class="rounded-circle bg-info bg-opacity-10" style="width: 600px; height: 600px; filter: blur(100px);"></div>
        </div>
    </div>

    <style>
        /* Workflow Animations */
        .workflow-pulse {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 100%;
            height: 100%;
            border: 2px solid var(--bs-info);
            border-radius: 50%;
            animation: pulse 2s infinite;
        }

        @keyframes pulse {
            0% {
                transform: translate(-50%, -50%) scale(1);
                opacity: 0.5;
            }
            100% {
                transform: translate(-50%, -50%) scale(1.5);
                opacity: 0;
            }
        }

        .workflow-dot-animation {
            animation: moveDot 2s infinite linear;
        }

        @keyframes moveDot {
            0% {
                transform: translateX(0);
            }
            100% {
                transform: translateX(100px);
            }
        }

        .workflow-processing-animation::after {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 40px;
            height: 40px;
            border: 3px solid transparent;
            border-top-color: var(--bs-info);
            border-radius: 50%;
            animation: spin 1s infinite linear;
        }

        @keyframes spin {
            0% {
                transform: translate(-50%, -50%) rotate(0deg);
            }
            100% {
                transform: translate(-50%, -50%) rotate(360deg);
            }
        }

        /* Glass Effect */
        .workflow-node {
            backdrop-filter: blur(10px);
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .workflow-node:hover {
            transform: translateY(-5px);
        }
    </style>
</section>
