
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="AjouterEtudiantServlet" method="get">
    <label>Nom :</label>
    <input type="text" name="nom" > <br/>

    <label>PreNom :</label>
    <input type="text" name="prenom" > <br/>

    <label>CNE :</label>
    <input type="text" name="cne" > <br/>

    <label>Adresse :</label>
    <input type="text" name="adresse" > <br/>

    <label>Niveau :</label>
    <input type="text" name="niveau" > <br/>

    <input type="submit" name="ok"  value="save"> <br/>

</form>
</body>
</html>
