/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sse.page;

import com.sse.beans.generales.Usuario;
import com.sse.dao.SQLExecutor;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author armando
 */
@WebServlet(name = "XMLIndexGenerator", urlPatterns = {"/inicio"})
public class XMLIndexGenerator extends HttpServlet {
    TransformerHandler handler;
    SQLExecutor execute;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
        out.print(getXMLMenu(request));
        }catch(Exception e){
            e.printStackTrace();
        }
        out.close();
    }
    
    
    private String getXMLMenu(HttpServletRequest request) throws Exception{
        String path = request.getContextPath();
        
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        handler = tf.newTransformerHandler();
        Transformer serializer = handler.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.VERSION, "1.0");
        Writer writer = new StringWriter();
        BufferedWriter bw = new BufferedWriter(writer);
        StreamResult result = new StreamResult(bw);
        handler.setResult(result);
                        
        handler.startDocument();   
        handler.processingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"" + path + "/xsl/menu.xsl\"");
        
        handler.startElement("", "MenuXML", "MenuXML", new AttributesImpl());           
        
        handler.startElement("", "path", "path", new AttributesImpl());
        handler.characters(path.toCharArray(), 0, path.length());
        handler.endElement("", "path", "path");
        
       
        execute = new SQLExecutor();
        String query =  "select m.idmenu,m.menu,m.nivel,m.orden,m.url from dicmenu m "+
                        "inner join tblmenupermiso mp on (m.idmenu=mp.idmenu) "+
                        "where mp.idpermiso in ("+((Usuario)request.getSession(false).getAttribute("usuario")).getPermisosAsignadosString(",") +") "+
                        "order by m.orden,m.nivel";
        
        ResultSet res = execute.executeQuery(query);
        int nivel,nivelAnterior=0;
        String menu, url;
        while(res.next()){
            nivel = res.getInt("nivel");
            menu = res.getString("menu");
            url = res.getString("url");
            
            if(nivel>nivelAnterior){
                handler.startElement("", "Nivel"+nivel, "Nivel"+nivel, new AttributesImpl());
                handler.startElement("", "menu", "menu", new AttributesImpl());
                handler.characters(menu.toCharArray(), 0, menu.length());
                handler.endElement("", "menu", "menu");
                nivelAnterior = nivel;
                
            }else if(nivel==nivelAnterior){
                handler.endElement("", "Nivel"+nivel, "Nivel"+nivel);
                
                handler.startElement("", "Nivel"+nivel, "Nivel"+nivel, new AttributesImpl());
                handler.startElement("", "menu", "menu", new AttributesImpl());
                handler.characters(menu.toCharArray(), 0, menu.length());
                handler.endElement("", "menu", "menu");
                
            }else if(nivel<nivelAnterior){                
                for(int i=nivelAnterior; i>=nivel; i--){
                    handler.endElement("", "Nivel"+i, "Nivel"+i);
                }                
                handler.startElement("", "Nivel"+nivel, "Nivel"+nivel, new AttributesImpl());
                handler.startElement("", "menu", "menu", new AttributesImpl());
                handler.characters(menu.toCharArray(), 0, menu.length());
                handler.endElement("", "menu", "menu");
                nivelAnterior = nivel;
            }
        }
        
        handler.endElement("", "Nivel1", "Nivel1");
        
        /*
        handler.startElement("", "Nivel1", "Nivel1", new AttributesImpl());
        handler.startElement("", "Nombre", "Nombre", new AttributesImpl());
        handler.characters("Inicio".toCharArray(), 0, "Inicio".length());
        handler.endElement("", "Nombre", "Nombre");
        handler.endElement("", "Nivel1", "Nivel1");
        
        handler.startElement("", "Nivel1", "Nivel1", new AttributesImpl());
        handler.startElement("", "Nombre", "Nombre", new AttributesImpl());
        handler.characters("Nosotros".toCharArray(), 0, "Nosotros".length());
        handler.endElement("", "Nombre", "Nombre");
        handler.endElement("", "Nivel1", "Nivel1");
        
        
        
        
        handler.startElement("", "Nivel1", "Nivel1", new AttributesImpl());
        handler.startElement("", "Nombre", "Nombre", new AttributesImpl());
        handler.characters("Productos".toCharArray(), 0, "Productos".length());
        handler.endElement("", "Nombre", "Nombre");
        
        handler.startElement("", "Nivel2", "Nivel2", new AttributesImpl());
        handler.startElement("", "Nombre", "Nombre", new AttributesImpl());
        handler.characters("Cremas".toCharArray(), 0, "Cremas".length());
        handler.endElement("", "Nombre", "Nombre");
        handler.endElement("", "Nivel2", "Nivel2");
        //--
        handler.startElement("", "Nivel2", "Nivel2", new AttributesImpl());
        handler.startElement("", "Nombre", "Nombre", new AttributesImpl());
        handler.characters("Shampoo".toCharArray(), 0, "Shampoo".length());
        handler.endElement("", "Nombre", "Nombre");
        handler.endElement("", "Nivel2", "Nivel2");
        
        
        handler.endElement("", "Nivel1", "Nivel1");
        */
       
        /*
        addElement("Padre", "Michael J.", "49");
        addElement("Madre", "Margaret A.", "46");
        
        handler.startElement("", "Ninos", "Ninos", new AttributesImpl());
        addElement("Nino", "Chloe C.", "12");
        addElement("Nino", "Adrian K.", "10");
        handler.endElement("", "Ninos", "Ninos");
        */
    
        handler.endElement("", "MenuXML", "MenuXML");
        handler.endDocument();
        execute.cerrarConexion();
        return writer.toString();
    }
    
    private void addElement(String element, String name, String age) throws SAXException {
        AttributesImpl ageAttrs = new AttributesImpl();
        ageAttrs.addAttribute("", "Edad", "Edad", "CDATA", age);
        handler.startElement("", element, element, ageAttrs);
        handler.characters(name.toCharArray(), 0, name.length());
        handler.endElement("", element, element);
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
