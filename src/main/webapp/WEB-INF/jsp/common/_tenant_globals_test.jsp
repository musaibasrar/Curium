<%-- 
MINIMAL TEST VERSION - Just context paths
--%>

<%
// Basic context path setup
String ctx = request.getContextPath();
request.setAttribute("ctx", ctx);
request.setAttribute("jsPath", ctx + "/js");
request.setAttribute("cssPath", ctx + "/css");
request.setAttribute("imagesPath", ctx + "/images");
%>

<script>
window.CTX_PATH = '${ctx}';
console.log('Test globals loaded:', '${ctx}');
</script>

