package server.model.RISK_ASSESSMENT;

//Line og Frederik

public class RiskReport
{
  private String type;
  private final RiskContainer item;

  public RiskReport(RiskContainer item, char type)
  {
    this.item = item;

    if (type == 'g') this.type = "Green";
    if (type == 'y') this.type = "Yellow";
    if (type == 'r') this.type = "Red";
  }

  public String getType()
  {
    return type;
  }

  public RiskContainer getItem()
  {
    return item;
  }
}
