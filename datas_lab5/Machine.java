
package datas_lab5;

public class Machine {
    String machineCode;
    String name;
    String description;
    int location;
    
    public Machine(String machineCode, String name,String description){
        this.machineCode = machineCode;
        this.name = name;
        this.description = description;        
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLocation() {
        return location;
    }
    public String ToString(){
        String str = "Code = " + machineCode + "\nName = "+ name + "\nDescription = "+description+"\nLocation = "+location;  
        return str;  
    }
    
}
