package pi.entities;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableData {

    public String colMed;
    public Integer quantite;
    public Float PrixUnitaire;
    public Float coutUnitaire;

    public TableData(String colMed, Integer quantite, Float PrixUnitaire, Float coutUnitaire) {
        this.colMed = colMed;
        this.quantite = quantite;
        this.PrixUnitaire = PrixUnitaire;
        this.coutUnitaire = coutUnitaire;
    }

    
    public TableData() {
    }

    public String getColMed() {
        return colMed;
    }

    public void setColMed(String colMed) {
        this.colMed = colMed;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPrixUnitaire() {
        return PrixUnitaire;
    }

    public void setPrixUnitaire(Float PrixUnitaire) {
        this.PrixUnitaire = PrixUnitaire;
    }

    public Float getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Float coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

}
