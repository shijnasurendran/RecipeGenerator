<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Welcome, ${name} <%@page import="java.util.List"%>
<%@page import="com.recipe.generator.model.Ingredient"%>
<br/>

<c:forEach items="${ingredients}" var="ingredient">
	${ingredient.ingredientName }
</c:forEach>
