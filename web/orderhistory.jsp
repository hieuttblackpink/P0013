
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/viewhistoryordercss.css" rel="stylesheet" type="text/css" >
        <!--<script src="assets/js/homejs.js"></script>-->
        <title>ORDER HISTORY</title>
    </head>
    <body>
        <header>
            <header class="leftH"><a href="home.jsp" class="class1">HANASHOP</a></header>
            
            <c:set value="${sessionScope.NAME}" var="user"/>
            <c:set value="${sessionScope.USERID}" var="userID"/>
            
            <c:if test="${not empty user}">
                <c:set value="${sessionScope.YOURCART}" var="yourcart"/>
                <header class="rightH">
                    <c:if test="${empty yourcart}">
                        <a href="viewcart.jsp"><img src="assets/css/img/cart empty.png" class="imgCart"></a>
                    </c:if>
                    <c:if test="${not empty yourcart}">
                        <a href="viewcart.jsp"><img src="assets/css/img/cart.png" class="imgCart"></a>
                    </c:if>
                </header>
                <header class="rightH">
                    <h2 class="yourCart">YOUR HISTORY ORDER</h2>
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
            
            <c:if test="${empty user}">
                <header class="rightH">
                    <form action="login.jsp" class="form1">
                        <input type="submit" value="LOGIN" name="btAction" class="login"/>
                    </form>
                </header>
            </c:if>
        </header>
            
        <div id="wrapper">
            
            <c:set var="historyOrder" value="${sessionScope.HISTORYORDER}"/>

            <c:if test="${not empty historyOrder}">
                <div id="wrapper3">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>ORDER ID</th>
                                <th>ORDER DATE</th>
                                <th>TOTAL</th>
                                <th>PAYMENT METHOD</th>
                                <th>VIEW</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${historyOrder}" varStatus="counter">
                                <form action="DispatchServlet">
                                    <tr>
                                        <td>${dto.orderID}</td>
                                        <td>${dto.orderDate}</td>
                                        <td>${dto.total} $</td>
                                        <td>${dto.paymentMethod}</td>
                                        <input type="hidden" name="txtOrderID" value="${dto.orderID}" readonly="readonly" />
                                        <input type="hidden" name="txtPaymentMethod" value="${dto.paymentMethod}" readonly="readonly" />
                                        <input type="hidden" name="txtName" value="${dto.name}" readonly="readonly" />
                                        <input type="hidden" name="txtPhone" value="${dto.phone}" readonly="readonly" />
                                        <input type="hidden" name="txtAddress" value="${dto.address}" readonly="readonly" />
                                        <td><input type="submit" value="VIEW DETAIL" name="btAction" class="btnView"/></td>
                                    </tr>
                                </form>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div id="wrapper5">
                        
                    </div>
                </div>
            </c:if>
            
            <c:if test="${empty historyOrder}">
                <h2 class="orderID">YOUR ORDER HISTORY IS EMPTY</h2>
            </c:if>
        </div>
    </body>
</html>
