<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeCss("reportingui", "reportsapp/home.css")
    ui.includeJavascript("appui", "bootstrap.min.js")
    ui.includeCss("appui", "bootstrap.min.css")
    ui.includeCss("ehospitalreports", "referenceapplication.css", 100)
%>
<script type="text/javascript">
</script>
<style>
.dashboard .info-container {
  width: 30%;
}
</style>
<div>
  ${ui.includeFragment("ehospitalreports", "reports")}
</div>

