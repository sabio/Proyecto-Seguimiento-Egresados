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
        case 5:
            execute = new SQLExecutor();
            String idAsignacionCuestionario5 = request.getParameter("idAsignacionCuestionario");
            String idCuestionario5 = request.getParameter("idCuestionario");
            String idGrupoAlumno5 = request.getParameter("idGrupoAlumno");
            String txtFechaInicio5 = request.getParameter("txtFechaInicio");
            String txtFechaFin5 = request.getParameter("txtFechaFin");
            
            query = "select case when "+
                    "STR_TO_DATE( '"+txtFechaInicio5+"', '%Y/%m/%d %I:%i %p' ) = "+
                    "STR_TO_DATE( '"+txtFechaFin5+"', '%Y/%m/%d %I:%i %p' ) "+
                    "then 'iguales' "+
                    "when "+
                    "TIME_TO_SEC(TIMEDIFF(STR_TO_DATE( '"+txtFechaFin5+"', '%Y/%m/%d %I:%i %p' ), "+
                    "STR_TO_DATE( '"+txtFechaInicio5+"', '%Y/%m/%d %I:%i %p' ))) < 0 "+
                    "then 'fechainicialsuperiorafinal' "+
                    "when (select count(1) from tblasignacioncuestionario "+
                    "where idAsignacionCuestionario != "+idAsignacionCuestionario5+" and idcuestionario="+idCuestionario5+" and idgrupoalumno="+idGrupoAlumno5+" and "+
                    "(( "+
                    "STR_TO_DATE( '"+txtFechaInicio5+"', '%Y/%m/%d %I:%i %p' ) >= fechainicio and "+
                    "STR_TO_DATE( '"+txtFechaInicio5+"', '%Y/%m/%d %I:%i %p' ) <= fechafin "+
                    ") "+
                    "or "+
                    "( "+
                    "STR_TO_DATE( '"+txtFechaFin5+"', '%Y/%m/%d %I:%i %p' ) >= fechainicio and "+
                    "STR_TO_DATE( '"+txtFechaFin5+"', '%Y/%m/%d %I:%i %p' ) <= fechafin "+
                    ") "+
                    "or "+
                    "( "+
                    "STR_TO_DATE( '"+txtFechaInicio5+"', '%Y/%m/%d %I:%i %p' ) <= fechainicio and "+
                    "STR_TO_DATE( '"+txtFechaFin5+"', '%Y/%m/%d %I:%i %p' ) >= fechafin "+
                    ") "+
                    ") "+
                    ")>0 "+
                    "then "+
                    "'fechatraslapada' "+
                    "else 'todobien' "+
                    "end from dual";
            //System.out.println("queryVal = "+query);
            res = execute.executeQuery(query);
            res.next();
            salida = res.getString(1);
            
            break;
    }
    out.print(salida);
    
    
    
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


select case when
STR_TO_DATE( '2011/11/09 03:20 PM', '%Y/%m/%d %I:%i %p' ) =
STR_TO_DATE( '2011/11/09 03:19 PM', '%Y/%m/%d %I:%i %p' )
then 'iguales'
when
TIME_TO_SEC(TIMEDIFF(STR_TO_DATE( '2011/11/09 03:21 PM', '%Y/%m/%d %I:%i %p' ),
STR_TO_DATE( '2011/11/09 03:20 PM', '%Y/%m/%d %I:%i %p' ))) < 0
then 'fechainicialsuperiorafinal'

when (select count(1) from tblasignacioncuestionario
      where idcuestionario=1 and idgrupoalumno=1 and
      ((
        STR_TO_DATE( '2011/11/11 03:00 PM', '%Y/%m/%d %I:%i %p' ) >= fechainicio and
        STR_TO_DATE( '2011/11/11 03:00 PM', '%Y/%m/%d %I:%i %p' ) <= fechafin
      )
      or
      (
        STR_TO_DATE( '2011/11/11 03:21 PM', '%Y/%m/%d %I:%i %p' ) >= fechainicio and
        STR_TO_DATE( '2011/11/11 03:21 PM', '%Y/%m/%d %I:%i %p' ) <= fechafin
      )
      or
      (
        STR_TO_DATE( '2011/11/11 03:00 PM', '%Y/%m/%d %I:%i %p' ) <= fechainicio and
        STR_TO_DATE( '2011/11/11 03:21 PM', '%Y/%m/%d %I:%i %p' ) >= fechafin
      )
      )
)>0
then
'fechatraslapada'
else 'todobien'
end from dual;

  
 
            */
%>