<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section id="cognitive-agents" class="py-5">
    <div class="container-fluid">
        <div class="text-center mb-5">
            <h2 class="display-5 fw-bold mb-4">
                Cognitive Agents: AI-Enabled<br>
                <span class="text-info">Intelligence</span>
            </h2>
            <p class="lead text-light mx-auto mb-5" style="max-width: 800px;">
                These agents handle tasks that mimic human cognitive abilities—reading, writing, reasoning, planning, and searching—based on Isaac Asimov's famous characters. They serve as the brainpower behind data processing, summarization, textual generation, and strategic thinking.
            </p>
            <a href="#technical-agents" class="btn btn-info btn-lg px-4 mb-5">
                Explore Technical Agents
                <i data-feather="chevron-down" class="ms-2" style="width: 18px; height: 18px;"></i>
            </a>
        </div>

        <div class="row g-4">
            <!-- Reading Agent -->
            <div class="col-lg-6">
                <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
                    <!-- Icon -->
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                            <i data-feather="book-open" class="text-info" style="width: 24px; height: 24px;"></i>
                        </div>
                        <div>
                            <h3 class="h4 fw-bold mb-1 text-light">Reading Agent</h3>
                            <p class="text-info fw-medium mb-0">Dr. Susan Calvin</p>
                        </div>
                    </div>

                    <!-- Core Capabilities -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Core Capabilities</h4>
                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Document Parsing & Summarization</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="file-text" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Ingests Word, PDF, or raw text</span>
                                </li>
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="list" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Extracts key points & bullet summaries</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="tag" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Identifies keywords & topics</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Contextual Extraction</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="filter" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Pulls relevant data points</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="database" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Feeds into Memory blocks</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3">
                            <h5 class="h6 text-light mb-2">Semantic Analysis</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="bar-chart-2" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Detects sentiment & tone</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="folder" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Categorizes documents</span>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- Use Cases -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Common Use Cases</h4>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Compliance: Summarize FDA guidelines or legal docs</span>
                        </div>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">User Feedback: Parse surveys & generate insights</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Meeting Notes: Extract tasks & decisions</span>
                        </div>
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
                                    dur="10s" 
                                    repeatCount="indefinite"
                                    values="M10,30 Q50,10 90,30 T90,70 T10,70 T10,30;
                                            M10,50 Q50,30 90,50 T90,90 T10,90 T10,50;
                                            M10,30 Q50,10 90,30 T90,70 T10,70 T10,30"/>
                            </path>
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Writing Agent -->
            <div class="col-lg-6">
                <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
                    <!-- Icon -->
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                            <i data-feather="edit-3" class="text-info" style="width: 24px; height: 24px;"></i>
                        </div>
                        <div>
                            <h3 class="h4 fw-bold mb-1 text-light">Writing Agent</h3>
                            <p class="text-info fw-medium mb-0">R. Daneel Olivaw</p>
                        </div>
                    </div>

                    <!-- Core Capabilities -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Core Capabilities</h4>
                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Text Generation & Rewriting</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="edit" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Clarifies & expands rough text</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="file-plus" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Generates new content from outlines</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Grammar & Style Enhancement</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="check-square" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Improves syntax & flow</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="type" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Adjusts tone (formal/casual)</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3">
                            <h5 class="h6 text-light mb-2">Multi-Language Support</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="globe" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Translates content</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="refresh-cw" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Maintains context across languages</span>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- Use Cases -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Common Use Cases</h4>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Marketing: Generate landing page & email copy</span>
                        </div>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Documentation: Convert technical notes to user guides</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Internal: Draft product specs & status updates</span>
                        </div>
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
                                    dur="12s" 
                                    repeatCount="indefinite"
                                    values="M20,20 Q50,50 80,20 T80,80 T20,80 T20,20;
                                            M20,40 Q50,70 80,40 T80,100 T20,100 T20,40;
                                            M20,20 Q50,50 80,20 T80,80 T20,80 T20,20"/>
                            </path>
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Planning Agent -->
            <div class="col-lg-6">
                <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
                    <!-- Icon -->
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                            <i data-feather="calendar" class="text-info" style="width: 24px; height: 24px;"></i>
                        </div>
                        <div>
                            <h3 class="h4 fw-bold mb-1 text-light">Planning Agent</h3>
                            <p class="text-info fw-medium mb-0">Hari Seldon</p>
                        </div>
                    </div>

                    <!-- Core Capabilities -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Core Capabilities</h4>
                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Task Decomposition & Scheduling</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="layers" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Breaks down complex objectives</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="clock" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Suggests timelines & dependencies</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Strategic Orchestration</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="alert-triangle" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Identifies bottlenecks</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="git-merge" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Integrates with Blueprint data</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3">
                            <h5 class="h6 text-light mb-2">High-Level Forecasting</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="trending-up" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Predicts resource usage</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="activity" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Estimates completion times</span>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- Use Cases -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Common Use Cases</h4>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Multi-Step Projects: Product launch planning</span>
                        </div>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Blueprint Guidance: Page & role prioritization</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Team Collaboration: Task & timeline coordination</span>
                        </div>
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
                                    dur="15s" 
                                    repeatCount="indefinite"
                                    values="M30,10 Q50,30 70,10 T70,90 T30,90 T30,10;
                                            M30,30 Q50,50 70,30 T70,110 T30,110 T30,30;
                                            M30,10 Q50,30 70,10 T70,90 T30,90 T30,10"/>
                            </path>
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Search Agent -->
            <div class="col-lg-6">
                <div class="card glass rounded-4 p-4 h-100 position-relative overflow-hidden agent-card">
                    <!-- Icon -->
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3 icon-pulse">
                            <i data-feather="search" class="text-info" style="width: 24px; height: 24px;"></i>
                        </div>
                        <div>
                            <h3 class="h4 fw-bold mb-1 text-light">Search Agent</h3>
                            <p class="text-info fw-medium mb-0">R. Giskard Reventlov</p>
                        </div>
                    </div>

                    <!-- Core Capabilities -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Core Capabilities</h4>
                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Data Retrieval</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="search" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Advanced keyword queries</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="filter" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Smart result ranking</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3 mb-3">
                            <h5 class="h6 text-light mb-2">Contextual Searching</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="link" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Memory & blueprint integration</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="git-branch" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Cross-reference analysis</span>
                                </li>
                            </ul>
                        </div>

                        <div class="card glass-dark p-3">
                            <h5 class="h6 text-light mb-2">Cross-Repository Lookups</h5>
                            <ul class="list-unstyled mb-0">
                                <li class="d-flex align-items-center feature-item mb-2">
                                    <i data-feather="database" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Multi-source search</span>
                                </li>
                                <li class="d-flex align-items-center feature-item">
                                    <i data-feather="layers" class="text-info me-2" style="width: 16px; height: 16px;"></i>
                                    <span class="text-light">Result aggregation</span>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- Use Cases -->
                    <div class="mb-4">
                        <h4 class="h6 text-info mb-3">Common Use Cases</h4>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Competitor Analysis: Find stored research</span>
                        </div>
                        <div class="d-flex align-items-center mb-3">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Knowledge Base: Quick memory block retrieval</span>
                        </div>
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-2 me-3">
                                <i data-feather="check" class="text-info" style="width: 16px; height: 16px;"></i>
                            </div>
                            <span class="text-light">Data Mining: Scan logs & user feedback</span>
                        </div>
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
                                    dur="18s" 
                                    repeatCount="indefinite"
                                    values="M40,20 Q60,40 80,20 T80,80 T40,80 T40,20;
                                            M40,40 Q60,60 80,40 T80,100 T40,100 T40,40;
                                            M40,20 Q60,40 80,20 T80,80 T40,80 T40,20"/>
                            </path>
                        </svg>
                    </div>
                </div>
            </div>
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

        /* Glass Dark Cards */
        .glass-dark {
            background: rgba(15, 23, 42, 0.6);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 0.5rem;
        }
    </style>
</section>
