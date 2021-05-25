package shared.objects;

import java.io.Serializable;

public class CustomerContainer implements Serializable
{
  private final int CVR;
  private final String address, pw, name;

  public CustomerContainer(int CVR, String address, String pw, String name)
  {
    this.CVR = CVR;
    this.address = address;
    this.pw = pw;
    this.name = name;
  }

  public int getCVR()
  {
    return CVR;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPw()
  {
    return pw;
  }

  public String getName()
  {
    return name;
  }
}
