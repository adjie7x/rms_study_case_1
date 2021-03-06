<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servlet, JSP and JDBC CRUD Operations</title>
<meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Content-Style-Type" content="text/css">
  
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <link rel="stylesheet" href="css/styles.css?v=1.0" type="text/css"> 
 
<style type="text/css">
	body{
        text-align: center;
    }
    table {
        margin-left: 15%;
        min-width: 70%;
        border: 1px solid #CCC;
        border-collapse: collapse;
    }
    table tr{line-height: 30px;}
    table tr th { background: #000033; color: #FFF;}
    table tr td { border:1px solid #CCC; margin: 5px;}
    input[type=text], input[type=email], input[type=tel]{
        min-width: 60%;
    }
    input[type=submit], a{
        background: green;
        padding: 5px;
        margin: 5px;
        color: #FFF;
    }
    a{
        text-decoration: none;
    }
</style>
</head>
<body>
    <div class="demo-layout-transparent mdl-layout mdl-js-layout">
      <header class="mdl-layout__header mdl-layout__header--transparent">
        <div class="mdl-layout__header-row">
          <!-- Title -->
          <span class="mdl-layout-title">RMS</span>
          <!-- Add spacer, to align navigation to the right -->
          <div class="mdl-layout-spacer"></div>
          <!-- Navigation -->
          <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/">Home</a>
          	<a class="mdl-navigation__link" href="${pageContext.request.contextPath}/users/list">Users</a>
          	<a class="mdl-navigation__link" href="${pageContext.request.contextPath}/customers/list">Customers</a>
          	<a class="mdl-navigation__link" href="${pageContext.request.contextPath}/logout">Logout</a>
          </nav>
        </div>
      </header>
      <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">RMS</span>
        <nav class="mdl-navigation">
          <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/">Home</a>
          <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/users/list">Users</a>
          <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/customers/list">Customers</a>
          <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/logout">Logout</a>
        </nav>
      </div>
      <main class="mdl-layout__content">
		<h1>Servlet, JSP and JDBC CRUD Operations</h1>
		<c:url value="/customers/register" var="registerUrl" />
		<form action="${registerUrl}" method="post">
			<table>
				<c:if test="${customer.id ne null}">
					<tr>
						<td>Customer ID:</td>
						<td><input type="text" name="id" value="${customer.id}"
							readonly="readonly"></td>
					</tr>
				</c:if>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName"
						value="${customer.firstName}" required></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastName"
						value="${customer.lastName}" required></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="email" name="email" value="${customer.email}"
						required></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<%-- <td><input type="tel" pattern="[789][0-9]{9}" name="mobile" value="${customer.mobile}" required></td> --%>
					<td><input type="text" name="mobile"
						value="${customer.mobile}" required></td>
				</tr>

				<c:if test="${customer.id ne null}">
					<tr>
						<td colspan="2"><input type="submit" value="Update"></td>
					</tr>
				</c:if>
				<c:if test="${customer.id eq null}">
					<tr>
						<td colspan="2"><input type="submit" value="Save"></td>
					</tr>
				</c:if>
			</table>
		</form>
		<br>
		<h1>List of Customers</h1>
		<table>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${customers}" var="customer">
				<tr>
					<td>${customer.id}</td>
					<td>${customer.firstName}</td>
					<td>${customer.lastName}</td>
					<td>${customer.email}</td>
					<td>${customer.mobile}</td>

					<td>
						<form action="<c:url value="/customers/update"/>" method="post">
							<input type="hidden" name="custId" value="${customer.id}">
							<input type="submit" value="Update">
						</form>
					<td>
						<form action="<c:url value="/customers/delete"/>" method="post">
							<input type="hidden" name="custId" value="${customer.id}">
							<input style="background: #F00;" type="submit" value="Delete">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

		</main>
    </div>
</body>
</html>