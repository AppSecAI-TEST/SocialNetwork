<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Social Network</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <style>
        p {
            margin: 0px !important;
        }
        .main-container {
            width: 100% !important;
        }
        .one-book {
            margin-bottom: 25px;
            margin-top: 25px;
        }
        .books-row {
            height: 310px;
        }
        .book-container {
            height: 100% !important;
            padding: 0px !important;
        }
        .img-wrapper {
            height: 310px;
            width: 200px;
            overflow: hidden;
            margin: 0 auto;
            z-index: -1;

        }
        .img-wrapper img {
            width: 100%;
            height: auto;
        }
        .img-wrapper:hover {
            width: 230px;
            height: auto;
            overflow: visible;
            z-index: 1000;
            position: relative;
            -webkit-transition: 1s all ease-in-out 0s;
            transition: 1s all ease-in-out 0s;
        }
        .book-name {
            text-align: center;
            margin-top: 20px;
            font-size: 25px;
        }
        .author {
            opacity: 0.5;
            text-align: center;

        }
        .genre {
            margin-top: 15px;
            text-align: center;
        }
        .genre span {
            padding: 5px 20px;
            background-color: #3ca6d7;
            color: #fff;
            border-radius: 10px;
        }
        .price {
            font-size: 30px;
            opacity: 0.5;
            bottom: 10px;
            position: absolute;
            text-align: center;
            width: 100%;
        }
        .add-button {
            position: absolute;
            width: 100%;
            text-align: center;
            bottom: 20%;
        }
        .add-button a {
            font-size: 20px;
            font-weight: 900;
            padding: 10px 50px;
            background-color: #b5b5b5;
            color: #fff;
            border-radius: 50px;
        }
        .add-button a:hover {
            text-decoration: none;
            background-color: #a1a1a1;
            -webkit-transition: 0.5s background-color ease-in-out 0s;
            transition: 0.5s background-color ease-in-out 0s;
        }
        #searchName {
            width: 20%;
            border: none;
            border-bottom: 1px solid #999;
            line-height: 35px;
            margin: 0px 20px 0px 20px;
            -webkit-transition: 2s width ease-in-out 0s;
            transition: 2s width ease-in-out 0s;
        }
        #searchName:focus {
            width: 70%;
            -webkit-transition: 2s width ease-in-out 0s;
            transition: 2s width ease-in-out 0s;

        }
        .srch {
            display: inline-block;
            margin-right: 10px;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div>
    <c:url value="/logout" var="logoutUrl" />
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
    <div class="panel panel-success">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="panel-heading">
            <h3 class="panel-title">
                <div align="left">
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
            <form action="searchUser">
                <div class="row">
                    <div class="col-md-4">Search Employees: <input type='text' name='searchName' id='searchName'/> </div>
                    <div class="col-md-4"><input class="btn btn-success" type='submit' value='Search'/></div>
                </div>
            </form>
        <c:if test="${empty usersList}">
    There are no Users
    </c:if>
    <c:if  test="${not empty usersList}">


    <div class="container main-container">
        <div class="row" id="parentRowContainer">
    <c:forEach items="${usersList}" var="user">
            <div class="col-lg-4 col-md-6 one-book">
                <div class="row books-row">
                        <div class="col-xs-6 book-container">
                            <div class="img-wrapper">
                                <img src="data:image/jpeg;base64,${user.getImgAsBase64()}">
                            </div>
                        </div>
                    <div class="col-xs-6 book-container">
                        <div class="book-name">
                            <p><c:out value="${user.name}"/></p>
                        </div>
                        <div class="author">
                            <p><c:out value="${user.surname}"/></p>
                        </div>
                        <div class="genre">
                            <span><c:out value="${user.info}"/></span>
                        </div>
                        <div class="price">
                            <a class="event-username" href="/user?id=<c:out value='${user.id}'/>"><p><c:out value="${user.username}"/></p></a>
                        </div>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <div class="add-button">
                                <a class="add-button-link" href="addToFriends?id=<c:out value='${user.id}'/>">Add</a>
                            </div>
                            <form:form id="form" cssClass="form-horizontal" method="post" action="sand">
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input class="form-control" type='text' name='massage' placeholder="massage...">
                                <input type="submit" class="btn btn-primary" value="sand"/>
                            </form:form>
                        </sec:authorize>
                        <a class="add-button-link" href="accept?id=<c:out value='${user.id}'/>">Add</a>
                    </div>
                </div>
            </div>
    </c:forEach>
        </div>
    </div>
    </c:if>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
     --%>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("#searchSubmitButton").click(function(e){
                        e.preventDefault();
                        /*
                        $.get("/searchBook",function(data,status){
                            alert("Data: " + data + "\nStatus: " + status);
                        });*/
                        searchViaAjax();
                    });
                });
                function searchViaAjax() {

                    var _searchName = $("#searchName").val();

                    $.ajax({
                        type : "GET",
                        contentType : "application/json",
                        url : "/searchBook?searchName="+ _searchName,
                        //data : JSON.stringify(search),
                        dataType : 'json',
                        timeout : 100000,
                        success : function(data) {
                            console.log("SUCCESS: ", data);


                            while (true) {
                                var elementsToRemove = document.getElementsByClassName("col-lg-4 col-md-6 one-book");
                                if (elementsToRemove.length == 0) break;
                                elementsToRemove[0].parentNode.removeChild(elementsToRemove[0]);
                            }

for (var i = 0; i < data.length; i++) {
    var _html = '<div class="col-lg-4 col-md-6 one-book">'+
            '<div class="row books-row">'+
            '<div class="col-xs-6 book-container">'+
            '<div class="img-wrapper">'+
            '<img src="data:image/jpeg;base64,'+data[i]["imgAsBase64"]+'">'+
            '</div>'+
            '</div>'+
            '<div class="col-xs-6 book-container">'+
            '<div class="book-name">'+
            '<p>'+data[i]["name"]+'</p>'+
            '</div>'+
            '<div class="author">'+
            '<p>'+data[i]["author"]+'</p>'+
            '</div>'+
            '<div class="genre">'+
            '<span>'+data[i]["type"]+'</span>'+
            '</div>'+
            '<sec:authorize access="hasRole('ROLE_ADMIN')">'+
            '<div class="add-button">'+
            '<a class="add-button-link" href="editBooks?id='+data[i]["id"]+'">Edit</a>'+
            '/div>'+
            '</sec:authorize>'+
            '<sec:authorize access="hasRole('ROLE_USER')">'+
            '<div class="add-button">'+
            '<a class="add-button-link" href="addBook?id='+data[i]["id"]+'">Add</a>'+
            '</div>'+
            '</sec:authorize>'+
            '<div class="price">'+
            '<p>'+data[i]["price"]+'$</p>'+
            '</div>'+
            '</div>'+
            '</div>'+
            '</div>';
    document.getElementById('parentRowContainer').innerHTML += _html;
}

                        },
                        error : function(e) {
                            console.log("ERROR: ", e);
                            //display(e);
                        },
                        done : function(e) {
                            console.log("DONE");
                            //enableSearchButton(true);
                        }
                    });

                }
    </script>
</body>
</html>