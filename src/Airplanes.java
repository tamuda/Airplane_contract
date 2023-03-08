import java.util.Scanner;

public class Airplanes {
   private String name;
    private double Tow_min, Tow_max, range,speed, hourly_cost, fuel_rate;


    //plane constructor
    public Airplanes(String name, double Tow_min, double Tow_max, double range, double speed, double hourly_cost, double fuel_burn){
        this.name = name;
        this.Tow_min = Tow_min;
        this.Tow_max = Tow_max;
        this.range = range;
        this.speed = speed;
        this.hourly_cost = hourly_cost;
        this.fuel_rate = fuel_burn;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numPlanes = input.nextInt();
        Airplanes[] planes = new Airplanes[numPlanes];
        for(int i = 0; i < numPlanes; i++){
            String name = input.next();
            double Tow_min = input.nextDouble();
            double Tow_max = input.nextDouble();
            double range = input.nextDouble();
            double speed = input.nextDouble();
            double hourly_cost = input.nextDouble();
            double fuel_rate = input.nextDouble();
            planes[i] = new Airplanes(name, Tow_min, Tow_max, range, speed, hourly_cost, fuel_rate);
        }
        //Should we take a contract?
        System.out.println("Enter Contract details");
        //take in contract details until we get quit
        double totalProfit = 0;
        while(true){
            String contract = input.next();
            if (contract.equals("quit")){
                break;
            }
            else {
                double mass = Double.parseDouble(contract);
                double distance = input.nextDouble();
                double payment = input.nextDouble();
                double profit = 0;
                double bestProfit = 0;

                boolean count = false;
                String bestPlane = "";
                //find the plane that can do the job
                for (int i=0; i<numPlanes;i++){
                    double totalMass = mass + planes[i].Tow_min;
                    double time = distance/planes[i].speed;
                    double cost = time*planes[i].hourly_cost;
                    double fuel_burn = time*planes[i].fuel_rate*totalMass;
                    if (totalMass <= planes[i].Tow_max && totalMass >= planes[i].Tow_min && distance <= planes[i].range && cost < payment){
                      profit = payment - (cost+fuel_burn);
                      count = true;
                      if (profit > bestProfit){
                          bestProfit = profit;
                          bestPlane = planes[i].name;
                          totalProfit += bestProfit;
                      }
                    }
                }
                if (count == false){
                    System.out.println("Decline");
                }
                else {
                    System.out.println(bestPlane +" "+ bestProfit);
                }


            }

        }
        System.out.println("Profit: " + totalProfit);


    }


}