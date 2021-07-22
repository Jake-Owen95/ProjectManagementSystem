package com.company;

import java.util.Scanner;

public class Individual {
    public static String individualName;
    public static String individualAddress;
    public static String individualContact;
    public static String individualType;
    static Individual individual = new Individual(individualName, individualAddress, individualContact, individualType);
    public String getIndividualName() { return individualName; }
    public String getIndividualAddress() {
        return individualAddress;
    }
    public String getIndividualContact() {
        return individualContact;
    }
    public String getIndividualType() {
        return individualType;
    }

    public String toString () {
        String output = "Name and surname: " + individualName;
        output += "\nAddress: " + individualAddress;
        output += "\nContact number: " + individualContact;
        output += "\nType of person: " + individualType;
        return output;
    }
    public Individual(String personName, String personAddress, String personContact, String personType) {
        this.individualName = personName;
        this.individualAddress = personAddress;
        this.individualContact = personContact;
        this.individualType = personType;
    }
    public static Individual printIndividualMenu(){
        return individual;
    }

    public static void addPerson() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nAdd new person");
        System.out.println("\nName:");
        individualName = input.nextLine();
        System.out.println("Address:");
        individualAddress = input.nextLine();
        System.out.println("Contact number:");
        individualContact = input.nextLine();
        System.out.println("Person title:");
        individualType = input.nextLine();
        System.out.println("\n" + individual + "\n");
    }
}

