<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


Created by IntelliJ IDEA.
User: Windows10
Date: 04-Mar-20
Time: 21:26
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<script type="text/javascript" src="js/answer.js"></script>
<link rel="stylesheet" type="text/css" href="css/newAnswer.css">
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>

<div class="ui-layout-center content">
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
</div>
<div id="newAnswer"></div>
<div id="editAnswer"></div>
<jsp:include page="/static/aside.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
</body>
</html>
