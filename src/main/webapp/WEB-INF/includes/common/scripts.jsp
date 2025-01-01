<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Initialize Feather Icons -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Initialize Feather Icons
        feather.replace();

        // Add scroll effect to navbar
        const navbar = document.querySelector('.navbar');
        function updateNavbar() {
            if (window.scrollY > 50) {
                navbar.classList.add('scrolled');
            } else {
                navbar.classList.remove('scrolled');
            }
        }
        window.addEventListener('scroll', updateNavbar);
        updateNavbar(); // Initial check

        // Handle "Explore Technical Agents" button click
        const exploreButton = document.querySelector('[data-target="#technical-agents"]');
        if (exploreButton) {
            exploreButton.addEventListener('click', function(e) {
                e.preventDefault();
                const technicalAgentsSection = document.getElementById('technical-agents');
                if (technicalAgentsSection) {
                    technicalAgentsSection.scrollIntoView({ 
                        behavior: 'smooth',
                        block: 'start'
                    });
                }
            });
        }

        // Initialize any tooltips
        const tooltips = document.querySelectorAll('[data-bs-toggle="tooltip"]');
        tooltips.forEach(tooltip => {
            new bootstrap.Tooltip(tooltip);
        });

        // Initialize any popovers
        const popovers = document.querySelectorAll('[data-bs-toggle="popover"]');
        popovers.forEach(popover => {
            new bootstrap.Popover(popover);
        });

        // Handle card animations on scroll
        const animateOnScroll = function() {
            const cards = document.querySelectorAll('.agent-card');
            cards.forEach(card => {
                const cardTop = card.getBoundingClientRect().top;
                const cardBottom = card.getBoundingClientRect().bottom;
                const windowHeight = window.innerHeight;
                
                if (cardTop < windowHeight * 0.85 && cardBottom > 0) {
                    card.classList.add('visible');
                }
            });
        };
        
        window.addEventListener('scroll', animateOnScroll);
        animateOnScroll(); // Initial check
    });
</script>
