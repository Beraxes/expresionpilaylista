/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresionpilaylista;

/**
 *
 * @author beraxes
 */
public class nodo {

    private String Dato;
    public nodo enlace;

    public nodo() {
        Dato = "";
        enlace = null;
    }

    public String getDato() {
        return Dato;
    }

    public void setDato(String Dato) {
        this.Dato = Dato;
    }

    public nodo getEnlace() {
        return enlace;
    }

    public void setEnlace(nodo enlace) {
        this.enlace = enlace;
    }

    public void mostrar() {
        System.out.println(getDato());
    }

}
