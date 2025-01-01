<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section id="technical-agents" class="py-5">
    <div class="container-fluid">
        <div class="text-center mb-5">
            <h2 class="display-5 fw-bold mb-4">
                Technical Agents: Powering Your Code &<br>
                <span class="text-info">Data with AI</span>
            </h2>
            <p class="lead text-light mx-auto mb-5" style="max-width: 800px;">
                Specialized AI agents that accelerate development by automating database operations, front-end scaffolding, and JavaScript logic—maintaining consistent code quality across your application.
            </p>
        </div>

        <div class="row g-4">
            <!-- SQL Writer Agent -->
            <div class="col-md-6">
                <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
                    <!-- Icon -->
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                            <i data-feather="database" class="text-info" style="width: 24px; height: 24px;"></i>
                        </div>
                        <div>
                            <h3 class="h4 fw-bold mb-1 text-light">SQL Writer Agent</h3>
                            <p class="text-info fw-medium mb-0">Generates and optimizes database queries</p>
                        </div>
                    </div>

                    <!-- Code Preview -->
                    <div class="card glass mb-4">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-2">
                                <i data-feather="code" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                <span class="text-info">Generated Query</span>
                            </div>
                            <pre class="mb-0" style="font-size: 0.85rem;"><code class="text-info">SELECT p.name, COUNT(t.id) as tasks
FROM projects p
LEFT JOIN tasks t ON p.id = t.project_id
WHERE p.status = 'active'
GROUP BY p.name
HAVING COUNT(t.id) > 5
ORDER BY tasks DESC;</code></pre>
                        </div>
                    </div>

                    <!-- Features -->
                    <ul class="list-unstyled mb-4">
                        <li class="mb-3 d-flex align-items-center feature-item">
                            <i data-feather="check-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                            <span class="text-light">Smart query generation</span>
                        </li>
                        <li class="mb-3 d-flex align-items-center feature-item">
                            <i data-feather="check-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                            <span class="text-light">Performance optimization</span>
                        </li>
                        <li class="d-flex align-items-center feature-item">
                            <i data-feather="check-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                            <span class="text-light">Schema suggestions</span>
                        </li>
                    </ul>

                    <!-- Animation Overlay -->
                    <div class="position-absolute agent-overlay">
                        <svg class="agent-pattern" viewBox="0 0 100 100" width="200" height="200">
                            <defs>
                                <linearGradient id="grad3" x1="0%" y1="0%" x2="100%" y2="100%">
                                    <stop offset="0%" style="stop-color:rgba(13,202,240,0.1);stop-opacity:1" />
                                    <stop offset="100%" style="stop-color:rgba(13,202,240,0);stop-opacity:1" />
                                </linearGradient>
                            </defs>
                            <path d="M10,30 Q50,10 90,30 T90,70 T10,70 T10,30" fill="none" stroke="url(#grad3)" stroke-width="0.5">
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

            <!-- HTML Templater Agent -->
            <div class="col-md-6">
                <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
                    <!-- Icon -->
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                            <i data-feather="layout" class="text-info" style="width: 24px; height: 24px;"></i>
                        </div>
                        <div>
                            <h3 class="h4 fw-bold mb-1 text-light">HTML Templater Agent</h3>
                            <p class="text-info fw-medium mb-0">Builds structured front-end layouts</p>
                        </div>
                    </div>

                    <!-- Code Preview -->
                    <div class="card glass mb-4">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-2">
                                <i data-feather="code" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                <span class="text-info">Generated Component</span>
                            </div>
                            <pre class="mb-0" style="font-size: 0.85rem;"><code class="text-info">&lt;div class="component glass"&gt;
  &lt;div class="header"&gt;
    &lt;div class="icon-wrapper"&gt;
      &lt;i data-feather="star"&gt;&lt;/i&gt;
    &lt;/div&gt;
    &lt;h3&gt;{{ title }}&lt;/h3&gt;
  &lt;/div&gt;
  &lt;div class="content"&gt;
    {{ content }}
  &lt;/div&gt;
&lt;/div&gt;</code></pre>
                        </div>
                    </div>

                    <!-- Features -->
                    <ul class="list-unstyled mb-4">
                        <li class="mb-3 d-flex align-items-center feature-item">
                            <i data-feather="check-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                            <span class="text-light">Component generation</span>
                        </li>
                        <li class="mb-3 d-flex align-items-center feature-item">
                            <i data-feather="check-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                            <span class="text-light">Responsive layouts</span>
                        </li>
                        <li class="d-flex align-items-center feature-item">
                            <i data-feather="check-circle" class="text-info me-2" style="width: 18px; height: 18px;"></i>
                            <span class="text-light">Accessibility built-in</span>
                        </li>
                    </ul>

                    <!-- Animation Overlay -->
                    <div class="position-absolute agent-overlay">
                        <svg class="agent-pattern" viewBox="0 0 100 100" width="200" height="200">
                            <defs>
                                <linearGradient id="grad4" x1="0%" y1="0%" x2="100%" y2="100%">
                                    <stop offset="0%" style="stop-color:rgba(13,202,240,0.1);stop-opacity:1" />
                                    <stop offset="100%" style="stop-color:rgba(13,202,240,0);stop-opacity:1" />
                                </linearGradient>
                            </defs>
                            <path d="M20,20 Q50,50 80,20 T80,80 T20,80 T20,20" fill="none" stroke="url(#grad4)" stroke-width="0.5">
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
        </div>

        <!-- Explore Button -->
        <div class="text-center mt-5">
            <a href="#" class="btn btn-info btn-lg px-5">
                Explore More Agents
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
            top: 0;
            right: 0;
            pointer-events: none;
            opacity: 0.5;
            z-index: 0;
        }

        .agent-pattern {
            transform: rotate(45deg);
        }
    </style>
</section>
