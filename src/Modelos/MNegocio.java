package Modelos;

public class MNegocio {

    private int idNegocio;
    private String nombreNegocio;
    private String domicilioNegocio;
    private String provinciaNegocio;
    private String telefonoNegocio;

    public MNegocio(int idNegocio, String nombreNegocio, String domicilioNegocio, String provinciaNegocio, String telefonoNegocio) {
        this.idNegocio = idNegocio;
        this.nombreNegocio = nombreNegocio;
        this.domicilioNegocio = domicilioNegocio;
        this.provinciaNegocio = provinciaNegocio;
        this.telefonoNegocio = telefonoNegocio;
    }

    public MNegocio() {
}

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDomicilioNegocio() {
        return domicilioNegocio;
    }

    public void setDomicilioNegocio(String domicilioNegocio) {
        this.domicilioNegocio = domicilioNegocio;
    }

    public String getProvinciaNegocio() {
        return provinciaNegocio;
    }

    public void setProvinciaNegocio(String provinciaNegocio) {
        this.provinciaNegocio = provinciaNegocio;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }

}
