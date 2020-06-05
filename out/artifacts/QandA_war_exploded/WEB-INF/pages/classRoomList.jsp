<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="az.maqa.project.model.ClassRoom" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 05-Mar-20
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<script type="text/javascript" src="js/classRoom.js"></script>
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>

<div class="ui-layout-center content">
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
</div>

<div id="newClassRoom">
</div>
<div id="editClassRoom"></div>
<jsp:include page="/static/aside.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
</body>
</html>
