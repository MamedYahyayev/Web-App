<%@ page import="az.maqa.project.model.Topic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $("#topicTable").DataTable({
        searching: false
    });
</script>
<table id="topicTable" style="width: 100%" class="display" border="1px solid black">
    <thead>
    <tr>
        <th>Id</th>
        <th>Class Number</th>
        <th>Lesson Name</th>
        <th>Topic Name</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${topicList}" var="tl">
        <tr>
            <th>${tl.id}</th>
            <th>${tl.classRoom.classNumber}</th>
            <th>${tl.lesson.lessonName}</th>
            <th>${tl.topicName}</th>
            <th><a href="javascript: editTopic(${tl.id})">Edit</a></th>
            <th><a href="javascript: deleteTopic(${tl.id})">Delete</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>