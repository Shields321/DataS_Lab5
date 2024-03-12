package datas_lab5;

public class LinearHashTable {
    private final static int MAXIMUM_CAPACITY = 1 << 30;
    private int maxSize;
    private Double loadFactor;
    private Machine noIndexMachine = new Machine("no index", "no index", "no index");
    Machine[] machine;
    int numItems;
    int index = 0;

    public LinearHashTable(int max, double loadFactor) {
        this.maxSize = max;
        this.loadFactor = loadFactor;
        machine = new Machine[maxSize];       
    }

    public int add(Machine m) {
        double loadFactor = (1.0*numItems)/maxSize;
        if(loadFactor < this.loadFactor){
            String code = m.getMachineCode();
            int hashCode = hashFunction(code);
            int pos = hashCode % maxSize;
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
            while (!(machine[pos].getMachineCode().equals(key))) {
                pos = (pos + 1) % maxSize;
            }                        
            return pos;
        }catch(Exception e){    
            
            //System.out.println("Error: " + j); // only un comment if you want to see what error happened
            System.out.println("\nKey Not in Machine List");
            return -1;
            
        } 
    }
    public int getDeleteLocation(String key) {
        try{
            int hashCode = hashFunction(key);
            int pos = hashCode % maxSize;   
            if(machine[pos] == null){
                while(machine[pos] == null){
                    pos++;
                }
            }
            while (!(machine[pos].getMachineCode().equals(key))) {
                pos = (pos + 1) % maxSize;
            }                        
            return pos;
        }catch(Exception e){    
            
            //System.out.println("Error: " + j); // only un comment if you want to see what error happened
            System.out.println("\nKey Not in Machine List");
            return -1;
            
        } 
    }
    
    public Machine retrieve(String key) {
        int pos = getLocation(key);
        if (pos == -1) {
            return noIndexMachine;
        }
        return machine[pos];
    }

    public boolean delete(String key) {
        try {
            int loc = getDeleteLocation(key);                
            machine[loc] = null;
            System.out.println("Value deleted at index:"+ loc);
            return true;
        } catch (Exception e) {
            System.out.println("No Value with the key: " + key);
            return false;   
        }
    }

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
