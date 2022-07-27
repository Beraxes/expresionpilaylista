/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package expresionpilaylista;

import javax.swing.JOptionPane;

/**
 *
 * @author beraxes
 */
public class ExpresionPilayLista {

    nodo cima;
    nodo falda;
    nodo cabeza;
    nodo fin;

    public ExpresionPilayLista() {
        cima = null;
        falda = null;
        cabeza = null;
        fin = null;
    }

    public void EPOS(String caracter) {
        if (cabeza == null) //no hay lista
        {
            cabeza = new nodo();
            cabeza.setDato(caracter);
            fin = cabeza;
        } else {
            nodo temporal = new nodo();
            temporal.setDato(caracter);
            fin.setEnlace(temporal);
            fin = temporal;
        }

    }

    public void apilar(String item) {
        if (falda == null) {
            falda = new nodo();
            falda.setDato(item);
            cima = falda;

        } else {
            nodo temp = new nodo();
            temp.setDato(item);
            temp.setEnlace(cima);
            cima = temp;
        }
    }

    public void desapilarTodo() {
        nodo temp;

        if (cima != null) {// PILA EXISTE
            while (cima != null) {

                if (cima == falda) {
                    EPOS(cima.getDato());
                    cima = null;
                    falda = null;

                } else {
                    temp = cima;
                    EPOS(cima.getDato());
                    cima = cima.getEnlace();
                    temp.setEnlace(null);
                }

            }
        }

    }

    public String desapilarElemento() {
        nodo temp;
        String aux = "";
        if (cima != null) {//PILA EXISTE

            // cima.mostrar();
            if (cima == falda) {
                aux = cima.getDato();
                cima = null;
                falda = null;

            } else {
                temp = cima;
                aux = cima.getDato();
                cima = cima.getEnlace();
                temp.setEnlace(null);
            }

        }
        return aux;
    }

    public void mostrarLista() {
        nodo temp = cabeza;
        while (temp != null) {//LISTA EXISTE            
            System.out.print(temp.getDato());//solucionado y mostrando la lista
            temp = temp.getEnlace();
        }
    }

    public static int Priorizar(String signo) {
        int valorPrioridad = -1;
        if (signo.equals("+") || signo.equals("-")) {
            valorPrioridad = 1;
        } else if (signo.equals("*") || signo.equals("/")) {
            valorPrioridad = 2;
        } else if (signo.equals("^")) {
            valorPrioridad = 3;
        }
        return valorPrioridad;
    }

    public static void main(String[] args) {
        String expresionUsuario = JOptionPane.showInputDialog("Ingrese una expresion"); // aqui pido por joption un string al usuario que seria una expresion matematica
        ExpresionPilayLista pila = new ExpresionPilayLista();
        for (int i = 0; i < expresionUsuario.length(); i++) {
            String aux = "";
            aux = aux + expresionUsuario.charAt(i);
            if (aux.equals("(")) {
                pila.apilar(aux);

            } else if (aux.equals(")")) {
                String dato;
                do {
                    dato = pila.desapilarElemento();
                    if (!dato.equals("(")) {
                        pila.EPOS(dato);
                    }
                } while (!dato.equals("("));

            } else if (aux.equals("+") || aux.equals("-") || aux.equals("*") || aux.equals("/") || aux.equals("^")) {
                int operador = Priorizar(aux);

                do {
                    if (pila.cima == null) {
                        pila.apilar(aux);
                        aux = "";
                    } else {

                        String salientePila = pila.desapilarElemento();
                        if (salientePila.equals("(")) {
                            pila.apilar(salientePila);
                            pila.apilar(aux);
                            aux = "";
                        } else {
                            int precedencia = Priorizar(salientePila);
                            if (operador == precedencia) {
                                pila.EPOS(salientePila);
                                pila.apilar(aux);
                                aux = "";

                            } else if (operador > precedencia) {
                                pila.apilar(salientePila);
                                pila.apilar(aux);
                                aux = "";
                            } else {
                                pila.EPOS(salientePila);
                                pila.apilar(aux);
                                aux = "";

                            }
                        }
                    }

                } while (!aux.equals(""));
            } else {
                pila.EPOS(aux);

            }

        }

        pila.desapilarTodo();

        pila.mostrarLista();

    }
}
