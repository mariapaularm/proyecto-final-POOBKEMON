package Domain;


public abstract class Item {
    private String nombre;
    private String descripcion;
    private String imagePath;


    public Item(String nombre, String descripcion, String imagePath) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagePath = imagePath;
    }


    public abstract boolean usar(Pokemon pokemon);

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagePath() {
        return imagePath;
    }
}

