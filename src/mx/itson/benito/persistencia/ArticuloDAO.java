/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author lm
 */
public class ArticuloDAO {
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
    
    public static boolean guardar(String nombre, double precio, String folio, int proveedorId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Articulo a = new Articulo();
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setFolio(folio);
            a.setProveedorId(proveedorId);
            
            session.save(a);
            session.getTransaction().commit();
            
            return a.getId() != 0;
            
        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }
    
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
    
    public static boolean editar(int id, String nombre, double precio, String folio, int proveedorId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Articulo a = obtenerPorId(id);
            
            if (a != null) {
                a.setNombre(nombre);
                a.setPrecio(precio);
                a.setFolio(folio);
                a.setProveedorId(proveedorId);
                
                session.saveOrUpdate(a);
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
