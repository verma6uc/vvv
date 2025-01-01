<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-lg-6">
    <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
        <!-- Icon -->
        <div class="d-flex align-items-center mb-4">
            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                <i data-feather="database" class="text-info" style="width: 24px; height: 24px;"></i>
            </div>
            <div>
                <h3 class="h4 fw-bold mb-1 text-light">SQL Writer Agent</h3>
                <p class="text-info fw-medium mb-0">Database Builder & Query Generator</p>
            </div>
        </div>

        <!-- Core Function -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Core Function</h4>
            <p class="text-light">Translates blueprint definitions into database schemas and optimized SQL queries, automating database operations while ensuring best practices.</p>
        </div>

        <!-- Use Cases -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">Primary Use Cases</h4>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">New Schema Generation</h5>
                <p class="text-light mb-0 small">Automatically creates tables and columns when new entities are defined in the Blueprint, eliminating manual SQL typing.</p>
            </div>
            <div class="card glass-dark p-3 mb-3">
                <h5 class="h6 text-light mb-2">On-the-Fly Updates</h5>
                <p class="text-light mb-0 small">Generates ALTER TABLE statements when Blueprint attributes change, enabling continuous iteration.</p>
            </div>
            <div class="card glass-dark p-3">
                <h5 class="h6 text-light mb-2">Complex Query Creation</h5>
                <p class="text-light mb-0 small">Builds optimized multi-join queries for sophisticated search and reporting features.</p>
            </div>
        </div>

        <!-- Code Preview -->
        <div class="card glass mb-4">
            <div class="card-header bg-transparent border-0 d-flex align-items-center">
                <i data-feather="code" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                <span class="text-info">Generated Schema</span>
            </div>
            <div class="card-body">
                <pre class="mb-0" style="font-size: 0.85rem;"><code class="text-info">CREATE TABLE projects (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  status project_status_enum,
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP,
  CONSTRAINT valid_status 
    CHECK (status IN ('draft','active','completed'))
);</code></pre>
            </div>
        </div>

        <!-- UI Integration -->
        <div class="mb-4">
            <h4 class="h6 text-info mb-3">UI Integration</h4>
            <ul class="list-unstyled">
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="eye" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">SQL preview panel with syntax highlighting</span>
                </li>
                <li class="mb-3 d-flex align-items-center feature-item">
                    <i data-feather="sliders" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Auto-run configuration toggles</span>
                </li>
                <li class="d-flex align-items-center feature-item">
                    <i data-feather="bell" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                    <span class="text-light">Real-time success notifications</span>
                </li>
            </ul>
        </div>

        <!-- Animation Overlay -->
        <div class="position-absolute agent-overlay">
            <svg class="agent-pattern" viewBox="0 0 100 100" width="200" height="200">
                <defs>
                    <linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="100%">
                        <stop offset="0%" style="stop-color:rgba(13,202,240,0.1);stop-opacity:1" />
                        <stop offset="100%" style="stop-color:rgba(13,202,240,0);stop-opacity:1" />
                    </linearGradient>
                </defs>
                <path d="M10,30 Q50,10 90,30 T90,70 T10,70 T10,30" fill="none" stroke="url(#grad1)" stroke-width="0.5">
                    <animate attributeName="d" 
                        dur="15s" 
                        repeatCount="indefinite"
                        values="M10,30 Q50,10 90,30 T90,70 T10,70 T10,30;
                                M10,50 Q50,30 90,50 T90,90 T10,90 T10,50;
                                M10,30 Q50,10 90,30 T90,70 T10,70 T10,30"/>
                </path>
            </svg>
        </div>
    </div>
</div>
