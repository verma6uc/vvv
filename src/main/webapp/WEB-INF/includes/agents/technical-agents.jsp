<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Previous HTML content remains the same until the style block -->

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
