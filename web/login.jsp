
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/logincss.css" rel="stylesheet" type="text/css" >
        <title>LOGIN PAGE</title>
    </head>
    <body>
        <header>
            <header class="leftH"><a href="home.jsp" class="class1">HANASHOP</a></header>
        </header>
        
        <c:set var="loginFail" value="${requestScope.LOGINFAIL}"/>
        
        <div id="wrapper">
            <form action="DispatchServlet" method="POST" class="form1">
                <p class="txtLogin">LOGIN</p>
                <label>ID</label>
                <input type="text" name="txtID" value="" /> </br> </br>
                <label style="margin-left: -150px;">PASSWORD</label> 
                <input type="password" name="txtPassword" value="" /> </br> 
                <c:if test="${not empty loginFail}">
                    <div class="loginFail">${loginFail}</div>
                </c:if>
                <input type="submit" value="LOGIN" name="btAction" id="submit"/>
            </form>
            <!--<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/AccessGoogle/login-google&response_type=code&client_id=32782945495-bugtv1fudom1ehvjnilaajisrffktkbp.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>-->
        </div>
    </body>
</html>
