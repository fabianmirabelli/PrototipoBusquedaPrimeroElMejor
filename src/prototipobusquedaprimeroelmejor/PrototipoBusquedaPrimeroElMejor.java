/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipobusquedaprimeroelmejor;

/**
 *
 * @author LoreyFaby
 */
import java.util.*;

class Nodo {
    String valor;
    int demora;
    List<Nodo> hijos;

    public Nodo(String valor, int demora) {
        this.valor = valor;
        this.demora = demora;
        this.hijos = new ArrayList<>();
    }
}

public class PrototipoBusquedaPrimeroElMejor {
    public static void main(String[] args) {
        Nodo raiz = construirArbol();
        List<Nodo> ruta = buscarMejorRuta(raiz);
        mostrarRuta(ruta);
    }

    private static Nodo construirArbol() {
        Nodo raiz = new Nodo("B", 0);
        Nodo b1 = new Nodo("B1", 1);
        Nodo b2 = new Nodo("B2", 7);
        Nodo b3 = new Nodo("B3", 2);
        Nodo b4 = new Nodo("B4", 3);
        Nodo b5 = new Nodo("B5", 4);
        Nodo b6 = new Nodo("B6", 2);
        Nodo a = new Nodo("A", 0);

        raiz.hijos.add(b1);
        raiz.hijos.add(b2);
        
        b1.hijos.add(b3);
        b2.hijos.add(b4);
        b2.hijos.add(a);
        b3.hijos.add(b5);
        b3.hijos.add(b6);
        b4.hijos.add(a);
        b5.hijos.add(a);
        b6.hijos.add(a);

        return raiz;
    }

    private static List<Nodo> buscarMejorRuta(Nodo raiz) {
        PriorityQueue<List<Nodo>> cola = new PriorityQueue<>(Comparator.comparingInt(ruta -> calcularDemoraTotal(ruta)));
        List<Nodo> rutaInicial = new ArrayList<>();
        rutaInicial.add(raiz);
        cola.offer(rutaInicial);

        while (!cola.isEmpty()) {
            List<Nodo> rutaActual = cola.poll();
            Nodo nodoActual = rutaActual.get(rutaActual.size() - 1);

            if (nodoActual.valor.equals("A")) {
                return rutaActual;
            }

            for (Nodo hijo : nodoActual.hijos) {
                List<Nodo> nuevaRuta = new ArrayList<>(rutaActual);
                nuevaRuta.add(hijo);
                cola.offer(nuevaRuta);
            }
        }

        return null;
    }

    private static int calcularDemoraTotal(List<Nodo> ruta) {
        int demoraTotal = 0;

        for (Nodo nodo : ruta) {
            demoraTotal += nodo.demora;
        }

        return demoraTotal;
    }

    private static void mostrarRuta(List<Nodo> ruta) {
        if (ruta == null) {
            System.out.println("No se encontró ningun movimiento de la pieza");
        } else {
            System.out.println("La ruta encontrada con el metodo Primero el Mejor\n para que la pieza sea colocada en la posicion A:");
            for (Nodo nodo : ruta) {
                System.out.print(nodo.valor + " ");
            }
            System.out.println();
        }
    }
}
