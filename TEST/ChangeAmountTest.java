
import client.core.factories.ClientFactory;
import client.core.factories.ModelFactory;
import client.customerclient.views.customerbrowser.CustomerBrowserViewModel;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.grosseraddproduct.GrosserAddProductViewModel;
import client.grosserclient.views.grosserwares.GrosserWaresViewModel;
import client.network.GrosserClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//Kasper Falk Mikkelsen



public class ChangeAmountTest {

    private static GrosserWaresViewModel viewModel;
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


    @BeforeEach public void setup()
    {
        viewModel = new GrosserWaresViewModel();
        grosserModel = ModelFactory.getInstance().getGrosserModel();
        viewModel.updateWareList();

    }


    @Test
    void changeAmount()  { //Når nedenstående køres, virker det ikke første gang det køres... Køres det så 2 gange virker det fint
        //Upper boundry
        viewModel.changeAmount(viewModel.getListForView().get(0),1);
        viewModel.updateWareList();
        assertEquals(1,viewModel.getListForView().get(0).getAmount());

        //Center boundry
        viewModel.changeAmount(viewModel.getListForView().get(0),0);
        viewModel.updateWareList();
        assertEquals(0,viewModel.getListForView().get(0).getAmount());

        //Lower boundry
        viewModel.changeAmount(viewModel.getListForView().get(0),-1);
        viewModel.updateWareList();
        assertEquals(0,viewModel.getListForView().get(0).getAmount()); //This should just ignore the change
    }
}

