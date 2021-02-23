package datatier.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Argjendari
 */

public class PersistenceUtil {
    private static final String PERSISTENCE_UNIT_NAME = "FinalAdvJavaProjectPU";
    private static final EntityManagerFactory EMF;
    
    static {
        EMF = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    
    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
}
