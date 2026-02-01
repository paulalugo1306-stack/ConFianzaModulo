package com.confianza;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Configuración de la base de datos
    private String url = "jdbc:mysql://localhost:3306/confianza_nueva"; 
    private String usuario = "root";
    private String contrasena = "31/07/2021Pa";

    // MÉTODO GET: Se usa para CONSULTAR y mostrar la lista
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<String[]> listaUsuarios = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);
            
            String sql = "SELECT nombre, email FROM usuarios";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String[] fila = { rs.getString("nombre"), rs.getString("email") };
                listaUsuarios.add(fila);
            }
            
            con.close();
            
            // Enviamos la lista al JSP de visualización
            request.setAttribute("listaUsuarios", listaUsuarios);
            request.getRequestDispatcher("lista.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MÉTODO POST: Se usa para REGISTRAR nuevos usuarios desde el formulario
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, usuario, contrasena);

            String sql = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, email);

            pstmt.executeUpdate();
            con.close();

            // Pasamos los datos al JSP de éxito
            request.setAttribute("nombre", nombre);
            request.setAttribute("email", email);
            request.getRequestDispatcher("resultados.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}