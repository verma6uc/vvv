<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-6">
    <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
        <!-- Icon -->
        <div class="d-flex align-items-center mb-4">
            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                <i data-feather="code" class="text-info" style="width: 24px; height: 24px;"></i>
            </div>
            <div>
                <h3 class="h4 fw-bold mb-1 text-light">JS Logic Agent</h3>
                <p class="text-info fw-medium mb-0">Interactive Behavior Generator</p>
            </div>
        </div>

        <!-- Core Function -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Core Function</h4>
            <p class="text-light">Handles front-end and simple back-end scripting, from form validations to dynamic UI updates, ensuring smooth user interactions.</p>
        </div>

        <!-- Use Cases -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Primary Use Cases</h4>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">Front-End Interactivity</h5>
                <p class="text-light mb-0 small">Manages conditional rendering, field validations, and dynamic UI updates based on user actions.</p>
            </div>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">Realtime Data Updates</h5>
                <p class="text-light mb-0 small">Implements polling and partial reloading for live data synchronization.</p>
            </div>
            <div class="card glass-dark p-3">
                <h5 class="h6 text-light mb-2">Server-Side Scripts</h5>
                <p class="text-light mb-0 small">Generates Node.js route handlers and function stubs for microservices.</p>
            </div>
        </div>

        <!-- Code Preview -->
        <div class="card glass mb-4">
            <div class="card-header bg-transparent border-0 d-flex align-items-center">
                <i data-feather="code" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                <span class="text-info">Generated Validation</span>
            </div>
            <div class="card-body">
                <pre class="mb-0" style="font-size: 0.85rem;"><code class="text-info">function validateProjectForm() {
  const name = document.getElementById('name');
  const status = document.getElementById('status');
  
  if (!name.value.trim()) {
    showError(name, 'Project name is required');
    return false;
  }
  
  if (!status.value) {
    showError(status, 'Please select a status');
    return false;
  }
  
  return true;
}</code></pre>
            </div>
        </div>

        <!-- UI Integration -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">UI Integration</h4>
            <ul class="list-unstyled">
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="play-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Live code testing environment</span>
                </li>
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="git-merge" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Blueprint integration hooks</span>
                </li>
                <li class="d-flex align-items-center feature-item">
                    <i data-feather="refresh-cw" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Auto-update on changes</span>
                </li>
            </ul>
        </div>

        <!-- Animation Overlay -->
        <div class="position-absolute agent-overlay">
            <svg class="agent-pattern" viewBox="0 0 100 100" width="200" height="200">
                <defs>
                    <linearGradient id="grad3" x1="0%" y1="0%" x2="100%" y2="100%">
                        <stop offset="0%" style="stop-color:rgba(13,202,240,0.1);stop-opacity:1" />
                        <stop offset="100%" style="stop-color:rgba(13,202,240,0);stop-opacity:1" />
                    </linearGradient>
                </defs>
                <path d="M30,10 Q50,30 70,10 T70,90 T30,90 T30,10" fill="none" stroke="url(#grad3)" stroke-width="0.5">
                    <animate attributeName="d" 
                        dur="20s" 
                        repeatCount="indefinite"
                        values="M30,10 Q50,30 70,10 T70,90 T30,90 T30,10;
                                M30,30 Q50,50 70,30 T70,110 T30,110 T30,30;
                                M30,10 Q50,30 70,10 T70,90 T30,90 T30,10"/>
                </path>
            </svg>
        </div>
    </div>
</div>
