package client.customerclient.views;

import client.core.ViewHandler;

import java.io.IOException;
import java.sql.SQLException;

// Andreas Young, Line Guld

public interface CustomerViewController {
  void init(ViewHandler viewHandler) throws SQLException;
  void swapScene(String sceneName) throws IOException, SQLException;
}