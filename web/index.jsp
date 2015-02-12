<%-- 
    Document   : index
    Created on : Jan 12, 2015, 8:57:21 PM
    Author     : Oana
--%>

<%@page import="drivenbeans.UserPrinter"%>
<%@page import="java.util.List"%>
<%@page import="controllers.MainController"%>
<%@page import="drivenbeans.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="myUser" scope="session" class="drivenbeans.User"/>
<jsp:setProperty name="myUser" property="*"/>



<%
    if (request.getParameter("conectare") != null) {
        String user = request.getParameter("user");
        String parola = request.getParameter("parola");
        User us = MainController.getInstance().login(user, parola);

        if (us != null) {
            myUser.setConectat(true);
            myUser.setId(us.getId());
            response.sendRedirect("index.jsp"); //reincarcarea paginii

        }

    }

    if (request.getParameter("programeaza") != null) {
        String data1 = request.getParameter("data1");
        String data2 = request.getParameter("data2");
        int i = Integer.parseInt(request.getParameter("idimp"));
        MainController.getInstance().createUserPrinter(myUser.getId(), i, data1, data2);

    }

    if (request.getParameter("dec") != null) {
        myUser.setConectat(false);
        myUser.setUser(null);
        myUser.setParola(null);
        myUser.setId(0);
    }

    if (request.getParameter("s") != null) {
        MainController.getInstance().stergeUserPrinter(Integer.parseInt(request.getParameter("s")));
        response.sendRedirect("index.jsp?p=check");
    }

    

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="css/sb-admin.css" type="text/css"/>
        <link rel="stylesheet" href="javascript/bootstrap.min.js" type="text/css"/>        
    </head>
    <body>
        <div id="wrapper">

            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">Acces imprimante publice</a>
                </div>
                <!-- /.navbar-header -->
                <!-- /.navbar-top-links -->

                <div class="navbar-default navbar-static-side" role="navigation">
                    <div class="sidebar-collapse">
                        <%if (!myUser.isConectat()) {%>
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="index.jsp?p=inregistrare"><i class="fa fa-hospital-o fa-fw"></i>Inregistrare</a>
                            </li>
                            <li>
                                <a href="index.jsp"><i class="fa fa-hospital-o fa-fw"></i>Conectare</a>
                            </li>
                        </ul>
                        <%} else {%>
                        <ul class="nav" id="side-menu">
                            <li>
                                <a href="index.jsp"><i class="fa fa-hospital-o fa-fw"></i>Programeaza</a>
                            </li>
                            <li>
                                <a href="index.jsp?p=check"><i class="fa fa-hospital-o fa-fw"></i>Verifica programari</a>
                            </li>
                            <li>
                                <a href="index.jsp?dec=1"><i class="fa fa-hospital-o fa-fw"></i>Deconectare</a>
                            </li>
                        </ul>                      
                        <% } %>
                    </div>
                </div>
            </nav>

            <div id="page-wrapper">       
                <%if (myUser.isConectat()) {%>

                <c:import url="pagini/conectat.jsp" />
                <%} else {%>
                <c:import url="pagini/neconectat.jsp" />
                <%}%>


            </div>

        </div>
    </body>
</html>
