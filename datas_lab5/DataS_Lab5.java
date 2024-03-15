package datas_lab5;

public class DataS_Lab5 {
    
    public static void main(String[] args) {
        Machine m = new Machine("java", "Dylan", "tea time");
        Machine m2 = new Machine("python", "Alex", "snake");
        Machine m3 = new Machine("csharp", "Alessio", "Dev");
        Machine m4 = new Machine("html", "Max", "design");
        Machine m5 = new Machine("http", "Web", "Look up");
        Machine m6 = new Machine("udp", "Packet", "0.0.0.0");
        Machine m7 = new Machine("tcp", "Packet", "127.0.0.1");
        Machine m8 = new Machine("icmp", "Ping", "Ping it");
         
        DoubleHashTable doubleMap = new DoubleHashTable(12, 0.6);
        LinearHashTable linearMap = new LinearHashTable(12, 0.6);
        QuadHashTable quadMap = new QuadHashTable(12, 0.6);
        
        //Testing the double hashing
        System.out.println("\ndouble Hash Table\n");
        doubleMap.add(m);
        doubleMap.add(m2);  
        doubleMap.add(m3); 
        doubleMap.add(m4);  
        doubleMap.add(m5);
        doubleMap.add(m6);
        doubleMap.add(m7);  
        doubleMap.add(m8); 
        
        //Deleting html and udp keys.
        System.out.println(doubleMap.delete("udp"));
        System.out.println(doubleMap.delete("html"));
        
        System.out.println("\nRetrieving Java from the table\n");
        System.out.println(doubleMap.retrieve("java").ToString());
        
        System.out.println("\nAfter deletion\n");
        System.out.println(doubleMap.displayMachines());
          
        //Testing the quadratic hashing
        System.out.println("\nQuadratic Hash Table\n");
        quadMap.add(m);
        quadMap.add(m2);  
        quadMap.add(m3); 
        quadMap.add(m4);  
        quadMap.add(m5);
        quadMap.add(m6);
        quadMap.add(m7);  
        quadMap.add(m8); 
        
        //Deleting the udp and csharp.
        System.out.println(quadMap.delete("udp"));
        System.out.println(quadMap.delete("csharp"));
        
        System.out.println("\nRetrieving tcp from the table\n");
        System.out.println(quadMap.retrieve("tcp").ToString());
        
        System.out.println("\nAfter deletion\n");
        System.out.println(quadMap.displayMachines());

        //Testing the linear hashing
        System.out.println("\nLinear Hash Table\n");
        linearMap.add(m);
        linearMap.add(m2);  
        linearMap.add(m3); 
        linearMap.add(m4);  
        linearMap.add(m5);
        linearMap.add(m6);
        linearMap.add(m7);  
        linearMap.add(m8); 
        
        //Deleting udp and http key.
        System.out.println(linearMap.delete("udp"));
        System.out.println(linearMap.delete("http"));
        
        System.out.println("\nRetrieving csharp from the table\n");
        System.out.println(linearMap.retrieve("csharp").ToString());
        
        System.out.println("\nAfter deletion\n");
        System.out.println(linearMap.displayMachines());

        
    }
}

