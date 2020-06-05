<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 13-Mar-20
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/student.js"></script>
<link rel="stylesheet" type="text/css" href="/css/newStudent.css">
<div id="formStudent">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" required>
    <label for="surname">Surname</label>
    <input type="text" name="surname" id="surname" required>
    <label for="username">Username</label>
    <input type="text" name="username" id="username" required>
    <label for="password">Password</label>
    <input type="text" name="password" id="password" required>
    <label>Role:</label>
    <input type="radio" name="role" value="Admin" id="admin">Admin &nbsp;
    <input type="radio" name="role" value="User" id="user">User
</div>