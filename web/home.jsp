
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/homecss.css" rel="stylesheet" type="text/css" >
        <script src="assets/js/homejs.js"></script>
        <title>HANASHOP</title>
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
            <div id="wrapper2">
                <form class="form3" action="DispatchServlet">
                    <div class="rangePrice">RANGE OF PRICE</div>
                    <input id="minPrice" type="number" name="txtMinPrice" min="0" max="100" step="0.1" value="${requestScope.MINPRICE}" class="rangePrice" onchange="checkMinPrice()">
                    -
                    <input id="maxPrice" type="number" name="txtMaxPrice" min="0" max="100" step="0.1" value="${requestScope.MAXPRICE}" class="rangePrice" onchange="checkMaxPrice()">
                    <input type="submit" value="OK" name="btAction" class="btOK"/>
                    
                    <c:set var="noresult" value="${requestScope.NORESULT}"/>
                    <h3 style="font-size: 15px; float: right;">${noresult}</h3>
                </form>
                
                <form class="form2" action="DispatchServlet">
                    <input type="submit" value="SEARCH" name="btAction" class="btSearch"/>
                    <input type="text" name="txtSearch" value="${param.txtSearch}" class="search"/>
                </form>
            </div>
            
            <c:set var="searchByPrice" value="${requestScope.SEARCHBYPRICE}"/>
            <c:set var="txtSearchValue" value="${param.txtSearch}"/>
            <c:set var="categories" value="${sessionScope.CATEGORIES}"/>
            
            <c:if test="${(empty txtSearchValue) && (empty searchByPrice)}">
                <c:set var="firstLoad" value="${sessionScope.FIRSTLOAD}"/>
                
                <c:if test="${not empty firstLoad}">
                    <div id="wrapper3">
                        <c:forEach var="dto" items="${firstLoad}" varStatus="counter">
                            <form action="DispatchServlet">
                                <span id="wrapper4">
                                    <div class="wrapperLeft">
                                        <img src="assets/css/img/${dto.image}" class="imgSearch"/>
                                    </div>
                                    
                                    <div class="wrapperMiddle">
                                        <h2 class="productName">${dto.name}</h2>
                                        
                                        <c:forEach var="category" items="${categories}">
                                            <c:if test="${dto.category eq category.categoryId}">
                                                <h3 class="productCategory">CATEGORY: ${category.categoryName}</h3>
                                            </c:if>
                                        </c:forEach>
                                                
                                        </br> </br> </br> </br>
                                                
                                        <h3 class="productDes">
                                            ${dto.description}
                                        </h3>
                                    </div>
                                    
                                    <div class="wrapperRight">
                                        <h2 id="priceShow${counter.count}" class="productPrice">${dto.price} $</h2>
                                        <input id="price${counter.count}" type="hidden" name="priceOfProduct" value="${dto.price}" readonly="readonly" />
                                        <input id="amount${counter.count}" type="number" name="txtAmount" min="1" max="${dto.quantity}" step="1" value="1" class="amount" onchange="calPrice(${counter.count})">
                                        <input id="total${counter.count}" type="hidden" name="txtTotalPrice" value="${dto.price}" />
                                        
                                        <input type="hidden" name="txtUserID" value="${userID}" readonly="readonly" />
                                        <input type="hidden" name="txtProductID" value="${dto.id}" readonly="readonly" />
                                        <input type="hidden" name="txtProductName" value="${dto.name}" readonly="readonly" />
                                        <input type="hidden" name="txtImage" value="${dto.image}" readonly="readonly" />
                                        <input type="hidden" name="txtDescription" value="${dto.description}" readonly="readonly" />
                                        <input type="hidden" name="txtCategory" value="${dto.category}" readonly="readonly" />
                                        <input type="hidden" name="txtQuantity" value="${dto.quantity}" readonly="readonly" />
                                        <input type="text" name="txtSearch" value="${txtSearchValue}" hidden/>
                                        
                                        <input type="submit" value="ADD TO CART" name="btAction" class="addToCart"/>
                                    </div>
                                </span>
                            </form>
                        </c:forEach>
                        
                        <c:set var="totalRecordFirst" value="${sessionScope.TOTALRECORDFIRST}"/>
                        <input type="hidden" name="t" value="${totalRecordFirst}" readonly="readonly" />
                        
                        <div id="wrapper5">
                            <form action="DispatchServlet" class="form4">
                                <c:if test="${totalRecordFirst lt 4}">
                                    <c:forEach begin="1" end="${totalRecordFirst}" varStatus="counterPageFirst">
                                        <input type="submit" value="${counterPageFirst.count}" name="btAction" class="btnPage"/>
                                    </c:forEach>
                                </c:if>
                                        
                                <c:if test="${totalRecordFirst ge 4}">
                                    <input type="submit" value="1" name="btAction" class="btnPage"/>
                                    <input type="submit" value="2" name="btAction" class="btnPage"/>
                                    <input type="submit" value="3" name="btAction" class="btnPage"/>
                                    <input type="submit" value="4" name="btAction" class="btnPage"/>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </c:if>
            </c:if>
            
            <c:if test="${(not empty txtSearchValue) || (not empty searchByPrice)}">
                <c:set var="resultSearch" value="${requestScope.RESULTSEARCH}"/>
                <c:set var="totalRecord" value="${requestScope.TOTALRECORD}"/>
            
                <c:if test="${not empty resultSearch}">
                    <div id="wrapper3">
                        <c:forEach var="dto" items="${resultSearch}" varStatus="counter">
                            <form action="DispatchServlet">
                                <span id="wrapper4">
                                    <div class="wrapperLeft">
                                        <img src="assets/css/img/${dto.image}" class="imgSearch"/>
                                    </div>
                                    
                                    <div class="wrapperMiddle">
                                        <h2 class="productName">${dto.name}</h2>
                                        
                                        <c:forEach var="category" items="${categories}">
                                            <c:if test="${dto.category eq category.categoryId}">
                                                <h3 class="productCategory">CATEGORY: ${category.categoryName}</h3>
                                            </c:if>
                                        </c:forEach>
                                                
                                        </br> </br> </br> </br>
                                                
                                        <h3 class="productDes">
                                            ${dto.description}
                                        </h3>
                                    </div>
                                    
                                    <div class="wrapperRight">
                                        <h2 id="priceShow${counter.count}" class="productPrice">${dto.price} $</h2>
                                        <input id="price${counter.count}" type="hidden" name="priceOfProduct" value="${dto.price}" readonly="readonly" />
                                        <input id="amount${counter.count}" type="number" name="txtAmount" min="1" max="${dto.quantity}" step="1" value="1" class="amount" onchange="calPrice(${counter.count})">
                                        <input id="total${counter.count}" type="hidden" name="txtTotalPrice" value="${dto.price}" />
                                        
                                        <input type="hidden" name="txtUserID" value="${userID}" readonly="readonly" />
                                        <input type="hidden" name="txtProductID" value="${dto.id}" readonly="readonly" />
                                        <input type="hidden" name="txtProductName" value="${dto.name}" readonly="readonly" />
                                        <input type="hidden" name="txtImage" value="${dto.image}" readonly="readonly" />
                                        <input type="hidden" name="txtDescription" value="${dto.description}" readonly="readonly" />
                                        <input type="hidden" name="txtCategory" value="${dto.category}" readonly="readonly" />
                                        <input type="hidden" name="txtQuantity" value="${dto.quantity}" readonly="readonly" />
                                        <input type="text" name="txtSearch" value="${txtSearchValue}" hidden/>
                                        
                                        <input type="submit" value="ADD TO CART" name="btAction" class="addToCart"/>
                                    </div>
                                </span>
                            </form>
                        </c:forEach>
                        
                        <div id="wrapper5">
                            <form action="DispatchServlet" class="form4">
                                <input type="text" name="txtSearch" value="${txtSearchValue}" hidden/>
                                <input type="hidden" name="txtSearchByPrice" value="${searchByPrice}" readonly="readonly" />
                                <c:forEach begin="1" end="${totalRecord}" varStatus="counterPage">
                                    <input type="submit" value="${counterPage.count}" name="btAction" class="btnPage"/>
                                </c:forEach>
                            </form>
                        </div>
                    </div>
                </c:if>
                
            </c:if>
            
        </div>
            
        
    </body>
</html>
