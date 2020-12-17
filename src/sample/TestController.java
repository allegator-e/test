package sample;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TestController {

    String name;
    int numberQuestion = 0;
    String[] questions;
    String[] answers;
    int countAnswer = 0;
    int countRightAnswer = 0;
    int countFalseAnswer = 0;
    ArrayList<MenuItem> buttonList = new ArrayList<>();

    TestController(String name) {
        this.name = name;
    }
    @FXML
    private MenuItem question1;

    @FXML
    private MenuItem question2;

    @FXML
    private MenuItem question3;

    @FXML
    private MenuItem question4;

    @FXML
    private MenuItem question5;

    @FXML
    private MenuItem question6;

    @FXML
    private MenuItem question7;

    @FXML
    private MenuItem question8;

    @FXML
    private MenuItem question9;

    @FXML
    private MenuItem question10;

    @FXML
    private MenuItem exitButton;

    @FXML
    private Label userName;

    @FXML
    private Label scoreAllAnswer;

    @FXML
    private Label scoreRightAnswer;

    @FXML
    private Label scoreFalseAnswer;

    @FXML
    private ProgressBar progress;

    @FXML
    private Button nextButton;

    @FXML
    private Button answerA;

    @FXML
    private Button answerB;

    @FXML
    private Button answerC;

    @FXML
    private Button answerD;

    @FXML
    private Label numberOfQuestion;

    @FXML
    private Label questionText;

    @FXML
    void initialize() throws IOException {
        userName.setText("Участник: " + name);
        exitButton.setOnAction(event -> {
            System.exit(1);
        });
        getData();
        buttonList.add(question1);
        buttonList.add(question2);
        buttonList.add(question3);
        buttonList.add(question4);
        buttonList.add(question5);
        buttonList.add(question6);
        buttonList.add(question7);
        buttonList.add(question8);
        buttonList.add(question9);
        buttonList.add(question10);

        nextQuestion(1);
        answerA.setOnAction(event -> {
            if (answers[numberQuestion-1].equals("а")) {
                rightAnswer();
            } else falseAnswer();
        });

        answerB.setOnAction(event -> {
            if (answers[numberQuestion-1].equals("б")) {
                rightAnswer();
            } else falseAnswer();
        });

        answerC.setOnAction(event -> {
            if (answers[numberQuestion-1].equals("в")) {
                rightAnswer();
            } else falseAnswer();
        });

        answerD.setOnAction(event -> {
            if (answers[numberQuestion-1].equals("г")) {
                rightAnswer();
            } else falseAnswer();
        });

        question1.setOnAction(event -> {
            nextQuestion(1);
        });
        question2.setOnAction(event -> {
            nextQuestion(2);
        });
        question3.setOnAction(event -> {
            nextQuestion(3);
        });
        question4.setOnAction(event -> {
            nextQuestion(4);
        });
        question5.setOnAction(event -> {
            nextQuestion(5);
        });
        question6.setOnAction(event -> {
            nextQuestion(6);
        });
        question7.setOnAction(event -> {
            nextQuestion(7);
        });
        question8.setOnAction(event -> {
            nextQuestion(8);
        });
        question9.setOnAction(event -> {
            nextQuestion(9);
        });
        question10.setOnAction(event -> {
            nextQuestion(10);
        });

        nextButton.setOnAction(event -> {
            nextQuestion(numberQuestion + 1);
        });
    }

    public void rightAnswer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Right Answer");
        alert.setHeaderText("Правильный ответ!");
        alert.showAndWait();
        countAnswer += 1;
        countRightAnswer +=1;
        buttonList.get(numberQuestion-1).setDisable(true);
        nextQuestion(numberQuestion + 1);
    }

    public void falseAnswer(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("False Answer");
        alert.setHeaderText("Неверный ответ!");
        alert.showAndWait();
        countAnswer += 1;
        countFalseAnswer +=1;
        buttonList.get(numberQuestion-1).setDisable(true);
        nextQuestion(numberQuestion + 1);
    }

    public void nextQuestion(int number){
        scoreAllAnswer.setText("Всего ответов: " + countAnswer);
        scoreRightAnswer.setText("Правильных: " + countRightAnswer);
        scoreFalseAnswer.setText("Ошибочных: " + countFalseAnswer);
        progress.setProgress(countAnswer/10D);

        if (countAnswer < 10) {
            numberQuestion = (number - 1) % 10 + 1;
            if (!buttonList.get(numberQuestion - 1).isDisable()) {
                numberOfQuestion.setText("Вопрос №" + numberQuestion);
                questionText.setText(questions[numberQuestion - 1]);
            } else nextQuestion(numberQuestion + 1);
        } else {
            rezult();
        }
    }

    public void getData() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader buffReader = new BufferedReader(new FileReader("./Resources/test.txt"))) {
            while (buffReader.ready()) {
                data.append(buffReader.readLine()).append("\n");
            }
        } catch (IOException e) {
            allert("Проверьте расположение файла test.txt и его корректность.");
        }
        questions = data.toString().split("####");
        for (int i = 1; i < questions.length; i++) {
            questions[i] = questions[i].replaceFirst("\n", "");
        }
        if (questions.length != 11) {
            allert("Файл test.txt не корректен. Ознакомтесь с README и измените данные в test.txt");
        } else answers = questions[10].replace("\n","").split("#");
        if (answers.length != 10) {
            allert("Файл test.txt не корректен. Ознакомтесь с README и измените данные в test.txt");
            System.exit(-1);
        }
    }

    public void rezult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Результат");
        alert.setHeaderText(name);
        alert.setContentText("Тест успешно завершен! Результат: " + countRightAnswer*10 + "%");
        alert.showAndWait();
        System.exit(0);
    }

    public void allert(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(text);
        alert.showAndWait();
        System.exit(-1);
    }
}

