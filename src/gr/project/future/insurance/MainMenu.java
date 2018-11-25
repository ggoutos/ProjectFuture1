package gr.project.future.insurance;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainMenu {


    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select Functionality to perform:");
        System.out.println("*1 Vehicle Insurance Status");
        System.out.println("*2 Forecoming Expiries");
        System.out.println("*3 Expiries by plate");

        int option1 = 0;

        while (option1 == 0) {
            try {
                option1 = Integer.parseInt(br.readLine());
                System.out.print("You chose : '" + option1 + "'");
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Enter export type:");
        System.out.println("*1 File");
        System.out.println("*2 Console");

        int option2 = 0;

        try {
            option2 = Integer.parseInt(br.readLine());
            System.out.print("You chose : '" + option2 + "'");
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format! Try again.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
