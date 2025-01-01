<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="hero py-5">
    <div class="container-fluid">
        <div class="row align-items-center g-5">
            <div class="col-lg-6">
                <h1 class="display-4 fw-bold mb-4">
                    Industry-Specific<br>
                    <span class="text-info">AI Solutions</span>
                </h1>
                <p class="lead text-light mb-5" style="font-size: 1.25rem;">
                    Empower your business with tailored AI solutions that streamline operations, enhance compliance, and accelerate growth across industries.
                </p>
                <div class="d-flex gap-3">
                    <a href="/contact" class="btn btn-info btn-lg px-4">
                        Talk to Sales
                        <i data-feather="message-circle" class="ms-2" style="width: 18px; height: 18px;"></i>
                    </a>
                    <a href="#solutions" class="btn btn-outline-light btn-lg px-4">
                        View Solutions
                        <i data-feather="chevron-down" class="ms-2" style="width: 18px; height: 18px;"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="position-relative">
                    <!-- Pharma Card -->
                    <div class="card glass rounded-4 p-4 mb-4 floating-card" style="transform: translateX(-5%);">
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="activity" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h4 class="h6 fw-bold mb-1 text-light">Pharmaceutical QA</h4>
                                <p class="text-info mb-0">Automated compliance & testing workflows</p>
                            </div>
                        </div>
                    </div>

                    <!-- E-commerce Card -->
                    <div class="card glass rounded-4 p-4 mb-4 floating-card" style="transform: translateX(5%);">
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="shopping-cart" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h4 class="h6 fw-bold mb-1 text-light">Multi-Brand E-commerce</h4>
                                <p class="text-info mb-0">Unified inventory & order management</p>
                            </div>
                        </div>
                    </div>

                    <!-- HR Card -->
                    <div class="card glass rounded-4 p-4 mb-4 floating-card" style="transform: translateX(-2%);">
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="users" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h4 class="h6 fw-bold mb-1 text-light">Global HR</h4>
                                <p class="text-info mb-0">Region-specific onboarding automation</p>
                            </div>
                        </div>
                    </div>

                    <!-- Finance Card -->
                    <div class="card glass rounded-4 p-4 floating-card" style="transform: translateX(3%);">
                        <div class="d-flex align-items-center">
                            <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                                <i data-feather="bar-chart-2" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                            <div>
                                <h4 class="h6 fw-bold mb-1 text-light">Financial Reporting</h4>
                                <p class="text-info mb-0">Multi-subsidiary consolidation</p>
                            </div>
                        </div>
                    </div>

                    <!-- Background Decoration -->
                    <div class="position-absolute top-0 end-0 translate-middle-y" style="z-index: -1;">
                        <div class="rounded-circle bg-info bg-opacity-10" style="width: 200px; height: 200px; filter: blur(60px);"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <style>
        .floating-card {
            transition: transform 0.3s ease;
        }

        .floating-card:hover {
            transform: translateY(-5px) !important;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }

        .hero .card:nth-child(1) {
            animation: float 4s ease-in-out infinite;
        }

        .hero .card:nth-child(2) {
            animation: float 4s ease-in-out infinite 1s;
        }

        .hero .card:nth-child(3) {
            animation: float 4s ease-in-out infinite 2s;
        }

        .hero .card:nth-child(4) {
            animation: float 4s ease-in-out infinite 3s;
        }
    </style>
</section>
