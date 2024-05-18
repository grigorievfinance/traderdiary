<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="position" type="com.grigorievfinance.traderdiary.model.Position" scope="request"/>
    <h3><spring:message code="${position.isNew() ? 'position.add' : 'position.edit'}"/></h3>
    <form method="post" action="positions">
        <input type="hidden" name="id" value="${position.id}">
        <dl>
            <dt><spring:message code="position.dateTime"/></dt>
            <dd><input type="datetime-local" value="${position.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="position.description"/></dt>
            <dd><input type="text" value="${position.symbol}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="position.profitLoss"/></dt>
            <dd><input type="number" value="${position.profitLoss}" name="profit" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/> </button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>