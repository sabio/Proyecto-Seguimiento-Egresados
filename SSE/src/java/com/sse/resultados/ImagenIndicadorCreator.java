/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.resultados;

import com.sse.dao.SQLExecutor;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author armando
 */
public class ImagenIndicadorCreator extends HttpServlet {
    Integer idAsignacion,idIndicador;
     SQLExecutor exe= new SQLExecutor();
    
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
         {
             this.idIndicador = new Integer(request.getParameter("idIndicador"));
             this.idAsignacion = new Integer(request.getParameter("idAsignacion"));
             
             DefaultPieDataset dataset = getDataset(this.idAsignacion,this.idIndicador );
             JFreeChart jfc;
             String indicador="";
             try{
                ResultSet res = exe.executeQuery("select  indicador from dicindicador where idindicador = "+this.idIndicador);
                res.next();
                indicador = res.getString(1);
             }catch(Exception e){
                 
             }
             jfc = ChartFactory.createPieChart(indicador, dataset, true, true, false);
            
             PiePlot pp = (PiePlot) jfc.getPlot();
             pp.setSectionOutlinesVisible(false);
             pp.setLabelFont(new Font("SansSerif", Font.PLAIN, 9));
             pp.setNoDataMessage("No hay informacion disponible");
             pp.setCircular(false);
             pp.setLabelGap(0.02);
             pp.setShadowYOffset(1);


             // Assume that we have the chart
             File image = File.createTempFile("image", "tmp");
             ChartUtilities.saveChartAsPNG(image, jfc, 500, 300);

             FileInputStream fileInStream = new FileInputStream(image);
             OutputStream outStream = response.getOutputStream();   

             long fileLength;
             byte[] byteStream;

             fileLength = image.length();
             byteStream = new byte[(int)fileLength];
             fileInStream.read(byteStream, 0, (int)fileLength);

             response.setContentType("image/png");
             response.setContentLength((int)fileLength);
             response.setHeader("Cache-Control", 
                 "no-store,no-cache, must-revalidate, post-check=0, pre-check=0");
             response.setHeader("Pragma", "no-cache");

             fileInStream.close();
             outStream.write(byteStream);
             outStream.flush();
             outStream.close();

         }
         catch (IOException e)
         {
            System.err.println("Problem occurred creating chart.");
         }


    }
    
    
    private DefaultPieDataset getDataset(Integer idAsignacion, Integer idIndicador){
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        try{
           
            String query =  "select idtipopregunta, respuestaInt from tblrespuesta "+
            "inner join tblaplicacioncuestionario on (tblrespuesta.idaplicacioncuestionario=tblaplicacioncuestionario.idaplicacioncuestionario) "+
            "inner join dicpregunta on (tblrespuesta.idpregunta=dicpregunta.idpregunta) "+
            "where tblaplicacioncuestionario.idasignacioncuestionario = "+idAsignacion+" "+
            "and dicpregunta.idindicador="+idIndicador+" and "+
            "(tblaplicacioncuestionario.fechafin is not null and tblaplicacioncuestionario.fechafin!='0000-00-00 00:00:00') ";   

            ResultSet res = exe.executeQuery(query);
            
            int cantPreguntas = 0;
            float satisfaccion=0f,insatisfaccion=0f;
            while(res.next()){
                if(res.getInt(1)==2){//opciones del 1 al 5
                    satisfaccion += (res.getInt(2)*20);
                    
                    cantPreguntas++;
                }
                else if(res.getInt(1)==3){//opciones si y no
                    //satisfaccion += (res.getInt(2)*100);
                    satisfaccion += res.getInt(2)==1 ? 100 : 0;
                    
                    cantPreguntas++;
                }
            }
            
            satisfaccion = satisfaccion/cantPreguntas;            
            insatisfaccion = 100f-satisfaccion;
            /*
            System.out.println("satisfaccion = "+satisfaccion);
            System.out.println("insatisfaccion = "+insatisfaccion);
              */
            
            dataset.setValue("Porcentaje Satisfaccion "+satisfaccion+"%",satisfaccion);
            dataset.setValue("Porcentaje Insatisfaccion "+insatisfaccion+"%",insatisfaccion);

        }
        catch( Exception e){
            e.printStackTrace();
            dataset=null;
        }
        return dataset;
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
