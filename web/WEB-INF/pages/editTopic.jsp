<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 17-Mar-20
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $("#classRoomU").val('${topic.classRoom.id}');
        $("#lessonU").val('${topic.lesson.id}');
    });
</script>
<style>
    #formTopic {
        display: grid;
        line-height: 20px;
        margin-top: 15px;
    }
</style>
<div id="formTopic">

    <label>Class Room</label>
    <select id="classRoomU">

        <c:forEach items="${classRoomList}" var="crl">

            <option value="${crl.id}">${crl.classNumber}</option>

        </c:forEach>
    </select>

    <label>Lesson</label>
    <select id="lessonU">

        <c:forEach items="${lessonList}" var="ll">

            <option value="${ll.id}">${ll.lessonName}</option>

        </c:forEach>
    </select>
    <label for="topicNameU">Topic Name:</label>
    <input type="text" id="topicNameU" name="topicName" value="${topic.topicName}">
</div>
