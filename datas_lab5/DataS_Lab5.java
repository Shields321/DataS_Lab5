package DataS_Lab5;

public class DataS_Lab5 {
    public static void main(String[] args) {
        Machine m = new Machine("Aleks", "Dylan", "YOUR MOM");
        Machine m2 = new Machine("Alessio", "Dylan", "YOUR MOM");
        
        
        
        LinearHashTable map2 = new LinearHashTable(10, 0.7);
        QuadHashTable map = new QuadHashTable(10, 0.7);

        map.add(m);
        map2.add(m2);
       


        System.out.println("Linear\n"+map.getLocation(m.getMachineCode()));
        System.out.println("-----------------------");
        System.out.println("Quad\n"+map2.getLocation(m2.getMachineCode()));

    }
}
