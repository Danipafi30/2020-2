import java.util.Scanner;
import java.util.Arrays;

public class TI1{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){  
        int off = 0;
        System.out.println("Enter the amount of materials u need to buy");
        int MATERIALS = sc.nextInt(); // Amount of materials
        String[] materials = new String[MATERIALS]; // Names of the materials
        int[] amount = new int[MATERIALS]; // Amount of each material
        int[] usage = new int[MATERIALS]; // The usage of the materials
        int[] priceHome = new int[MATERIALS]; // Prices for Homecenter
        int[] priceCenter = new int[MATERIALS]; // Prices for Ferreteria del centro
        int[] priceNeighbor = new int[MATERIALS]; // Prices for Ferreteria del barrio
        int counterBlackW = 0; // Size of black work list
        String[] blackWork = new String[counterBlackW]; // Black work materials
        int totalHome = 0; // Total price of Homecenter
        int totalCenter = 0; // Total price of Ferreteria del centro
        int totalNeighbor = 0; // Total price of Ferreteria del barrio
        int totalBestProduct = 0; // Total price for the best product selection
        boolean extraBlackw = false; // Black work add
        boolean extraWhitew = false; // Withe work add
        boolean extraPaint = false; // Paint work add
        String msg = ""; // Simple messages 
        int geo = 0; // Location of the property
        int extra = 0; // Extra price for hand work
        int extraGeo = 0; // Domicile of the materials
        do{
            System.out.println("MENU \n1. Enter the products"); //Basic Menu with options
            System.out.println("2. Homecenter");
            System.out.println("3. Ferreteria del centro");
            System.out.println("4. Ferreteria del barrio");
            System.out.println("5. Best product");
            System.out.println("6. Black work products");
            off = sc.nextInt();
            sc.nextLine();
            switch(off){
                case 1:
                msg = createProducts(MATERIALS, materials, amount, usage);
                System.out.println(msg);
                extra = extraPrice(MATERIALS, usage);
                System.out.println("Enter the location of ur property\n1. South\n2. Center\n3. North");
                geo = sc.nextInt();
                sc.nextLine();
                break;
                case 2:
                totalHome = totalPriceHome(MATERIALS, priceHome, materials, amount);
                extraGeo = domicile(totalHome, totalCenter, totalNeighbor, geo);
                System.out.println("The total to pay for Homecenter is $"+(totalHome+extraGeo)+" and an workforce of $"+extra+", generating a total of $"+((totalHome+extraGeo)+extra));
                break;
                case 3:
                totalCenter = totalPriceCenter(MATERIALS, priceCenter, materials, amount);
                extraGeo = domicile(totalHome, totalCenter, totalNeighbor, geo);
                System.out.println("The total to pay for Ferreteria del centro is $"+(totalCenter+extraGeo)+ " and an workforce of $"+extra+", generating a total of $"+((totalCenter+extraGeo)+extra));
                break;
                case 4:
                totalNeighbor = totalPriceNeighbor(MATERIALS, priceNeighbor, materials, amount);
                extraGeo = domicile(totalHome, totalCenter, totalNeighbor, geo);
                System.out.println("The total to pay for Ferreteria del barrio is $"+(totalNeighbor+extraGeo)+ " and an workforce of $"+extra+", generating a total of $"+((totalNeighbor+extraGeo)+extra));
                break;
                case 5:
                totalBestProduct = bestPlaceProduct(materials, priceHome, priceCenter, priceNeighbor);
                System.out.println("The price to pay for all this products is $"+totalBestProduct);
                break;
                case 6:
                blackWork = blackWorkMaterials(materials, usage, counterBlackW);
                System.out.println("The materials chosen for black work list are:");
                System.out.println(Arrays.toString(blackWork));
                break;
            }

            System.out.println("MENU: 0 \nClose the program: 1 ");
            off = sc.nextInt();
        }while (off == 0);
    }

    /**
	* Generate the materials asking for the name, the amount and the usage <br>
	* <b> pre: </b> The amount of materials
	* <b> pos: </b> The 3 arrays will be filled
	* @param MATERIALS int amount of materials
    * @param materials String[] for the names of the materials
    * @param amount int[] for the amount of a material
    * @param usage int[] for the usage of the materials
	* @return msg, a message saying that the products were entered correctly
	*/
    public static String createProducts(int MATERIALS, String[] materials, int[] amount, int[] usage){
        String msg = "The products were entered correctly";
        System.out.println("Enter the product");
        for(int i=0; i<MATERIALS; i++){
            System.out.println("Name");
            materials[i] = sc.nextLine();
            System.out.println("Amount");
            amount[i] = sc.nextInt();
            sc.nextLine();
            System.out.println("Usage \n1. Black work \n2. White work \n3. Paint ");
            usage[i] = sc.nextInt();
            sc.nextLine();
        }
        return msg;
    }

    /**
	* Calculates if there is an extra price for handwork <br>
	* <b> pre: </b> The amount of materials and the usage
	* <b> pos: </b> Add an extra price if there is a handwork
	* @param MATERIALS int amount of materials
    * @param usage int[] for the usage of the materials
	* @return extra, the total extra price
	*/
    public static int extraPrice(int MATERIALS, int[] usage){
        int extra = 0;
        boolean extraBlackw = false;
        boolean extraWhitew = false;
        boolean extraPaint = false;
        for(int i=0; i<MATERIALS; i++){
            if(usage[i] == 1){
                extraBlackw = true;
            }
            if(usage[i] == 2){
                extraWhitew = true;
            }
            if(usage[i] == 3){
                extraPaint = true;
            }
        }
        if(extraBlackw){
            extra += 1300000;
        }
        if(extraWhitew){
            extra += 2600000;
        }
        if(extraPaint){
            extra += 980000;
        }
        return extra;
    }

    /**
	* Calculates the total price for Homecenter <br>
	* <b> pre: </b> The amount and name of materials, the amount of each material and the prices
	* <b> pos: </b> Save the total price for the ironmongery
	* @param MATERIALS int amount of materials
    * @param priceHome int[] prices of the products 
    * @param materials String[] for the names of the materials
    * @param amount int[] for the amount of a material
	* @return total, the total price of Homecenter
	*/
    public static int totalPriceHome(int MATERIALS, int[] priceHome, String[] materials, int[] amount){
        int total = 0;
        System.out.println("HOMECENTER\nEnter the prices of the products\n");
        for(int i=0; i<MATERIALS; i++){
            System.out.println("Price for "+materials[i]);
            priceHome[i] = sc.nextInt() * amount[i];
            total += priceHome[i];
        }
        return total;
    }

    /**
	* Calculates the total price for Ferreteria del centro <br>
	* <b> pre: </b> The amount and name of materials, the amount of each material and the prices
	* <b> pos: </b> Save the total price for the ironmongery
	* @param MATERIALS int amount of materials
    * @param priceHome int[] prices of the products 
    * @param materials String[] for the names of the materials
    * @param amount int[] for the amount of a material
	* @return total, the total price of Ferreteria del centro
	*/
    public static int totalPriceCenter(int MATERIALS, int[] priceCenter, String[] materials, int[] amount){
        int total = 0;
        System.out.println("FERRETERIA DEL CENTRO\nEnter the prices of the products\n");
        for(int i=0; i<MATERIALS; i++){
            System.out.println("Price for "+materials[i]);
            priceCenter[i] = sc.nextInt() * amount[i];
            total += priceCenter[i];
        }
        return total;
    }

    /**
	* Calculates the total price for Ferreteria del barrio <br>
	* <b> pre: </b> The amount and name of materials, the amount of each material and the prices
	* <b> pos: </b> Save the total price for the ironmongery
	* @param MATERIALS int amount of materials
    * @param priceHome int[] prices of the products 
    * @param materials String[] for the names of the materials
    * @param amount int[] for the amount of a material
	* @return total, the total price of Ferreteria del barrio
	*/
    public static int totalPriceNeighbor(int MATERIALS, int[] priceNeighbor, String[] materials, int[] amount){
        int total = 0;
        System.out.println("FERRETERIA DEL BARRIO\nEnter the prices of the products\n");
        for(int i=0; i<MATERIALS; i++){
            System.out.println("Price for "+materials[i]);
            priceNeighbor[i] = sc.nextInt() * amount[i];
            total += priceNeighbor[i];
        }
        return total;
    }

    /**
	* Calculates the price of the domicile if there is one <br>
	* <b> pre: </b> The total prices of each ironmongery and the location of the property
	* <b> pos: </b> Add the price of the domicile if there is one
    * @param totalHome int total price for Homecenter
    * @param totalCenter int total price for Ferreteria del Centro
    * @param totalNeighbor int total price for Ferreteria del Barrio
    * @param geo int the number of the ubication of the property
	* @return extra, the price of the domicile
	*/
    public static int domicile(int totalHome, int totalCenter, int totalNeighbor, int geo){
        int extra = 0;
        if(totalHome < 80000){
            if(geo == 1){
                extra = 120000;
            }else if(geo == 2){
                extra = 50000;
            }else if(geo == 3){
                extra = 120000;
            }
        }else if(totalHome < 300000){
            if(geo == 1){
                extra = 55000;
            }else if(geo == 3){
                extra = 28000;
            }
        }
        if(totalCenter < 80000){
            if(geo == 1){
                extra = 120000;
            }else if(geo == 2){
                extra = 50000;
            }else if(geo == 3){
                extra = 120000;
            }
        }else if(totalCenter < 300000){
            if(geo == 1){
                extra = 55000;
            }else if(geo == 3){
                extra = 28000;
            }
        }
        if(totalNeighbor < 80000){
            if(geo == 1){
                extra = 120000;
            }else if(geo == 2){
                extra = 50000;
            }else if(geo == 3){
                extra = 120000;
            }
        }else if(totalNeighbor < 300000){
            if(geo == 1){
                extra = 55000;
            }else if(geo == 3){
                extra = 28000;
            }
        }
        return extra;
    }

    /**
	* Calculates the lowest price for each material <br>
	* <b> pre: </b> The names and the prices for each ironmongery of the materials
	* <b> pos: </b> Save the best option for each material
    * @param materials int amount of materials
    * @param priceHome int total price for Homecenter
    * @param priceHome int total price for Ferreteria del Centro
    * @param priceNeighbor int total price for Ferreteria del Barrio
	* @return total, the sum of the best prices for each product
	*/
    public static int bestPlaceProduct(String[] materials, int[] priceHome, int[] priceCenter, int[] priceNeighbor){
        int total = 0;
        for(int i=0; i<materials.length; i++){
            if(priceHome[i] < priceCenter[i] && priceHome[i] < priceNeighbor[i]){
                System.out.println("The best place to buy "+materials[i]+" is in Homecenter, the price is $"+priceHome[i]);
                total += priceHome[i];
            }else if(priceCenter[i] < priceHome[i] && priceCenter[i] < priceNeighbor[i]){
                System.out.println("The best place to buy "+materials[i]+" is in Ferreteria del centro, the price is $"+priceCenter[i]);
                total += priceCenter[i];
            }else{
                System.out.println("The best place to buy "+materials[i]+" is in Ferreteria del barrio, the price is $"+priceNeighbor[i]);
                total += priceNeighbor[i];
            }
        }
        return total;
    }

    /**
	* Generate a new array with the black work materials chosen by the user <br>
	* <b> pre: </b> The name of materials and the usage
	* <b> pos: </b> First select the black work materials, then select the chosen by the user
	* @param materials String[] for the names of the materials
    * @param usage int[] for the usage of the materials
	* @return newBlackWork, the materials chosen 
	*/
    public static String[] blackWorkMaterials(String[] materials, int[] usage, int counterBlackW){
        int counter = 0;
        int newCounter = 0;
        int choose = 0;
        
        for(int i=0; i<materials.length; i++){
            if(usage[i] == 1){
                counter++;
            }
        }
        String[] blackWork = new String[counter];
        counterBlackW = counter;
        int aux = 0;
        for(int i=0; i<materials.length; i++){
            if(usage[i] == 1){
               blackWork[aux] = materials[i];
               aux++;
            }
        }
        int[] chosen = new int[counter];
        System.out.println("Choose\n1. Choose\n2. Delete");
        for(int i=0; i<blackWork.length; i++){
            System.out.println("Do u want the "+blackWork[i]);
            chosen[i] = sc.nextInt();
            sc.nextLine();
            if(chosen[i] == 1){
                newCounter++;
            }
        }
        String[] newBlackWork = new String[newCounter];
        int temp = 0;
        for(int i=0; i<blackWork.length; i++){  
            if(chosen[i] == 1){
                newBlackWork[temp] = blackWork[i];
                temp++;
            }
        }
        return newBlackWork;
    }
}
