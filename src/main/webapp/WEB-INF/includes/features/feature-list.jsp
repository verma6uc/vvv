<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section id="feature-list" class="py-5">
    <div class="container-fluid">
        <!-- AI-Driven Development -->
        <div class="row align-items-center g-5 mb-5">
            <div class="col-lg-6">
                <div class="pe-lg-5">
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                            <i data-feather="cpu" class="text-info" style="width: 32px; height: 32px;"></i>
                        </div>
                        <h2 class="h3 fw-bold mb-0">AI-Driven Development</h2>
                    </div>
                    <p class="lead text-medium-contrast mb-4">
                        Experience a revolutionary approach to software development with our AI-powered platform that understands your needs and automates repetitive tasks.
                    </p>
                    <ul class="feature-list">
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Intelligent code generation and optimization</span>
                        </li>
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Automated testing and quality assurance</span>
                        </li>
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Smart debugging suggestions</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card glass rounded-4 p-4">
                    <pre class="mb-0"><code class="text-info">// Example of AI-assisted code generation
const generateAPI = async (schema) => {
  const routes = await AI.analyze(schema);
  const tests = await AI.generateTests(routes);
  const docs = await AI.createDocs(routes);
  
  return {
    routes,
    tests,
    documentation: docs
  };
};</code></pre>
                </div>
            </div>
        </div>

        <!-- Workflow Automation -->
        <div class="row align-items-center g-5 mb-5 flex-lg-row-reverse">
            <div class="col-lg-6">
                <div class="ps-lg-5">
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                            <i data-feather="git-branch" class="text-info" style="width: 32px; height: 32px;"></i>
                        </div>
                        <h2 class="h3 fw-bold mb-0">Workflow Automation</h2>
                    </div>
                    <p class="lead text-medium-contrast mb-4">
                        Streamline your development process with automated workflows that handle everything from deployment to testing.
                    </p>
                    <ul class="feature-list">
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Automated CI/CD pipelines</span>
                        </li>
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Integrated version control</span>
                        </li>
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Automated dependency management</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card glass rounded-4 p-4">
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-success bg-opacity-10 p-2 me-3">
                            <i data-feather="check-circle" class="text-success" style="width: 16px; height: 16px;"></i>
                        </div>
                        <span class="text-success">Workflow completed successfully</span>
                    </div>
                    <div class="progress mb-3" style="height: 8px;">
                        <div class="progress-bar bg-info" style="width: 100%"></div>
                    </div>
                    <div class="d-flex justify-content-between text-medium-contrast">
                        <span>Build</span>
                        <span>Test</span>
                        <span>Deploy</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Multi-Tenant Architecture -->
        <div class="row align-items-center g-5 mb-5">
            <div class="col-lg-6">
                <div class="pe-lg-5">
                    <div class="d-flex align-items-center mb-4">
                        <div class="rounded-circle bg-info bg-opacity-10 p-3 me-3">
                            <i data-feather="layers" class="text-info" style="width: 32px; height: 32px;"></i>
                        </div>
                        <h2 class="h3 fw-bold mb-0">Multi-Tenant Architecture</h2>
                    </div>
                    <p class="lead text-medium-contrast mb-4">
                        Built for scale from day one with a robust multi-tenant architecture that supports multiple teams and projects.
                    </p>
                    <ul class="feature-list">
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Isolated tenant environments</span>
                        </li>
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Role-based access control</span>
                        </li>
                        <li>
                            <i data-feather="check-circle"></i>
                            <span>Resource usage monitoring</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="position-relative">
                    <div class="card glass rounded-4 p-4 mb-4">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-medium-contrast">Team A Resources</span>
                            <div class="badge bg-info">Active</div>
                        </div>
                        <div class="progress mt-3" style="height: 6px;">
                            <div class="progress-bar bg-info" style="width: 65%"></div>
                        </div>
                    </div>
                    <div class="card glass rounded-4 p-4 mb-4" style="margin-left: 2rem;">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-medium-contrast">Team B Resources</span>
                            <div class="badge bg-info">Active</div>
                        </div>
                        <div class="progress mt-3" style="height: 6px;">
                            <div class="progress-bar bg-info" style="width: 45%"></div>
                        </div>
                    </div>
                    <div class="card glass rounded-4 p-4" style="margin-left: 4rem;">
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="text-medium-contrast">Team C Resources</span>
                            <div class="badge bg-info">Active</div>
                        </div>
                        <div class="progress mt-3" style="height: 6px;">
                            <div class="progress-bar bg-info" style="width: 80%"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
