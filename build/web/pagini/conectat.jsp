<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    if (request.getParameter("idimp") != null) {


%>

<c:import url="pagini/dateimprimanta.jsp" />

<% } else if (request.getParameter("p") != null) {

%>
<c:import url="pagini/afisarelistautilizator.jsp" />

<%} else if (request.getParameter("p") == null) {%>

<c:import url="pagini/afisareimprimante.jsp" />

<%}%>



