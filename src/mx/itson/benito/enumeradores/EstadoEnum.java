/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mx.itson.benito.enumeradores;

/**
 *
 * @author lm
 */
public enum EstadoEnum {
    ACTIVA(1),
    CERRADA(2),
    CANCELADA(3);
    
    private int numero;
    
    EstadoEnum(int numero) {
        this.numero = numero;
    }
}
