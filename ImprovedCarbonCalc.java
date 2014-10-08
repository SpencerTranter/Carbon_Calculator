import java.util.*;

public class ImprovedCarbonCalc {
  public static void main (String[] args) {
  Scanner console = new Scanner(System.in);

    writeBlank(1);
    System.out.println("It is time to calculate your carbon footprint! Please answer the following questions below.");
    writeBlank(1);
    System.out.print("Kilometers driven per day : ");
    double kmPerDay = console.nextDouble();
    writeBlank(1);
    System.out.print("Fuel efficiency of car (km/litre) : ");
    double fuelEfficiency = console.nextDouble();
    writeBlank(1);
    double kgCO2YearlyEmission = determineTransportationEmission(kmPerDay, fuelEfficiency);

    writeBlank(2);
    System.out.print("Kilowatt hours per month (usually found on electrical bill) : ");
    double kWhPerMonth = console.nextDouble();
    writeBlank(1);
    System.out.print("Number of people living in the household : ");
    double numPeopleInHome = console.nextDouble();
    writeBlank(1);
    double kgCO2YearlyElec = determineElectricityEmission(kWhPerMonth, numPeopleInHome);

    writeBlank(2);
    System.out.print("Home heating from oil per month in litres (if applicable) : ");
    double litresPerMonth = console.nextDouble();
    writeBlank(1);
    double kgCO2YearlyHeating = determineHeatingEmission(litresPerMonth);

    writeBlank(2);
    System.out.print("Percentage of yearly diet based around meat and fish (out of 100) : ");
    double meatEaten = console.nextDouble();
    writeBlank(1);
    System.out.print("Percentage of yearly diet based around dairy products (out of 100) : ");
    double dairyEaten = console.nextDouble();
    writeBlank(1);
    System.out.print("Percentage of yearly diet based around fruits and vegetables (out of 100) : ");
    double fruitEaten = console.nextDouble();
    writeBlank(1);
    System.out.print("Percentage of yearly diet based around carbohydrates (out of 100) : ");
    double carbsEaten = console.nextDouble();
    writeBlank(1);
    double kgCO2YearlyFood = determineFoodEmission(meatEaten, dairyEaten, fruitEaten, carbsEaten);

    writeBlank(2);
    System.out.print("Km of air travel per year : ");
    double airTraveled = console.nextDouble();
    writeBlank(1);
    double kgCO2YearlyAir = determineAirEmission(airTraveled);

    double totalCarbonFootprint = calculateTotalEmission(kgCO2YearlyEmission, kgCO2YearlyElec, kgCO2YearlyHeating, kgCO2YearlyFood, kgCO2YearlyAir);

    writeBlank(2);
    printReport(kgCO2YearlyEmission, kgCO2YearlyElec, kgCO2YearlyHeating, kgCO2YearlyFood, kgCO2YearlyAir, totalCarbonFootprint);
  }

  public static void writeBlank(int number) {
    for (int i = 1; i <= number; i++) {
      System.out.println();
    }
  }

  public static double determineTransportationEmission(Double kmPerDay, Double fuelEfficiency) {
    double litresPerYear = (365 * (kmPerDay / fuelEfficiency));
    double kgCO2YearlyEmission = (litresPerYear * 2.3);

    System.out.println("Litres used per year (in kg) = " + kgCO2YearlyEmission);

    return kgCO2YearlyEmission;
  }

  public static double determineElectricityEmission(Double kWhPerMonth, Double numPeopleInHome) {
    double kgCO2YearlyElec = ((kWhPerMonth * 12 * 0.257) / numPeopleInHome);

    System.out.println("Yearly CO2 emmissions from electricity (in kg) = " + kgCO2YearlyElec);

    return kgCO2YearlyElec;
  }

  public static double determineHeatingEmission(Double litresPerMonth) {
    double kgCO2YearlyHeating = ((litresPerMonth * 2.6) * 12 );

    System.out.println ("Yearly CO2 emmissions from oil heating (in kg) = " + kgCO2YearlyHeating);

    return kgCO2YearlyHeating;
  }

  public static double determineFoodEmission(Double meatEaten, Double dairyEaten, Double fruitEaten, Double carbsEaten) {
    double kgCO2Meat = (meatEaten * 53.1);
    double kgCO2Dairy = (dairyEaten * 13.8);
    double kgCO2Fruit = (fruitEaten * 7.6);
    double kgCO2Carbs = (carbsEaten * 3.1);
    double kgCO2YearlyFood = (kgCO2Meat + kgCO2Dairy + kgCO2Fruit + kgCO2Carbs);

    System.out.println("Yearly CO2 emissions from food (in kg) = " + kgCO2YearlyFood);

    return kgCO2YearlyFood;
  }

  public static double determineAirEmission(Double airTraveled) {
    double kgCO2YearlyAir = (airTraveled / 5);

    System.out.println("Yearly CO2 emission from air travel (in kg) = " + kgCO2YearlyAir);

    return kgCO2YearlyAir;
  }

  public static double calculateTotalEmission(double kgCO2YearlyEmission, double kgCO2YearlyElec, double kgCO2YearlyHeating, double kgCO2YearlyFood, double kgCO2YearlyAir) {
    double totalCarbonFootprint = ((kgCO2YearlyEmission + kgCO2YearlyElec + kgCO2YearlyHeating + kgCO2YearlyFood + kgCO2YearlyAir) / 1000);

    return totalCarbonFootprint;
  }

  public static void printReport(double kgCO2YearlyEmission, double kgCO2YearlyElec, double kgCO2YearlyHeating, double kgCO2YearlyFood, double kgCO2YearlyAir, double totalCarbonFootprint) {
    double carPercent = (((kgCO2YearlyEmission / 1000) / totalCarbonFootprint) * 100);
    double elecPercent = (((kgCO2YearlyElec / 1000) / totalCarbonFootprint) * 100);
    double oilPercent = (((kgCO2YearlyHeating / 1000) / totalCarbonFootprint) * 100);
    double foodPercent = (((kgCO2YearlyFood / 1000) / totalCarbonFootprint) * 100);
    double airPercent = (((kgCO2YearlyAir / 1000) / totalCarbonFootprint) * 100);
    double averagePerPerson = ((totalCarbonFootprint / 9) * 100);

    System.out.println("You produce an annual total of " + totalCarbonFootprint + " metric tons of CO2 per year.");
    System.out.println("The breakdown is as follows:");
    System.out.println("\t Car:         "  + carPercent + " %");
    System.out.println("\t Electricity: "  + elecPercent + " %");
    System.out.println("\t Heating (oil):" + oilPercent + " %");
    System.out.println("\t Food:        "  + foodPercent + " %");
    System.out.println("\t air:         "  + airPercent + " %");
    System.out.println();
    System.out.println("The average Canadian in 2004 was pesprnosible for 9 metric tons of CO2 per year.");
    System.out.println("This means your annual CO2 output is equal to " + averagePerPerson + "% of the average Canadians.");
    System.out.println();
  }
}
/*
*ID and name:V00803225 Spencer Tranter
*Program Name:CarbonCalc.java
*Program Description:This program takes inputs from the user abput their lives inorder to estimate their
 carbon footprint for the year
*Outputs:Prints out the total CO2 emission for each field, it then gives a total amount  all toageather,
 then it shows a breakdown of the total in percent, and fianlly it compares the users total yearly CO2
 emission to the average canadians.
*Inputs:Uses a Scanner Object's to take inputs from the users life, in this case food, electricity,
 air travel, land transportaion, and household heating (oil) information.
*/
