package datas_lab5;

public class LinearHashTable {
    private int maxSize; //Used to set the max size of the array. 
    private Double loadFactor; 
    private Machine noIndexMachine = new Machine("no index", "no index", "no index");
    Machine[] machine;
    int numItems;
    int index = 0;

    public LinearHashTable(int max, double loadFactor) {
        this.maxSize = max;
        this.loadFactor = loadFactor; //This is the load factor set by the user. 
        machine = new Machine[maxSize]; //Creating the array that store the keys.        
    }

    public int add(Machine m) {
        double loadFactor = (1.0*numItems)/maxSize; //This load factor is used to check if the array is near full.
        if(loadFactor < this.loadFactor){ //Adds the element when the calculated load factor is smaller than the default load factor. 
            String code = m.getMachineCode(); 
            int hashCode = hashFunction(code);
            int pos = hashCode % maxSize; //index of where the element can be added. 
            // Linear probing to handle collisions
            while (machine[pos] != null) {// if pos = 15
                pos = (pos + 1) % maxSize; // add 1 to pos --- 16 mod 16 = 0 so the value would go into the first index if first index is ocupied go to second
            }
            machine[pos] = m;
            numItems++;
            //System.out.println(pos);
            return pos;
        }else{
            System.out.println("Not enough space");//exceded load Factor typically would need to resize the array after this point
            return 0;
        }        
    }

    public int hashFunction(String key) {
        int hash = 0;
        // Iterate over each character in the string
        for (int i = 0; i < key.length(); i++) {
            // Add the ASCII value of the character to the hash
            hash += key.charAt(i);
        }
        // Take the remainder when divided by the size of the hash table
        return hash % maxSize;
    }
    
    public int getLocation(String key) {
        try{ 
            int hashCode = hashFunction(key);
            int pos = hashCode % maxSize;  
            if(machine[pos] == null){ //This is triggered if the starting position to look for the key is null.
                while(machine[pos] == null){
                    pos++;
                }
            }
            while (!(machine[pos].getMachineCode().equals(key))) { //looks for the key.
                pos = (pos + 1) % maxSize;
            }                        
            return pos;
        }catch(Exception e){ //This catch is triggered when the key is not in the array.  
            
            //System.out.println("Error: " + j); // only un comment if you want to see what error happened
            System.out.println("\nKey Not in Machine List");
            return -1;
            
        } 
    }    
    
    public Machine retrieve(String key) {
        int pos = getLocation(key); //Gets the index of the machine.
        if (pos == -1) { //returns no index when key has no machine at that index. 
            return noIndexMachine;
        }
        return machine[pos];
    }

    public boolean delete(String key) {
        try {
            int loc = getLocation(key); //gets the location of the key.                
            machine[loc] = null; //make the machine at that index null.
            System.out.println("Value deleted at index:"+ loc);
            return true;
        } catch (Exception e) { //Triggers when the key is not in the array.
            System.out.println("No Value with the key: " + key);
            return false;   
        }
    }

    //This is used to display all the elements in the array. 
    public String displayMachines() {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < maxSize; i++) {
            if (machine[i] != null) {
                builder.append("\n\nIndex " + i + "\n");
                builder.append(machine[i].ToString());
            }
        }
        return builder.toString();
    }
}
