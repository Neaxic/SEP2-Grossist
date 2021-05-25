import client.core.factories.ClientFactory;
import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.network.GrosserClient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;

import java.sql.SQLException;

public class RiskAssessmentTest
{
  private static GrosserClient grosserClient;
  static GrosserModelInterface grosserModel;

  @BeforeAll //Hapset fra Young
  static void init()
  {
    try
    {
      RMIServerInterface server = new RMIServer();
      assertDoesNotThrow(server::startServer);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    ClientFactory.getInstance().getClient().start();
  }

  @Test void stockJustRight(){
    riskAssess(item, soldDaily)
  }

  @Test void tooMuchStock(){

  }

  @Test void  stockOneTooMuch(){

  }

  @Test void tooLittleStock(){

  }

  @Test void dateSlipping(){

  }
}
