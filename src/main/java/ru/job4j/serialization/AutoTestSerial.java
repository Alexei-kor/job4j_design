package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

public class AutoTestSerial implements Serializable {

    private static final long serialVersionUID = new Random(5454545121231L).nextLong();
    private String model;
    private int power;
    private String color;
    private int count;
    private String[] properties;
    transient private int countDoors;
    private static final Logger LOG = LoggerFactory.getLogger(AutoTestSerial.class.getName());

    public AutoTestSerial(String model, int power, String color, int count, String[] properties, int countDoors) {
        this.model = model;
        this.power = power;
        this.color = color;
        this.count = count;
        this.properties = properties;
        this.countDoors = countDoors;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public String getColor() {
        return color;
    }

    public int getCount() {
        return count;
    }

    public int getCountDoors() {
        return countDoors;
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
                && Objects.equals(properties, that.properties)
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
                + ", properties=" + properties
                + ", countDoors=" + countDoors
                + '}';
    }

    public static void main(String[] args) throws ClassNotFoundException {
        AutoTestSerial toyota = new AutoTestSerial("Toyota", 200, "red", 50, new String[]{"super", "puper"}, 4);
        final Gson gson = new GsonBuilder().create();
        LOG.debug(gson.toJson(toyota));
        final String toyotaStr = "{model:ToyotaLoad,power:300,color:red,count:30,properties:[Nosuper,Nopuper]}";
        AutoTestSerial toyotaLoad = gson.fromJson(toyotaStr, AutoTestSerial.class);
        System.out.println(toyotaLoad);
    }

}
