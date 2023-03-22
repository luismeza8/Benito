package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Orden;
import mx.itson.benito.entidades.Pedido;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * El Data Access Object de la orden
 *
 * @author lm
 */
public class OrdenDAO {

    /**
     * Retorna todos las ordenes
     *
     * @return La lista de las ordenes
     */
    public static List<Orden> obtenerTodos() {
        List<Orden> ordenes = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Orden> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Orden.class);
            criteriaQuery.from(Orden.class);

            ordenes = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }

        return ordenes;
    }

    /**
     * Guarda una nueva orden en la base de datos
     *
     * @param pedidos Los pedidos de la nueva orden
     * @param total El total de la nueva orden
     * @param subtotal El subtotal de la nueva orden
     * @param comentario El comentario de la nueva orden
     * @param fecha La fecha de la nueva orden
     * @param folio El folio de la nueva orden
     * @return Un booleano con la confirmacion del metodo
     */
    public static boolean guardar(List<Pedido> pedidos, String comentario, Date fecha, String folio) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Orden o = new Orden();
            o.setPedidos(pedidos);
            o.setComentario(comentario);
            o.setFecha(fecha);
            o.setFolio(folio);

            session.save(o);
            session.getTransaction().commit();

            return o.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Retorna la orden del ID que le pases
     *
     * @param id El ID de la orden que deseas
     * @return El objeto orden
     */
    public static Orden obtenerPorId(int id) {
        Orden o = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            o = session.get(Orden.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return o;
    }

    /**
     * Actualiza los valores de una orden en la base de datos
     *
     * @param id El ID de la orden a editar
     * @param pedidos Los nuevos pedidos de la orden
     * @param total El nuevo total de la orden
     * @param subtotal El nuevo subtotal de la orden
     * @param comentario El nuevo comentario de la orden
     * @param fecha La nueva fecha de la orden
     * @param folio El nuevo folio de la orden
     * @return Un boolean con el resultado del metodo
     */
    public static boolean editar(int id, List<Pedido> pedidos, String comentario, Date fecha, String folio) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Orden o = session.get(Orden.class, id);

            if (o != null) {
                o.setPedidos(pedidos);
                o.setComentario(comentario);
                o.setFecha(fecha);
                o.setFolio(folio);

                session.update(o);
                session.getTransaction().commit();
                return true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }

    /**
     * Elimina una orden que la base de datos
     *
     * @param id El ID de la orden a eliminar
     * @return Un boleeano con el resultado del metodo
     */
    public static boolean eliminar(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Orden o = session.get(Orden.class, id);

            if (o != null) {
                session.delete(o);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
}
