<%
    def appFrameworkService = context.getService(context.loadClass("org.openmrs.module.appframework.service.AppFrameworkService"))
    def quarterly = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ehospital.reports.quarterly")
    def monthly = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ehospital.reports.monthly")
    def registers = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ehospital.reports.registers")
    def contextModel = [:]
%>
<div class="dashboard clear">
    <div class="info-container column">
        <% if (registers) { %>
        <div class="info-section">
            <div class="info-header"><h3>Reports</h3></div>

            <div class="info-body">
                <ul>
                    <% registers.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>
    </div>
</div>
