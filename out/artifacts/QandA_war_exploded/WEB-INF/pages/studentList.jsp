<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 04-Mar-20
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<script type="text/javascript" src="js/student.js"></script>
<link rel="stylesheet" type="text/css" href="css/newStudent.css">
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>
<div class="ui-layout-center content">
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
</div>

<jsp:include page="/static/aside.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
<div id="newStudent"></div>
<div id="editStudentDialog"></div>
</body>
</html>
