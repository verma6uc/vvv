# Onboarding Wizard with Yuvi Design Language

## 1. Overview

This multi-step wizard collects essential information (user email, company, archetype/theme, color palette, etc.) and visualizes design choices (cards, shadows, color previews) in real time. It fully aligns with the design language by:

- Using the dark palette (`#121315`, `#1C1D21`, `#2B2D31`) for backgrounds and surfaces
- Applying accent color (`#08A0F8`) for primary actions, links, and interactive highlights
- Ensuring text meets WCAG contrast guidelines (`#F3F3F3` for main text)
- Adhering to the Creator archetype—i.e., minimal friction, high clarity, and flexible customization for the user

## 2. Wizard Structure & Flow

### Step 1: Welcome & Email Collection

#### Title & Subtitle
- Headline: "Let's Get You Building with Yuvi"
- Subheading: "Sign up or join an existing company, and customize your workspace in just a few steps."

#### Fields
- Email (required)

#### Buttons
- Next (primary)

#### Design Integration
- Background: Use Background 2 (`#1C1D21`) for the page
- Card: Place the form in a Card style (`#2B2D31`)

```css
.wizard-card {
  background: var(--surface); /* #2B2D31 */
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.4);
}

input[type="email"] {
  background: var(--surface);
  border: 1px solid var(--border); /* #3A3B3E */
  color: var(--primary-text); /* #F3F3F3 */
  padding: 8px 16px;
  border-radius: 6px;
}

input[type="email"]:focus {
  border-color: var(--accent); /* #08A0F8 */
}

.btn-primary {
  background: var(--accent);
  color: #FFFFFF;
  padding: 8px 16px;
  border-radius: 6px;
  font-weight: 600;
  transition: background 200ms ease-in-out;
}

.btn-primary:hover {
  background: var(--accent-hover); /* #2D9BF0 */
}
```

#### Validation
- If email is invalid, show an Error color (`#FF5C5C`) highlight on the input border with a short message in Secondary Text (`#B0B0B0`)

### Step 2: Company Verification

#### Headline: "Existing Company or New Company?"

#### Radio / Toggle
- "Join an Existing Company" or "Create a New Company"

#### Conditionals
- If Existing: Prompt for Domain (1 field)
  - On blur or button click: Validate domain → If found, load company data
- If New: Prompt for Company Name, optionally "Vertical," "Domain," or "Size"

#### Buttons
- Back (secondary)
- Next (primary)

#### Styling
```css
.btn-secondary {
  background: transparent;
  border: 1px solid var(--accent); /* #08A0F8 */
  color: var(--accent);
  transition: background 200ms ease-in-out;
}

.btn-secondary:hover {
  background: rgba(8, 160, 248, 0.1);
}
```
- Use Info color (`#8A7CE6`) for any help text that clarifies domain usage

### Step 3: Archetype & Branding Customization

This is the crucial step for the Creator archetype approach: users visually pick or confirm the design archetype, color palette, card shapes, etc.

#### Headline: "Style Your Workspace"

#### Fields & Options
- Archetype Selection (dropdown or a set of visual cards, e.g. "Minimal," "Playful," "Yuvi Default")
- Primary Color (color picker; default `#08A0F8`)
- Secondary Color (color picker; default `#B0B0B0` or user-chosen)
- Accent Color (color picker; default `#08A0F8` if you want a uniform brand)
- Card Shadow Style (dropdown: "No Shadow," "Light Shadow," "Elevated Shadow")
- Card Border Radius (slider or dropdown: 0 → 16px, default 8px)

#### Real-Time Preview
```css
.preview-card {
  background: var(--surface);
  color: var(--primary-text);
  /* Shadow, border radius, etc. update dynamically as user picks */
}
```

#### Edge Case
- If user is joining an existing company, show their existing brand archetype and allow modifications only if the user's role or the system policy permits. Otherwise, display it in read-only mode.

### Step 4: Confirmation & Verification

#### Headline: "Check Your Inbox"
- Text: Summarize user's email address, selected company, chosen archetype, color scheme

#### Action
- "Confirm & Send Verification Email" button
- On click: Insert user record, create new company record if needed, store design settings, send verification email

#### Success Screen
- "A confirmation link has been sent to [user@email]. Please verify to activate your account."
- Possibly a Resend link if the user missed the email

#### Design
- Again, a Card for summary text
- Buttons consistent with earlier steps, using accent color (`#08A0F8`)
- If an error occurs, highlight in Error color (`#FF5C5C`)

### Step 5: Post-Verification Welcome

#### Page: "Email Verified / Account Activated"
- Message: "Welcome to Yuvi! Your workspace is ready."
- CTA: "Go to Dashboard" or "Start a New Project"

#### Design
- Use a celebratory accent, possibly a subtle success badge with Success color (`#31CC74`)
- Provide a quick overview of next steps (e.g., "Invite teammates," "Build your first app")

## 3. Detailed UI Styling

### Dark Mode Backgrounds

#### Global Body
```css
body {
  background-color: var(--background-1); /* #121315 */
  color: var(--primary-text); /* #F3F3F3 */
  font-family: 'Inter', system-ui, sans-serif;
}
```

#### Wizard Container
```css
.wizard-container {
  max-width: 600px;
  margin: 0 auto;
  background-color: var(--background-2); /* #1C1D21 */
  border-radius: 8px;
  padding: 32px;
}
```

### Cards
```css
.wizard-step-card {
  background: var(--surface); /* #2B2D31 */
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.4);
  margin-bottom: 24px;
}

.wizard-step-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.6);
  transition: box-shadow 200ms ease-in-out, transform 200ms ease-in-out;
}
```

### Buttons

#### Primary
```css
.btn-primary {
  background: var(--accent); /* #08A0F8 */
  color: #FFFFFF;
  padding: 8px 16px;
  border-radius: 6px;
  font-weight: 600;
  transition: background 200ms ease-in-out;
}

.btn-primary:hover {
  background: var(--accent-hover); /* #2D9BF0 */
}
```

#### Secondary
```css
.btn-secondary {
  background: transparent;
  border: 1px solid var(--accent);
  color: var(--accent);
  padding: 8px 16px;
  border-radius: 6px;
  font-weight: 500;
}

.btn-secondary:hover {
  background: rgba(8, 160, 248, 0.1);
}
```

#### Destructive
```css
.btn-danger {
  background: #FF5C5C;
  color: #FFFFFF;
  padding: 8px 16px;
  border-radius: 6px;
  font-weight: 600;
}

.btn-danger:hover {
  background: #E54646;
}
```

### Form Inputs
```css
input, select {
  background: var(--surface);
  border: 1px solid var(--border); /* #3A3B3E */
  color: var(--primary-text);
  padding: 8px 16px;
  border-radius: 6px;
  font-size: 16px;
}

input:focus, select:focus {
  border-color: var(--accent);
}
```

### Color Pickers (Archetype Step)
```css
.color-preview {
  width: 24px;
  height: 24px;
  border-radius: 4px;
  margin-left: 8px;
  border: 1px solid var(--border);
}
```

### Shadow & Border Radius Controls
```css
.slider-shadow {
  /* Ranges from 0 to 3 or so, mapping to different shadow intensities */
}

.slider-radius {
  /* Ranges from 0px to 16px */
}
```

## 4. Interactions & Animations

### Transition Durations
- Use 200ms or 300ms from the design language for hover states and changes in the preview card

### Focus States
- For form fields, emphasize a 1px or 2px border in Accent (`#08A0F8`)

### Page Transitions
- Between wizard steps, fade or slide in with a 300–400ms ease-in-out

## 5. Accessibility & Validation

### Contrast
- Ensure button text (`#FFFFFF`) on accent (`#08A0F8`) meets AA contrast
- For error messages, use Error color (`#FF5C5C`) with accessible text (`#F3F3F3` or `#B0B0B0`) for clarity

### Keyboard Navigation
- Tab order in wizard steps is logical
- "Back" and "Next" are in the correct sequence

### Screen Readers
- Label all form fields with aria-label or `<label>` tags
- Provide aria-live regions for error messages if user presses "Next" with invalid input

## 6. Post-Wizard Implementation Details

### Storing Data

#### Users table
- email, is_active=false, company_id (if existing or newly created), design_choices (optional JSON if user-level branding is allowed)

#### Companies table
- name, domain, vertical, etc.

#### Company_Settings table (or a design_config table)
- company_id, archetype, primary_color, secondary_color, accent_color, card_shadow_style, card_border_radius, etc.

### Email Confirmation
- email_verification_requests: Insert a record with (user_id, verification_token, expires_at)
- Send an email using the user's chosen style or a simpler template if design is mostly for in-app usage

### Final Setup for User
- Once they click verification link, redirect them to a "Complete Setup" or "Welcome" page

## 7. Example Code Snippets

### HTML Structure (Simplified)
```html
<div class="wizard-container">
  <div class="wizard-step-card step1">
    <h2>Enter Your Email</h2>
    <input type="email" placeholder="you@example.com" />
    <button class="btn-primary">Next</button>
  </div>
</div>
```

### Real-Time Preview JS (Archetype & Color Step)
```javascript
const primaryColorInput = document.getElementById('primaryColor');
const previewCard = document.querySelector('.preview-card');

primaryColorInput.addEventListener('change', (e) => {
  previewCard.style.backgroundColor = e.target.value;
  // Also update CSS variable if you want dynamic theming
  document.documentElement.style.setProperty('--accent', e.target.value);
});
```

## 8. Putting It All Together

By applying the Yuvi Design Language (dark mode backgrounds, accent color for interactive elements, consistent card styles, typography, spacing) to every step in the onboarding wizard, you achieve:

- A Creator-friendly experience, letting users easily customize their space without rummaging through complex or hidden settings
- Visual consistency, ensuring each wizard step is clearly part of the same platform (cohesive color usage, shape language, type styles)
- Reduced cognitive load, as each step is chunked into a separate UI card, with ample space (md: 16px to lg: 24px) around elements
- High clarity for toggles, color pickers, and input fields thanks to bold accent outlines (`#08A0F8`) on focus

### Flow Recap
1. Email →
2. Existing vs. New Company →
3. Design Archetype & Branding →
4. Confirmation & Verification →
5. Welcome / Login

At each stage, the design language ensures dark backgrounds, bright accent calls to action, well-defined hover/focus states, and smooth transitions (200–300ms ease).

This final integrated approach merges the Onboarding Wizard concept with the Yuvi Design Language—yielding a thorough, user-centric flow that is both functional and visually appealing, aligned with the Creator archetype.
