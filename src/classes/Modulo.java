package classes;

import java.util.List;

public class Modulo {
    private List<Funcion> funciones;

    public void addFuncion(Funcion f) {
        this.funciones.add(f);
    }

    public void removeFuncion(Funcion f) {
        this.funciones.remove(f);
    }

    public List<Funcion> getFunciones() {
        return this.funciones;
    }
}
