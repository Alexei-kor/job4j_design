package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "AutoTestSerial")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutoTestSerial {

    @XmlAttribute
    private String model;
    @XmlAttribute
    private int power;
    @XmlAttribute
    private String color;
    @XmlAttribute
    private int count;
    @XmlElementWrapper(name = "Props")
    @XmlElement(name = "Prop")
    private String[] properties;
    @XmlAttribute
    private int countDoors;
    @XmlElement
    private Owner owner;
    private static final Logger LOG = LoggerFactory.getLogger(AutoTestSerial.class.getName());

    public AutoTestSerial() {

    }

    public AutoTestSerial(String model, int power,
                          String color, int count,
                          String[] properties, int countDoors,
                          Owner owner) {
        this.model = model;
        this.power = power;
        this.color = color;
        this.count = count;
        this.properties = properties;
        this.countDoors = countDoors;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoTestSerial that = (AutoTestSerial) o;
        return power == that.power
                && count == that.count
                && Objects.equals(model, that.model)
                && Arrays.equals(properties, that.properties)
                && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, power, color, count, properties);
    }

    @Override
    public String toString() {
        return "AutoTestSerial{"
                + "Model='" + model + '\''
                + ", power=" + power
                + ", color='" + color + '\''
                + ", count=" + count
                + ", properties=" + Arrays.toString(properties)
                + ", countDoors=" + countDoors
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
        AutoTestSerial toyota = new AutoTestSerial(
                "Toyota",
                200,
                "red",
                50,
                new String[]{"Super", "Puper"},
                4,
                new Owner("Иванов Иван"));
        serialJSON(toyota);
        serialXml(toyota);
    }

    public static void serialJSON(AutoTestSerial auto) {
        final Gson gson = new GsonBuilder().create();
        LOG.debug(gson.toJson(auto));
        final String autoStr = "{model:ToyotaLoad,power:300,color:red,count:30,properties:[Nosuper,Nopuper]}";
        AutoTestSerial autoLoad = gson.fromJson(autoStr, AutoTestSerial.class);
    }

    public static void serialXml(AutoTestSerial auto) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(AutoTestSerial.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(auto, writer);
            xml = writer.getBuffer().toString();
            LOG.debug(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            AutoTestSerial autoLoad = (AutoTestSerial) unmarshaller.unmarshal(reader);
            LOG.debug(String.format("Before serial: %s", auto.toString()));
            LOG.debug(String.format("After serial: %s", autoLoad.toString()));
            LOG.debug(String.format("Objects equal: %b", autoLoad.equals(auto)));
        }
    }
}


