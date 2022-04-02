package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Owner")
public class Owner {
    @XmlAttribute
    private String name;

    public Owner() {

    }

    public Owner(String name) {
        this.name = name;
    }

}
