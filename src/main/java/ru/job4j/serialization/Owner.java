package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Owner")
public class Owner {
    @XmlAttribute
    private String name;
    private AutoTestSerial auto;

    public Owner() {

    }

    public Owner(String name) {
        this.name = name;
    }

    public Owner(String name, AutoTestSerial auto) {
        this.name = name;
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\''
                + "auto='" + auto + '\''
                + '}';
    }
}
