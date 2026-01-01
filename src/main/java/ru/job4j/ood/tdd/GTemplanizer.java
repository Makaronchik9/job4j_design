package ru.job4j.ood.tdd;

import java.util.Map;

public class GTemplanizer implements Generator {

    @Override
    public String produce(String template, Map<String, String> args) throws IllegalArgumentException {
        for (Map.Entry<String, String> entry: args.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String templateVariable = "${" + key + "}";
            if (!template.contains(templateVariable)) {
                throw new IllegalArgumentException("Template does not contain key: " + key);
            }
            template = template.replace(templateVariable, value);
        }
        return template;
    }
}