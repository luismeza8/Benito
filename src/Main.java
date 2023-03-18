
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
        System.out.println(ProveedorDAO.obtenerTodos().get(0).getEmail());
    }
}
