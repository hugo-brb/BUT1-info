public class ReleveMensuel {
    //Attributs
    private String mois;
    private float concentration;

    //Constructeur
    public ReleveMensuel(String mois, float concentration){
        this.mois = mois;
        this.concentration = concentration;
    }

    //Getteurs
    public String getMois(){
        return this.mois;
    }
    public float getConcentration() {
        return this.concentration;
    }

    public String toString(){
        return mois + " : " + concentration;
    }
}
