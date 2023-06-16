package pi.entities;

import java.util.HashMap;
import java.util.Map;


public class Panier {

    private int id;
    private Map<Integer, Integer> meds;

    public Panier(int id) {
        this.id = id;
        this.meds = new HashMap<>();
    }

    public Panier() {
        this.meds = new HashMap<>();
    }

    public Map<Integer, Integer> getMeds() {
        return meds;
    }

    public void setMeds(Map<Integer, Integer> meds) {
        this.meds = meds;
    }

    public void ajouterMed(ligne_commande L) {
        meds.put(L.getId_med(), L.getquantite());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", meds=" + meds + '}';
    }
    public String convertMedsToJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Map.Entry<Integer, Integer> entry : meds.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
        }

        if (!meds.isEmpty()) {
            sb.setLength(sb.length() - 1); // Remove the trailing comma
        }

        sb.append("}");

        return sb.toString();
    }
    
    
        public void setMedsFromJson(String json) {
        // Parse the JSON string manually and populate the meds map
        Map<Integer, Integer> medsMap = new HashMap<>();
        json = json.trim();

        if (json.startsWith("{") && json.endsWith("}")) {
            // Remove the opening and closing braces
            json = json.substring(1, json.length() - 1);

            // Split the key-value pairs
            String[] pairs = json.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    // Extract the key and value
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    // Remove quotes if present
                    if (key.startsWith("\"") && key.endsWith("\"")) {
                        key = key.substring(1, key.length() - 1);
                    }
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }

                    // Parse key and value as integers
                    try {
                        int medId = Integer.parseInt(key);
                        int quantity = Integer.parseInt(value);
                        medsMap.put(medId, quantity);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing medication ID or quantity: " + e.getMessage());
                    }
                }
            }
        }

        // Set the meds map
        this.meds = medsMap;
    }

}
