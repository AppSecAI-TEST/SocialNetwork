<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Information</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
        .title {
            text-align: center;
            font-size: 30px;
            font-weight: 900;
        }
        .registration-container {
            margin: 0 auto;
            min-width: 600px;
            max-height: 800px;
        }
        .form-control {
            border: none;
            border-bottom: 1px solid #999;
            margin: 0 auto;
            padding-left: 30px;
            padding-right: 30px;
            width: 40%;
            box-shadow: none;
            -webkit-box-shadow: none;
            border-radius: 0px;
            margin-top: 30px;
        }
        #addUser {
            margin: 0 auto;
            width: 40%;
            display: block;
            margin-top: 30px;
        }

    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-offset-4 col-xs-4 col-xs-offset-4">
                <h3 class="title">
                    Users Registration
                </h3>
            </div>
                <form:form id="usersRegisterForm" cssClass="form-horizontal"  method="post" action="addUser" enctype="multipart/form- data">
                    <div class="container registration-container">
                        <div class="row">
                            <div class="col-xs-12">
                                <input class="form-control" type='text' name='name' placeholder="Your Name...">
                            </div>
                            <div class="col-xs-12">
                                <input class="form-control" type='text' name='surname' placeholder="Surname...">
                            </div>
                            <div class="col-xs-12">
                                <input class="form-control" type='text' name='info' placeholder="info about you...">
                            </div>
                            <div class="col-xs-12">
                                <input class="form-control" type='text' name='username' placeholder="username...">
                            </div>
                            <div class="col-xs-12">
                                <input class="form-control" type='password' name='password' placeholder="Password...">
                            </div>
                            <div class="col-xs-12">
                                <input class="form-control" type='file' name='avatar'>
                            </div>
                            <div class="col-xs-12">
                                <input type="submit" id="addUser" class="btn btn-primary" value="Save" onclick="return submitUserForm();"/>
                            </div>
                        </div>
                    </div>
                </form:form>
        </div>
    </div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function submitUserForm() {
			
		    // getting the User form values

		    var username = $('#username').val();
            var password = $('#password').val();

		    if(password <= 0) {
		        alert('Please enter proper password');
		        $('#paswword').focus();
		        return false;
		    }
	
		    if(username <= 0) {
		        alert('Please enter proper username');
		        $('#username').focus();
		        return false;
		    }
		    return true;
		};	
	</script>

</body>
</html>