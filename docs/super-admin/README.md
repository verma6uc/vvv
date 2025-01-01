# Super Admin Documentation

This documentation covers the pages and features available to users with the SUPERADMIN role.

## Pages Overview

### Company Detail Page (`/super-admin/companies/[id]`)

The company detail page serves as a central hub for managing a specific company. It provides:

- Company overview with key information (name, domain, vertical, size)
- Quick statistics (users, applications, roles)
- List of recent applications with their status
- Quick action links to other sections

#### Components:
- StatCard: Displays key metrics with icons and trends
- ApplicationsList: Shows applications with phase and deployment status
- QuickAction: Navigation links to other sections

### Users Page (`/super-admin/companies/[id]/users`)

The users page displays all users within a company with their roles and status.

Features:
- Search functionality for users
- Pagination for large lists
- User information display:
  - Name and email
  - Company roles
  - Status (Active/Inactive)
  - Last login time
- Statistics showing:
  - Total users
  - Active users count
  - Inactive users count

### Roles Page (`/super-admin/companies/[id]/roles`)

The roles page manages company-specific roles and their permissions.

Features:
- List of company roles
- Role details:
  - Role name
  - Description
  - Number of users
  - Creation date
- Search and filter capabilities
- Pagination for large lists

### Spaces Page (`/super-admin/companies/[id]/spaces`)

The spaces page shows all spaces within a company and their deployed applications.

Features:
- Space listing with:
  - Space name
  - Type (TEAM, DEPARTMENT, DIVISION, etc.)
  - Number of users
  - Status
  - Deployed applications
- Color-coded space types
- Application status indicators
- Search functionality
- Statistics showing:
  - Total spaces
  - Active spaces
  - Total applications

### Audit Logs Page (`/super-admin/companies/[id]/audit-logs`)

The audit logs page tracks all changes and actions within the company.

Features:
- Comprehensive filtering:
  - Date range selection
  - Module type filter
  - Entity filter
  - Application filter
  - User filter
- Detailed log entries showing:
  - User who performed the action
  - Module type (color-coded)
  - Entity affected
  - Action performed
  - Application context
  - Change details with old/new values
- Search functionality
- Pagination

#### Components:
- FiltersSection: Handles all filtering controls
- LogsSection: Displays the audit log entries

## Navigation

The navigation between pages is handled through:
1. Quick action links on the company detail page
2. Next.js client-side navigation using the Link component
3. Dynamic routing using company ID parameter

## Styling

All pages follow a consistent dark theme with:
- Background: #141517
- Card backgrounds: #1C1D21
- Border colors: #3A3B3E
- Text colors:
  - Primary: #F3F3F3
  - Secondary: #B0B0B0
- Accent colors:
  - Blue: #08A0F8
  - Green: #31CC74
  - Red: #FF5C5C

## Component Structure

Each page is structured with:
1. Header section with:
   - Page title
   - Description
   - Search functionality (where applicable)
2. Main content area
3. Statistics or summary section (where applicable)
4. Pagination (for list views)

## Future Enhancements

Planned features:
1. Super Admin Dashboard with company cards
2. Create Company Page with archetype selection
3. Enhanced filtering capabilities
4. Real-time updates
5. Export functionality for logs and reports
