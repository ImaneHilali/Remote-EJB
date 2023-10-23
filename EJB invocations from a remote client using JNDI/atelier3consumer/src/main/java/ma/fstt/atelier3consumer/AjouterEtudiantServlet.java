package ma.fstt.atelier3consumer;

import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import jakarta.servlet.annotation.WebServlet;
import ma.fstt.ejb.remoteinterface;
import ma.fstt.model.Etudiant;

@WebServlet(name = "AjouterEtudiantServlet", value = "/AjouterEtudiantServlet")
public class AjouterEtudiantServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve user-entered data from the request parameters.
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String cne = request.getParameter("cne");
            String adresse = request.getParameter("adresse");
            String niveau = request.getParameter("niveau");

            // Create a JNDI context.
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(properties);

            // Look up the remote interface using the JNDI context.
            remoteinterface remoteInterface = (remoteinterface) context.lookup("ejb:/atelier3-1.0-SNAPSHOT/etudiantejb!ma.fstt.ejb.remoteinterface");

            // Create a proxy object for the remote interface.
            remoteinterface proxy = (remoteinterface) remoteInterface;

            // Create an Etudiant object with the user-entered data.
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(nom);
            etudiant.setPrenom(prenom);
            etudiant.setCne(cne);
            etudiant.setAdresse(adresse);
            etudiant.setNiveau(niveau);

            // Invoke the save method on the proxy object.
            proxy.save(etudiant);

            request.getRequestDispatcher("FindAllEtudiantsServlet").forward(request,response);

            // Send a response to the client indicating success.
            //response.getWriter().write("Etudiant saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and send an error response.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
