<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 05-Mar-20
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<jsp:include page="/static/import.jsp"></jsp:include>
<script type="text/javascript" src="js/topic.js"></script>
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>

<div class="ui-layout-center content">
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
</div>

<div id="newTopic"></div>
<div id="editTopic"></div>
<jsp:include page="/static/aside.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
</body>
</html>

