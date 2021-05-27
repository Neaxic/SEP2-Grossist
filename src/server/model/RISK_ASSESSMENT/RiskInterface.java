package server.model.RISK_ASSESSMENT;

import java.util.ArrayList;

//Line og Frederik

public interface RiskInterface
{
  ArrayList<RiskReport> massAssess(
      ArrayList<RiskContainer> containerList);
  ArrayList<RiskReport> getAllReports();
  ArrayList<RiskReport> getYellowReports();
  ArrayList<RiskReport> getRedReports();
}
