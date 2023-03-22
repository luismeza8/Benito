package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Orden;
import mx.itson.benito.entidades.Pedido;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * El Data Access Object del pedido
 *
 * @author lm
 */
public class PedidoDAO {

    /**
     * Retorna todos los pedidos
     *
     * @return La lista de los pedidos
     */
    public static List<Pedido> obtenerTodos() {

        List<Pedido> pedidos = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Pedido> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Pedido.class);
            criteriaQuery.from(Pedido.class);

            pedidos = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return pedidos;
    }

    /**
     * Guarda un nuevo pedido en la base de datos
     *
     * @param articulo El articulo del nuevo pedido
     * @param orden La orden del nuevo pedido
     * @param cantidad La cantidad del nuevo pedido
     * @return Un booleano con la confirmacion del metodo
     */
    public static boolean guardar(Articulo articulo, Orden orden, int cantidad) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Pedido p = new Pedido();
            p.setArticulo(articulo);
            p.setOrden(orden);
            p.setCantidad(cantidad);

            session.save(p);

            session.getTransaction().commit();

            return p.getId() != 0;

        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Retorna el pedido del ID que le pases
     *
     * @param id El ID del pedido que deseas
     * @return El objeto pedido
     */
    public static Pedido obtenerPorId(int id) {
        Pedido p = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            p = session.get(Pedido.class, id);

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return p;
    }

    /**
     * Actualiza los valores de un pedido en la base de datos
     *
     * @param id El ID del pedido a editar
     * @param articulo El nuevo articulo del pedido
     * @param orden La nueva orden del pedido
     * @param cantidad La nueva cantidad del pedido
     * @return Un boolean con el resultado del metodo
     */
    public static boolean editar(int id, Articulo articulo, Orden orden, int cantidad) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Pedido p = session.get(Pedido.class, id);

            if (p != null) {
                p.setArticulo(articulo);
                p.setOrden(orden);
                p.setCantidad(cantidad);

                session.update(p);
                session.getTransaction().commit();
                return true;
            }

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }

    /**
     * Elimina un pedido que la base de datos
     *
     * @param id El ID del pedido a eliminar
     * @return Un boleeano con el resultado del metodo
     */
    public static boolean eliminar(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Pedido p = obtenerPorId(id);

            if (p != null) {
                session.delete(p);
                session.getTransaction().commit();
                return true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
}
