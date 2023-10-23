package ma.fstt.atelier3consumer;

import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ma.fstt.ejb.remoteinterface;

@WebServlet(name = "DeleteEtudiantByIdServlet", value = "/DeleteEtudiantByIdServlet")
public class DeleteEtudiantByIdServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the ID entered by the user from the request parameters.
            String idString = request.getParameter("id_etudiant");

            // Convert the ID string to a Long (you can add error handling for invalid input).
            Long id = Long.parseLong(idString);

            // Create a JNDI context.
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(properties);

            // Look up the remote interface using the JNDI context.
            remoteinterface remoteInterface = (remoteinterface) context.lookup("ejb:/atelier3-1.0-SNAPSHOT/etudiantejb!ma.fstt.ejb.remoteinterface");

            // Create a proxy object for the remote interface.
            remoteinterface proxy = (remoteinterface) remoteInterface;

            // Delete the Etudiant by ID.
            proxy.deleteById(id);

            // Send a response to the client indicating success.
            response.getWriter().write("Etudiant deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and send an error response.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}
