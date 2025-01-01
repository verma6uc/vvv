<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-6">
    <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
        <!-- Icon -->
        <div class="d-flex align-items-center mb-4">
            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                <i data-feather="layout" class="text-info" style="width: 24px; height: 24px;"></i>
            </div>
            <div>
                <h3 class="h4 fw-bold mb-1 text-light">HTML Templater Agent</h3>
                <p class="text-info fw-medium mb-0">Front-End Scaffolder</p>
            </div>
        </div>

        <!-- Core Function -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Core Function</h4>
            <p class="text-light">Generates front-end HTML scaffolding for pages, forms, and UI modules based on blueprint definitions, ensuring consistent layout and design system compliance.</p>
        </div>

        <!-- Use Cases -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Primary Use Cases</h4>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">Visual PRD Prototypes</h5>
                <p class="text-light mb-0 small">Instantly generates working HTML skeletons from Blueprint page definitions for immediate preview.</p>
            </div>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">Reusable Components</h5>
                <p class="text-light mb-0 small">Creates consistent cards, modals, and list views with dynamic data field integration.</p>
            </div>
            <div class="card glass-dark p-3">
                <h5 class="h6 text-light mb-2">Design System Compliance</h5>
                <p class="text-light mb-0 small">Enforces brand standards through consistent classes and styling rules.</p>
            </div>
        </div>

        <!-- Code Preview -->
        <div class="card glass mb-4">
            <div class="card-header bg-transparent border-0 d-flex align-items-center">
                <i data-feather="code" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                <span class="text-info">Generated Component</span>
            </div>
            <div class="card-body">
                <pre class="mb-0" style="font-size: 0.85rem;"><code class="text-info">&lt;section class="project-card glass"&gt;
  &lt;header class="d-flex align-items-center"&gt;
    &lt;div class="icon-wrapper"&gt;
      &lt;i data-feather="folder"&gt;&lt;/i&gt;
    &lt;/div&gt;
    &lt;h3&gt;{{ project.name }}&lt;/h3&gt;
  &lt;/header&gt;
  &lt;div class="content"&gt;
    &lt;div class="status-badge {{ project.status }}"&gt;
      {{ project.status }}
    &lt;/div&gt;
    &lt;p&gt;{{ project.description }}&lt;/p&gt;
  &lt;/div&gt;
&lt;/section&gt;</code></pre>
            </div>
        </div>

        <!-- UI Integration -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">UI Integration</h4>
            <ul class="list-unstyled">
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="layout" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Live component preview</span>
                </li>
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="edit-2" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Style customization panel</span>
                </li>
                <li class="d-flex align-items-center feature-item">
                    <i data-feather="check-square" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Accessibility validation</span>
                </li>
            </ul>
        </div>

        <!-- Animation Overlay -->
        <div class="position-absolute agent-overlay">
            <svg class="agent-pattern" viewBox="0 0 100 100" width="200" height="200">
                <defs>
                    <linearGradient id="grad2" x1="0%" y1="0%" x2="100%" y2="100%">
                        <stop offset="0%" style="stop-color:rgba(13,202,240,0.1);stop-opacity:1" />
                        <stop offset="100%" style="stop-color:rgba(13,202,240,0);stop-opacity:1" />
                    </linearGradient>
                </defs>
                <path d="M20,20 Q50,50 80,20 T80,80 T20,80 T20,20" fill="none" stroke="url(#grad2)" stroke-width="0.5">
                    <animate attributeName="d" 
                        dur="18s" 
                        repeatCount="indefinite"
                        values="M20,20 Q50,50 80,20 T80,80 T20,80 T20,20;
                                M20,40 Q50,70 80,40 T80,100 T20,100 T20,40;
                                M20,20 Q50,50 80,20 T80,80 T20,80 T20,20"/>
                </path>
            </svg>
        </div>
    </div>
</div>
