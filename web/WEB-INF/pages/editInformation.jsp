<%@ page import="az.maqa.project.model.Informations" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 17-Mar-20
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% Informations informations = (Informations) request.getAttribute("information");%>--%>
<div id="formInformation">
    <label for="informationU">Information:</label>
    <textarea style="width: 667px;
    height: 517px;" id="informationU" required>${informations.informations}</textarea>
</div>
