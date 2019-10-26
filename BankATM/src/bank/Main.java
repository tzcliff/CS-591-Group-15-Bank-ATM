/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Person manager = new Person("Christine", "Papadakis");
        BankManagerAccount managerAccount = new BankManagerAccount(manager);
        GridPane welcomePage = new GridPane();
        Bank bank = new Bank(managerAccount, primaryStage, welcomePage);

        primaryStage.setTitle("ATM");
        welcomePage.setAlignment(Pos.CENTER);
        welcomePage.setHgap(10);
        welcomePage.setVgap(10);
        welcomePage.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(welcomePage, 800, 600);

        Text scenetitle = new Text("Welcome! Please pick your role.");
        welcomePage.add(scenetitle, 0, 0, 2, 1);

        Button customerBtn = new Button();
        customerBtn.setText("Customer");
        customerBtn.setFocusTraversable(false);
        welcomePage.add(customerBtn, 0, 1);
        customerBtn.setOnAction(event -> {
            primaryStage.getScene().setRoot(bank.getCustomerHomePage());
        });

        Button managerBtn = new Button();
        managerBtn.setText("Bank Manager");
        managerBtn.setOnAction(event -> {
            bank.setupBankManagerGUI();
            primaryStage.getScene().setRoot(bank.getBankerHomePage());
        });
        managerBtn.setFocusTraversable(false);
        welcomePage.add(managerBtn, 1, 1);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
