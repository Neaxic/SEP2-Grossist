package server.model;

import shared.wares.Products;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RISKTEST
{
  public static void main(String[] args)
  {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate bb1 = LocalDate.parse("08/04/2021", dtf);
    LocalDate bb2 = LocalDate.parse("16/04/2021", dtf);
    LocalDate bb3 = LocalDate.parse("01/05/2021", dtf);
    LocalDate bb4 = LocalDate.parse("26/11/2021", dtf);
    Products tomato = new Products(2000, 200, 5, 20, bb1);
    Products tinOfBeans = new Products(20, 5, 10, 0, bb4);
    Products milk = new Products(5000, 1000, 2, 20, bb2);
    Products cheese = new Products(4000, 300, 3, 20, bb3);

    ArrayList<Products> items = new ArrayList<>();
    items.add(tomato);
    items.add(tinOfBeans);
    items.add(milk);
    items.add(cheese);

    RiskReport riskReport = RISKASSESSPROTO.riskAssess(tomato);
    System.out.println("Single Test: " + riskReport.getSeverity() + ": " + riskReport.getMessage());

    ArrayList<RiskReport> reports = RISKASSESSPROTO.massAssess(items);

    for (RiskReport massReport : reports)
    {
      System.out.println("Mass Test: " + massReport.getSeverity() + ": " + massReport.getMessage());
    }
  }
}
