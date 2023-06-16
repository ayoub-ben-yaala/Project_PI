/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pi.util.DataSource;

/**
 *
 * @author usernvme
 */
public class Medicament {

    private Connection cnx = DataSource.getInstance().getCnx();

    public int id;
    public String nom;
    public float prix;

    public Medicament(int id, String nom, float prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Medicament() {
    }

    public static List<Medicament> getAll() {
        List<Medicament> meds = new ArrayList<>();
        meds.add(new Medicament(1, "adol", 2.85f));
        meds.add(new Medicament(2, "lexomil", 30));
        meds.add(new Medicament(3, "tramadol", 20));
        meds.add(new Medicament(4, "ketamin", 50));

        return (meds);
    }

    public static int getIdByName(String name) {
        List<Medicament> allMedications = getAll();
        for (Medicament medication : allMedications) {
            if (medication.nom.equalsIgnoreCase(name)) {
                return medication.id;
            }
        }
        return 0; // Return 0 or a suitable value if the medication is not found
    }
    public static Medicament getIdById(int id) {
        List<Medicament> allMedications = getAll();
        for (Medicament medication : allMedications) {
            if (medication.id==id) {
                return medication;
            }
        }
        return null; // Return 0 or a suitable value if the medication is not found
    }
}
