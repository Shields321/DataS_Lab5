package DataS_Lab5;

/*public class LinearHashTable {
    private final static int MAXIMUM_CAPACITY = 1 << 30;
    private int maxSize;
    private Double loadFactor;
    Machine noIndexMachine = new Machine("no index", "no index", "no index");
    Machine[] machine;
    int numItems;
    int index = 0;


    public LinearHashTable(int max, double loadFactor) {
        if (max > MAXIMUM_CAPACITY) {
            this.maxSize = MAXIMUM_CAPACITY;
            Machine[] upSizedMachine = new Machine[maxSize];
            for(int i = 0;i<maxSize;i++){
                upSizedMachine[i] = machine[i];
            }
        } else
            this.maxSize = trimToPowerOf2(max);
        this.loadFactor = loadFactor;
        machine = new Machine[maxSize];
        
    }

    private int trimToPowerOf2(int startingSize) {
        
        int capacity = 1;
        while (capacity < startingSize) {
            capacity <<= 1;
        }
        return capacity;
    }

    public int add(Machine m) {
        try {
            
            String code = m.getMachineCode();
            int hashCode = hashFunction(code);
            int pos = hashCode % maxSize;
            // Linear probing to handle collisions
            while (machine[pos] != null) {// if pos = 15
                pos = (pos + 1) % maxSize; // add 1 to pos --- 16 mod 16 = 0 so the value would go into the first index
                                           // if first index is ocupied go to second
            }
            machine[pos] = m;
            numItems++;
            
            return pos;
        } catch (Exception e) {
            maxSize = trimToPowerOf2(maxSize);
        }
        return 0;
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
        int hashCode = hashFunction(key);
        int pos = hashCode % maxSize;
        if (machine[pos] == null) {
            return -1;
        }
        while (machine[pos].getMachineCode().equals(key)) {
            pos = (pos + 1) % maxSize;
        }
        return pos;
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
            int loc = getLocation(key);
            machine[loc] = null;
            return true;
        } catch (Exception e) {
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
}*/
import java.util.ArrayList;

public class LinearHashTable {
    private final static int MAXIMUM_CAPACITY = 1 << 30;
    private int maxSize;
    private Double loadFactor;
    private Machine noIndexMachine = new Machine("no index", "no index", "no index");
    private ArrayList<Machine> machineList;
    private int numItems;

    public LinearHashTable(int max, double loadFactor) {
        if (max > MAXIMUM_CAPACITY) {
            this.maxSize = MAXIMUM_CAPACITY;
        } else {
            this.maxSize = trimToPowerOf2(max);
        }
        this.loadFactor = loadFactor;
        this.machineList = new ArrayList<>(maxSize);
        for (int i = 0; i < maxSize; i++) {
            this.machineList.add(null);
        }
    }

    private int trimToPowerOf2(int startingSize) {
        int capacity = 1;
        while (capacity < startingSize) {
            capacity <<= 1;
        }
        return capacity;
    }

    public int add(Machine m) {
        try {
            String code = m.getMachineCode();
            int hashCode = hashFunction(code);
            int pos = hashCode % maxSize;
            // Linear probing to handle collisions
            while (machineList.get(pos) != null) {
                pos = (pos + 1) % maxSize;
            }
            machineList.set(pos, m);
            numItems++;
            return pos;
        } catch (Exception e) {
            maxSize = trimToPowerOf2(maxSize);
        }
        return 0;
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
        int hashCode = hashFunction(key);
        int pos = hashCode % maxSize;
        if (machineList.get(pos) == null) {
            return -1;
        }
        while (!machineList.get(pos).getMachineCode().equals(key)) {
            pos = (pos + 1) % maxSize;
        }
        return pos;
    }

    public Machine retrieve(String key) {
        int pos = getLocation(key);
        if (pos == -1) {
            return noIndexMachine;
        }
        return machineList.get(pos);
    }

    public boolean delete(String key) {
        try {
            int loc = getLocation(key);
            machineList.set(loc, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String displayMachines() {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < maxSize; i++) {
            if (machineList.get(i) != null) {
                builder.append("Index ").append(i).append("\n");
                builder.append(machineList.get(i).ToString());
            }
        }
        return builder.toString();
    }
}

