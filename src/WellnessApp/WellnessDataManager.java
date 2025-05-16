package WellnessApp;

import java.util.HashMap;
import java.util.Map;

public class WellnessDataManager {
    private Map<String, String> userCredentials = new HashMap<>();
    private Map<String, Map<String, Object>> userData = new HashMap<>();
    
    public WellnessDataManager() {
       
        userCredentials.put("user", "pass");
        
        Map<String, Object> dummyData = new HashMap<>();
        dummyData.put("habits", new String[]{"drink water", "meditate"});
        dummyData.put("waterIntake", 5);
        userData.put("user", dummyData);
    }
    
    public boolean validateLogin(String username, String password) {
        return userCredentials.containsKey(username) && 
               userCredentials.get(username).equals(password);
    }
    
    public boolean createAccount(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return false;
        }
        userCredentials.put(username, password);
        userData.put(username, new HashMap<>());
        return true;
    }
    
    public Map<String, Object> getUserData(String username) {
        return userData.getOrDefault(username, new HashMap<>());
    }
    
    public void updateTaskStatus(String task, boolean completed) {
        // Here Implementation to save task status
    }

    public boolean getTaskStatus(String task) {
        // Here Implementation to get task status
        return false; // Default value
    }
    
    public void updateUserData(String username, String key, Object value) {
        if (!userData.containsKey(username)) {
            userData.put(username, new HashMap<>());
        }
        userData.get(username).put(key, value);
    }
}