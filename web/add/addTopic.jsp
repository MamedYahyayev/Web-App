<%@ page import="az.maqa.project.model.ClassRoom" %>
<%@ page import="az.maqa.project.service.inter.ClassService" %>
<%@ page import="java.util.List" %>
<%@ page import="az.maqa.project.dao.inter.ClassDao" %>
<%@ page import="az.maqa.project.dao.impl.ClassDaoImpl" %>
<%@ page import="az.maqa.project.service.impl.ClassServiceImpl" %>
<%@ page import="az.maqa.project.dao.inter.LessonDao" %>
<%@ page import="az.maqa.project.service.inter.LessonService" %>
<%@ page import="az.maqa.project.service.impl.LessonServiceImpl" %>
<%@ page import="az.maqa.project.dao.impl.LessonDaoImpl" %>
<%@ page import="az.maqa.project.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 15-Mar-20
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/topic.js" type="text/javascript"></script>
<style>
    #formTopic{
        display: grid;
        line-height: 20px;
        margin-top: 15px;
    }
</style>
<div id="formTopic">
<label>Class Room</label>
<select id="classRoom">
    <% ClassDao classRoomDao = new ClassDaoImpl();
        ClassService classService = new ClassServiceImpl(classRoomDao);
        List<ClassRoom> classRoomList = classService.getClassList();
        for (ClassRoom classRoom : classRoomList) { %>
    <option value="<%= classRoom.getId()%>"><%= classRoom.getClassNumber() %>
    </option>
    <% } %>
</select>
<label>Lesson</label>
<select id="lesson">
    <% LessonDao lessonDao = new LessonDaoImpl();
        LessonService lessonService = new LessonServiceImpl(lessonDao);
        List<Lesson> lessonList = lessonService.getLessonList();
        for (Lesson lesson : lessonList) { %>
    <option value="<%= lesson.getId()%>"><%= lesson.getLessonName()%>
    </option>
    <% } %>
</select>
<label for="topicName">Topic Name:</label>
<input type="text" id="topicName" name="topicName">
</div>