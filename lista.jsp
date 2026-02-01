<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado de Usuarios</title>
</head>
<body>
    <h2>Usuarios Registrados en el Sistema</h2>
    <table border="1" cellpadding="10">
        <thead>
            <tr style="background-color: #eee;">
                <th>Nombre Completo</th>
                <th>Correo Electrónico</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<String[]> lista = (List<String[]>) request.getAttribute("listaUsuarios");
                if (lista != null && !lista.isEmpty()) {
                    for (String[] usuario : lista) {
            %>
                <tr>
                    <td><%= usuario[0] %></td>
                    <td><%= usuario[1] %></td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr><td colspan="2">No hay usuarios registrados aún.</td></tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <a href="formulario.html">Volver al Formulario de Registro</a>
</body>
</html>