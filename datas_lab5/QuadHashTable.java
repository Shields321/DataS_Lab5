package datas_lab5;

public class QuadHashTable {
    private int maxSize; //Used to set the max size of the array
    private Double loadFactor;
    private Machine noIndexMachine = new Machine("no index", "no index", "no index");
    private Machine[] machine;
    private int numItems;
    

    public QuadHashTable(int max, double loadFactor) {
        this.maxSize = max;
        this.loadFactor = loadFactor; //This is the load factor set by the user.
        machine = new Machine[maxSize]; //Creating the array that store the keys.  
    }

    public int add(Machine m) {
        double loadFactor = (1.0*numItems)/maxSize;  //This load factor is used to check if the array is near full.
        if(loadFactor < this.loadFactor){ //Adds the element when the calculated load factor is smaller than the default load factor.
            int steps=1;
            int pos2 =0;
            String code = m.getMachineCode();
            int pos = hashFunction(code);
            
            // Linear probing to handle collisions
            while (machine[pos2] != null) {
                pos2 = (pos + steps*steps) % maxSize; 
                steps++;
            }
            machine[pos2] = m;
            numItems++;
            System.out.println(pos2);
            return pos2;
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
            int steps =1;
            int pos = hashFunction(key);
            if(machine[pos] == null){ //This is triggered if the hash code starting position to look for the key is null.
                while(machine[pos] == null){
                    pos++;
                }
            }
            
            while (!(machine[pos].getMachineCode().equals(key))) { //looks for the key
                pos = (pos + steps*steps) % maxSize; 
                steps++;
            }
            return pos;
        }catch(Exception e){ //This catch is triggered when the key is not in the array.
            //System.out.println("Error: " + e); // only un comment if you want to see what error happened
            System.out.println("Key Not in Machine List");
            return -1;
        } 
    }
    
    public Machine retrieve(String key) {
        int pos = getLocation(key); //Gets the index of the machine.
        if (pos == -1) {//returns no index when key has no machine at that index.
            return noIndexMachine;
        }
        return machine[pos];
    }

    public boolean delete(String key) {
        try {
            int loc = getLocation(key); //gets the location of the key.
            machine[loc] = null; //make the machine at that index null.
            return true;
        } catch (Exception e) {  //Triggers when the key is not in the array.
            System.out.println("No Value with the key: " + key);
            return false;   
        }
    }

    public String displayMachines() {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < maxSize; i++) {
            if (machine[i] != null) {
                builder.append("Index " + i + "\n");
                builder.append(machine[i].ToString());
            }
        }
        return builder.toString();
    }
}
