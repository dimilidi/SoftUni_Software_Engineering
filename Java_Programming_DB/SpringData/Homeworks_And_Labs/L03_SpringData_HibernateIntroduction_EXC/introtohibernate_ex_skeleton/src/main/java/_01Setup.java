import javax.persistence.EntityManager;

public class _01Setup {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();

        manager.close();
        JpaUtil.closeFactory();
    }
}
