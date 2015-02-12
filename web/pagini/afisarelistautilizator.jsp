<%@page import="controllers.MainController"%>
<%@page import="drivenbeans.UserPrinter"%>
<%@page import="java.util.List"%>
<jsp:useBean id="myUser" scope="session" class="drivenbeans.User"/>
<jsp:setProperty name="myUser" property="*"/>



<h1 class="page-header">
    <small> Afisare lista programarilor pentru utilizatorul curent</small>
</h1>


<%
    if (request.getParameter("modifica") != null) {
        String start = request.getParameter("data1");
        String end = request.getParameter("data2");
        int id = Integer.parseInt(request.getParameter("idm"));
        MainController.getInstance().updateUserPrinter(id, start, end);

    }

%>


<%    if (request.getParameter("m") != null) {
        int id = Integer.parseInt(request.getParameter("m"));

        UserPrinter p = MainController.getInstance().getUserPrinter(id);

%>


<div class="row">
    <div class="col-lg-6">

        <h1 class="page-header">
            Formular programare
        </h1>
        <form role="form" action="index.jsp?p=check" method="post">
            <input type="hidden" name="idm" value="<%=request.getParameter("m")%>">
            <div class="form-group">
                <label>Zi si ora inceput</label>
                <input class="form-control" name="data1" value="<%= p.getText1()%>">                               
            </div>
            <div class="form-group">
                <label>Zi si ora sfarsit</label>
                <input class="form-control" name="data2" value="<%= p.getText2()%>">                               
            </div>
            <button type="submit" class="btn btn-default" name="modifica">Update</button>
        </form>



    </div>
</div>


<% } %>



<div class="row">
    <div class="col-lg-6">
        <h3>Tabel programari</h3><br />
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>

                        <th>ID imprimanta</th>
                        <th>Data incept</th>
                        <th>Data sfarsit</th>

                    </tr>
                </thead>


                <tbody>
                    <%
                        List<UserPrinter> imprimante = null;
                        imprimante = MainController.getInstance().getPrintersByUser(myUser.getId());

                        for (UserPrinter p : imprimante) {   %>  
                    <tr>

                        <td><% out.println(p.getPrinter()); %></td>
                        <td><% out.println(p.getText1()); %></td>
                        <td><% out.println(p.getText2()); %></td>
                        <td><a href="index.jsp?p=check&s=<% out.println(p.getId()); %>">--Sterge--</a></td>
                        <td><a href="index.jsp?p=check&m=<% out.println(p.getId()); %>">--Modifica--</a></td>

                    </tr>
                    <% }%>

                </tbody>
            </table>
        </div>
    </div>
</div>
