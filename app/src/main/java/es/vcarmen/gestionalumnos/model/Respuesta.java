package es.vcarmen.gestionalumnos.model;


public class Respuesta {
    private String message;

    public Respuesta(String respuesta) {
        this.message = respuesta;
    }

    public String getRespuesta() {
        return message;
    }

    public void setRespuesta(String respuesta) {
        this.message = respuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "message='" + message + '\'' +
                '}';
    }
}
