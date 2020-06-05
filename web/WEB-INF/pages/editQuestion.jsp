<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="az.maqa.project.dao.inter.TopicDao" %>
<%@ page import="az.maqa.project.service.inter.TopicService" %>
<%@ page import="az.maqa.project.service.impl.TopicServiceImpl" %>
<%@ page import="az.maqa.project.dao.impl.TopicDaoImpl" %>
<%@ page import="az.maqa.project.model.Topic" %>
<%@ page import="java.util.List" %>
<%@ page import="az.maqa.project.model.Questions" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 17-Mar-20
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/css/newQuestion.css">

<% Questions question = (Questions) request.getAttribute("questions"); %>

<div id="formQuestion">
    <label>Topic</label>
    <select id="topicU" required>
        <option value="${questions.topic.id}">${questions.topic.classRoom.classNumber}
            -- ${questions.topic.lesson.lessonName} -- ${questions.topic.topicName}
        </option>
        <c:forEach items="${topicList}" var="tl">
            <c:if test="${tl.id != questions.topic.id}">
                <option value="${tl.id}">${tl.classRoom.classNumber} -- ${tl.lesson.lessonName}
                    -- ${tl.topicName}</option>
            </c:if>
        </c:forEach>
    </select>
    <label for="questionU">Question</label>
    <textarea id="questionU" style="width: 595px;
    height: 290px;" name="question" required><%=question.getQuestions()%>></textarea>
</div>
