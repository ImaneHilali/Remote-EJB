package ma.fstt.atelier3consumer;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import ma.fstt.ejb.remoteinterface;
import ma.fstt.model.Etudiant;

@WebServlet(name = "FindAllEtudiantsServlet", value = "/FindAllEtudiantsServlet")
public class FindAllEtudiantsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Create a JNDI context and look up the remote interface
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(properties);
            remoteinterface remoteInterface = (remoteinterface) context.lookup("ejb:/atelier3-1.0-SNAPSHOT/etudiantejb!ma.fstt.ejb.remoteinterface");

            // Retrieve the list of Etudiants
            List<Etudiant> etudiants = remoteInterface.findAll();

            // Store the list in a request attribute for the JSP to use
            request.setAttribute("etudiants", etudiants);



            // Forward to the JSP to display the data
            request.getRequestDispatcher("FindAllEtudiants.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and send an error response
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}
