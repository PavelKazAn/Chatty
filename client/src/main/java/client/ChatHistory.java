package client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChatHistory implements AutoCloseable {
    private String login;
    private File historyFile;
    private FileReader fileReader;
    private PrintWriter printWriter;

    public ChatHistory(String login) {
        this.login = login;
    }

    public void writeHistoryChat() {
        try {
            historyFile = createChatHistoryFile();
            this.printWriter = new PrintWriter(new BufferedWriter(new FileWriter(historyFile, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File createChatHistoryFile() throws IOException {
        File file = new File("C:\\JavaPractice\\Chatty\\client\\src\\main\\java\\client\\chatHistory\\history_" + login + ".txt");

        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        return file;
    }

    public void appendMessage(String msg) {
        printWriter.print("\n" + msg);
        printWriter.close();
    }

    public ArrayList<String> getLast100LinesHistoryList() {
        ArrayList<String> arrLines = new ArrayList<>();
        ArrayList<String> arrLines2 = new ArrayList<>();
        try {
            arrLines = (ArrayList<String>) Files.readAllLines(Paths.get("C:\\JavaPractice\\Chatty\\client\\src\\main\\java\\client\\chatHistory\\history_" + login + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (arrLines.size() > 100) {
            for (int i = arrLines.size() - 100; i < 100; i++) {
                arrLines2.add(arrLines.get(i));
            }
            return arrLines2;
        } else {
            return arrLines;
        }
    }

    @Override
    public void close() throws Exception {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}
