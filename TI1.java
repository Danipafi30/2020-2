import java.util.Scanner;

public class EjerciciosP1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int off = 0;
        do{
            int option = 0;
            System.out.println("MENU \n1. Exercise 1 (part 1)");//Basic Menu with options
            
            option = sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:
                break;
            }

            System.out.println("MENU: 0 \nClose the program: 1 ");
            off = sc.nextInt();
        }while (off == 0);
    }
}