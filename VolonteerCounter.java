package stukVolonteer;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;


public class VolonteerCounter {

    
    private Map<Person, Integer> personList = new TreeMap<Person, Integer>();
    private Set<Person> personSet = new HashSet<Person>();

    public VolonteerCounter() {
        loadFromFile("stukData.bin");

        personSet = personList.keySet();

    }
    public void addEntry(Person person, int points){
        //denna funktionen lägger till entry även om personen inte finns
        Map.Entry<Person,Integer> chosenEntry = entryWhereNameIs(person.getName());
        int newValue = 0;
        
        if (chosenEntry != null) {
            newValue = chosenEntry.getValue() + points;
            this.personList.put(chosenEntry.getKey(), newValue); 
            chosenEntry.getKey().addPoints(points);   
        }
        else{
            this.personList.put(person, points);
            person.addPoints(points);
        }
        
        saveToFile("stukData.bin");
    }


    public List<Map.Entry<Person,Integer>> getList(){
        List<Map.Entry<Person,Integer>> lista = new ArrayList<Map.Entry<Person,Integer>>();
        lista.addAll(personList.entrySet());
        return lista;
    }

    public Set<String> getNamesSet() {
        Set<String> namesSet = new HashSet<>();
        for (Person person : personList.keySet()) {
            namesSet.add(person.getName());
        }
        return namesSet;
    }
 
    public Map.Entry<Person,Integer> entryWhereNameIs(String namn){
        for(Map.Entry<Person,Integer> p: personList.entrySet()){
            if(p.getKey().getName().equals(namn)){
                return p;
            }
        }
        return null;
    }   

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(personList);
            System.out.println("Map saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load the personList map from a file
    //على الله jag har ingen aning hur denna funkar
    //det var ChatGPT
    //det verkar funka dock
    public void loadFromFile(String filename) {
        File file = new File(filename);
        // Verifica si el archivo existe y no está vacío
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Cast the read object directly to the expected type, which is Map<Person, Integer>
                personList = (Map<Person, Integer>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo " + filename + " no existe o está vacío. Inicializando con un mapa vacío.");
            personList = new TreeMap<>(); // Asegura que personList no sea null
        }
    }
    
    
}

