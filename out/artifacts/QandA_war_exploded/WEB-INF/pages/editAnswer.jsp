<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Windows10
  Date: 16-Mar-20
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/css/newAnswer.css">
<script type="text/javascript">
    $(function () {
        $('#questionU').val('${answer.questions.id}');
    });


</script>
<div id="formAnswer">
    <label>Question</label>
    <select id="questionU">
        <%--<option value="${answer.questions.id}">${answer.questions.questions}</option>--%>
        <c:forEach items="${questionList}" var="ql">
            <%--<c:if test="${ql.id != answer.questions.id}">--%>
            <option value="${ql.id}">${ql.questions}</option>
            <%--</c:if>--%>
        </c:forEach>
    </select>
    <label for="answerU">Answer</label>
    <textarea id="answerU" style="width: 595px;
    height: 200px;" name="answer">${answer.answer}</textarea>
    <div class="radiobtndesign">
        <label>Answer Status:</label>
        <c:set var="answerStatus" value="${answer.answerStatus.id}"></c:set>
        <c:if test="${answerStatus == 1}">
            <input type="radio" name="answerStatusU" value="DogruCavab" checked>Dogru Cavab &nbsp;
            <input type="radio" name="answerStatusU" value="YanlisCavab">Yanlis Cavab
        </c:if>
        <c:if test="${answerStatus == 2}">
            <input type="radio" name="answerStatusU" value="DogruCavab">Dogru Cavab &nbsp;
            <input type="radio" name="answerStatusU" value="YanlisCavab" checked>Yanlis Cavab
        </c:if>
    </div>
</div>
