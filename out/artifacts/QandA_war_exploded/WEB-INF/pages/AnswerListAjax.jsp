<%@ page import="az.maqa.project.model.Answer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $("#answerTable").DataTable({
        searching: false
    });
</script>

<table border="1px solid black" id="answerTable" class="display" style="width: 100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>Class Number</th>
        <th>Lesson Name</th>
        <th>Topic Name</th>
        <th>Question</th>
        <th>Answer</th>
        <th>Answer Status</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${answerList}" var="al">
        <tr>
            <th>${al.id}</th>
            <th>${al.questions.topic.classRoom.classNumber}</th>
            <th>${al.questions.topic.lesson.lessonName}</th>
            <th>${al.questions.topic.topicName}</th>
            <th>${al.questions.questions}</th>
            <th>${al.answer}</th>
            <th>${al.answerStatus.statusName}</th>
            <th><a href="javascript: editAnswer(${al.id})">Edit</a></th>
            <th><a href="javascript: deleteAnswer(${al.id})">Delete</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>