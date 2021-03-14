
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/viewcartcss.css" rel="stylesheet" type="text/css" >
        <script src="assets/js/viewcartjs.js"></script>
        <title>YOUR CART</title>
    </head>
    <body>
        <header>
            <header class="leftH"><a href="home.jsp" class="class1">HANASHOP</a></header>
            
            <c:set value="${sessionScope.NAME}" var="user"/>
            <c:set value="${sessionScope.USERID}" var="userID"/>
            <c:set value="${sessionScope.YOURCART}" var="yourcart"/>
            
            <c:if test="${not empty user}">
                <header class="rightH">
                    <h2 class="yourCart">YOUR CART</h2>
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
            <c:set var="categories" value="${sessionScope.CATEGORIES}"/>
            <c:if test="${empty yourcart}">
                <h1 style="text-align: center; color: white; font-weight: bolder;">NOTHING TO SHOW</h1>
            </c:if>
            <c:if test="${not empty yourcart}">
                <div id="wrapper3">
                    <c:set var="items" value="${yourcart.items}"/>
                    <c:set var="totalbill" value="${0}"/>
                    <c:forEach var="productItems" items="${items.keySet()}" varStatus="counter">
                        
                        <form action="DispatchServlet">
                            <span id="wrapper4">
                                <div class="wrapperLeft">
                                    <img src="assets/css/img/${productItems.image}" class="imgSearch"/>
                                </div>

                                <div class="wrapperMiddle">
                                    <h2 class="productName">${productItems.name}</h2>

                                    <c:forEach var="category" items="${categories}">
                                        <c:if test="${productItems.category eq category.categoryId}">
                                            <h3 class="productCategory">CATEGORY: ${category.categoryName}</h3>
                                        </c:if>
                                    </c:forEach>

                                    </br> </br> </br> </br>

                                    <h3 class="productDes">
                                        ${productItems.description}
                                    </h3>
                                </div>

                                <div class="wrapperRight">
                                    <h2 id="priceShow${counter.count}" class="productPrice">${productItems.price * items.get(productItems)} $</h2>
                                    <input id="price${counter.count}" type="hidden" name="priceOfProduct" value="${productItems.price}" readonly="readonly" />
                                    <input id="amountBefore${counter.count}" type="hidden" name="amountBefore" value="${items.get(productItems)}" readonly="readonly" />
                                    <input id="amount${counter.count}" type="number" name="txtAmount" min="1" max="${productItems.quantity}" step="1" value="${items.get(productItems)}" class="amount" onchange="calPrice(${counter.count})">
                                    <input id="total${counter.count}" type="hidden" name="txtTotalPrice" value="${productItems.price}" />
                                    
                                    <input type="hidden" name="txtProductID" value="${productItems.id}" readonly="readonly" />
                                    <input type="hidden" name="txtProductName" value="${productItems.name}" readonly="readonly" />
                                    <input type="hidden" name="txtUserID" value="${userID}" readonly="readonly" />
                                    <input type="hidden" name="txtImage" value="${dto.image}" readonly="readonly" />
                                    <input type="hidden" name="txtDescription" value="${dto.description}" readonly="readonly" />
                                    <input type="hidden" name="txtCategory" value="${dto.category}" readonly="readonly" />
                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}" readonly="readonly" />
                                    <input type="text" name="txtSearch" value="${txtSearchValue}" hidden/>

                                    <input type="submit" value="REMOVE" name="btAction" class="removeFromCart" onclick="return confirmRemove()"/>
                                    <input id="addMore${counter.count}" type="submit" value="ADD MORE" name="btAction" class="addToCart" hidden/>
                                    <input id="reduce${counter.count}" type="submit" value="DECREASE" name="btAction" class="addToCart" hidden/>
                                </div>
                            </span>
                        </form>
                        <c:set var="totalbill" value="${totalbill + productItems.price*items.get(productItems)}"/>
                    </c:forEach>

                    <div id="wrapper5">
                        <form action="DispatchServlet">
                            <h1 class="bill">TOTAL BILL</h1> <div class="bill">${totalbill} $<div>
                            <input type="hidden" name="txtTotalBill" value="${totalbill}" readonly="readonly" />
                            <input type="hidden" name="txtUserID" value="${userID}" readonly="readonly" />
                            <input type="submit" value="CONFIRM" name="btAction" class="confirm"/>
                        </form>
                    </div>
                </div>

            </c:if>
            
        </div>
            
    </body>
</html>
