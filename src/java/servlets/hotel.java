/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.Alquiler;
import clases.AlquilerDB;
import clases.Habitacion;
import clases.HabitacionDB;
import clases.Usuario;
import clases.UsuarioDB;
import clases.tipoHabitacionDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author josev
 */
public class hotel extends HttpServlet {

    private Connection conexion;
    private final String url = "jdbc:mysql://localhost/hotel";
    private final String user = "root";
    private final String password = "";
    private static String ruta;

    @Override
    public void init() {
        ruta = this.getServletContext().getRealPath("/");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            UsuarioDB.conectar(conexion);
            HabitacionDB.conectar(conexion);
            tipoHabitacionDB.conectar(conexion);
            AlquilerDB.conectar(conexion);
            // getServletContext()
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion;
        HttpSession sesion;
        String url = "";
        PrintWriter out = response.getWriter();

        sesion = request.getSession();
        accion = request.getParameter("accion");
        if (accion != null) {
            accion = accion.toUpperCase().replaceAll(" ", "");
            switch (accion) {
                case "ACCEDER":
                    try {
                        boolean ok;
                        int tipo;
                        ok = UsuarioDB.acceder(request);
                        if (ok) {
                            sesion.setAttribute("usuario", request.getParameter("login"));
                            tipo = (int) request.getAttribute("tipo");
                            sesion.setAttribute("tipo", tipo);

                            switch (tipo) {
                                case 1:
                                    url = "/index.jsp";
                                    break;
                                case 2:
                                    url = "/menuRecepcion.jsp";
                                    break;
                                default:
                                    url = "/menuAdmin.jsp";
                                    break;
                            }
                        } else {
                            // Preparamos el mensaje a través de la sesión
                            sesion.setAttribute("error", "Usuario incorrecto");
                            url = "/acceso.jsp";
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                        url = "/error.jsp";
                    }
                    break;
                case "REGISTRO":
                    url = "/registro.jsp";
                    break;
                case "REGISTRAR":
                    try {
                        UsuarioDB.registrar(request);
                        url = "/acceso.jsp";
                    } catch (SQLException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                        url = "/error.jsp";
                    }
                    break;
                case "RESERVAR":
                    try {
                        AlquilerDB.alquilarHabitacion(request);
                        url = "/index.jsp";
                    } catch (SQLException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "MISRESERVAS":
                    url = "/misReservas.jsp";
                    break;
                case "CERRARSESION":
                    url = "/acceso.jsp";
                    request.getSession().invalidate();
                    break;
                case "IMPORTARCOMOXML":
                    try {
                        ArrayList<Alquiler> array = importarXML(sesion);
                        request.setAttribute("array", array);
                        url = "/misReservas.jsp";
                    } catch (SAXException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case "VOLVER":
                    int tipo = (int) sesion.getAttribute("tipo");
                    switch (tipo) {
                        case 1:
                            url = "/index.jsp";
                            break;
                        case 2:
                            url = "/menuRecepcion.jsp";
                            break;
                        default:
                            url = "/menuAdmin.jsp";
                            break;
                    }
                    break;
                case "EDITAR":
                    url = "/editarHabitacion.jsp";
                    break;
                case "AGREGARHABITACION":
                    url = "/agregarHabitacion.jsp";
                    break;
                case "ACEPTAR":

                    try {
                        HabitacionDB.actualizarHabitacion(request);
                    } catch (SQLException ex) {
                        Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    url = "/menuRecepcion.jsp";
                    break;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] listarFicheros(String ruta) {
        File folder = new File(ruta);
        File[] listOfFiles = folder.listFiles();
        String[] array = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                array[i] = listOfFiles[i].getName();
            }
        }
        return array;
    }

    public static void exportarXML(ArrayList<Alquiler> alquileres, HttpServletResponse response, HttpSession sesion) throws SQLException {
        try {
            String fichero = sesion.getAttribute("usuario") + ".xml";
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("alquileres");
            doc.appendChild(rootElement);

            Iterator it = alquileres.iterator();
            while (it.hasNext()) {
                Alquiler alquiler = (Alquiler) it.next();
                Habitacion h = HabitacionDB.consultarHabitacion(alquiler);

                Element objeto = doc.createElement("alquiler");
                rootElement.appendChild(objeto);

                Element idAlquiler = doc.createElement("idAlquiler");
                idAlquiler.appendChild(doc.createTextNode(Integer.toString(alquiler.getIdAlquiler())));
                objeto.appendChild(idAlquiler);

                Element fechaEntrada = doc.createElement("fechaEntrada");
                fechaEntrada.appendChild(doc.createTextNode(alquiler.getFechaEntrada()));
                objeto.appendChild(fechaEntrada);

                Element fechaSalida = doc.createElement("fechaSalida");
                fechaSalida.appendChild(doc.createTextNode(alquiler.getFechaSalida()));
                objeto.appendChild(fechaSalida);

                Element costoTotal = doc.createElement("costoTotal");
                costoTotal.appendChild(doc.createTextNode(Integer.toString(alquiler.getCostoTotal())));
                objeto.appendChild(costoTotal);

                Element habitacion = doc.createElement("habitacion");
                habitacion.appendChild(doc.createTextNode(Integer.toString(h.getNumero())));
                objeto.appendChild(habitacion);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ruta + "/pensiones/" + fichero));

            transformer.transform(source, result);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Alquiler> importarXML(HttpSession sesion) throws IOException, ServletException, SAXException {
        String fichero = sesion.getAttribute("usuario") + ".xml";
        ArrayList<Alquiler> array = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            org.w3c.dom.Document doc = db.parse(ruta + "/pensiones/" + fichero);

            NodeList alquiler = doc.getElementsByTagName("alquiler");
            for (int i = 0; i < alquiler.getLength(); i++) {
                Alquiler a;
                NodeList hijos = alquiler.item(i).getChildNodes();

                int idAlquiler = Integer.valueOf(hijos.item(0).getFirstChild().getNodeValue());
                String fechaEntrada = hijos.item(1).getFirstChild().getNodeValue();
                String fechaSalida = hijos.item(2).getFirstChild().getNodeValue();
                int costoTotal = Integer.valueOf(hijos.item(3).getFirstChild().getNodeValue());
                int habitacion = Integer.valueOf(hijos.item(4).getFirstChild().getNodeValue());
                String usuario = (String) sesion.getAttribute("usuario");

                a = new Alquiler(idAlquiler, fechaEntrada, fechaSalida, costoTotal, usuario, habitacion);
                array.add(a);
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(hotel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;

    }

    private boolean upload(HttpServletRequest request) throws ServletException {

        boolean ok = true;

        HttpSession sesion = request.getSession();
        try {
            Part fichero = request.getPart("fichero");
            fichero.write(fichero.getSubmittedFileName());
            sesion.setAttribute("error", "Nombre:" + fichero.getName() + "Fichero:" + fichero.getSubmittedFileName());
        } catch (IOException ex) {
            ok = false;
            sesion.setAttribute("error", ex.getMessage());
        }
        return ok;
    }

    /*public static void generarPDF(HttpServletResponse response, Usuario usuario) throws DocumentException, FileNotFoundException, IOException{
        Document documento = new Document(PageSize.A4, 50, 50, 100, 72);
        FileOutputStream ficheroPdf = new FileOutputStream(ruta+"usuarios"+File.separator+usuario.getLogin()+File.separator+"curriculum.pdf");

        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
        documento.open();
        documento.add(new Paragraph("Curriculum",
                FontFactory.getFont("arial",   // fuente
                22,                            // tamaño
                Font.ITALIC)));
        documento.add(new Paragraph("Nombre: "+usuario.getNombre()+" "+usuario.getApellidos()));
        documento.add(new Paragraph("Edad: "+usuario.getEdad()));
        documento.add(new Paragraph("Telefono: "+usuario.getTelefono()));
        documento.add(new Paragraph("Direccion: "+usuario.getDireccion()));
        documento.add(new Paragraph("Poblacion: "+usuario.getPoblacion()));
        documento.add(new Paragraph("Provincia: "+usuario.getProvincia()));
        documento.add(new Paragraph(" "));
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell("Descripcion");
        tabla.addCell("Fecha Inicio");
        tabla.addCell("Fecha Fin");
        ArrayList<Experiencia> experiencias = usuario.getExperiencia();
        Iterator iterador;
        iterador = experiencias.iterator();
        while (iterador.hasNext()){
             Experiencia experiencia=(Experiencia) iterador.next();
             tabla.addCell(experiencia.getCaracteristicas());
             tabla.addCell(experiencia.getFecha_inicio());
             tabla.addCell(experiencia.getFecha_final());
        }
        documento.add(tabla);
        documento.close();

    }*/
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
