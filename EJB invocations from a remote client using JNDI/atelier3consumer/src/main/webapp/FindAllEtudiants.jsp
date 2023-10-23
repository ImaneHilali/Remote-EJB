<%@ page import="ma.fstt.model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find All Etudiants</title>
</head>
<body>

<h1>Find All Etudiants</h1>

<table border="1">
    <% List<Etudiant> malist = (List<Etudiant>) request.getAttribute("etudiants") ;

    %>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>CNE</th>
        <th>Adresse</th>
        <th>Niveau</th>


    <% for ( Etudiant tmp :malist
    ) {
    %>
    <tr>
        <td><%= tmp.getId_etudiant()%></td>
        <td><%= tmp.getNom()%></td>
        <td><%= tmp.getPrenom()%></td>
        <td><%= tmp.getCne()%></td>
        <td><%= tmp.getAdresse()%></td>
        <td><%= tmp.getNiveau()%></td>
        <td></td>
    </tr>
    <% } %>

</table>
</body>
</html>
