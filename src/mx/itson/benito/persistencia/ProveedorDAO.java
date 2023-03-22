package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * El Data Access Object del proveedor
 *
 * @author lm
 */
public class ProveedorDAO {

    /**
     * Retorna todos los proveedores
     *
     * @return La lista de los proveedores
     */
    public static List<Proveedor> obtenerTodos() {
        List<Proveedor> proveedores = new ArrayList<>();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);

            proveedores = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return proveedores;
    }

    /**
     * Guarda un nuevo proveedor en la base de datos
     *
     * @param nombre El nombre del nuevo proveedor
     * @param direccion la direccion del nuevo proveedor
     * @param telefono El telefono del nuevo proveedor
     * @param email El email del nuevo proveedor
     * @param contacto El contacto del nuevo proveedor
     * @return Un booleano con la confirmacion del metodo
     */
    public static boolean guardar(String nombre, String direccion, String telefono, String email, String contacto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setEmail(email);
            p.setContacto(contacto);

            session.save(p);

            session.getTransaction().commit();

            return p.getId() != 0;

        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Retorna el proveedor del ID que le pases
     *
     * @param id El ID del proveedor que deseas
     * @return El objeto proveedor
     */
    public static Proveedor obtenerPorId(int id) {
        Proveedor p = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            p = session.get(Proveedor.class, id);

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return p;
    }

    /**
     * Actualiza los valores de un proveedor en la base de datos
     *
     * @param id El ID del provedor a editar
     * @param nombre El nombre del proveedor a editar
     * @param direccion La direccion del proveedor a editar
     * @param telefono El telefono del proveedor a editar
     * @param email El email del proveedor a editar
     * @param contacto El contacto del proveedor a editar
     * @return Un boolean con el resultado del metodo
     */
    public static boolean editar(int id, String nombre, String direccion, String telefono, String email, String contacto) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Proveedor p = obtenerPorId(id);

            if (p != null) {
                p.setNombre(nombre);
                p.setDireccion(direccion);
                p.setTelefono(telefono);
                p.setEmail(email);
                p.setContacto(contacto);

                session.saveOrUpdate(p);
                session.getTransaction().commit();
                return true;
            }

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }

    /**
     * Elimina un proveedor que la base de datos
     *
     * @param id El ID del proveedor a eliminar
     * @return Un boleeano con el resultado del metodo
     */
    public static boolean eliminar(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Proveedor p = obtenerPorId(id);

            if (p != null) {
                session.delete(p);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
}
