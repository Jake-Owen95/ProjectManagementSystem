package com.company;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static com.company.Finalize.projectInvoice;
import static com.company.Individual.*;
import static com.company.Project.*;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        //DataBase Connection:
        String host = "jdbc:mysql://localhost:3306/poised";
        String user = "root";
        String password = "jordynutjob20";

        Connection conn = DriverManager.getConnection(host, user, password);
        Statement myState = conn.createStatement();

        //Scans for user input:
        Scanner input = new Scanner(System.in);

        //Individual Array to store individuals.
        Individual[] allIndividuals = new Individual[10];
        //Set individual index.
        int individualIndex = 0;

        //Main Menu Navigation.
        int menuNav = 0;

        //=================================================Menu Loop====================================================

        //While the user does not choose to exit, keep bringing the main menu back:
        while (menuNav != 7) {
            new Menu();
            menuNav = input.nextInt();
            //=========================================(1) Add Individual ==============================================

            //If the user chooses menu option 1(AddIndividual):
            if (menuNav == 1) {
                //Call addPerson() method.
                addPerson();

                //Create new individual.
                Individual newIndividual = Individual.printIndividualMenu();

                //Individual Index to increment 1 for every new person
                allIndividuals[individualIndex] = newIndividual;
                individualIndex++;

                //Add members to text file 'PoisedMembers.txt'.
                try{
                    myState.executeUpdate("insert into members  values('"+individualIndex+"', '"+individualName+"', '"+individualContact+"', '"+individualAddress+"')");
                } catch (SQLException e) {
                        System.out.println("PoisedMembers.txt not found!");
                        e.printStackTrace();
                }
            }
            //==========================================(2) Add Project ================================================
            //If user chooses menu option 2(AddProject):
            if (menuNav == 2) {

                //Call addProject() method.
                Project.addProject();
                String newProject = project;
                Project.projects[projectIndex] = newProject;
                projectIndex++;

                try {
                    myState.executeUpdate("insert into projects value('" + projectNumber + "', '" + projectName + "', '" + projectAddress + "', '" + projectErfNumber + "', '" + projectFee + "', '" + feePaid + "', '" + projectDeadline + "', '" + projectContractor + "', '" + projectArchitect + "', '" + projectClient + "')");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
                //===========================================(3) Edit Project ==========================================
            //If user chooses menu option 3(EditProject):

            if (menuNav == 3) {

                //Reading from database
                String sql = "select * from projects";
                ResultSet rs = myState.executeQuery(sql);
                //gets the first column's rows.
                System.out.println("Type the number of the project you'd like to edit: ");
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }

                int menuNav2 = input.nextInt();
                //Call editProject method().
                //allow user to edit project
                System.out.println("\nNew Project Name: " + input.nextLine());
                String newProjectName = input.nextLine();
                System.out.println("\nNew Project Address: ");
                String newpProjectAddress = input.nextLine();
                System.out.println("\nNew Project Erf Number:");
                String newProjectErfNumber = input.nextLine();
                System.out.println("\nNew Total Cost: ");
                Float newProjectFee = input.nextFloat();
                System.out.println("\nNew Already paid: ");
                Float newFeePaid = input.nextFloat();
                System.out.println("\nProject Deadline: input in this format - yyyy/MM/dd" + input.nextLine());
                String newProjectDeadline = input.nextLine();
                System.out.println("\nProject Architect: ");
                String newProjectArchitect = input.nextLine();
                System.out.println("\nProject Contractor: ");
                String newProjectContractor = input.nextLine();
                System.out.println("\nProject Client: ");
                String newProjectClient = input.nextLine();

                //editProject();
                //Edit database using new information.
                myState.executeUpdate("UPDATE projects SET projectName"+newProjectName+" where projectNumber="+menuNav2+"");
            }
            //If user chooses menu option 4(FinalizeProject):

            if(menuNav == 4) {
                //Call projectInvoice method().
                projectInvoice();
                //Ask user which project they would like to Finalize.
                System.out.println("Enter the number of the project you'd like to finalize.");
                int menuNav2 = input.nextInt();
                //myState.executeUpdate("UPDATE projects SET projectName"+newProjectName+" where projectNumber="+menuNav2+"");
                }
            }
            //========================================(5) Projects Past Due Date========================================
            //If user chooses menu option 5(ProjectsPastDueDate):

            if (menuNav == 5) {
                //Call projectsPastDueDate() method.
                Project.projectsPastDueDate();
            }
            //=============================================== Quit ====================================================
            //If user chooses menu option 7(Quit)
            if (menuNav == 7) {
                System.out.println("You have now quit.");
                input.close();
        }
    }
}


