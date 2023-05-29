package pi.gui; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.util.StringConverter;
import java.util.Map;

public class MapConverter extends StringConverter<String> {
    private final Map<String, String> valueMap = new HashMap<>();

    public MapConverter() {
        valueMap.put("en cours", "En Cours"); 
        valueMap.put("delivered", "Delivered"); 
    }

    @Override
    public String toString(String key) {
        String retVal = valueMap.get(key);
        if(retVal == null || "".equals(retVal)){
            return key;
        } 
        return retVal; 
    }

    @Override
    public String fromString(String label) {
        // Reverse lookup of the value to get the original key if needed
        return valueMap.entrySet()
                .stream()
                .filter(entry -> label.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
    
    public List<String> getValuesList() {
        return new ArrayList<>(valueMap.values());
    }
    
    
}
