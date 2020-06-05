<%@ page import="az.maqa.project.model.ClassRoom" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $("#classRoomTable").DataTable({
        searching: false
    });
</script>
<table border="1px solid black" id="classRoomTable" style="width: 100%" class="display">
    <thead>
    <tr>
        <th>Id</th>
        <th>Class Number</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${classRoomList}" var="crl">
        <tr>
            <th>${crl.id}</th>
            <th>${crl.classNumber}</th>
            <th><a href="javascript: editClassRoom(${crl.id})">Edit</a></th>
            <th><a href="javascript: deleteClassRoom(${crl.id})">Delete</a></th>
        </tr>
    </c:forEach>

    </tbody>
</table>