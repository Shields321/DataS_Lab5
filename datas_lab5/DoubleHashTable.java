package datas_lab5;

public class DoubleHashTable {
    private int maxSize; //Used to set the max size of the array. 
    private Double loadFactor;
    private Machine noIndexMachine = new Machine("no index", "no index", "no index");
    private Machine[] machine;
    private int numItems = 1;

    public DoubleHashTable(int max, double loadFactor) {
        this.maxSize = max;
        this.loadFactor = loadFactor;//This is the load factor set by the user. 
        machine = new Machine[maxSize]; //Creating the array that store the keys. 
    }

    public boolean add(Machine m) {
        double loadFactor = (1.0 * numItems) / maxSize; //This load factor is used to check if the array is near full.
        
        if (loadFactor < this.loadFactor) { //Adds the element when the calculated load factor is smaller than the default load factor.
            
            String code = m.getMachineCode(); //Getting the machine code of the key. 
            int stepSize = hashFunction2(code); //Calling the second hash function.
            int pos = hashFunction(code); //Calling the first hash function. 
            int step = 0; //Counts the number of collisions that happen. 
            int pos2 = 0; //Stores the index. 
            //double probing to handle collisions
            while (machine[pos2] != null) {
                pos2 = (pos + (step*stepSize))  % maxSize;
                step++;
            }
            machine[pos2] = m;
            numItems++; //Increment the number of items in the array. 
            return true;
        } else {
            System.out.println("Not enough space");// exceded load Factor typically would need to resize the array after                                     
            return false;
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

    public int hashFunction2(String key) {
        int number = 3; //Using the number 3 to make the output a third of the array size.
        int value = number - (hashFunction(key) % number);
        return value;
    }

    public int getLocation(String key) {
        try {
            int stepSize = hashFunction2(key); //Calling the second hash function.
            int pos = hashFunction(key); //Calling the first hash function. 
            int step = 0; //Counts the number of collisions that happen. 
            int pos2 = 0; //Stores the index. 
            
            while (!(machine[pos2].getMachineCode().equals(key))) {
                pos2 = (pos + (step*stepSize))  % maxSize;
                step++;
            }
            return pos2;
        } catch (Exception e) { //This catch is triggered when the key is not in the array.  
            System.out.println("Key Not in Machine List");
            return -1;
        }
    }

    public Machine retrieve(String key) {
        int pos = getLocation(key);//Gets the index of the machine.
        if (pos == -1) {//returns no index when key has no machine at that index. 
            return noIndexMachine;
        }
        return machine[pos];
    }

    public boolean delete(String key) {
        try {
            int loc = getLocation(key);  //gets the location of the key.   
            machine[loc] = null; //make the machine at that index null.
            System.out.println("Value deleted at index:"+ loc);
            return true;
        } catch (Exception e) {//Triggers when the key is not in the array.
            System.out.println("No Value with the key: " + key);
            return false;
        }
    }
     //This is used to display all the elements in the array. 
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
