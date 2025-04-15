package com.project.ES;
import java.util.Scanner;

public class ES {
    public String entradaString(){
        boolean continuar = false;
        Scanner input = new Scanner(System.in);
        String retornoStr = "";

        while (!continuar){
            System.out.print(">");
            if (input.hasNextLine()){
                retornoStr = input.nextLine();
                continuar = true;
            }
            else
                System.out.println("ERRO: Digite uma String");
        }
        input.close();
        return retornoStr;
    }

    public int entradaInt(){
        boolean continuar = false;
        Scanner input = new Scanner(System.in);
        int retornoInt = -1;

        while (!continuar){
            System.out.print(">");
            if (input.hasNextInt()){
                retornoInt = input.nextInt();
                continuar = true;
            }
            else
                System.out.println("ERRO: Digite um Int");
        }
        input.close();
        return retornoInt;
    }

    public int entradaInt(int min, int max){
        boolean continuar = false;
        Scanner input = new Scanner(System.in);
        int retornoInt = -1;

        while (!continuar){
            System.out.print(">");
            if (input.hasNextInt()){
                retornoInt = input.nextInt();
                if (retornoInt >= min && retornoInt <= max){
                    continuar = true;
                }
                else
                    System.out.printf("ERRO: digite um valor entre %d e %d", min, max);
            }
            else
                System.out.println("ERRO: Digite um Int");
        }
        input.close();
        return retornoInt;
    }

    public float entradaFloat(){
        boolean continuar = false;
        Scanner input = new Scanner(System.in);
        float retornoStr = -1;

        while (!continuar){
            System.out.print(">");
            if (input.hasNextFloat()){
                retornoStr = input.nextFloat();
                continuar = true;
            }
            else
                System.out.println("ERRO: Digite um Float");
        }
        input.close();
        return retornoStr;
    }
}
