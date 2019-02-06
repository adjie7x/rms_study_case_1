<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">

  <title>RMS</title>
  <meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Content-Style-Type" content="text/css">
  
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <link rel="stylesheet" href="css/styles.css?v=1.0" type="text/css"> 
  <!-- <c:url value="css/styles.css?v=1.0" var="styleUrl"/>
  <link href="${styleUrl}" rel="stylesheet" type="text/css"> -->

  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
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
      </main>
    </div>
  <!-- <script src="js/scripts.js"></script> -->
</body>
</html>
