# Changelog

All notable changes to the Yuvi project will be documented in this file.

## [Unreleased]

### Added Authentication Pages
- Created static authentication pages with consistent dark mode design:
  - Login page with:
    - Email and password fields
    - Remember me option
    - Forgot password link
    - Social login options (Google, GitHub, GitLab)
    - Sign up link
  - Sign up page with:
    - First and last name fields
    - Email field
    - Password with strength requirements
    - Terms acceptance checkbox
    - Social signup options
    - Real-time password validation
  - Forgot password page with:
    - Email input
    - Clear instructions
    - Back to login link
  - Reset password page with:
    - New password input
    - Password confirmation
    - Password requirements checker
    - Token validation
- Added common features across auth pages:
  - Form validation
  - Password visibility toggle
  - Auto-dismissing alerts
  - Responsive design
  - Glassmorphic UI elements
  - Consistent branding

### Added Database Schema Support
- Created DatabaseSchemaUtils for type-safe database operations:
  - Enum mappings for database types:
    - AgentType (PLANNING, CONVERSATION, etc.)
    - ApplicationPhase (MEMORY, BLUEPRINT, etc.)
    - MemoryBlockType (IDEA, FEATURE_CONCEPT, etc.)
    - TaskStatus (OPEN, IN_PROGRESS, etc.)
  - CRUD operations for:
    - Agents and agent task types
    - Memory blocks and application phases
    - Tasks and task status management
  - Schema validation and initialization utilities
  - PostgreSQL enum type casting support

### Added Database Infrastructure
- Created database utilities package (ai.yuvi.db.utilities):
  - ConnectionPool: Manages database connection pooling
  - DatabaseUtils: Common database operations and utilities
- Added database configuration:
  - Connection pooling support
  - Performance optimization settings
  - Prepared statement caching
  - Transaction management
  - Connection validation
  - JMX monitoring capabilities

### Added Marketing Pages Infrastructure
- Created marketing pages package (ai.yuvi.marketing.pages):
  - BaseMarketingServlet: Abstract base class for marketing pages
  - Common functionality for all marketing pages:
    - Request parameter handling
    - Error/success message management
    - AJAX request detection
    - JSON response formatting
    - Common attribute setting
    - View path management

### Added Marketing Pages
- Created modular home page with components:
  - Common header and footer components
  - Hero section with main value proposition
  - Features section highlighting key differentiators
  - Process section showing 3-step application building
  - AI Agents section showcasing all 8 agents:
    - Cognitive Agents (Asimov-inspired): Dr. Susan Calvin, R. Daneel Olivaw, Hari Seldon, R. Giskard Reventlov
    - Technical Agents: SQL Writer, HTML Templater, JS Logic, Action Effects
  - Call-to-action section
- Implemented responsive design using Bootstrap 5
- Added Feather Icons for enhanced visual elements
- Organized CSS into modular files:
  - base.css: Variables, typography, and root styles
  - components.css: Buttons, cards, navigation elements
  - layout.css: Grid systems, sections, and page layouts
  - utilities.css: Helper classes and common utilities
- Created common JSP includes:
  - head.jsp: Meta tags, CSS imports, and scripts
  - scripts.jsp: Common JavaScript functionality
  - Reusable page components

### Added Features Page
- Created modular features page with components:
  - Hero section with feature cards
  - Detailed feature list with code examples
  - Interactive workflow demonstrations
  - Resource monitoring visualizations
  - Custom CTA section

### Added Solutions Page
- Created modular solutions page with components:
  - Hero section with solution cards
  - Detailed solutions list with:
    - Enterprise solutions with metrics
    - Startup solutions with timeline
    - Agency solutions with dashboards
  - Client testimonials section
  - Custom CTA section
- Added interactive elements:
  - Progress bars and metrics
  - Timeline visualization
  - Resource utilization charts

### Added Agents Page
- Created comprehensive agents showcase with components:
  - Hero section with real-time agent status
  - Cognitive agents section featuring:
    - Dr. Susan Calvin (Reading Agent)
    - R. Daneel Olivaw (Writing Agent)
  - Technical agents section featuring:
    - SQL Writer (Database Agent)
    - HTML Templater (Frontend Agent)
  - Integration section with code examples
  - Custom CTA section
- Added interactive demonstrations:
  - Live document analysis
  - Content generation examples
  - Schema generation preview
  - Component templating showcase
- Implemented consistent styling:
  - Added backdrop-filter blur effects
  - Created glassmorphic UI components
  - Enhanced hover animations and transitions
  - Added full-width container layouts
  - Improved typography with Inter font
  - Added subtle background gradients

### Enhanced Agents Page
- Redesigned hero section with animated workflow diagram:
  - Added Input -> AI Agents -> Result visualization
  - Implemented pulsing animations and particle effects
  - Created smooth transitions between workflow states
- Revamped cognitive agents section:
  - Added card-based layout with hover effects
  - Implemented SVG pattern animations
  - Enhanced visual hierarchy and spacing
  - Added icon pulse animations
- Updated technical agents section:
  - Redesigned code preview cards
  - Added animated SVG backgrounds
  - Implemented feature list hover effects
  - Enhanced card transitions and shadows
- Added consistent animations across all sections:
  - Smooth hover transitions
  - Pulsing icon effects
  - Animated SVG patterns
  - Feature list interactions

### Enhanced Cognitive Agents Section
- Expanded Dr. Susan Calvin (Reading Agent) features:
  - Added document parsing capabilities
  - Implemented contextual extraction
  - Added semantic analysis features
  - Included use case examples
- Enhanced R. Daneel Olivaw (Writing Agent) features:
  - Added text generation capabilities
  - Implemented grammar & style enhancement
  - Added multi-language support
  - Included use case examples
- Added Hari Seldon (Planning Agent):
  - Implemented task decomposition
  - Added strategic orchestration
  - Added high-level forecasting
  - Included use case examples
- Added R. Giskard Reventlov (Search Agent):
  - Implemented data retrieval
  - Added contextual searching
  - Added cross-repository lookups
  - Included use case examples
- Improved UI components:
  - Added nested glass-dark cards for capabilities
  - Enhanced feature item interactions
  - Added icon-based feature lists
  - Improved hover animations
  - Added SVG pattern overlays
  - Enhanced responsive layout

### Added Documentation
- Created PAGES_TO_BUILD.md to track pending pages:
  - High priority core pages (signup, signin, pricing, etc.)
  - Medium priority documentation pages (API, integration guides, etc.)
  - Low priority company pages (about, careers, blog, etc.)
  - Implementation notes and guidelines
  - Development process workflow
  - Tech stack requirements

### Initial Setup
- Initial project setup
- Updated pom.xml with essential dependencies:
  - Jakarta Servlet API 6.0.0
  - JSTL 3.0.0
  - Gson 2.10.1
  - PostgreSQL Driver 42.6.0
  - JUnit Jupiter 5.9.2
- Added Maven plugins:
  - maven-compiler-plugin configured for Java 17
  - maven-war-plugin for WAR packaging
- Set up project with Java 17 configuration
- Specified Apache Tomcat 10.1.18 requirement for Jakarta EE 9+ compatibility

### Fixed
- Added missing Jakarta dependencies to resolve JSP compilation issues:
  - jakarta.servlet.jsp-api 3.1.1
  - jakarta.servlet.jsp.jstl 3.0.1 (JSTL implementation)
- Improved text contrast in agents page for better accessibility:
  - Enhanced feature list text visibility
  - Increased contrast for section descriptions
  - Added text-light class to headings
  - Removed low-opacity text styles
  - Standardized text colors for better readability
