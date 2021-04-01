package server.model;

import shared.wares.Produce;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RISKTEST
{
  public static void main(String[] args)
  {
    Calendar bb1 = new GregorianCalendar(2021, Calendar.APRIL, 8);
    Produce tomato = new Produce(1000, 20, 3, 20, bb1);

  }
}
