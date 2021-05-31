package server.model.riskassessment;

import java.util.ArrayList;

//Line Guld og Frederik Bergmann

public interface RiskInterface
{
  ArrayList<RiskReport> massAssess(
      ArrayList<RiskContainer> containerList);
  ArrayList<RiskReport> getAllReports();
  ArrayList<RiskReport> getYellowReports();
  ArrayList<RiskReport> getRedReports();
}
