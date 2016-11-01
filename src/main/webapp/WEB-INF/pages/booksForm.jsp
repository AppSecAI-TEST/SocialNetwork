<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Book Information</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
        .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">
                Book Details
            </h3>
        </div>
        <div class="panel-body">
            <form:form id="booksRegisterForm" cssClass="form-horizontal" modelAttribute="book" method="post" action="saveBook">
                <th><a href="deleteBooks?id=<c:out value='${books.id}'/>">Delete</a></th>
                <div class="form-group">
                    <div class="control-label col-xs-3"> <form:label path="name" >name</form:label> </div>
                    <div class="col-xs-6">
                        <form:hidden path="id" value="${bookObject.id}"/>
                        <form:input cssClass="form-control" path="name" value="${bookObject.name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="surname" cssClass="control-label col-xs-3">surname</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="surname" value="${bookObject.surname}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="info" cssClass="control-label col-xs-3">info</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="info" value="${bookObject.info}"/>
                        <form:errors path="info" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="username" cssClass="control-label col-xs-3">username</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="username" value="${bookObject.username}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="password" cssClass="control-label col-xs-3">password</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="password" value="${bookObject.password}"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4">
                            <input type="submit" id="saveBook" class="btn btn-primary" value="Save"/>
                        </div>
                        <div class="col-xs-4">
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>

</body>
</html>