<%@ page import="az.maqa.project.dao.inter.QuestionsDao" %>
<%@ page import="az.maqa.project.dao.impl.QuestionsDaoImpl" %>
<%@ page import="az.maqa.project.service.inter.QuestionsService" %>
<%@ page import="az.maqa.project.service.impl.QuestionsServiceImpl" %>
<%@ page import="az.maqa.project.model.Questions" %>
<%@ page import="java.util.List" %>
<%@ page import="az.maqa.project.dao.inter.TopicDao" %>
<%@ page import="az.maqa.project.service.inter.TopicService" %>
<%@ page import="az.maqa.project.model.Topic" %>
<%@ page import="az.maqa.project.dao.impl.TopicDaoImpl" %>
<%@ page import="az.maqa.project.service.impl.TopicServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/answer.js"></script>
<link rel="stylesheet" type="text/css" href="/css/newAnswer.css">
<div id="formAnswer">
    <label>Topic</label>
    <select id="topic" required>
        <% TopicDao topicDao = new TopicDaoImpl();
            TopicService topicService = new TopicServiceImpl(topicDao);
            List<Topic> topicList = topicService.getTopicList();
            for (Topic topic : topicList) { %>
        <option value="<%=topic.getId()%>"><%=topic.getClassRoom().getClassNumber() + "---" + topic.getLesson().getLessonName() + "---" + topic.getTopicName() %>
        </option>
        <% } %>
    </select>
    <label>Question</label>
    <select id="question" required>
        <% QuestionsDao questionsDao = new QuestionsDaoImpl();
            QuestionsService questionsService = new QuestionsServiceImpl(questionsDao);
            List<Questions> questionsList = questionsService.getQuestionsList();
            for (Questions questions : questionsList) { %>
        <option value="<%=questions.getId()%>"><%=questions.getQuestions()%>
        </option>
        <% } %>
    </select>
    <label for="answer">Answer</label>
    <textarea id="answer" style="width: 595px;
    height: 200px;" name="answer" required></textarea>
    <div class="radiobtndesign">
        <label>Answer Status:</label>
        <input type="radio" name="answerStatus" value="DogruCavab">Dogru Cavab &nbsp;
        <input type="radio" name="answerStatus" value="YanlisCavab">Yanlis Cavab
    </div>
</div>