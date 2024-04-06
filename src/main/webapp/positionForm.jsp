<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create position' : 'Edit position'}</h2>
    <jsp:useBean id="position" type="com.grigorievfinance.traderdiary.model.Position" scope="request"/>
    <form method="post" action="positions">
        <input type="hidden" name="id" value="${position.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${position.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${position.symbol}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${position.profitLoss}" name="calories" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
