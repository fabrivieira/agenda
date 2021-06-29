<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
List<JavaBeans> contatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html">Novo contato</a>
	<table border ="1">
		<thead>
			<th>Id</th>
			<th>Nome</th>
			<th>Email</th>
			<th>Fone</th>
		</thead>
		<tbody>
				<%
				for (JavaBeans c : contatos) {
				%>
				<tr>
				<td><%=c.getId()%></td>
				<td><%=c.getNome()%></td>
				<td><%=c.getEmail()%></td>
				<td><%=c.getFone()%></td>
				</tr>
				<%
				}
				%>
		</tbody>


	</table>


</body>


</html>