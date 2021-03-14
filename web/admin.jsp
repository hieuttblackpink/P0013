
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/adminpagecss.css" rel="stylesheet" type="text/css" >
        <script src="assets/js/adminjs.js"></script>
        <title>ADMIN PAGE</title>
    </head>
    <body>
        <h1>
            HANASHOP ADMIN, Welcome ${sessionScope.NAME}
            <form action="DispatchServlet" class="form1">
                <input type="submit" value="LOGOUT" name="btAction" class="logout"/>
            </form>
        </h1>
            
        <c:set var="categories" value="${sessionScope.CATEGORIES}"/> 
        <c:set var="firstCategories" value="${sessionScope.CATEGORIESFIRST}"/> 
        <c:set var="errors" value="${requestScope.ERRORADD}"/>
        
        <div class="wrapper1">
            <h2>ADD PRODUCT</h2>
            <form action="DispatchServlet">
                <label style="margin-right: 97px;">ID</label> <input type="text" name="txtIDProduct" value="${param.txtIDProduct}" required/> </br> </br>
                <label style="margin-right: 67px;">NAME</label> <input type="text" name="txtName" value="${param.txtName}" /> </br> </br>
                <label>DESCRIPTION</label> <textarea name="txtDescription" rows="10" cols="20">${param.txtDescription}</textarea> </br> </br>
                <label style="margin-right: 67px;">PRICE</label> <input type="text" name="txtPrice" value="${param.txtPrice}" /> </br> </br>
                <label style="margin-right: 27px;">CATEGORY</label> 
                <select name="txtCategory" style="width: 165px;" id="categorySelect" onchange="categorySelected()" required>
                    <c:forEach items="${categories}" var="category" varStatus="counter"> 
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="txtCategoryId" value="${firstCategories}" id="categoryID"/>
                </br> </br>
                <label style="margin-right: 27px;">QUANTITY</label> <input type="text" name="txtQuantity" value="${param.txtQuantity}" /> </br> </br>
                <label style="margin-right: 57px;">IMAGE</label>
                <input type="file" id="img" name="img" accept="image/*" onchange="loadFile(event)" hidden>
                <label for="img" class="label1">CHOOSE IMAGE</label> </br> </br>
                <img src="" id="output"/>
                </br> </br> 
                
                <input type="submit" value="ADD NEW" name="btAction" class="btAdd"/>
                
                </br> </br> 
                
                <label class="errors">
                    <c:if test="${not empty errors.idIsExistedErr}">
                        <div>${errors.idIsExistedErr}</div>
                    </c:if>
                        
                    <c:if test="${not empty errors.priceIsNotFloatErr}">
                        <div>${errors.priceIsNotFloatErr}</div>
                    </c:if>
                        
                    <c:if test="${not empty errors.priceSmallerThan0Err}">
                        <div>${errors.priceSmallerThan0Err}</div>
                    </c:if>
                        
                    <c:if test="${not empty errors.quantityIsNotIntErr}">
                        <div>${errors.quantityIsNotIntErr}</div>
                    </c:if>
                        
                    <c:if test="${not empty errors.quantitySmallerThan0Err}">
                        <div>${errors.quantitySmallerThan0Err}</div>
                    </c:if>
                </label>
            </form>
        </div>
                
        <div class="wrapper2">
            <h2>PRODUCT MANAGER</h2>
            
            <c:set var="errorsUpdate" value="${sessionScope.ERRORUPDATE}"/>
            <c:set var="updated" value="${sessionScope.UPDATED}"/>
            
            <label class="errorsUpdate">
                <c:if test="${not empty errorsUpdate.priceIsNotFloatErr}">
                    <div>${errorsUpdate.priceIsNotFloatErr}</div>
                </c:if>

                <c:if test="${not empty errorsUpdate.priceSmallerThan0Err}">
                    <div>${errorsUpdate.priceSmallerThan0Err}</div>
                </c:if>

                <c:if test="${not empty errorsUpdate.quantityIsNotIntErr}">
                    <div>${errorsUpdate.quantityIsNotIntErr}</div>
                </c:if>

                <c:if test="${not empty errorsUpdate.quantitySmallerThan0Err}">
                    <div>${errorsUpdate.quantitySmallerThan0Err}</div>
                </c:if>
                    
                <c:if test="${empty errorsUpdate.priceIsNotFloatErr}">
                    <c:if test="${empty errorsUpdate.priceSmallerThan0Err}">
                        <c:if test="${empty errorsUpdate.quantityIsNotIntErr}">
                            <c:if test="${empty errorsUpdate.quantitySmallerThan0Err}">
                                <div style="color: green; font-size: 15px; font-weight: bolder;">${updated}</div>
                            </c:if>
                        </c:if>
                    </c:if>
                </c:if>

            </label>
            
            <div class="searchBar">
                <form action="DispatchServlet">
                    <input type="text" name="txtSearch" value="${param.txtSearch}" class="searchBar"/>
                    <input type="hidden" name="txtRole" value="ADMIN" />
                    <input type="submit" value="SEARCH" name="btAction" class="btnSearch" />
                </form>
            </div>
                    
            <c:set var="txtSearchValue" value="${param.txtSearch}"/>
            <c:set var="totalRecord" value="${requestScope.TOTALRECORD}"/>
            
            <div class="tblSearch">
                <c:if test="${empty txtSearchValue}">
                    <c:set var="firstLoad" value="${sessionScope.FIRSTLOAD}"/>

                    <c:if test="${not empty firstLoad}">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>NO</th>
                                        <th>ID</th>
                                        <th>NAME</th>
                                        <th>DESCRIPTION</th>
                                        <th>PRICE</th>
                                        <th>CATEGORY</th>
                                        <th>QUANTITY</th>
                                        <th>IMAGE</th>
                                        <th>STATUS</th>
                                        <th>UPDATE</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach var="dto" items="${firstLoad}" varStatus="counter">
                                        <form action="DispatchServlet">
                                            <tr>
                                               <td>
                                                   ${counter.count}
                                               </td>
                                               <td>
                                                   ${dto.id}
                                                   <input type="hidden" name="txtProductIdUpdate" value="${dto.id}" readonly="readonly" />
                                               </td>
                                               <td>
                                                   <input type="text" name="txtNameUpdate" value="${dto.name}" class="inTable1"/>
                                               </td>
                                               <td>
                                                   <textarea name="txtDescriptionUpdate" rows="10" cols="20" class="textArea1">${dto.description}</textarea>
                                               </td>
                                               <td>
                                                   <input type="text" name="txtPriceUpdate" value="${dto.price}" class="inTable2"/>
                                               </td>
                                               <td>
                                                   <select name="txtCategoryUpdate" style="width: 90px;" id="categorySelect${counter.count}" onchange="categorySelected2(${counter.count})" required>
                                                        <c:forEach items="${categories}" var="category" varStatus="counter1">
                                                            <c:if test="${dto.category eq category.categoryId}">
                                                                <option value="${category.categoryId}" selected>${category.categoryName}</option>
                                                            </c:if>
                                                            <c:if test="${dto.category ne category.categoryId}">
                                                                <option value="${category.categoryId}">${category.categoryName}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                    <input type="hidden" name="txtCategoryIdUpdate" value="${dto.category}" id="categoryID${counter.count}"/>
                                               </td>
                                               <td>
                                                   <input type="text" name="txtQuantityUpdate" value="${dto.quantity}" class="inTable2"/>
                                               </td>
                                               <td>
                                                   <img src="assets/css/img/${dto.image}" width="70" height="70" id="image${counter.count}"/>
                                                   <input type="file" id="img${counter.count}" name="img${counter.count}" accept="image/*" onchange="loadFile2(event, ${counter.count})" hidden>
                                                   <label for="img${counter.count}" class="label2">CHANGE</label> </br> </br>

                                                   <input type="hidden" name="txtChangeImg${counter.count}" value="NOTCHANGE" id="changeImg${counter.count}"/>
                                                   <input type="hidden" name="txtImg${counter.count}" value="${dto.image}" readonly="readonly" />

                                                   <input type="hidden" name="txtNameImg" value="img${counter.count}" readonly="readonly" />
                                                   <input type="hidden" name="txtNameChangImg" value="txtChangeImg${counter.count}" readonly="readonly" />
                                                   <input type="hidden" name="txtNameImgOld" value="txtImg${counter.count}" readonly="readonly" />
                                               </td>
                                               <td>
                                                   <select name="txtStatus" id="statusChoose${counter.count}">
                                                       <option value="Active">Active</option>
                                                       <option value="Inactive">Inactive</option>
                                                   </select>
                                               </td>
                                               <td>
                                                   <input type="submit" value="UPDATE" name="btAction" class="btUpdate" onclick="return confirmRemove(${counter.count})"/>
                                                   <input type="hidden" name="txtSearchUpdate" value="${param.txtSearch}" readonly="readonly" />
                                                   <input type="hidden" name="txtUserID" value="${sessionScope.USERID}" readonly="readonly" />
                                               </td>
                                            </tr> 
                                        </form>
                                    </c:forEach>

                                </tbody>
                            </table>
                            
                            <c:set var="totalRecordFirst" value="${sessionScope.TOTALRECORDFIRST}"/>

                            <div id="wrapper5">
                                <form action="DispatchServlet" class="form4">
                                    <input type="hidden" name="txtRole" value="ADMIN" readonly="readonly" />
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
                    </c:if>
                </c:if>
            
                <c:if test="${not empty txtSearchValue}">
                    <c:set var="resultSearch" value="${requestScope.RESULTSEARCH}"/>
                    <c:if test="${not empty resultSearch}">
                        
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>NO</th>
                                    <th>ID</th>
                                    <th>NAME</th>
                                    <th>DESCRIPTION</th>
                                    <th>PRICE</th>
                                    <th>CATEGORY</th>
                                    <th>QUANTITY</th>
                                    <th>IMAGE</th>
                                    <th>STATUS</th>
                                    <th>UPDATE</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <c:forEach var="dto" items="${resultSearch}" varStatus="counter">
                                    <form action="DispatchServlet">
                                        <tr>
                                           <td>
                                               ${counter.count}
                                           </td>
                                           <td>
                                               ${dto.id}
                                               <input type="hidden" name="txtProductIdUpdate" value="${dto.id}" readonly="readonly" />
                                           </td>
                                           <td>
                                               <input type="text" name="txtNameUpdate" value="${dto.name}" class="inTable1"/>
                                           </td>
                                           <td>
                                               <textarea name="txtDescriptionUpdate" rows="10" cols="20" class="textArea1">${dto.description}</textarea>
                                           </td>
                                           <td>
                                               <input type="text" name="txtPriceUpdate" value="${dto.price}" class="inTable2"/>
                                           </td>
                                           <td>
                                               <select name="txtCategoryUpdate" style="width: 90px;" id="categorySelect${counter.count}" onchange="categorySelected2(${counter.count})" required>
                                                    <c:forEach items="${categories}" var="category" varStatus="counter1">
                                                        <c:if test="${dto.category eq category.categoryId}">
                                                            <option value="${category.categoryId}" selected>${category.categoryName}</option>
                                                        </c:if>
                                                        <c:if test="${dto.category ne category.categoryId}">
                                                            <option value="${category.categoryId}">${category.categoryName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <input type="hidden" name="txtCategoryIdUpdate" value="${dto.category}" id="categoryID${counter.count}"/>
                                           </td>
                                           <td>
                                               <input type="text" name="txtQuantityUpdate" value="${dto.quantity}" class="inTable2"/>
                                           </td>
                                           <td>
                                               <img src="assets/css/img/${dto.image}" width="70" height="70" id="image${counter.count}"/>
                                               <input type="file" id="img${counter.count}" name="img${counter.count}" accept="image/*" onchange="loadFile2(event, ${counter.count})" hidden>
                                               <label for="img${counter.count}" class="label2">CHANGE</label> </br> </br>
                                               
                                               <input type="hidden" name="txtChangeImg${counter.count}" value="NOTCHANGE" id="changeImg${counter.count}"/>
                                               <input type="hidden" name="txtImg${counter.count}" value="${dto.image}" readonly="readonly" />
                                               
                                               <input type="hidden" name="txtNameImg" value="img${counter.count}" readonly="readonly" />
                                               <input type="hidden" name="txtNameChangImg" value="txtChangeImg${counter.count}" readonly="readonly" />
                                               <input type="hidden" name="txtNameImgOld" value="txtImg${counter.count}" readonly="readonly" />
                                           </td>
                                           <td>
                                               <select name="txtStatus" id="statusChoose${counter.count}">
                                                   <option value="Active">Active</option>
                                                   <option value="Inactive">Inactive</option>
                                               </select>
                                           </td>
                                           <td>
                                               <input type="submit" value="UPDATE" name="btAction" class="btUpdate" onclick="return confirmRemove(${counter.count})"/>
                                               <input type="hidden" name="txtSearchUpdate" value="${param.txtSearch}" readonly="readonly" />
                                               <input type="hidden" name="txtUserID" value="${sessionScope.USERID}" readonly="readonly" />
                                           </td>
                                        </tr> 
                                    </form>
                                </c:forEach>
                                
                            </tbody>
                        </table>
                        
                        <div id="wrapper5">
                            <form action="DispatchServlet" class="form4">
                                <input type="text" name="txtSearch" value="${txtSearchValue}" hidden/>
                                <input type="hidden" name="txtRole" value="ADMIN" readonly="readonly" />
                                <c:forEach begin="1" end="${totalRecord}" varStatus="counterPage">
                                    <input type="submit" value="${counterPage.count}" name="btAction" class="btnPage"/>
                                </c:forEach>
                            </form>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
                
    </body>
</html>
