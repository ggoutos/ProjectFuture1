package gr.project.future.insurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select Functionality to perform:");
        System.out.println("*1 Vehicle Insurance Status");
        System.out.println("*2 Forecoming Expiries");
        System.out.println("*3 Expiries by plate");

        int option =  0;

        while (option == 0) {
            try {
                option = Integer.parseInt(br.readLine());
                System.out.print("You chose : '" + option + "'");
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
