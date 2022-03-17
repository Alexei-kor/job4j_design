package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
             PrintWriter printWriter = new PrintWriter(new FileOutputStream(target))) {
            String dateBegin = "", dateEnd = "";
            while (bufferedReader.ready()) {
                String[] tmp = bufferedReader.readLine().split(" ");
                if (dateBegin.isEmpty() && (tmp[0].equals("400") || tmp[0].equals("500"))) {
                    dateBegin = tmp[1];
                } else if (!dateBegin.isEmpty() && (tmp[0].equals("200") || tmp[0].equals("300"))) {
                    dateEnd = tmp[1];
                }
                if (!dateBegin.isEmpty() && !dateEnd.isEmpty()) {
                    printWriter.println(dateBegin + ";" + dateEnd);
                    dateBegin = "";
                    dateEnd = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("log.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("log.csv", "unavailable.csv");
    }
}
