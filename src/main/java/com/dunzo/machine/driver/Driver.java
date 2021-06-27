package com.dunzo.machine.driver;

import com.dunzo.machine.model.Beverage;
import com.dunzo.machine.model.BeverageVO;
import com.dunzo.machine.model.Outlet;
import com.dunzo.machine.model.impl.DefaultOutlet;
import com.dunzo.machine.model.impl.Ingredient;
import com.dunzo.machine.service.CoffeMachineImpl;
import com.dunzo.machine.store.Storage;
import com.dunzo.machine.store.impl.IngredientMapStorage;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Driver Program does not given much attention for designing here.
 *  Terminal Based interactive Driver
 *  Read readme file to know how to run.
 * @author shahid
 *
 */
public class Driver {

  public static void main(String[] args) {
    JSONObject input = new JSONObject(new JSONTokener(Driver.class.getClassLoader().getResourceAsStream("input.json")));
    Storage<Ingredient> storage = new IngredientMapStorage(100);
    Outlet outlet = new DefaultOutlet(input.getJSONObject("machine").getJSONObject("outlets").getInt("count_n"));
    CoffeMachineImpl machine = new CoffeMachineImpl(storage, outlet);
    JSONObject totalItems = input.getJSONObject("machine").getJSONObject("total_items_quantity");
    List<Ingredient> initialIngredient = new ArrayList<>();
    totalItems.keySet().stream().forEach(key -> initialIngredient.add(new Ingredient(totalItems.getInt(key), key)));
    machine.refill(initialIngredient);
    JSONObject inputBeverages = input.getJSONObject("machine").getJSONObject("beverages");
    List<BeverageVO> vos = inputBeverages.keySet().stream()
        .map(key -> convertToBeverageVO(inputBeverages.getJSONObject(key), key)).collect(Collectors.toList());
    List<Beverage> beverages = machine.prepareBeverages(vos);
    beverages.stream().forEach(bev -> System.out.println(bev.getDescription()));
    print(machine.showIngedients());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean exit = false;
    while (!exit) {
      try {
        String line = br.readLine();
        String[] str = line.split(" ");
        if (str.length == 0) {
          System.out.println("Invalid Input");
          continue;
        }
        if ("refill".equalsIgnoreCase(str[0])) {
          if (str.length != 3) {
            System.out.println("Refill input format e.g. => Refill hot_water 100");
            continue;
          }
          machine.refill(Arrays.asList(new Ingredient(Integer.parseInt(str[2]), str[1])));
          System.out.println("Added Successfully. Run command \"indicator\" to see the output.");
        } else if ("indicator".equalsIgnoreCase(str[0])) {
          print(machine.showIngedients());
        } else if("exit".equalsIgnoreCase(str[0])) {
          exit = true;
          outlet.shutDown();
        } else if("prepare".equalsIgnoreCase(str[0])) {
          if (str.length < 3) {
            System.out.println("Prepare input format e.g. => Prepare hot_water 100 hot_milk 50 ginger_syrup 10 ...");
            continue;
          }
          System.out.println(machine.prepareBeverages(Arrays.asList(createBeverage(str))).get(0).getDescription());
        }
      } catch (Exception ex) {
        System.out.println("Invalid command");
      }
    }

  }
  
  private static BeverageVO createBeverage(String[] ingredient) {
    List<Ingredient> ing = new ArrayList<>();
    for(int i=2; i<ingredient.length; i+=2) {
      ing.add(new Ingredient(Integer.parseInt(ingredient[i+1]), ingredient[i]));
    }
    BeverageVO b = new BeverageVO();
    b.setName(ingredient[1]);
    b.setIngredientList(ing);
    return b;
  }

  private static void print(List<Ingredient> ingedients) {
    ingedients.stream()
        .forEach(in -> System.out.println("Ingredient : " + in.getName() + " Quantity : " + in.getQuantity()));
  }

  private static BeverageVO convertToBeverageVO(JSONObject obj, String key) {
    BeverageVO vo = new BeverageVO();
    vo.setName(key);
    List<Ingredient> ing = new ArrayList<>();
    obj.keySet().stream().forEach(k -> ing.add(new Ingredient(obj.getInt(k), k)));
    vo.setIngredientList(ing);
    return vo;
  }

}
