<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 04-Mar-20
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<script type="text/javascript" src="js/question.js"></script>
<link rel="stylesheet" type="text/css" href="css/newQuestion.css">
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>

<div class="ui-layout-center content">
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
                <th><a href="javascript: deleteQuestion(${ql.id})">Delete</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="newQuestion"></div>
<div id="editQuestion"></div>
<jsp:include page="/static/aside.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
</body>
</html>
