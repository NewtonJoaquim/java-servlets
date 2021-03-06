<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List,br.com.alura.gerenciador.modelo.Empresa" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url value="/entrada?acao=RemovaEmpresa" var="linkServletRemoveEmpresa"/>
<c:url value="/entrada?acao=MostraEmpresa" var="linkServletEditEmpresa"/>
<html>
<body>

	<c:import url="logout-parcial.jsp"/>
	Usuario Logado: ${usuarioLogado.login }

	<br/>
	<ul>
		<c:forEach items="${empresas}" var="empresa">
		
			<li>
			${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"/> 
			<a href="${linkServletEditEmpresa }&id=${empresa.id}">Edit</a>
			<a href="${linkServletRemoveEmpresa }&id=${empresa.id}">Remove</a>
			</li>
		</c:forEach>
	</ul>

</body>
</html>