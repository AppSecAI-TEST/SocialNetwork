<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bohdan
  Date: 17.10.16
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${massageList}" var="massage">
    <div id="page">
        <p class="event-head"><c:out value="${massage.massage}"/></p>
        <p class="event-head">from :<c:out value="${massage.firsUser.username}"/></p>
    </div>
</c:forEach>
</body>
</html>
