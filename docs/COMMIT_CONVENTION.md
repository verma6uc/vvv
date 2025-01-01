# Commit Convention

This project follows the [Conventional Commits](https://www.conventionalcommits.org/) specification for commit messages.

## Format

```
<type>(<scope>): <description>

[optional body]

[optional footer(s)]
```

## Types

- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation only changes
- `style`: Changes that do not affect the meaning of the code (white-space, formatting, etc)
- `refactor`: A code change that neither fixes a bug nor adds a feature
- `perf`: A code change that improves performance
- `test`: Adding missing tests or correcting existing tests
- `chore`: Changes to the build process or auxiliary tools and libraries
- `ci`: Changes to CI configuration files and scripts
- `revert`: Reverts a previous commit

## Scope

The scope should be the name of the component or area of the codebase affected (e.g., auth, navbar, api).

## Examples

```
feat(auth): add login functionality
fix(api): handle null response from server
docs(readme): update installation instructions
style(components): format according to style guide
refactor(utils): simplify date formatting function
test(auth): add unit tests for login
chore(deps): update dependencies
```

## Breaking Changes

Breaking changes should be indicated by adding `!` after the type/scope and including `BREAKING CHANGE:` in the footer:

```
feat(api)!: change authentication endpoint response format

BREAKING CHANGE: Authentication response now returns JWT token instead of session ID
