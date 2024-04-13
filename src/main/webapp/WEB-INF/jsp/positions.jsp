<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://traderdiary.grigorievfinance.com/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><spring:message code="position.title"/></h3>

    <form method="get" action="meals/filter">
        <dl>
            <dt><spring:message code="position.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="position.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="position.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="position.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="position.filter"/></button>
    </form>
    <hr>
    <a href="meals/create"><spring:message code="position.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="position.dateTime"/></th>
            <th><spring:message code="position.description"/></th>
            <th><spring:message code="position.profit"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.positions}" var="position">
            <jsp:useBean id="position" type="com.grigorievfinance.traderdiary.to.PositionTo"/>
            <tr data-meal-excess="${position.excess}">
                <td>
                        <%--${position.dateTime.toLocalDate()} ${position.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(position.getDateTime())%>--%>
                        <%--${fn:replace(position.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(position.dateTime)}
                </td>
                <td>${position.description}</td>
                <td>${position.calories}</td>
                <td><a href="positions/update?id=${position.id}"><spring:message code="common.update"/></a></td>
                <td><a href="positions/delete?id=${position.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>