package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("even.txt")) {
            StringBuilder str = new StringBuilder();
            int read;
            while ((read = fis.read()) != -1) {
                str.append((char) read);
            }
            String[] arr = str.toString().split(System.lineSeparator());
            for (int i = 0; i < arr.length; i++) {
                try {
                    int value = Integer.parseInt(arr[i]);
                    System.out.println(value + " is " + (value % 2 == 0 ? "even" : "not even"));
                } catch (NumberFormatException e) {
                    System.out.println(arr[i] + " is not number");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
