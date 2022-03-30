package ru.job4j.io;

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
    transient private int countDoors;
    private static final Logger LOG = LoggerFactory.getLogger(AutoTestSerial.class.getName());

    public AutoTestSerial(String model, int power, String color, int count, int countDoors) {
        this.model = model;
        this.power = power;
        this.color = color;
        this.count = count;
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
        return power == that.power && count == that.count && Objects.equals(model, that.model) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, power, color, count);
    }

    @Override
    public String toString() {
        return "AutoTestSerial{"
                + "Model='" + model + '\''
                + ", power=" + power
                + ", color='" + color + '\''
                + ", count=" + count
                + ", countDoors=" + countDoors
                + '}';
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Path path = Paths.get("./Data/Serial/auto.json");

        AutoTestSerial toyota = new AutoTestSerial("Toyota", 200, "red", 50, 4);
        AutoTestSerial honda = new AutoTestSerial("Honda", 250, "yellow", 48, 5);

        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toyota);
            oos.writeObject(honda);
        } catch (IOException e) {
            LOG.error("Error serializable", e);
        }

        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            AutoTestSerial toyota1 = (AutoTestSerial) ois.readObject();
            AutoTestSerial honda1 = (AutoTestSerial) ois.readObject();

            System.out.println(toyota1.equals(toyota));
            System.out.println(honda1.equals(honda));

        } catch (IOException e) {
            LOG.error("Error deserializable", e);
        }

    }

}
