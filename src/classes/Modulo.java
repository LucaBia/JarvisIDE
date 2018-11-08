package classes;

import java.util.List;

public class Modulo {
    static List<Funcion> funciones;

    public static void addFuncion(Funcion f) {
        funciones.add(f);
    }

    public void removeFuncion(Funcion f) {
        this.funciones.remove(f);
    }

    public List<Funcion> getFunciones() {
        return this.funciones;
    }
}
