package client;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChatHistory implements AutoCloseable {
    private String login;
    private File historyFile;
    private PrintWriter printWriter;
//    String fileLocation = "C:\\JavaPractice\\Chatty\\client\\src\\main\\java\\client\\chatHistory\\history_" + login + ".txt";


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

    public String getLast100LinesHistoryList() {
        StringBuilder text = new StringBuilder();
        try {
            List<String> textList = Files.readAllLines(Paths.get("C:\\JavaPractice\\Chatty\\client\\src\\main\\java\\client\\chatHistory\\history_" + login + ".txt"));
            if (textList.size() < 100) {
                for (String line : textList) {
                    text.append(line).append(System.lineSeparator());
                }
            }else{
                for (int i = textList.size() - 100 ; i < textList.size() ; i++) {
                    text.append(textList.get(i)).append(System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    @Override
    public void close() throws Exception {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}
