<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro Exitoso</title>
</head>
<body>
    <h2 style="color: green;">¡Usuario registrado con éxito!</h2>
    <p><strong>Nombre:</strong> <%= request.getAttribute("nombre") %></p>
    <p><strong>Email:</strong> <%= request.getAttribute("email") %></p>
    <br>
    <a href="formulario.html">Registrar otro usuario</a> | 
    <a href="UsuarioServlet">Ver listado completo</a>
</body>
</html>