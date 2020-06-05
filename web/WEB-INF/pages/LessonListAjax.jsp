<%@ page import="az.maqa.project.model.Lesson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $("#lessonTable").DataTable({
        searching: false
    });
</script>
<table border="1px solid black" id="lessonTable" style="width: 100%" class="display">
    <thead>
    <tr>
        <th>Id</th>
        <th>Lesson Name</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lessonList}" var="ll">
        <tr>
            <th>${ll.id}</th>
            <th>${ll.lessonName}</th>
            <th><a href="javascript: editLesson(${ll.id})">Edit</a></th>
            <th><a href="javascript: deleteLesson(${ll.id})">Delete</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>