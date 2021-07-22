package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.FileReader;

import static com.company.Individual.*;
import static com.company.Individual.individualAddress;

public class Project {
    public static int projectIndex;
    static int projectNumber = projectIndex++;
    static String projectName;
    static String projectType;
    static String projectAddress;
    static String projectErfNumber;
    static float projectFee;
    static float feePaid;
    static String projectDeadline;
    static String projectArchitect;
    static String architectNumber;
    static String architectEmail;
    static String architectAddress;
    static String projectContractor;
    static String contractorNumber;
    static String contractorEmail;
    static String contractorAddress;
    static String projectClient;
    static String clientNumber;
    static String clientEmail;
    static String clientAddress;
    static String project = projectNumber + projectName + projectType + projectAddress + projectErfNumber + projectFee + feePaid + projectDeadline + projectArchitect + architectNumber + architectEmail + architectAddress + contractorNumber + contractorEmail + contractorAddress + clientNumber + clientEmail + clientAddress;
    static String[] projects = new String[10];
    public String toString () {
        project = projectNumber + projectName + projectType + projectAddress + projectErfNumber + projectFee + feePaid + projectDeadline + projectArchitect + architectNumber + architectEmail + architectAddress + contractorNumber + contractorEmail + contractorAddress + clientNumber + clientEmail + clientAddress;
        String output = "Project number: " + projectNumber;
        output += "\nProject Name: " + projectName;
        output += "\nProject Type: " + projectType;
        output += "\nProject Address: " + projectAddress;
        output += "\nProject Erf: " + projectErfNumber;
        output += "\nProject Fee: " + projectFee;
        output += "\nProject Fee paid: " + feePaid;
        output += "\nProject Deadline: " + projectDeadline;
        output += "\nProject Architect: " + projectArchitect;
        output += "\nArchitect Number: " + architectNumber;
        output += "\nArchitect Email: " + architectEmail;
        output += "\nArchitect Address: " + architectAddress;
        output += "\nProject Contractor: " + projectContractor;
        output += "\nContractor Number: " + contractorNumber;
        output += "\nContractor Email: " + contractorEmail;
        output += "\nContractor Address: " + contractorAddress;
        output += "\nProject Client: " + projectClient;
        output += "\nClient Number: " + clientNumber;
        output += "\nClient Email: " + clientEmail;
        output += "\nClient Address: " + clientAddress;
        output += "\n";
        return output;
    }
    //==================================================Constructor=====================================================
    Project(int projectNumber, String projectName, String projectType, String projectAddress, String erfNumber, float projectFee, float feePaid, String projectDeadline, String projectArchitect, String architectNumber, String architectEmail, String architectAddress, String projectContractor, String contractorNumber, String contractorEmail, String contractorAddress, String projectClient, String clientNumber, String clientEmail, String clientAddress, int projectIndex) {
        this.projectIndex = projectIndex;
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projectType = projectType;
        this.projectAddress = projectAddress;
        this.projectErfNumber = erfNumber;
        this.projectFee = projectFee;
        this.feePaid = feePaid;
        this.projectDeadline = projectDeadline;
        this.projectArchitect = projectArchitect;
        this.architectNumber = architectNumber;
        this.architectEmail = architectEmail;
        this.architectAddress = architectAddress;
        this.projectContractor = projectContractor;
        this.contractorNumber = contractorNumber;
        this.contractorEmail = contractorEmail;
        this.contractorAddress = contractorAddress;
        this.projectClient = projectClient;
        this.clientNumber = clientNumber;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
       // this.project = projectIndex + projectNumber + projectName + projectType + projectAddress + erfNumber + projectFee + feePaid + projectDeadline + projectArchitect + architectNumber + architectEmail + architectAddress + projectContractor + contractorNumber + contractorEmail + contractorAddress + projectClient + clientNumber + clientEmail + clientAddress;
    }
    //==================================================================================================================
    //==============================================ADD PROJECT=========================================================
    public static void addProject() throws IOException, SQLException {
        //Scanner for user input:
        Scanner input = new Scanner(System.in);

        //Get input from user:
        System.out.println("\nAdd new Project");
        System.out.println("\nProject Name: ");
        projectName = input.nextLine();
        System.out.println("\nProject Type: ");
        projectType = input.nextLine();
        System.out.println("\nProject Address: ");
        projectAddress = input.nextLine();
        System.out.println("\nProject Erf Number:");
        projectErfNumber = input.nextLine();
        System.out.println("\nTotal Cost: ");
        projectFee = input.nextFloat();
        System.out.println("\nAlready paid: ");
        feePaid = input.nextFloat();
        System.out.println("\nProject Deadline: input in this format - yyyy/MM/dd" + input.nextLine());
        projectDeadline = input.nextLine();

        System.out.println("\nProject Architect: ");
        projectArchitect = input.nextLine();

        System.out.println("\nProject Contractor: ");
        projectContractor = input.nextLine();

        System.out.println("\nProject Client: ");
        projectClient = input.nextLine();

        //If no project Name has been entered, name as client name + project type.
        if(!projectName.contains(" ")){
            projectName = projectType + " " +  projectClient;
        }
        project = "\nProjectName: " + projectName + "\nProjectType: " + projectType + "\nProjectAddress: "
                + projectAddress + "\nProjectErfNumber: " + projectErfNumber + "\nProjectFee: "
                + projectFee + "\nFeePaid: " + feePaid + "\nProjectDeadLine: " + projectDeadline + "\nProjectArchitect: "
                + projectArchitect + "\nArchitectNumber: " + architectNumber + "\nArchitectEmail: "
                + architectEmail + "\nArchitectAddress: " + architectAddress + "\nProjectContractor: "
                + projectContractor + "\nContractorNumber: " + contractorNumber + "\nContractorEmail: "
                + contractorEmail + "\nContractorAddress: " + contractorAddress + "\nProjectClient: "
                + projectClient + "\nClientNumber:" + clientNumber + "\nClientEmail: "
                + clientEmail + "\nClientAddress: " + clientAddress;

        //Display the added project to the user:
        System.out.println("\n" + project + "\n");
    }
    //=================================================================================================================

    //==================================================================================================================
    public static void projectsPastDueDate() throws FileNotFoundException {

        //Get current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Date Today: " );
        System.out.println(dtf.format(now));
        //If project deadline is before current date print project.
        System.out.println("Projects Past due Date: \n");

        try {
            if(new SimpleDateFormat("yyyy/MM/dd").parse(projectDeadline).before(new Date())){
                 System.out.println(project);
             }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}






