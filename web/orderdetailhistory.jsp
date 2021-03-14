
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/viewhistoryorderdetailcss.css" rel="stylesheet" type="text/css" >
        <!--<script src="assets/js/homejs.js"></script>-->
        <title>ORDER HISTORY</title>
    </head>
    <body>
        <header>
            <header class="leftH"><a href="home.jsp" class="class1">HANASHOP</a></header>
            
            <c:set value="${sessionScope.NAME}" var="user"/>
            <c:set value="${sessionScope.USERID}" var="userID"/>
            
            <c:set value="${requestScope.ORDERINFO}" var="orderInfo"/>
            <c:set var="orderID" value="${requestScope.ORDERID}"/>
            
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
                    <h2 class="yourCart">${orderID}</h2>
                </header>
                <header class="rightH">
                    <form action="DispatchServlet" class="form1">
                        <input type="submit" value="LOGOUT" name="btAction" class="logout"/>
                        <input type="submit" value="VIEW HISTORY" name="btAction" class="viewHistory"/>
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
            
            <c:set var="historyOrderDetail" value="${requestScope.HISTORYORDERDETAIL}"/>
            <c:set var="listProduct" value="${requestScope.LISTPRODUCT}"/>
            <c:set var="categories" value="${sessionScope.CATEGORIES}"/>

            <c:if test="${not empty historyOrderDetail}">
                <h2 class="orderID">ORDER ID ${orderID}</h2>
                
                <div id="wrapper2">
                    <div id="wrapperTitleLeft">
                        RECEIVER'S ADDRESS
                    </div>

                    <div id="wrapperTitleRight">
                        PAYMENT METHOD
                    </div>
                    
                    <div id="wrapperContentLeft">
                        <h3 class="infoName">${orderInfo.name}</h3>
                        <h3 class="infoAddress">ADDRESS: ${orderInfo.address}</h3>
                        <h3 class="infoAddress">PHONE: ${orderInfo.phone}</h3>
                    </div>

                    <div id="wrapperContentRight">
                        <h3 class="infoName">${orderInfo.paymentMethod}</h3>
                    </div>
                </div>
                
                
                <div id="wrapper3">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>PRODUCT DETAIL</th>
                                <th>PRICE</th>
                                <th>AMOUNT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${historyOrderDetail}" varStatus="counter">
                                <form action="DispatchServlet">
                                    
                                    <tr>
                                        <td>
                                            <c:forEach var="pitems" items="${listProduct}" varStatus="counter1">
                                                <c:if test="${dto.productID eq pitems.getKey()}">
                                                    <form action="DispatchServlet">
                                                        <span id="wrapper4">
                                                            <div class="wrapperLeft">
                                                                <img src="assets/css/img/${pitems.value.image}" class="imgSearch"/>
                                                            </div>

                                                            <div class="wrapperMiddle">
                                                                <h2 class="productName">${pitems.value.name}</h2>

                                                                <c:forEach var="category" items="${categories}">
                                                                    <c:if test="${pitems.value.category eq category.categoryId}">
                                                                        <h3 class="productCategory">CATEGORY: ${category.categoryName}</h3>
                                                                    </c:if>
                                                                </c:forEach>

                                                                </br> </br>

                                                                <h3 class="productDes">
                                                                    ${pitems.value.description}
                                                                </h3>
                                                            </div>
                                                        </span>
                                                    </form>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${dto.price} $</td>
                                        <td>${dto.amount}</td>
                                        <input type="hidden" name="txtOrderID" value="" readonly="readonly" />
                                    </tr>
                                </form>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div id="wrapper5">
                        
                    </div>
                </div>
            </c:if>
                
            <c:if test="${empty historyOrderDetail}">
                <h2 class="orderID">YOUR ORDER HISTORY IS EMPTY</h2>
            </c:if>
        </div>
    </body>
</html>
