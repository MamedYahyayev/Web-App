<%@ page import="az.maqa.project.model.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 14-Mar-20
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $("#studentTable").DataTable({
        searching: false
    });
</script>
<table border="1px solid black" id="studentTable" class="display" style="width: 100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Username</th>
        <th>Password</th>
        <th>Role</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="sl">
        <tr>
            <th>${sl.id}</th>
            <th>${sl.name}</th>
            <th>${sl.surname}</th>
            <th>${sl.username}</th>
            <th>${sl.password}</th>
            <th>${sl.role.roleName}</th>
            <th><a href="javascript: editStudent(${sl.id})">Edit</a></th>
            <th><a href="javascript: deleteStudent(${sl.id})">Delete</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>
