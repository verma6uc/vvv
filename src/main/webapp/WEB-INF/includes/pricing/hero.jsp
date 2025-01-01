<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="hero py-5">
    <div class="container-fluid">
        <div class="text-center mb-5">
            <h1 class="display-4 fw-bold mb-4">
                Simple, Transparent<br>
                <span class="text-info">Pay-as-You-Grow Pricing</span>
            </h1>
            <p class="lead text-medium-contrast mb-5 mx-auto" style="max-width: 800px;">
                Start free and scale as your needs grow. Only pay for what you use with our flexible, usage-based pricing model.
            </p>
            <div class="d-flex justify-content-center gap-4 mb-5">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" id="billingToggle" style="width: 3rem; height: 1.5rem;">
                    <label class="form-check-label" for="billingToggle">
                        <span class="text-info fw-medium">Save 20% with annual billing</span>
                    </label>
                </div>
            </div>
        </div>

        <div class="row justify-content-center g-4">
            <!-- Free Tier -->
            <div class="col-lg-4">
                <div class="card glass h-100 rounded-4 p-4">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <div>
                                <h2 class="h4 fw-bold mb-1">Free</h2>
                                <p class="text-info mb-0">For Individual Developers</p>
                            </div>
                            <div class="rounded-circle bg-info bg-opacity-10 p-3">
                                <i data-feather="user" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                        </div>
                        <div class="mb-4">
                            <div class="d-flex align-items-baseline">
                                <h3 class="display-4 fw-bold mb-0">$0</h3>
                                <span class="text-medium-contrast ms-2">/month</span>
                            </div>
                            <p class="text-medium-contrast">Forever free plan</p>
                        </div>
                        <ul class="feature-list mb-4">
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Up to 3 active projects</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Basic AI agent access</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Community support</span>
                            </li>
                        </ul>
                        <a href="/signup" class="btn btn-outline-light w-100">
                            Get Started
                            <i data-feather="arrow-right" class="ms-2" style="width: 18px; height: 18px;"></i>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Pro Tier -->
            <div class="col-lg-4">
                <div class="card glass h-100 rounded-4 p-4" style="border: 2px solid var(--primary-color);">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <div>
                                <h2 class="h4 fw-bold mb-1">Pro</h2>
                                <p class="text-info mb-0">For Teams & Startups</p>
                            </div>
                            <div class="rounded-circle bg-info bg-opacity-10 p-3">
                                <i data-feather="award" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                        </div>
                        <div class="mb-4">
                            <div class="d-flex align-items-baseline">
                                <h3 class="display-4 fw-bold mb-0">$49</h3>
                                <span class="text-medium-contrast ms-2">/month</span>
                            </div>
                            <p class="text-medium-contrast">Per team member</p>
                        </div>
                        <ul class="feature-list mb-4">
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Unlimited projects</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Full AI agent access</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Priority support</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Advanced analytics</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Custom workflows</span>
                            </li>
                        </ul>
                        <a href="/signup?plan=pro" class="btn btn-primary w-100">
                            Start Free Trial
                            <i data-feather="arrow-right" class="ms-2" style="width: 18px; height: 18px;"></i>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Enterprise Tier -->
            <div class="col-lg-4">
                <div class="card glass h-100 rounded-4 p-4">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <div>
                                <h2 class="h4 fw-bold mb-1">Enterprise</h2>
                                <p class="text-info mb-0">For Large Organizations</p>
                            </div>
                            <div class="rounded-circle bg-info bg-opacity-10 p-3">
                                <i data-feather="briefcase" class="text-info" style="width: 24px; height: 24px;"></i>
                            </div>
                        </div>
                        <div class="mb-4">
                            <div class="d-flex align-items-baseline">
                                <h3 class="h4 fw-bold mb-0">Custom Pricing</h3>
                            </div>
                            <p class="text-medium-contrast">Tailored to your needs</p>
                        </div>
                        <ul class="feature-list mb-4">
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Everything in Pro</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Dedicated support</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>Custom integrations</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>SLA guarantees</span>
                            </li>
                            <li>
                                <i data-feather="check-circle"></i>
                                <span>On-premise deployment</span>
                            </li>
                        </ul>
                        <a href="/contact" class="btn btn-outline-light w-100">
                            Contact Sales
                            <i data-feather="message-circle" class="ms-2" style="width: 18px; height: 18px;"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
