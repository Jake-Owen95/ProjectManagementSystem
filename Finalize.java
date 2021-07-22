package com.company;

import java.io.*;
import java.util.Scanner;

public class Finalize {
    public static void projectInvoice() throws IOException {
       // Scanner input = new Scanner(System.in);
        System.out.println("\nCURRENT PROJECTS:");
        //Read through File
        File projectsFile = new File("PoisedProjects.txt");
        Scanner scan = new Scanner(projectsFile);
        BufferedReader br = new BufferedReader(new FileReader("PoisedProjects.txt"));
            String project;
            while((project = String.valueOf(br.read()))!=null){
                System.out.println(scan.nextLine());
                System.out.println(project);
            }
    }
}
