package ru.job4j.serialization;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

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

    @JSONPropertyIgnore
    public AutoTestSerial getAuto() {
        return auto;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\''
                + "auto='" + auto + '\''
                + '}';
    }
}
