<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 16-Mar-20
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/css/newStudent.css">
<div id="formStudent">
    <label for="nameU">Name</label>
    <input type="text" name="name" id="nameU" value="${student.name}">
    <label for="surnameU">Surname</label>
    <input type="text" name="surname" id="surnameU" value="${student.surname}">
    <label for="usernameU">Username</label>
    <input type="text" name="username" id="usernameU" value="${student.username}">
    <label for="passwordU">Password</label>
    <input type="text" name="password" id="passwordU" value="${student.password}">
    <label>Role:</label>
    <c:set var="role" value="${student.role.id}"></c:set>
    <c:if test="${role == 1}">
        <input type="radio" name="roleU" value="Admin" id="adminU" checked>Admin &nbsp;
        <input type="radio" name="roleU" value="User" id="userU">User
    </c:if>
    <c:if test="${role == 2}">
        <input type="radio" name="roleU" value="Admin" id="adminU">Admin &nbsp;
        <input type="radio" name="roleU" value="User" id="userU" checked>User
    </c:if>
</div>
