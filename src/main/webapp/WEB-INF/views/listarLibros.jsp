<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Inventario de Libros</title>
</head>
<body>
    <h1>Inventario de la Biblioteca</h1>
    
    <c:if test="${not empty libros}">
        <table>
            <tr>
                <th>Título</th>
                <th>Año</th>
                <th>Autor</th>
            </tr>
            <c:forEach items="${libros}" var="libro">
                <tr>
                    <td>${libro.titulo}</td>
                    <td>${libro.anioPublicacion}</td>
                    <td>${libro.autor.nombre}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty libros}">
        <p>No hay libros registrados en la biblioteca.</p>
    </c:if>
</body>
</html>