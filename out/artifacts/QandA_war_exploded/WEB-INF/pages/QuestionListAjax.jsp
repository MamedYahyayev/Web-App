<%@ page import="az.maqa.project.model.Questions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $("#questionTable").DataTable({
        searching: false
    });
</script>
<table border="1px solid black" id="questionTable" style="width: 100%" class="display">
    <thead>
    <tr>
        <th>Id</th>
        <th>Class Number</th>
        <th>Lesson Name</th>
        <th>Topic Name</th>
        <th>Question</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${questionList}" var="ql">
        <tr>
            <th>${ql.id}</th>
            <th>${ql.topic.classRoom.classNumber}</th>
            <th>${ql.topic.lesson.lessonName}</th>
            <th>${ql.topic.topicName}</th>
            <th>${ql.questions}</th>
            <th><a href="javascript: editQuestions(${ql.id})">Edit</a></th>
            <th><a href="javascript: deleteQuestions(${ql.id})">Delete</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>