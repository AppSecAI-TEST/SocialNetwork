<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <style>
        #page {
            margin-left: 100px;
            width: 600px;
            height: 200px;
            background-color: aliceblue;
        }
        .event-head{
            font-weight: normal;
            color: darkgoldenrod;
        }
        .event-head{
            font-weight: normal;
            color: darkgoldenrod;
        }
        .event-username{
            font-weight: normal;
            color: darkgoldenrod;
        }
        .img-icon{
            height:50px;
            width: 50px;
            overflow: hidden;
            margin: 0 auto;
            z-index: -1;
        }
    </style>
</head>
<body>
<sec:authorize access="!hasRole('ROLE_USER')">
    <div class="panel-heading">
        <h3 class="panel-title">
            <div align="left"><b>login</b> </div>
            <div align="right"><a href="/login/">login</a></div>
        </h3>
    </div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="panel-heading">
        <h3 class="panel-title">
            <div style="display: inline-block">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <h2>
                        Welcome : ${pageContext.request.userPrincipal.name} | <a
                            href="javascript:formSubmit()"> Logout</a>
                    </h2>
                </c:if>
            </div>
        </h3>
    </div>
</sec:authorize>
<c:forEach items="${eventList}" var="event">
<div id="page">
    <p class="event-head"><c:out value="${event.head}"/></p>
    <p class="event-body"><p><c:out value="${event.body}"/></p></p>
    <div>
        <img class="img-icon" src="data:image/jpeg;base64,${event.user.getImgAsBase64()}">
        <a class="event-username" href="/user?id=<c:out value='${event.user.id}'/>"><p><c:out value="${event.user.username}"/></p></a>
    </div>
</div>
</c:forEach>
</body>
</html>
