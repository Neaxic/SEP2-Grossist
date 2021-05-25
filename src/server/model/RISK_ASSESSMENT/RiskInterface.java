package server.model.RISK_ASSESSMENT;

import java.util.ArrayList;

public interface RiskInterface
{
  ArrayList<RiskReport> massAssess(RiskContainer container);

}
