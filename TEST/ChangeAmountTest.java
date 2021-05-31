import client.core.factories.ClientFactory;
import client.core.factories.ModelFactory;
import client.grosserclient.model.GrosserModelInterface;
import client.grosserclient.views.grosserwares.GrosserWaresViewModel;
import client.network.GrosserClient;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.RMIServer;
import shared.network.RMIServerInterface;
import shared.wares.Alcohol;
import shared.wares.Drink;
import shared.wares.FruitsAndVegetable;
import shared.wares.Product;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Kasper Falk Mikkelsen



public class ChangeAmountTest {

    private static GrosserWaresViewModel viewModel;
    static GrosserModelInterface grosserModel;
    static GrosserClient grosser;
    static Product testProduct1, testProduct2, testProduct3;

    @BeforeAll
    static void init() throws SQLException
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
        grosser = ClientFactory.getInstance().getGrosserClient();
        testProduct1 = new Alcohol("Royal Export", "Liter", LocalDate.now().plusMonths(10), 2001, 10, 4.5, "Royal Unibrew", "", "Danmark", 4.6, "Øl");
        testProduct2 = new Drink("Jolly Cola Sukkerfri", "Liter", LocalDate.now().plusMonths(3), 2002, 20, 5.95, "Jolly", "Sukkerfri", "Sodavand");
        testProduct3 = new FruitsAndVegetable("Banan", "Kg", LocalDate.now().plusDays(7), 2003, 1, 2.3, "Palmer", "GMO", "Spanien");
        grosser.createProduct(new Pair<>(testProduct1, 100));
        grosser.createProduct(new Pair<>(testProduct2, 100));
        grosser.createProduct(new Pair<>(testProduct3, 100));
    }


    @BeforeEach public void setup()
    {
        viewModel = new GrosserWaresViewModel();
        grosserModel = ModelFactory.getInstance().getGrosserModel();
        viewModel.updateWareList();

    }

    @AfterAll
    static void adminCleanUp() {
        GrosserClient admin = ClientFactory.getInstance().getGrosserClient();
        admin.deleteWare(testProduct1);
        admin.deleteWare(testProduct2);
        admin.deleteWare(testProduct3);
    }


    @Test
    void changeAmount() {
        //Upper boundry
        viewModel.reduceStock(viewModel.getListForView().get(0),99);
        System.out.println(viewModel.getListForView());
        viewModel.updateWareList();
        assertEquals(1,viewModel.getListForView().get(0).getAmount());

        //Center boundry
        viewModel.reduceStock(viewModel.getListForView().get(0),1);
        viewModel.updateWareList();
        assertEquals(0,viewModel.getListForView().get(0).getAmount());

        //Lower boundry
        viewModel.reduceStock(viewModel.getListForView().get(0),1);
        viewModel.updateWareList();
        assertEquals(0,viewModel.getListForView().get(0).getAmount());
    }
}

