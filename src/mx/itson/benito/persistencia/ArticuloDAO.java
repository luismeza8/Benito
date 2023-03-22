package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * El Data Access Object del articulo
 *
 * @author lm
 */
public class ArticuloDAO {

    /**
     * Retorna todos los articulos
     *
     * @return La lista de los articulos
     */
    public static List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            CriteriaQuery<Articulo> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);

            articulos = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }

        return articulos;
    }

    /**
     * Guarda un nuevo articulo en la base de datos
     *
     * @param nombre El nombre del nuevo articulo
     * @param precio El precio del nuevo articulo
     * @param folio El folio del nuevo articulo
     * @param proveedor El proveedor del nuevo articulo
     * @return Un booleano con la confirmacion del metodo
     */
    public static boolean guardar(String nombre, double precio, String folio, Proveedor proveedor) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Articulo a = new Articulo();
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setFolio(folio);
            a.setProveedor(proveedor);

            session.save(a);
            session.getTransaction().commit();

            return a.getId() != 0;

        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Retorna el articulo del ID que le pases
     *
     * @param id El ID del articulo que deseas
     * @return El objeto articulo
     */
    public static Articulo obtenerPorId(int id) {
        Articulo a = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            a = session.get(Articulo.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return a;
    }

    /**
     * Actualiza los valores de un articulo en la base de datos
     *
     * @param id El ID del articulo a editar
     * @param nombre El nuevo nombre del articulo
     * @param precio El nuevo precio del articulo
     * @param folio El nuevo folio del articulo
     * @param proveedor El nuevo proveedor del articulo
     * @return Un boolean con el resultado del metodo
     */
    public static boolean editar(int id, String nombre, double precio, String folio, Proveedor proveedor) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Articulo a = obtenerPorId(id);

            if (a != null) {
                a.setNombre(nombre);
                a.setPrecio(precio);
                a.setFolio(folio);
                a.setProveedor(proveedor);

                session.saveOrUpdate(a);
                session.getTransaction().commit();
                return true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }

    /**
     * Elimina un articulo que la base de datos
     *
     * @param id El ID del articulo a eliminar
     * @return Un boleeano con el resultado del metodo
     */
    public static boolean eliminar(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Articulo a = obtenerPorId(id);

            if (a != null) {
                session.delete(a);
                session.getTransaction().commit();
                return true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
}
