package pe.jrivera6.nicollperuoperarios.models;


public class Tubo {

    private Long id;
    private String descripcion_tubo;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion_tubo() {
        return descripcion_tubo;
    }

    public void setDescripcion_tubo(String descripcion_tubo) {
        this.descripcion_tubo = descripcion_tubo;
    }

    @Override
    public String toString() {
        return "Tubo{" +
                "id=" + id +
                ", descripcion_tubo='" + descripcion_tubo + '\'' +
                '}';
    }
}

