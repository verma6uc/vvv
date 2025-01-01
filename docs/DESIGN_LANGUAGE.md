# Yuvi Design Language

A comprehensive design language tailored to Yuvi's Creator archetype with a dark mode aesthetic. This document outlines our design principles, color system, typography, component styling, and layout guidelines.

## 1. Foundational Concept

### Creator Archetype
- Emphasizes innovation and self-expression
- Focuses on building and creation
- Maintains simplicity without sacrificing functionality
- Reduces cognitive load during creative processes

### Dark Mode Philosophy
- Reduces eye strain for prolonged usage
- Provides modern, professional aesthetic
- Emphasizes content through strategic contrast
- Supports extended development sessions

## 2. Color System

### Primary Palette

#### Background Colors
- **Background 1 (Darkest)**
  - Hex: `#121315`
  - Usage: Outermost canvas, main site background
  - Properties: Minimizes glare, creates depth

- **Background 2 (Primary Dark)**
  - Hex: `#1C1D21`
  - Usage: General page background
  - Properties: Primary working surface

- **Surface**
  - Hex: `#2B2D31`
  - Usage: Cards, modals, secondary panels
  - Properties: Slightly elevated, creates hierarchy

- **Border**
  - Hex: `#3A3B3E`
  - Usage: Subtle separators and outlines
  - Properties: Low-contrast definition

#### Text Colors
- **Primary Text**
  - Hex: `#F3F3F3`
  - Usage: Main text content
  - Properties: High contrast, excellent readability

- **Secondary Text**
  - Hex: `#B0B0B0`
  - Usage: Supporting text, labels
  - Properties: Reduced emphasis while maintaining readability

#### Brand Colors
- **Accent**
  - Hex: `#08A0F8`
  - Usage: Primary actions, links, highlights
  - Properties: Vibrant, draws attention

- **Accent Hover**
  - Hex: `#2D9BF0`
  - Usage: Interactive state for accent color
  - Properties: Slightly lighter for feedback

### Status Colors

- **Success**
  - Hex: `#31CC74`
  - Usage: Positive actions, completion states
  - Properties: Calming green, indicates success

- **Error**
  - Hex: `#FF5C5C`
  - Usage: Error states, destructive actions
  - Properties: Attention-grabbing red

- **Warning**
  - Hex: `#FEC02F`
  - Usage: Caution states, important notices
  - Properties: Alert yellow, draws attention

- **Info**
  - Hex: `#8A7CE6`
  - Usage: Information states, neutral alerts
  - Properties: Subtle purple, informative

## 3. Typography

### Font Family
- Primary: Inter
- Fallback: system-ui, -apple-system, sans-serif
- Reasoning: Clean, modern, highly readable

### Font Sizes
```css
h1: 36px  /* Major headlines */
h2: 28px  /* Section headers */
h3: 24px  /* Subsection headers */
body: 16px /* Main content */
small: 13px /* Supporting text */
```

### Font Weights
```css
bold: 700      /* Headlines */
semibold: 600  /* Subheadings */
regular: 500   /* Body text */
light: 400     /* Supporting text */
```

### Line Heights
- Headlines: 1.2
- Body Text: 1.5
- Buttons: 1.4

## 4. Components

### Cards
```css
/* Base Card */
background: #2B2D31
padding: 24px
border-radius: 8px
box-shadow: 0 3px 8px rgba(0, 0, 0, 0.4)

/* Hover State */
transform: translateY(-2px)
box-shadow: 0 8px 16px rgba(0, 0, 0, 0.6)
```

### Buttons
```css
/* Primary Button */
background: #08A0F8
color: white
hover: #2D9BF0

/* Secondary Button */
border: 1px solid #08A0F8
color: #08A0F8
hover-background: rgba(8, 160, 248, 0.1)

/* Destructive Button */
background: #FF5C5C
color: white
hover: #E54646
```

### Form Elements
```css
/* Input Field */
background: #2B2D31
border: 1px solid #3A3B3E
color: #F3F3F3
padding: 8px 16px
border-radius: 6px

/* Focus State */
border-color: #08A0F8
```

### Status Badges
```css
/* Base Badge */
border-radius: 9999px
padding: 4px 12px
font-size: 13px

/* Variants */
success: rgba(49, 204, 116, 0.2)
error: rgba(255, 92, 92, 0.2)
warning: rgba(254, 192, 47, 0.2)
info: rgba(138, 124, 230, 0.2)
```

## 5. Icons

### Feather Icons
- Standard size: 24x24px
- Stroke width: 2px
- Color: Inherits from text color

### Agent Icons
- **Reading (Dr. Susan Calvin)**
  - Icon: `<Book />`
  - Color: Accent color (#08A0F8)

- **Writing (R. Daneel Olivaw)**
  - Icon: `<Edit3 />`
  - Color: Accent color (#08A0F8)

- **Planning (Hari Seldon)**
  - Icon: `<Calendar />`
  - Color: Accent color (#08A0F8)

- **Search (R. Giskard Reventlov)**
  - Icon: `<Search />`
  - Color: Accent color (#08A0F8)

## 6. Spacing System

### Base Units
```css
xs: 4px
sm: 8px
md: 16px
lg: 24px
xl: 32px
xxl: 48px
```

### Common Applications
- Card padding: 24px
- Button padding: 8px 16px
- Section margins: 48px
- Grid gaps: 24px

## 7. Animation & Transitions

### Duration
- Quick interactions: 200ms
- Complex animations: 300ms
- Page transitions: 400ms

### Easing
- Default: ease-in-out
- Enter: ease-out
- Exit: ease-in

## 8. Responsive Design

### Breakpoints
```css
sm: 640px   /* Mobile devices */
md: 768px   /* Tablets */
lg: 1024px  /* Laptops */
xl: 1280px  /* Desktops */
2xl: 1536px /* Large screens */
```

### Grid System
- 12-column layout
- Responsive gutters (16px mobile, 24px desktop)
- Container max-width: 1200px

## 9. Best Practices

### Accessibility
- Maintain WCAG AA contrast ratios
- Support keyboard navigation
- Include focus states
- Provide hover feedback

### Dark Mode Considerations
- Avoid pure black (#000000)
- Use subtle shadows for depth
- Maintain sufficient contrast
- Prevent eye strain with muted colors

### Performance
- Optimize transitions
- Minimize shadow usage
- Use system fonts in fallbacks
- Implement responsive images

## 10. Implementation

### CSS Variables
```css
:root {
  --background-1: #121315;
  --background-2: #1C1D21;
  --surface: #2B2D31;
  --border: #3A3B3E;
  --primary-text: #F3F3F3;
  --secondary-text: #B0B0B0;
  --accent: #08A0F8;
  --accent-hover: #2D9BF0;
}
```

### Tailwind Configuration
```javascript
theme: {
  extend: {
    colors: {
      'background-1': '#121315',
      'background-2': '#1C1D21',
      // ... other colors
    },
    // ... other theme extensions
  }
}
```

This design language serves as the foundation for creating consistent, accessible, and visually appealing interfaces across Yuvi's platform. It emphasizes clarity, reduces cognitive load, and supports extended usage through careful consideration of contrast, spacing, and interaction patterns.
