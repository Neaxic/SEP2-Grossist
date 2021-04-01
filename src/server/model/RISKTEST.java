package server.model;

import shared.wares.Produce;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RISKTEST
{
  public static void main(String[] args)
  {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate bb1 = LocalDate.parse("08/04/2021", dtf);
    Produce tomato = new Produce(2000, 200, 5, 20, bb1);

    RiskReport riskReport = RISKASSESSPROTO.riskAssess(tomato);
    System.out.println(riskReport.getSeverity() + ": " + riskReport.getMessage());
  }
}
