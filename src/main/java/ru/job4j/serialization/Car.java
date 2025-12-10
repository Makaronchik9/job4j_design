package ru.job4j.serialization;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean old;

    @XmlAttribute
    private int age;

    @XmlElement
    private Additional additional;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private String[] owners;

    public Car() {
    }

    public Car(boolean old, int age, Additional additional, String... owners) {
        this.old = old;
        this.age = age;
        this.additional = additional;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "old=" + old
                + ", age=" + age
                + ", additional=" + additional
                + ", owners=" + String.join(", ", owners)
                + '}';
    }
}
