<%@page import="java.sql.ResultSet"%>
<%@page import="com.sse.dao.SQLExecutor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int consulta = new Integer(request.getParameter("consulta"));
    SQLExecutor execute;
    ResultSet res;
    String salida = "";
    String query=null;
    
    switch(consulta){
        case 1:
            execute = new SQLExecutor();
            res = execute.executeQuery("select count(1) from dicpregunta where idindicador = "+request.getParameter("idIndicador"));
            res.next();
            salida = res.getInt(1)+"";            
            break;
        case 2:
            execute = new SQLExecutor();
            if(request.getParameter("idUsuario")!=null && !request.getParameter("idUsuario").equals(""))
                query = "select count(1) from dicusuario where UPPER(usuario) like UPPER('"+request.getParameter("login")+"') and idusuario != "+request.getParameter("idUsuario");
            else
                query = "select count(1) from dicusuario where UPPER(usuario) like UPPER('"+request.getParameter("login")+"')";                            
            res = execute.executeQuery(query);
            res.next();
            salida = res.getInt(1)+"";                        
            break;
        case 3:
            execute = new SQLExecutor();
            if(request.getParameter("idUsuario")!=null && !request.getParameter("idUsuario").equals(""))
                query = "select count(1) from dicusuario where UPPER(email) like UPPER('"+request.getParameter("email")+"') and idusuario != "+request.getParameter("idUsuario");                
            else    
                query = "select count(1) from dicusuario where UPPER(email) like UPPER('"+request.getParameter("email")+"')";                            
            res = execute.executeQuery(query);
            res.next();
            salida = res.getInt(1)+"";                        
            break;
        case 4:
            execute = new SQLExecutor();
            res = execute.executeQuery("select count(1) from dicgrupoalumnos where upper(grupoalumno) like upper('"+request.getParameter("nombre")+"') ");
            res.next();
            salida = res.getInt(1)+"";            
            break;
            //SELECT date_format( curdate( ) , '%d/%m/%Y' ) FROM dual
            //http://www.w3schools.com/sql/func_date_format.asp
            //http://dev.mysql.com/doc/refman/5.0/es/date-calculations.html
            
         /*   
            select
case 
when
 PERIOD_DIFF (STR_TO_DATE( '2011/11/09 03:21 PM', '%Y/%m/%d %I:%i %p' ),
STR_TO_DATE( '2011/11/09 03:20 PM', '%Y/%m/%d %I:%i %p' )) < 0
then "S"
else "N"
end
from dual
            */
            
    }
    out.print(salida);
%>