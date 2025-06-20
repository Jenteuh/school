package be.vdab.school.leerlingen;

public class Leerling {

    private long nummer;
    private String voornaam;
    private String familienaam;

    public Leerling(long nummer, String voornaam, String familienaam) {
        this.nummer = nummer;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
    }

    public long getNummer() {
        return nummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }
}
