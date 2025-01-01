<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-6">
    <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
        <!-- Icon -->
        <div class="d-flex align-items-center mb-4">
            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                <i data-feather="activity" class="text-info" style="width: 24px; height: 24px;"></i>
            </div>
            <div>
                <h3 class="h4 fw-bold mb-1 text-light">Action Effects Agent</h3>
                <p class="text-info fw-medium mb-0">Workflow Orchestrator</p>
            </div>
        </div>

        <!-- Core Function -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Core Function</h4>
            <p class="text-light">Orchestrates multi-step effect chains for complex actions, ensuring all operations from database updates to notifications execute in the correct sequence.</p>
        </div>

        <!-- Use Cases -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Primary Use Cases</h4>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">Approval Flows</h5>
                <p class="text-light mb-0 small">Manages complete approval sequences including status updates, notifications, and audit logging.</p>
            </div>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">Publishing Workflows</h5>
                <p class="text-light mb-0 small">Handles content publishing with environment deployment, notifications, and state management.</p>
            </div>
            <div class="card glass-dark p-3">
                <h5 class="h6 text-light mb-2">Complex Auto-Responses</h5>
                <p class="text-light mb-0 small">Coordinates multi-step responses to user actions with automated assignments and notifications.</p>
            </div>
        </div>

        <!-- Workflow Preview -->
        <div class="card glass mb-4">
            <div class="card-header bg-transparent border-0 d-flex align-items-center">
                <i data-feather="git-branch" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                <span class="text-info">Effect Chain</span>
            </div>
            <div class="card-body">
                <div class="workflow-step d-flex align-items-center mb-3">
                    <div class="step-number bg-info bg-opacity-10 rounded-circle p-2 me-3">
                        <span class="text-info">1</span>
                    </div>
                    <span class="text-light">Update project status to "Approved"</span>
                </div>
                <div class="workflow-step d-flex align-items-center mb-3">
                    <div class="step-number bg-info bg-opacity-10 rounded-circle p-2 me-3">
                        <span class="text-info">2</span>
                    </div>
                    <span class="text-light">Send approval notification email</span>
                </div>
                <div class="workflow-step d-flex align-items-center">
                    <div class="step-number bg-info bg-opacity-10 rounded-circle p-2 me-3">
                        <span class="text-info">3</span>
                    </div>
                    <span class="text-light">Log approval in audit trail</span>
                </div>
            </div>
        </div>

        <!-- UI Integration -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">UI Integration</h4>
            <ul class="list-unstyled">
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="git-branch" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Visual workflow editor</span>
                </li>
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="play" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Effect chain simulation</span>
                </li>
                <li class="d-flex align-items-center feature-item">
                    <i data-feather="monitor" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Real-time execution monitoring</span>
                </li>
            </ul>
        </div>

        <!-- Animation Overlay -->
        <div class="position-absolute agent-overlay">
            <svg class="agent-pattern" viewBox="0 0 100 100" width="200" height="200">
                <defs>
                    <linearGradient id="grad4" x1="0%" y1="0%" x2="100%" y2="100%">
                        <stop offset="0%" style="stop-color:rgba(13,202,240,0.1);stop-opacity:1" />
                        <stop offset="100%" style="stop-color:rgba(13,202,240,0);stop-opacity:1" />
                    </linearGradient>
                </defs>
                <path d="M40,20 Q60,40 80,20 T80,80 T40,80 T40,20" fill="none" stroke="url(#grad4)" stroke-width="0.5">
                    <animate attributeName="d" 
                        dur="25s" 
                        repeatCount="indefinite"
                        values="M40,20 Q60,40 80,20 T80,80 T40,80 T40,20;
                                M40,40 Q60,60 80,40 T80,100 T40,100 T40,40;
                                M40,20 Q60,40 80,20 T80,80 T40,80 T40,20"/>
                </path>
            </svg>
        </div>
    </div>
</div>
