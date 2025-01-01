/*
 Navicat Premium Dump SQL

 Source Server         : main
 Source Server Type    : PostgreSQL
 Source Server Version : 150008 (150008)
 Source Host           : 35.200.212.230:5432
 Source Catalog        : yuvi2
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 150008 (150008)
 File Encoding         : 65001

 Date: 28/12/2024 12:55:47
*/

-- ----------------------------
-- Type structure for agent_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."agent_type_enum";
CREATE TYPE "public"."agent_type_enum" AS ENUM (
  'PLANNING',
  'CONVERSATION',
  'MEMORY_CONTEXT',
  'RESEARCH',
  'IMPACT_ANALYSIS',
  'DATA_MODELING',
  'QUERY_ENDPOINT',
  'HTML_TEMPLATING',
  'JS_LOGIC',
  'ACTION_EFFECTS',
  'API_INTEGRATION',
  'DATA_DISCOVERY',
  'OTHER'
);
ALTER TYPE "public"."agent_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."agent_type_enum" IS 'Enumerates possible AI agent specializations: planning, conversation, data modeling, etc.';

-- ----------------------------
-- Type structure for application_deployment_status_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."application_deployment_status_enum";
CREATE TYPE "public"."application_deployment_status_enum" AS ENUM (
  'SUSPENDED',
  'WITHDRAWN',
  'TEST',
  'UAT',
  'LIVE'
);
ALTER TYPE "public"."application_deployment_status_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."application_deployment_status_enum" IS 'Enumerates statuses for an application deployment: SUSPENDED, WITHDRAWN, TEST, UAT, LIVE.';

-- ----------------------------
-- Type structure for application_phase_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."application_phase_enum";
CREATE TYPE "public"."application_phase_enum" AS ENUM (
  'MEMORY',
  'BLUEPRINT',
  'VISUAL_PRD',
  'DEVELOPMENT',
  'MAINTENANCE'
);
ALTER TYPE "public"."application_phase_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."application_phase_enum" IS 'Enumerates the primary phases of an application's lifecycle:
 MEMORY (research), BLUEPRINT, VISUAL_PRD, DEVELOPMENT, MAINTENANCE.';

-- ----------------------------
-- Type structure for attribute_data_type
-- ----------------------------
DROP TYPE IF EXISTS "public"."attribute_data_type";
CREATE TYPE "public"."attribute_data_type" AS ENUM (
  'SERIAL',
  'VARCHAR',
  'INT',
  'BOOLEAN',
  'TIMESTAMP',
  'TEXT',
  'FLOAT',
  'DATE',
  'TIME',
  'ENUM',
  'ARRAY',
  'JSONB',
  'OTHERS',
  'BYTEA'
);
ALTER TYPE "public"."attribute_data_type" OWNER TO "postgres";

-- ----------------------------
-- Type structure for blueprint_item_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."blueprint_item_type_enum";
CREATE TYPE "public"."blueprint_item_type_enum" AS ENUM (
  'ROLE',
  'PAGE',
  'FEATURE',
  'ACTION',
  'OTHER'
);
ALTER TYPE "public"."blueprint_item_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."blueprint_item_type_enum" IS 'Enumerates categories of blueprint items for design-time definitions: ROLE, PAGE, FEATURE, ACTION, OTHER.';

-- ----------------------------
-- Type structure for company_size_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."company_size_enum";
CREATE TYPE "public"."company_size_enum" AS ENUM (
  'tiny',
  'small',
  'medium',
  'large',
  'enterprise'
);
ALTER TYPE "public"."company_size_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."company_size_enum" IS 'Enumerates rough size categories for companies, from tiny to enterprise.';

-- ----------------------------
-- Type structure for company_vertical_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."company_vertical_enum";
CREATE TYPE "public"."company_vertical_enum" AS ENUM (
  'FINANCE',
  'HEALTHCARE',
  'SOFTWARE',
  'MANUFACTURING',
  'RETAIL',
  'OTHER'
);
ALTER TYPE "public"."company_vertical_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."company_vertical_enum" IS 'Enumerates common industries/verticals for companies, e.g. finance, healthcare, etc.';

-- ----------------------------
-- Type structure for date_format_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."date_format_enum";
CREATE TYPE "public"."date_format_enum" AS ENUM (
  'MM-DD-YYYY',
  'DD-MM-YYYY',
  'YYYY-MM-DD',
  'MM/DD/YYYY',
  'DD/MM/YYYY',
  'YYYY/MM/DD',
  'MM-DD-YYYYZ',
  'DD-MM-YYYYZ',
  'YYYY-MM-DDZ',
  'MM/DD/YYYYZ',
  'DD/MM/YYYYZ',
  'YYYY/MM/DDZ'
);
ALTER TYPE "public"."date_format_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."date_format_enum" IS 'Enumerates a small set of allowed date formats for display or input.';

-- ----------------------------
-- Type structure for deployment_environment_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."deployment_environment_enum";
CREATE TYPE "public"."deployment_environment_enum" AS ENUM (
  'TEST',
  'UAT',
  'STAGING',
  'PRODUCTION'
);
ALTER TYPE "public"."deployment_environment_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."deployment_environment_enum" IS 'Enumerates the environment type for a deployment, e.g. TEST, UAT, STAGING, PRODUCTION.';

-- ----------------------------
-- Type structure for memory_block_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."memory_block_type_enum";
CREATE TYPE "public"."memory_block_type_enum" AS ENUM (
  'IDEA',
  'FEATURE_CONCEPT',
  'PERSONA',
  'CONSTRAINT',
  'RESEARCH_NOTES',
  'OTHER'
);
ALTER TYPE "public"."memory_block_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."memory_block_type_enum" IS 'Enumerates possible categories for a memory_block in the research phase: IDEA, FEATURE_CONCEPT, PERSONA, etc.';

-- ----------------------------
-- Type structure for module_type
-- ----------------------------
DROP TYPE IF EXISTS "public"."module_type";
CREATE TYPE "public"."module_type" AS ENUM (
  'FORM_MANAGEMENT',
  'PAGE_MANAGEMENT',
  'AUTHENTICATION',
  'BACKGROUND_TASKS',
  'WORKFLOW_MANAGEMENT',
  'COMPANY_MANAGEMENT',
  'ROLE_MANAGEMENT',
  'USER_MANAGEMENT',
  'FORM_OPERATIONS',
  'APPLICATION_MANAGEMENT'
);
ALTER TYPE "public"."module_type" OWNER TO "postgres";

-- ----------------------------
-- Type structure for page_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."page_type_enum";
CREATE TYPE "public"."page_type_enum" AS ENUM (
  'LIST',
  'DETAIL',
  'DASHBOARD',
  'SETTINGS',
  'OTHER'
);
ALTER TYPE "public"."page_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."page_type_enum" IS 'Categorizes the style/purpose of pages: LIST, DETAIL, DASHBOARD, SETTINGS, etc.';

-- ----------------------------
-- Type structure for relationship_type
-- ----------------------------
DROP TYPE IF EXISTS "public"."relationship_type";
CREATE TYPE "public"."relationship_type" AS ENUM (
  'MANY_TO_ONE',
  'MANY_TO_MANY',
  'ONE_TO_MANY',
  'ONE_TO_ONE'
);
ALTER TYPE "public"."relationship_type" OWNER TO "postgres";

-- ----------------------------
-- Type structure for section_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."section_type_enum";
CREATE TYPE "public"."section_type_enum" AS ENUM (
  'LIST',
  'FORM',
  'CHART',
  'FILTER',
  'CUSTOM',
  'OTHER'
);
ALTER TYPE "public"."section_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."section_type_enum" IS 'Categorizes types of sections: a data LIST, an input FORM, a CHART, etc.';

-- ----------------------------
-- Type structure for space_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."space_type_enum";
CREATE TYPE "public"."space_type_enum" AS ENUM (
  'TEAM',
  'DEPARTMENT',
  'DIVISION',
  'OFFICE',
  'BRANCH',
  'FACILITY',
  'WAREHOUSE',
  'LAB',
  'PROJECT',
  'VIRTUAL',
  'OTHER'
);
ALTER TYPE "public"."space_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."space_type_enum" IS 'Enumerates a broad set of space types (TEAM, DEPARTMENT, DIVISION, OFFICE, BRANCH, FACILITY, WAREHOUSE, LAB, PROJECT, VIRTUAL, OTHER) in uppercase for consistency.';

-- ----------------------------
-- Type structure for space_user_status_type
-- ----------------------------
DROP TYPE IF EXISTS "public"."space_user_status_type";
CREATE TYPE "public"."space_user_status_type" AS ENUM (
  'ACTIVE',
  'INACTIVE',
  'PENDING',
  'SUSPENDED'
);
ALTER TYPE "public"."space_user_status_type" OWNER TO "postgres";

-- ----------------------------
-- Type structure for system_role_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."system_role_enum";
CREATE TYPE "public"."system_role_enum" AS ENUM (
  'NONE',
  'SUPERADMIN'
);
ALTER TYPE "public"."system_role_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."system_role_enum" IS 'Indicates whether a user is a global SUPERADMIN or has NONE system role. All other roles are company-level.';

-- ----------------------------
-- Type structure for task_status_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."task_status_enum";
CREATE TYPE "public"."task_status_enum" AS ENUM (
  'OPEN',
  'IN_PROGRESS',
  'DONE',
  'BLOCKED',
  'CANCELLED'
);
ALTER TYPE "public"."task_status_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."task_status_enum" IS 'Enumerates possible states of a development task: OPEN, IN_PROGRESS, DONE, BLOCKED, CANCELLED.';

-- ----------------------------
-- Type structure for task_type_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."task_type_enum";
CREATE TYPE "public"."task_type_enum" AS ENUM (
  'BUILD_DATA_MODEL',
  'BUILD_DUMMY_DATA',
  'BUILD_QUERIES',
  'CREATE_HTML_TEMPLATE',
  'WRITE_JS',
  'ACTION_EFFECTS',
  'API_INTEGRATION',
  'PLANNING',
  'RESEARCH',
  'IMPACT_ANALYSIS',
  'MEMORY_CONTEXT',
  'CONVERSATION'
);
ALTER TYPE "public"."task_type_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."task_type_enum" IS 'Enumerates the main tasks in no-code product-building: build data model, dummy data, queries, HTML/JS, actions, plus planning/research, etc.';

-- ----------------------------
-- Type structure for time_format_enum
-- ----------------------------
DROP TYPE IF EXISTS "public"."time_format_enum";
CREATE TYPE "public"."time_format_enum" AS ENUM (
  'HH:mm 12H',
  'HH:mm:ss 12H',
  'HH:mm 24H',
  'HH:mm:ss 24H',
  'HH:mm 12HZ',
  'HH:mm:ss 12HZ',
  'HH:mm 24HZ',
  'HH:mm:ss 24HZ'
);
ALTER TYPE "public"."time_format_enum" OWNER TO "postgres";
COMMENT ON TYPE "public"."time_format_enum" IS 'Enumerates a small set of allowed time formats, e.g., 12-hour or 24-hour clock.';

-- ----------------------------
-- Sequence structure for action_effects_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."action_effects_id_seq";
CREATE SEQUENCE "public"."action_effects_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for action_permissions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."action_permissions_id_seq";
CREATE SEQUENCE "public"."action_permissions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for agent_task_types_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."agent_task_types_id_seq";
CREATE SEQUENCE "public"."agent_task_types_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for agents_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."agents_id_seq";
CREATE SEQUENCE "public"."agents_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for application_deployment_metadata_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."application_deployment_metadata_id_seq";
CREATE SEQUENCE "public"."application_deployment_metadata_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for application_deployments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."application_deployments_id_seq";
CREATE SEQUENCE "public"."application_deployments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for application_entities_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."application_entities_id_seq";
CREATE SEQUENCE "public"."application_entities_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for applications_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."applications_id_seq";
CREATE SEQUENCE "public"."applications_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for attributes_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."attributes_id_seq";
CREATE SEQUENCE "public"."attributes_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for audit_logs_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."audit_logs_id_seq";
CREATE SEQUENCE "public"."audit_logs_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for change_logs_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."change_logs_id_seq";
CREATE SEQUENCE "public"."change_logs_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for companies_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."companies_id_seq";
CREATE SEQUENCE "public"."companies_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for company_role_assignments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."company_role_assignments_id_seq";
CREATE SEQUENCE "public"."company_role_assignments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for company_roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."company_roles_id_seq";
CREATE SEQUENCE "public"."company_roles_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for company_settings_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."company_settings_id_seq";
CREATE SEQUENCE "public"."company_settings_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for email_verification_requests_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."email_verification_requests_id_seq";
CREATE SEQUENCE "public"."email_verification_requests_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for entities_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."entities_id_seq";
CREATE SEQUENCE "public"."entities_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for entity_actions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."entity_actions_id_seq";
CREATE SEQUENCE "public"."entity_actions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for entity_states_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."entity_states_id_seq";
CREATE SEQUENCE "public"."entity_states_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for entity_transitions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."entity_transitions_id_seq";
CREATE SEQUENCE "public"."entity_transitions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for enum_types_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."enum_types_id_seq";
CREATE SEQUENCE "public"."enum_types_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for memory_block_feedback_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."memory_block_feedback_id_seq";
CREATE SEQUENCE "public"."memory_block_feedback_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for memory_block_versions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."memory_block_versions_id_seq";
CREATE SEQUENCE "public"."memory_block_versions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for memory_blocks_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."memory_blocks_id_seq";
CREATE SEQUENCE "public"."memory_blocks_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for notification_masters_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."notification_masters_id_seq";
CREATE SEQUENCE "public"."notification_masters_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for notifications_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."notifications_id_seq";
CREATE SEQUENCE "public"."notifications_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for page_permissions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."page_permissions_id_seq";
CREATE SEQUENCE "public"."page_permissions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pages_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pages_id_seq";
CREATE SEQUENCE "public"."pages_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for password_history_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."password_history_id_seq";
CREATE SEQUENCE "public"."password_history_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for password_reset_requests_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."password_reset_requests_id_seq";
CREATE SEQUENCE "public"."password_reset_requests_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for project_plans_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."project_plans_id_seq";
CREATE SEQUENCE "public"."project_plans_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_entities_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_entities_id_seq";
CREATE SEQUENCE "public"."proposed_entities_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_entity_actions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_entity_actions_id_seq";
CREATE SEQUENCE "public"."proposed_entity_actions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_entity_states_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_entity_states_id_seq";
CREATE SEQUENCE "public"."proposed_entity_states_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_entity_transitions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_entity_transitions_id_seq";
CREATE SEQUENCE "public"."proposed_entity_transitions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_features_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_features_id_seq";
CREATE SEQUENCE "public"."proposed_features_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_feedback_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_feedback_id_seq";
CREATE SEQUENCE "public"."proposed_feedback_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_pages_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_pages_id_seq";
CREATE SEQUENCE "public"."proposed_pages_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_role_pages_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_role_pages_id_seq";
CREATE SEQUENCE "public"."proposed_role_pages_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proposed_roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."proposed_roles_id_seq";
CREATE SEQUENCE "public"."proposed_roles_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for relationships_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."relationships_id_seq";
CREATE SEQUENCE "public"."relationships_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for section_actions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."section_actions_id_seq";
CREATE SEQUENCE "public"."section_actions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for section_queries_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."section_queries_id_seq";
CREATE SEQUENCE "public"."section_queries_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sections_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sections_id_seq";
CREATE SEQUENCE "public"."sections_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sessions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sessions_id_seq";
CREATE SEQUENCE "public"."sessions_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for space_settings_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."space_settings_id_seq";
CREATE SEQUENCE "public"."space_settings_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for spaces_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."spaces_id_seq";
CREATE SEQUENCE "public"."spaces_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for task_comments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."task_comments_id_seq";
CREATE SEQUENCE "public"."task_comments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for task_dependencies_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."task_dependencies_id_seq";
CREATE SEQUENCE "public"."task_dependencies_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for tasks_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."tasks_id_seq";
CREATE SEQUENCE "public"."tasks_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_clicks_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_clicks_id_seq";
CREATE SEQUENCE "public"."user_clicks_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_login_attempts_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_login_attempts_id_seq";
CREATE SEQUENCE "public"."user_login_attempts_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for user_spaces_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_spaces_id_seq";
CREATE SEQUENCE "public"."user_spaces_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for visual_prd_feedback_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."visual_prd_feedback_id_seq";
CREATE SEQUENCE "public"."visual_prd_feedback_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854
