<%@page import="java.sql.ResultSet"%>
<%@page import="com.sse.dao.SQLExecutor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int consulta = new Integer(request.getParameter("consulta"));
    SQLExecutor execute;
    String salida = "";
    
    switch(consulta){
        case 1:
            execute = new SQLExecutor();
            ResultSet res = execute.executeQuery("select count(1) from dicpregunta where idindicador = "+request.getParameter("idIndicador"));
            res.next();
            salida = res.getInt(1)+"";            
            break;
        
    }
    out.print(salida);
%>