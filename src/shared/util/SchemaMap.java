package shared.util;

import shared.wares.*;

import java.util.HashMap;

public class SchemaMap {
	private static HashMap<Class, String> map;

	public static String Mapping(Class c) {
		if (map == null) {
			map = new HashMap<>();
			map.put(Alcohol.class, "alcoholicBeverage");
			map.put(Drink.class, "nonAlcoholicBeverage");
			map.put(MeatAndFish.class, "meatAndSeafood");
			map.put(FruitsAndVegetable.class, "fruitsAndVegetables");
			map.put(Colonial.class, "colonial");
			map.put(Frozen.class, "frozenFood");
			map.put(CooledAndDairy.class, "dairyAndEggs");
		}
		return map.get(c);
	}
}
