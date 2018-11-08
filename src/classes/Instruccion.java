package classes;

public class Instruccion implements Accion {
    private String instruccion;

    public void setInstruccion(String ins) {
        this.instruccion = ins;
    }

    public String getInstruccion() {
        return this.instruccion;
    }

    @Override
    public void escribir() {
        //todo
    }
}
