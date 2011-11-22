<%@page import="java.sql.ResultSet"%>
<%@page import="com.sse.dao.SQLExecutor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int consulta = new Integer(request.getParameter("consulta"));
    SQLExecutor execute;
    ResultSet res;
    String salida = "";
    
    switch(consulta){
        case 1:
            execute = new SQLExecutor();
            res = execute.executeQuery("select count(1) from dicpregunta where idindicador = "+request.getParameter("idIndicador"));
            res.next();
            salida = res.getInt(1)+"";            
            break;
        case 2:
            execute = new SQLExecutor();
            res = execute.executeQuery("select count(1) from dicusuario where UPPER(usuario) like UPPER('"+request.getParameter("login")+"')");
            res.next();
            salida = res.getInt(1)+"";                        
            break;
        case 3:
            execute = new SQLExecutor();
            res = execute.executeQuery("select count(1) from dicusuario where UPPER(email) like UPPER('"+request.getParameter("email")+"')");
            res.next();
            salida = res.getInt(1)+"";                        
            break;
    }
    out.print(salida);
%>