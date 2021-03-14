

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/paymentcss.css" rel="stylesheet" type="text/css" >
        <title>SUCCESS PAYMENT</title>
    </head>
    <body>
        <header>
            <header class="leftH"><a href="home.jsp" class="class1">HANASHOP</a></header>
            
            <c:set value="${sessionScope.NAME}" var="user"/>
            <c:set value="${sessionScope.USERID}" var="userID"/>
            <c:set value="${sessionScope.YOURCART}" var="yourcart"/>
            
            <c:if test="${not empty user}">
                <header class="rightH">
                    <c:if test="${empty yourcart}">
                        <a href="viewcart.jsp"><img src="assets/css/img/cart empty.png" class="imgCart"></a>
                    </c:if>
                    <c:if test="${not empty yourcart}">
                        <a href="viewcart.jsp"><img src="assets/css/img/cart.png" class="imgCart"></a>
                    </c:if>
                </header>
                <header class="rightH">
                    <h2 class="yourCart">YOUR BILL</h2>
                </header>
                <header class="rightH">
                    <form action="DispatchServlet" class="form1">
                        <input type="submit" value="LOGOUT" name="btAction" class="logout"/>
                    </form>
                </header>
                <header class="rightH">
                    <div class="div1">HELLO ${user}</div>
                </header>
            </c:if>
        </header>
        
        <div id="wrapper">
            <h1 class="success">YOUR ORDER HAS BEEN RECEIVED </br> THANK YOU FOR YOUR PURCHASE</h1>
            <div>
                <form action="DispatchServlet" class="form2">
                    <input type="submit" value="CONTINUE SHOPPING" name="btAction" class="button1"/>
                    <input type="submit" value="VIEW HISTORY" name="btAction" class="button1"/>
                </form>
            </div>
        </div>
    </body>
</html>
