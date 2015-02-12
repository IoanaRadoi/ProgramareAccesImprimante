
<%@page import="drivenbeans.UserPrinter"%>
<%@page import="java.util.List"%>
<%@page import="controllers.MainController"%>
<jsp:useBean id="myUser" scope="session" class="drivenbeans.User"/>
<jsp:setProperty name="myUser" property="*"/>


<div class="row">
    <div class="col-lg-6">
        <h3>Programarile pentru imprimanta curenta</h3><br />
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID user</th>                        
                        <th>Data incept</th>
                        <th>Data sfarsit</th>

                    </tr>
                </thead>


                <tbody>
                    <%
                        List<UserPrinter> imprimante = null;
                        int i = Integer.parseInt(request.getParameter("idimp"));
                        imprimante = MainController.getInstance().getUsersByPrinter(i);

                        for (UserPrinter p : imprimante) {   %>  
                    <tr>
                        <td><% out.println(p.getUser()); %></td>                        
                        <td><% out.println(p.getText1()); %></td>
                        <td><% out.println(p.getText2()); %></td>

                    </tr>
                    <% } %>

                </tbody>
            </table>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-lg-6">

        <h3>
            Formular programare
        </h3> <br />
        <form role="form" action="index.jsp?idimp=<% out.println(request.getParameter("idimp"));%>" method="post">
            <div class="form-group">
                <label>Zi si ora inceput</label>
                <input class="form-control" name="data1">                               
            </div>
            <div class="form-group">
                <label>Zi si ora sfarsit</label>
                <input class="form-control" name="data2">                               
            </div>
            <button type="submit" class="btn btn-default" name="programeaza">Programare</button>
        </form>



    </div>
</div>




