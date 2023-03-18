/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author lm
 */
public class ProveedorDAO {

    public static List<Proveedor> obtenerTodos() {

        List<Proveedor> proveedores = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery
                    = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);

            proveedores = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return proveedores;
    }

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
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return false;
    }
}
