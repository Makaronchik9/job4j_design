package ru.job4j.serialization.json;

import org.json.JSONObject;

public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hello", getHello());
        if (b != null) {
            json.put("bHasA", b.getA() != null);
        }
        return json;
    }
}
