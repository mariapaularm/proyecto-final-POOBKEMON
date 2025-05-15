package Domain;


public class Entrenador {
    private String nombre;
    private String descripcion;
    private String imagePath;
    private int nivelInicial;
    private String especialidad;


    public Entrenador(String nombre, String descripcion, String imagePath, int nivelInicial, String especialidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagePath = imagePath;
        this.nivelInicial = nivelInicial;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getNivelInicial() {
        return nivelInicial;
    }


    public int getNivel() {
        return nivelInicial;
    }


    public void setNivelInicial(int nivelInicial) {
        this.nivelInicial = nivelInicial;
    }


    public String getEspecialidad() {
        return especialidad;
    }


    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", nivelInicial=" + nivelInicial +
                '}';
    }
}