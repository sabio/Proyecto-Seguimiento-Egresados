/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.resultados;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sse.beans.generales.Pregunta;
import com.sse.dao.SQLExecutor;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;




/**
 *
 * @author armando
 */
public class GeneraPDF extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SQLExecutor execute = new SQLExecutor();
            String idAsignacion = request.getParameter("idAsignacion");
                        
            Document document = new Document();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            
            document.open();
            
            Font font1 = new Font(Font.FontFamily.HELVETICA  , 10, Font.BOLD);

            String query = "select cuestionario, grupoalumno, " +
                        "date_format( fechainicio , '%Y/%m/%d %I:%i %p' )," +
                        "date_format( fechafin , '%Y/%m/%d %I:%i %p' ) " +
                        "from tblasignacioncuestionario "+
                        "inner join diccuestionario using (idcuestionario) "+
                        "inner join dicgrupoalumnos using (idgrupoalumno) "+
                        " where idasignacioncuestionario="+idAsignacion;
            
            try{
                ResultSet res = execute.executeQuery(query);
                res.next();
                document.add(new Paragraph("Cuestionario: "+res.getString(1),font1));
                document.add(new Paragraph("Grupo: "+res.getString(2),font1));
                document.add(new Paragraph("Fecha inicio: "+res.getString(3),font1));
                document.add(new Paragraph("Fecha fin: "+res.getString(4),font1));
                
                String imageUrl;
                Image image;                                    

                query = "select distinct idindicador from tblrespuesta "+
                "inner join tblaplicacioncuestionario on (tblrespuesta.idaplicacioncuestionario=tblaplicacioncuestionario.idaplicacioncuestionario) "+
                "inner join dicpregunta on (tblrespuesta.idpregunta=dicpregunta.idpregunta) "+
                "where tblaplicacioncuestionario.idasignacioncuestionario = "+idAsignacion+" and "+
                "idindicador is not null and  "+
                "(tblaplicacioncuestionario.fechafin is not null and tblaplicacioncuestionario.fechafin!='0000-00-00 00:00:00')"; 

                res = execute.executeQuery(query);
                int y=400;
                while(res.next()){
                    imageUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/ImagenIndicadorCreator?idAsignacion="+idAsignacion+"&idIndicador="+res.getInt(1);
                    image = Image.getInstance(new URL(imageUrl));
                    image.scaleAbsolute(350, 200);                    
                    /*image.setAbsolutePosition(100, y);
                    y+=200;*/
                    document.add(image);
                    /*System.out.println("size = "+document.getPageSize().getHeight());
                    if(y>document.getPageSize().getHeight()){
                        document.newPage();
                        y=200;
                    }*/
                }
                                
                query = "select idpregunta,pregunta,respuestaString from tblrespuesta "+
                    "inner join tblaplicacioncuestionario using (idaplicacioncuestionario) "+
                    "inner join tblasignacioncuestionario using (idasignacioncuestionario) "+
                    "inner join dicpregunta using (idpregunta) "+
                    "where respuestaString is not null "+
                    "and idasignacioncuestionario="+idAsignacion+
                    " order by pregunta";
                res = execute.executeQuery(query);
                String preguntaAnterior=null;
                Font font2 = new Font(Font.FontFamily.HELVETICA  , 9, Font.BOLD);
                Font font3 = new Font(Font.FontFamily.HELVETICA  , 8, Font.NORMAL);
                Font font4 = new Font(Font.FontFamily.HELVETICA  , 8, Font.ITALIC);
                boolean aux=true;
                int i=0,j=1;
                while(res.next()){                    
                    if(aux){
                        document.add(new Paragraph("\n\n\n\n\n\nRespuestas de preguntas abiertas",font1));
                        aux=false;
                    }
                    if(preguntaAnterior==null || !preguntaAnterior.equals(res.getString(2))){
                        document.add(new Paragraph(j+") "+res.getString(2),font2));
                        j++;
                    }
                    if(i%2==0)
                        document.add(new Paragraph(res.getString(3),font3));
                    else
                        document.add(new Paragraph(res.getString(3),font4));
                    preguntaAnterior = res.getString(2);
                    
                    i++;
                }     
                
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            document.close();
 
            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-disposition", "attachment; filename=resultados.pdf");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

/*System.out.println("imageUrl="+imageUrl);
            System.out.println("request.getContextPath()="+request.getContextPath());
            System.out.println("request.getServletPath()="+request.getServletPath());
            System.out.println("request.getLocalAddr()="+request.getLocalAddr());
            System.out.println("request.getLocalName()="+request.getLocalName());
            System.out.println("request.getRequestURI()="+request.getRequestURI());
            System.out.println("request.getScheme()="+request.getScheme());
            System.out.println("request.getServerName()="+request.getServerName());
            System.out.println("request.getServerPort()="+request.getServerPort());
            System.out.println("request.getServerPort()="+request.getProtocol());
            */