package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> phrases;
    private List<String> log = new ArrayList<>();
    private Scanner console = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        phrases = readPhrases();
        String request = console.nextLine();
        addLog(request);
        boolean shutUp = false;
        while (!ConsoleChat.OUT.equals(request)) {
            if (ConsoleChat.STOP.equals(request)) {
                shutUp = true;
            } else if (ConsoleChat.CONTINUE.equals(request)) {
                shutUp = false;
            }
            if (!shutUp) {
                String answer = phrases.get(new Random().nextInt(phrases.size()));
                System.out.println(answer);
                addLog(answer);
            }
            request = console.nextLine();
            addLog(request);
        }
        saveLog(log);
    }

    private void addLog(String record) {
        log.add(record);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            rsl = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./Data/log.txt", "./Data/answers.txt");
        cc.run();
    }

}
