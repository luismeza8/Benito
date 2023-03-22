/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.text.SimpleDateFormat;
import java.util.Date;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Estado;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.enumeradores.EstadoEnum;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author lm
 */
public class EstadoDAO {
    public static boolean guardar(EstadoEnum estado, String comentario) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Estado e = new Estado();
            e.setFecha(new Date());
            e.setEstado(estado);
            e.setComentario(comentario);

            session.save(e);
            session.getTransaction().commit();

            return e.getId() != 0;

        } catch (HibernateException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }
    
    public static Estado obtenerPorId(int id) {
        Estado e = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            e = session.get(Estado.class, id);
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return e;
    }
}
