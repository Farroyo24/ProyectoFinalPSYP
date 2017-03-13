package es.vcarmen.gestionalumnos.model;

import java.util.Date;


public class Matricula {

    private String dni_alumno;
    private String codigo_asignatura;
    private int nota;
    private String fecha;

    public Matricula(String dni_alumno, String codigo_asignatura, int nota, String fecha) {
        this.dni_alumno = dni_alumno;
        this.codigo_asignatura = codigo_asignatura;
        this.nota = nota;
        this.fecha = fecha;
    }

    public String getDni_alumno() {
        return dni_alumno;
    }

    public void setDni_alumno(String dni_alumno) {
        this.dni_alumno = dni_alumno;
    }

    public String getCodigo_asignatura() {
        return codigo_asignatura;
    }

    public void setCodigo_asignatura(String codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "dni_alumno='" + dni_alumno + '\'' +
                ", codigo_asignatura='" + codigo_asignatura + '\'' +
                ", nota=" + nota +
                ", fecha=" + fecha +
                '}';
    }
}
