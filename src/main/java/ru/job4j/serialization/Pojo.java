package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pojo {

    public static void main(String[] args) {
        JSONObject jsonAdditional = new JSONObject();
        jsonAdditional.put("attachment", "attachment");
        jsonAdditional.put("bonus", "bonus");

        List<String> owners = new ArrayList<>();
        owners.add("Alex");
        owners.add("Lisa");
        JSONArray jsonOwners = new JSONArray(owners);

        final Car car = new Car(true, 11, new Additional("attachment", "bonus"), new String[]{"Alex", "Lisa"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("old", car.isOld());
        jsonObject.put("age", car.getAge());
        jsonObject.put("additional", jsonAdditional);
        jsonObject.put("owners", jsonOwners);

        System.out.println("Manual JSON:");
        System.out.println(jsonObject.toString(2));

        System.out.println("\nJSONObject from Car:");
        System.out.println(new JSONObject(car).toString(2));
    }
}
