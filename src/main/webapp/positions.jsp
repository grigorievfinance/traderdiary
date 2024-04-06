<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://traderdiary.grigorievfinance.com/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.positions}" var="position">
            <jsp:useBean id="position" type="com.grigorievfinance.traderdiary.to.PositionTo"/>
            <tr class="${position.profitable ? 'excess' : 'normal'}">
                <td>
                        <%--${position.dateTime.toLocalDate()} ${position.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(position.getDateTime())%>--%>
                        <%--${fn:replace(position.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(position.dateTime)}
                </td>
                <td>${position.symbol}</td>
                <td>${position.profitLoss}</td>
                <td><a href="positions?action=update&id=${position.id}">Update</a></td>
                <td><a href="positions?action=delete&id=${position.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>