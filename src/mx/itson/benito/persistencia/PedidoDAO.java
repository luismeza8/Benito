/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author lm
 */
public class PedidoDAO {
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
    
    public static boolean editar(int id, Articulo articulo, int cantidad) {
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Pedido p = obtenerPorId(id);
            
            if (p != null) {
                p.setArticulo(articulo);
                p.setCantidad(cantidad);
                
                session.saveOrUpdate(p);
                session.getTransaction().commit();
                return true;
            }
            
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
    
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
