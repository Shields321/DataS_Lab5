package datas_lab5;

public class DataS_Lab5 {
    
    public static void main(String[] args) {
        Machine m = new Machine("Aleks", "Dylan", "YOUR MOM");
        Machine m2 = new Machine("abc", "Dylan", "YOUR MOM");
                    
        LinearHashTable map = new LinearHashTable(16, 0.7);

        map.add(m2);
        map.add(m);  
        map.add(m2); 
        map.add(m2);  
        map.add(m); 
        
        System.out.println(map.displayMachines());
        
        System.out.println("\n");
        
        System.out.println(map.delete("abc"));
        System.out.println(map.delete("Aleks"));
        System.out.println(map.delete("abc"));
        System.out.println(map.delete("Aleks"));
        System.out.println(map.delete("abc"));
        System.out.println(map.delete("a"));
        
        
        System.out.println("\nAfter deletion\n");
        
        System.out.println(map.displayMachines());
        
    }
}
