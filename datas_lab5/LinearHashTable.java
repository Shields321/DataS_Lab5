package datas_lab5;

public class LinearHashTable {
    private final static int MAXIMUM_CAPACITY = 1<<30;
    private int maxSize;
    private Double loadFactor;
    Machine[] machine;
    int numItems;
    int index=0;
    
    public void getNumItems(){
        for(int i =0;i<=maxSize; i++){
            if(machine != null){
                numItems++;
            }
        }
    }
    public void linearHashTable(int max,double lf){
        if(max > MAXIMUM_CAPACITY){
            this.maxSize = MAXIMUM_CAPACITY;
        }
        else
            this.maxSize = trimToPowerOf2(max);
        this.loadFactor = lf;
        machine = new Machine[maxSize];
    }
    private int trimToPowerOf2(int startingSize){
        int capacity=0;
        while(capacity<startingSize){
            capacity<<=1;
        }
        return capacity;
    }
    
    public int hashFunction(String key){
        return hashHelp(Integer.parseInt(key))& (maxSize-1);
    }
    private int hashHelp(int h){        
        h ^=(h >>>20) ^ (h >>>12);
        return h;
    }
    public boolean add(Machine m){
        
        machine[index] = m;
        return true;
    }
    
}
