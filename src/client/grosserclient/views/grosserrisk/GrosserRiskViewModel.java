package client.grosserclient.views.grosserrisk;

import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.GrosserViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import server.model.riskassessment.RiskReport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

// Frederik Bergmann

public class GrosserRiskViewModel implements GrosserViewModel,
    PropertyChangeListener
{
  private final ListProperty<RiskReport> riskReports;
  private final GrosserModelInterface grosserModel;

  public GrosserRiskViewModel()
  {
    riskReports = new SimpleListProperty<>();
    grosserModel = ModelFactory.getInstance().getGrosserModel();
    grosserModel.addListener(this);
  }

  private void updateRiskReports(ArrayList<RiskReport> reports)
  {
    riskReports.set(FXCollections.observableList(reports));
  }

  public void getRiskData()
  {
    grosserModel.getRiskData();
  }

  public ListProperty<RiskReport> getRiskReportsProperty()
  {
    return riskReports;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("riskReports"))
      updateRiskReports((ArrayList<RiskReport>) evt.getNewValue());
  }
}
