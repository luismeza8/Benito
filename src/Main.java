import mx.itson.benito.persistencia.ArticuloDAO;
import mx.itson.benito.persistencia.OrdenDAO;
import mx.itson.benito.persistencia.PedidoDAO;
import mx.itson.benito.persistencia.ProveedorDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lm
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(OrdenDAO.eliminar(1));
    }
}
