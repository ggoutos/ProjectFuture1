package gr.project.future.insurance;

import gr.project.future.enums.OptionIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FineCalculator {
    private HashMap<String,List<Vehicle>> vehiclesOwnersMap;


    public FineCalculator(List<Vehicle> vehiclesInitial){
        vehiclesOwnersMap = new HashMap<>();
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehiclesInitial;
        for(Vehicle v:vehicles){
            String name = (v.getOwner().getName().strip()+v.getOwner().getSurname().strip()).toLowerCase();
            if(vehiclesOwnersMap.containsKey(name)){
                vehiclesOwnersMap.get(name).add(v);
            }else{
                List<Vehicle> l = new ArrayList<Vehicle>();
                l.add(v);
                vehiclesOwnersMap.put(name.toLowerCase(),l);
            }
        }
    }

    public double getFine(String name,double fine,int optionIO,String firstName,String lastName){
        List<Vehicle> ownersVehicles= vehiclesOwnersMap.get(name.toLowerCase());
        int numberOfUninsured;
        numberOfUninsured = 0;
        for (Vehicle v:ownersVehicles){
            if(v.insuranceIsExpired()){
                numberOfUninsured++;
            }
        }
        if (optionIO == OptionIO.FILE.getOption()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("resources/output.txt"));
                writer.write("The fine for owner: "+ firstName+" "+lastName + " is: "+fine*numberOfUninsured );
                writer.write("\n");
                writer.close();
                System.out.println("Message written in log file '" + "resources/output.txt" + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fine*numberOfUninsured;
    }

    public boolean isNameValid(String nameInput,String surnameInput){
        String name= nameInput.toLowerCase().strip();
        String surname=surnameInput.toLowerCase().strip();
        return vehiclesOwnersMap.containsKey(name+surname)||vehiclesOwnersMap.containsKey(surname+name);
    }

    public String getName(String nameInput,String surnameInput){
        String name= nameInput.toLowerCase().strip();
        String surname=surnameInput.toLowerCase().strip();
        if(vehiclesOwnersMap.containsKey(name+surname)){
            return name+surname;
        }else if (vehiclesOwnersMap.containsKey(surname+name)){
            return surname+name;
        }else{
            return "Name invalid";
        }
    }
}
