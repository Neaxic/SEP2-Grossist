package core;

public class ViewModelFactory
{static ViewModelFactory instance;

  public static ViewModelFactory getInstance()
  {
    if (instance == null)
    {
      instance = new ViewModelFactory();
    }
    return instance;
  }

  ModelFactory modelFactory;
  private ViewModelFactory()
  {
    modelFactory = ModelFactory.getInstance();
  }
}
