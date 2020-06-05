<%@ page import="az.maqa.project.dao.inter.TopicDao" %>
<%@ page import="az.maqa.project.dao.impl.TopicDaoImpl" %>
<%@ page import="az.maqa.project.service.inter.TopicService" %>
<%@ page import="az.maqa.project.service.impl.TopicServiceImpl" %>
<%@ page import="az.maqa.project.model.Topic" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/question.js"></script>
<link rel="stylesheet" type="text/css" href="/css/newQuestion.css">
<div id="formQuestion">
    <label>Topic</label>
    <select id="topic" required>
        <%
            TopicDao topicDao = new TopicDaoImpl();
            TopicService topicService = new TopicServiceImpl(topicDao);
            List<Topic> topicList = topicService.getTopicList();
            for (Topic topic : topicList) { %>
        <option value="<%=topic.getId()%>"><%=topic.getClassRoom().getClassNumber() + "---" + topic.getLesson().getLessonName() + "---" + topic.getTopicName() %>
        </option>
        <% } %>
    </select>
    <label for="question">Question</label>
    <textarea id="question" style="width: 595px;
    height: 290px;" name="question" required></textarea>
</div>