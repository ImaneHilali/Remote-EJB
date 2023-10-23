package ma.fstt.atelier3consumer;

import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ma.fstt.ejb.remoteinterface;
import ma.fstt.model.Etudiant;

@WebServlet(name = "FindEtudiantByIdServlet", value = "/FindEtudiantByIdServlet")
public class FindEtudiantByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the ID entered by the user from the request parameters.
            String idString = request.getParameter("id_etudiant");

            if (idString == null || idString.isEmpty()) {
                response.getWriter().write("ID is missing or empty.");
                return;
            }

            Long id;
            try {
                id = Long.parseLong(idString);
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid ID format: " + idString);
                return;
            }

            // Create a JNDI context.
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(properties);

            // Look up the remote interface using the JNDI context.
            remoteinterface remoteInterface = (remoteinterface) context.lookup("ejb:/atelier3-1.0-SNAPSHOT/etudiantejb!ma.fstt.ejb.remoteinterface");

            // Create a proxy object for the remote interface.
            remoteinterface proxy = (remoteinterface) remoteInterface;

            // Find the Etudiant by ID.
            Etudiant etudiant = proxy.findById(id);

            if (etudiant != null) {
                // Send a response to the client with the Etudiant's information.
                response.getWriter().write("Etudiant found:\n");
                response.getWriter().write("ID: " + etudiant.getId_etudiant() + "\n");
                response.getWriter().write("Nom: " + etudiant.getNom() + "\n");
                response.getWriter().write("Prenom: " + etudiant.getPrenom() + "\n");
                response.getWriter().write("CNE: " + etudiant.getCne() + "\n");
                response.getWriter().write("Adresse: " + etudiant.getAdresse() + "\n");
                response.getWriter().write("Niveau: " + etudiant.getNiveau() + "\n");
            } else {
                // Send a response to the client indicating that the Etudiant was not found.
                response.getWriter().write("Etudiant not found for ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and send an error response.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}
