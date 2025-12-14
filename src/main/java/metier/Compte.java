package metier;

public class Compte {

    private Long code;
    private double solde;

    public Compte(Long code, double solde) {
        this.code = code;
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }

    public Long getCode() {
        return code;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }


}
