/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author lm
 */
public class OrdenDAO {
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
    
    public static boolean guardar(List<Pedido> pedidos, double total, double subtotal, String comentario, Date fecha, String folio) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Orden o = new Orden();
            o.setPedidos(pedidos);
            o.setTotal(total);
            o.setSubtotal(subtotal);
            o.setComentario(comentario);
            o.setFecha(fecha);
            o.setFolio(folio);
            
            session.save(o);
            session.getTransaction().commit();
            
            return o.getId() != 0;
        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }
    
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
    
    public static boolean editar(int id, List<Pedido> pedidos, double total, double subtotal, String comentario, Date fecha, String folio) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Orden o = obtenerPorId(id);
            
            if (o != null) {
                o.setPedidos(pedidos);
                o.setTotal(total);
                o.setSubtotal(subtotal);
                o.setComentario(comentario);
                o.setFecha(fecha);
                o.setFolio(folio);
                
                session.saveOrUpdate(o);
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
            
            Orden o = obtenerPorId(id);
            
            if (o != null) {
                session.delete(o);
                session.getTransaction().commit();
                return true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
}
