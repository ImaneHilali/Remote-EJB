package ma.fstt.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import ma.fstt.ejb.remoteinterface;
import ma.fstt.model.Etudiant;

public class Client {
    public static void main(String[] args) {
        try {
            // Create a JNDI context.
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            Context context = new InitialContext(properties);

            // Look up the remote interface using the JNDI context.
            remoteinterface remoteInterface = (remoteinterface) context.lookup("ejb:/atelier3-1.0-SNAPSHOT/etudiantejb!ma.fstt.ejb.remoteinterface");

            // Create a proxy object for the remote interface.
            remoteinterface proxy = (remoteinterface) remoteInterface;

            // Invoke the CRUD methods on the proxy object.
            Etudiant etudiant = new Etudiant();
            etudiant.setNom("John");
            etudiant.setPrenom("Doe");
            etudiant.setCne("123456789");
            etudiant.setAdresse("123 Main Street");
            etudiant.setNiveau("L1");

            proxy.save(etudiant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
