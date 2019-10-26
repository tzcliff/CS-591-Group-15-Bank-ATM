/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Bank {
    private ArrayList<CustomerAccount> customerAccounts;
    private BankManagerAccount bankManagerAccount;
    private CustomerAccount currentCustomer;

    private Stage primaryStage;
    private GridPane welcomePage;
    private GridPane loginPage;
    private GridPane registerPage;

    private GridPane bankerHomePage;
    private GridPane dailyReportPage;
    private GridPane recentsReportPage;

    private GridPane customerHomePage;
    private GridPane createNewAccountPage;
    private GridPane createNewSavingsAccountPage;
    private GridPane createNewCheckingAccountPage;
    private GridPane loansPage;
    private GridPane requestLoanPage;
    private GridPane checkLoansPage;
    private GridPane transactionsPage;
    private GridPane checkTransactionsPage;
    private GridPane makeWithdrawalPage;
    private GridPane makeDepositPage;
    private GridPane makeTransferPage;
    private GridPane myAccountsPage;
    private GridPane accountDetailsPage;

    public Bank(BankManagerAccount bankManagerAccount, Stage primaryStage, GridPane welcomePage) {
        //Person client = new Person("At", "Fi");
        this.customerAccounts = new ArrayList<CustomerAccount>();
        //this.customerAccounts.add(new CustomerAccount(client));
        this.bankManagerAccount = bankManagerAccount;
        //this.currentCustomer = customerAccounts.get(0);

        this.primaryStage = primaryStage;
        this.welcomePage = welcomePage;

        //setupBankManagerGUI();

        //setupCustomerGUI();
    }

    private void setupCustomerGUI(){
        setupCustomerHomePage();
        setupCreateNewAccountPage();
        setupLoansPage();
        setupTransactionsPage();
        setupLoginPage();
    }

    private void setupRegisterPage(){
        this.registerPage = new GridPane();
        setupBasicCustomerAlignments(registerPage);

        Text registerTitle = new Text();
        registerTitle.setText("Please type your first and last name to register.");
        registerPage.add(registerTitle, 0, 0, 2, 1);

        Label firstNameLbl = new Label("First Name:");
        registerPage.add(firstNameLbl, 0, 1);
        TextField firstName = new TextField();
        registerPage.add(firstName, 1,1);

        Label lastNametLbl = new Label("Last Name:");
        registerPage.add(lastNametLbl, 0, 2);
        TextField lastName = new TextField();
        registerPage.add(lastName, 1,2);

        Text replyMessage = new Text();
        registerPage.add(replyMessage, 0, 4, 2, 1);

        Button registerButton = new Button("Register");
        registerButton.setFocusTraversable(false);
        registerButton.setOnAction(event -> {
            Person tmpPerson = new Person(firstName.getText(), lastName.getText());
            registerNewCustomer(tmpPerson);
            replyMessage.setFill(Color.GREEN);
            replyMessage.setText("Successful registration! Now head back to log in..");
        });
        registerPage.add(registerButton, 1, 3);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            //setupMyAccountsPage();
            primaryStage.getScene().setRoot(welcomePage);
        });
        registerPage.add(closeButton, 0, 3);

    }



    private void setupLoginPage(){
        this.loginPage = new GridPane();
        setupBasicCustomerAlignments(loginPage);

        Text loginTitle = new Text();
        loginTitle.setText("Please log in if you already have a customer account otherwise tap register.");
        loginPage.add(loginTitle, 0, 0, 2, 1);

        Label firstNameLbl = new Label("First Name:");
        loginPage.add(firstNameLbl, 0, 1);
        TextField firstName = new TextField();
        loginPage.add(firstName, 1,1);

        Label lastNametLbl = new Label("Last Name:");
        loginPage.add(lastNametLbl, 0, 2);
        TextField lastName = new TextField();
        loginPage.add(lastName, 1,2);

        Text replyMessage = new Text();
        loginPage.add(replyMessage, 0, 4, 2, 1);

        Button loginButton = new Button("Log in");
        loginButton.setFocusTraversable(false);
        loginButton.setOnAction(event -> {
            Person tmpPerson = new Person(firstName.getText(), lastName.getText());
            currentCustomer = loginCustomer(tmpPerson);
            if (currentCustomer != null){
                setupCustomerGUI();
                primaryStage.getScene().setRoot(customerHomePage);
            }else{
                replyMessage.setFill(Color.FIREBRICK);
                replyMessage.setText("Account not found.");
            }
            //setupMyAccountsPage();
            //primaryStage.getScene().setRoot(myAccountsPage);
        });
        loginPage.add(loginButton, 1, 3);

        Button registerButton = new Button("Register");
        registerButton.setFocusTraversable(false);
        registerButton.setOnAction(event -> {
            setupRegisterPage();
            primaryStage.getScene().setRoot(registerPage);
        });
        loginPage.add(registerButton, 2, 3);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            //setupMyAccountsPage();
            primaryStage.getScene().setRoot(welcomePage);
        });
        loginPage.add(closeButton, 0, 3);

    }





    private void setupAccountDetailsPage(Account selectedAccount){
        this.accountDetailsPage = new GridPane();
        setupBasicCustomerAlignments(accountDetailsPage);

        Text accountDetailsTitle = new Text();
        accountDetailsTitle.setText("Account's: " + selectedAccount.getAccountNumber() + " details");
        accountDetailsPage.add(accountDetailsTitle, 0, 0, 2, 1);

        Label amountLbl = new Label("Current Balance:");
        accountDetailsPage.add(amountLbl, 0, 1);
        Text balance = new Text();
        final String[] tmpCurr = {selectedAccount.getCurrency().toString()};
        balance.setText(selectedAccount.getBalanceInLocalCurrency()*Currency.getRate(tmpCurr[0]) + tmpCurr[0]);
        accountDetailsPage.add(balance, 1,1);


        ComboBox currencySelector = new ComboBox(FXCollections.observableArrayList(Currency.getAbbreviationToFullName().keySet()));
        currencySelector.getSelectionModel().select(tmpCurr[0]);
        final Currency[] newCurrency = {new Currency(tmpCurr[0])};
        currencySelector.setOnAction(event -> {
            //System.out.println(currencySelector.getValue().toString());
            newCurrency[0] = new Currency(currencySelector.getValue().toString());
            selectedAccount.setCurrency(newCurrency[0]);
            tmpCurr[0] = selectedAccount.getCurrency().toString();
            balance.setText(selectedAccount.getBalanceInLocalCurrency()*Currency.getRate(tmpCurr[0]) + tmpCurr[0]);
        });
        accountDetailsPage.add(currencySelector, 2,1);


        Button withdrawButton = new Button("Make withdrawal");
        withdrawButton.setFocusTraversable(false);
        withdrawButton.setOnAction(event -> {
            setupMakeWithdrawalPage(selectedAccount);
            primaryStage.getScene().setRoot(makeWithdrawalPage);
        });
        accountDetailsPage.add(withdrawButton, 0, 2, 1, 1);

        Button depositButton = new Button("Make deposit");
        depositButton.setFocusTraversable(false);
        depositButton.setOnAction(event -> {
            setupMakeDepositPage(selectedAccount);
            primaryStage.getScene().setRoot(makeDepositPage);
        });
        accountDetailsPage.add(depositButton, 1, 2, 1, 1);

        Button transferButton = new Button("Make transfer");
        transferButton.setFocusTraversable(false);
        transferButton.setOnAction(event -> {
            setupMakeTransferPage(selectedAccount);
            primaryStage.getScene().setRoot(makeTransferPage);
        });
        accountDetailsPage.add(transferButton, 2, 2, 1, 1);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            setupMyAccountsPage();
            primaryStage.getScene().setRoot(myAccountsPage);
        });
        accountDetailsPage.add(closeButton, 1, 3);
    }

    private void setupMyAccountsPage(){
        this.myAccountsPage = new GridPane();
        setupBasicCustomerAlignments(myAccountsPage);

        Text myAccountsTitle = new Text();
        myAccountsTitle.setText("Below you can see your accounts. Tap in any of them for more details/actions");
        myAccountsPage.add(myAccountsTitle, 0, 0, 2, 1);

        Text myChAccountsTitle = new Text();
        myChAccountsTitle.setText("Checking Accounts:");
        myAccountsPage.add(myChAccountsTitle, 0, 1, 1, 1);

        Text mySaAccountsTitle = new Text();
        mySaAccountsTitle.setText("Savings Accounts:");
        myAccountsPage.add(mySaAccountsTitle, 1, 1, 1, 1);


        final ListView chAccListView = new ListView(FXCollections.observableList(currentCustomer.getCheckingAccounts()));
        chAccListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //System.out.println("clicked on " + chAccListView.getSelectionModel().getSelectedItem());
                if (chAccListView.getSelectionModel().getSelectedItem() != null){
                    setupAccountDetailsPage((Account)chAccListView.getSelectionModel().getSelectedItem());
                    primaryStage.getScene().setRoot(accountDetailsPage);
                }
            }
        });
        //System.out.println(currentCustomer.getLoans());
        chAccListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        myAccountsPage.add(chAccListView, 0, 2, 1, 1);

        final ListView saAccListView = new ListView(FXCollections.observableList(currentCustomer.getSavingsAccounts()));
        saAccListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + saAccListView.getSelectionModel().getSelectedItem());
                if (saAccListView.getSelectionModel().getSelectedItem() != null){
                    setupAccountDetailsPage((Account)saAccListView.getSelectionModel().getSelectedItem());
                    primaryStage.getScene().setRoot(accountDetailsPage);
                }
            }
        });
        //System.out.println(currentCustomer.getLoans());
        saAccListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        myAccountsPage.add(saAccListView, 1, 2, 1, 1);


        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        myAccountsPage.add(closeButton, 1, 5);


    }

    private void setupMakeTransferPage(Account account){
        this.makeTransferPage = new GridPane();
        setupBasicCustomerAlignments(makeTransferPage);

        Text makeTransferTitle = new Text();
        makeTransferTitle.setText("Please type the amount you wish to transfer from your selected account and the account/routing number of the target:");
        makeTransferPage.add(makeTransferTitle, 0, 0, 2, 1);



        Label amountLbl = new Label("Amount:");
        makeTransferPage.add(amountLbl, 0, 1);
        TextField strAmount = new TextField();
        makeTransferPage.add(strAmount, 1,1);

        Text amountReplyMessage = new Text();
        makeTransferPage.add(amountReplyMessage, 2, 1);


        Label accountNLbl = new Label("Account number:");
        makeTransferPage.add(accountNLbl, 0, 2);
        TextField strAccountN = new TextField();
        makeTransferPage.add(strAccountN, 1,2);

        Text accountNReplyMessage = new Text();
        makeTransferPage.add(accountNReplyMessage, 2, 2);


        Label routingNLbl = new Label("Routing number:");
        makeTransferPage.add(routingNLbl, 0, 3);
        TextField strRoutingN = new TextField();
        makeTransferPage.add(strRoutingN, 1,3);

        Text routingNReplyMessage = new Text();
        makeTransferPage.add(routingNReplyMessage, 2, 3);


        Text replyMessage = new Text();
        makeTransferPage.add(replyMessage, 0, 4, 2, 1);


        Button submitBtn = new Button();
        submitBtn.setText("Transfer amount");
        submitBtn.setFocusTraversable(false);
        final Float[] amount = new Float[1];
        submitBtn.setOnAction(event -> {
            try {
                amount[0] = Float.parseFloat(strAmount.getText());
                if (amount[0] >= 0){
                    try {
                        int accountN = Integer.parseInt(strAccountN.getText());
                        try {
                            int routingN = Integer.parseInt(strRoutingN.getText());
                            Float amountInDollars = account.getCurrency().convertTo("USD", amount[0]);
                            if (currentCustomer.withdrawTransferAmount(amountInDollars, account.getCurrency(), account.getAccountNumber(), account.getRoutingNumber(), accountN, routingN)){
                                if (findTransfersReceiverAcc(amountInDollars, account.getCurrency(), account.getAccountNumber(), account.getRoutingNumber(), accountN, routingN)){
                                    replyMessage.setFill(Color.GREEN);
                                    Float tmpRate = Currency.getRate(account.getCurrency().toString());
                                    replyMessage.setText("Transfer successful. Your new account balance is: " + account.getBalanceInLocalCurrency()*tmpRate + " " + account.getCurrency().toString());
                                }else{
                                    replyMessage.setFill(Color.FIREBRICK);
                                    replyMessage.setText("Transfer unsuccessful. Target account was not found.");
                                    currentCustomer.cancelLastTransferWithdrawal();
                                }
                            }else{
                                amountReplyMessage.setFill(Color.FIREBRICK);
                                amountReplyMessage.setText("Transfer unsuccessful. Insufficient balance.");
                            }
                        }catch (NumberFormatException e){
                            routingNReplyMessage.setFill(Color.FIREBRICK);
                            routingNReplyMessage.setText("Transfer unsuccessful. Routing number has to be a number.");
                        }
                    }catch (NumberFormatException e){
                        accountNReplyMessage.setFill(Color.FIREBRICK);
                        accountNReplyMessage.setText("Transfer unsuccessful. Account number has to be a number.");
                    }
                }else{
                    amountReplyMessage.setFill(Color.FIREBRICK);
                    amountReplyMessage.setText("Only positive amounts requests permitted.");
                }
            }catch (NumberFormatException e){
                amountReplyMessage.setFill(Color.FIREBRICK);
                amountReplyMessage.setText("Please insert only numbers in the amount field.");
            }
        });
        makeTransferPage.add(submitBtn, 1 , 5);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            setupAccountDetailsPage(account);
            primaryStage.getScene().setRoot(accountDetailsPage);
        });
        makeTransferPage.add(closeButton, 1, 6);
    }

    private void setupMakeDepositPage(Account account){
        this.makeDepositPage = new GridPane();
        setupBasicCustomerAlignments(makeDepositPage);

        Text makeDepositTitle = new Text();
        makeDepositTitle.setText("Please type the amount you wish to deposit:");
        makeDepositPage.add(makeDepositTitle, 0, 0, 2, 1);


        Label amountLbl = new Label("Amount:");
        makeDepositPage.add(amountLbl, 0, 1);
        TextField strAmount = new TextField();
        makeDepositPage.add(strAmount, 1,1);


        Text replyMessage = new Text();
        makeDepositPage.add(replyMessage, 0, 3 , 2, 1);


        Button submitBtn = new Button();
        submitBtn.setText("Deposit amount");
        submitBtn.setFocusTraversable(false);
        final Float[] amount = new Float[1];
        submitBtn.setOnAction(event -> {
            try {
                amount[0] = Float.parseFloat(strAmount.getText());
                if (amount[0] >= 0){
                    Float amountInDollars = account.getCurrency().convertTo("USD", amount[0]);
                    if (currentCustomer.depositAmount(amountInDollars, account.getCurrency(), account.getAccountNumber(), account.getRoutingNumber())){
                        replyMessage.setFill(Color.GREEN);
                        Float tmpRate = Currency.getRate(account.getCurrency().toString());
                        replyMessage.setText("Deposit successful. Your new account balance is: " + account.getBalanceInLocalCurrency()*tmpRate + " " + account.getCurrency().toString());

                        strAmount.setText("");
                        //System.out.println(amount[0]);
                        //System.out.println(tmpCurrency[0].toString());
                    }else{
                        replyMessage.setFill(Color.FIREBRICK);
                        replyMessage.setText("Deposit unsuccessful. Your account was not found.");
                    }
                }else{
                    replyMessage.setFill(Color.FIREBRICK);
                    replyMessage.setText("Only positive amounts requests permitted.");
                }
            }catch (NumberFormatException e){
                //System.out.println("Typed command was not a number. Please type again:");
                replyMessage.setFill(Color.FIREBRICK);
                replyMessage.setText("Please insert only numbers in the amount field.");
            }
        });
        makeDepositPage.add(submitBtn, 1 , 2);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            setupAccountDetailsPage(account);
            primaryStage.getScene().setRoot(accountDetailsPage);
        });
        makeDepositPage.add(closeButton, 1, 4);
    }


    private void setupMakeWithdrawalPage(Account account){
        this.makeWithdrawalPage = new GridPane();
        setupBasicCustomerAlignments(makeWithdrawalPage);

        Text makeWithdrawalTitle = new Text();
        makeWithdrawalTitle.setText("Please type the amount you wish to withdraw:");
        makeWithdrawalPage.add(makeWithdrawalTitle, 0, 0, 2, 1);


        Label amountLbl = new Label("Amount:");
        makeWithdrawalPage.add(amountLbl, 0, 1);
        TextField strAmount = new TextField();
        makeWithdrawalPage.add(strAmount, 1,1);


        Text replyMessage = new Text();
        makeWithdrawalPage.add(replyMessage, 0, 3, 2, 1);


        Button submitBtn = new Button();
        submitBtn.setText("Withdraw amount");
        submitBtn.setFocusTraversable(false);
        final Float[] amount = new Float[1];
        submitBtn.setOnAction(event -> {
            try {
                amount[0] = Float.parseFloat(strAmount.getText());
                if (amount[0] >= 0){
                    Float amountInDollars = account.getCurrency().convertTo("USD", amount[0]);
                    if (currentCustomer.withdrawAmount(amountInDollars, account.getCurrency(), account.getAccountNumber(), account.getRoutingNumber())){
                        replyMessage.setFill(Color.GREEN);
                        replyMessage.setText("Withdrawal successful. Please standby to receive your: " + amount[0].toString() + " " + account.getCurrency().toString());

                        strAmount.setText("");
                        //System.out.println(amount[0]);
                        //System.out.println(tmpCurrency[0].toString());
                    }else{
                        replyMessage.setFill(Color.FIREBRICK);
                        replyMessage.setText("Withdrawal unsuccessful. Not sufficient balance.");
                    }
                }else{
                    replyMessage.setFill(Color.FIREBRICK);
                    replyMessage.setText("Only positive amounts requests permitted.");
                }
            }catch (NumberFormatException e){
                //System.out.println("Typed command was not a number. Please type again:");
                replyMessage.setFill(Color.FIREBRICK);
                replyMessage.setText("Please insert only numbers in the amount field.");
            }
        });
        makeWithdrawalPage.add(submitBtn, 1 , 2);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            setupAccountDetailsPage(account);
            primaryStage.getScene().setRoot(accountDetailsPage);
        });
        makeWithdrawalPage.add(closeButton, 1, 4);
    }

    private void setupTransactionsPage(){
        this.transactionsPage = new GridPane();
        //setupBasicCustomerAlignments(transactionsPage);
        transactionsPage.setAlignment(Pos.CENTER);
        transactionsPage.setHgap(30);
        transactionsPage.setVgap(20);
        transactionsPage.setPadding(new Insets(25, 25, 25, 25));

        Text transactionsTitle = new Text();
        transactionsTitle.setText("Please tap your option:");
        transactionsPage.add(transactionsTitle, 0, 0 , 2, 1);

        Button checkTransactionsButton = new Button("Check my transactions");
        checkTransactionsButton.setFocusTraversable(false);
        checkTransactionsButton.setOnAction(event -> {
            setupCheckTransactionsPage();
            primaryStage.getScene().setRoot(checkTransactionsPage);
        });
        transactionsPage.add(checkTransactionsButton, 0, 1, 1, 1);

        Button withdrawButton = new Button("Make withdrawal");
        withdrawButton.setFocusTraversable(false);
        withdrawButton.setOnAction(event -> {
            setupMyAccountsPage();
            primaryStage.getScene().setRoot(myAccountsPage);
        });
        transactionsPage.add(withdrawButton, 1, 1, 1, 1);

        Button depositButton = new Button("Make deposit");
        depositButton.setFocusTraversable(false);
        depositButton.setOnAction(event -> {
            setupMyAccountsPage();
            primaryStage.getScene().setRoot(myAccountsPage);
        });
        transactionsPage.add(depositButton, 2, 1, 1, 1);

        Button transferButton = new Button("Make transfer");
        transferButton.setFocusTraversable(false);
        transferButton.setOnAction(event -> {
            setupMyAccountsPage();
            primaryStage.getScene().setRoot(myAccountsPage);
        });
        transactionsPage.add(transferButton, 3, 1, 1, 1);



        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        transactionsPage.add(closeButton, 1, 2, 1, 1);

    }

    private void setupCheckTransactionsPage(){
        this.checkTransactionsPage = new GridPane();
        setupBasicBankerAlignments(checkTransactionsPage);

        Text checkTransactionsTitle = new Text();
        checkTransactionsTitle.setText("Your transactions:");
        checkTransactionsPage.add(checkTransactionsTitle, 0, 0 , 2, 1);

        ListView<Transaction> listView = new ListView<>();
        if (currentCustomer == null){
            System.out.println("AKALA");
        }
        if (currentCustomer.getTransactions() == null){
            System.out.println("OLO MALAKIES");
        }
        listView.getItems().addAll(currentCustomer.getTransactions());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        checkTransactionsPage.add(listView, 0, 1, 2, 1);

        Button closeButton = new Button("Close");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        checkTransactionsPage.add(closeButton, 1, 2, 1, 1);

    }





    private void setupCheckLoansPage(){
        this.checkLoansPage = new GridPane();
        setupBasicCustomerAlignments(checkLoansPage);

        Text checkLoansTitle = new Text();
        checkLoansTitle.setText("List of your loans:");
        checkLoansPage.add(checkLoansTitle, 0, 0, 2, 1);

        ListView<Loan> listView = new ListView<>();
        listView.getItems().addAll(currentCustomer.getLoans());
        //System.out.println(currentCustomer.getLoans());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        checkLoansPage.add(listView, 0, 1, 2, 1);

        Button closeButton = new Button("Close");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        checkLoansPage.add(closeButton, 1, 2);
    }

    private void setupRequestLoanPage(){
        this.requestLoanPage = new GridPane();
        setupBasicCustomerAlignments(requestLoanPage);

        Text requestLoanPageTitle = new Text("Please type the amount of the loan you will need and select its currency:");
        requestLoanPage.add(requestLoanPageTitle, 0, 0, 2 , 1);

        Label amountLbl = new Label("Amount:");
        requestLoanPage.add(amountLbl, 0, 1);
        TextField strAmount = new TextField();
        requestLoanPage.add(strAmount, 1,1);


        ComboBox currencySelector = new ComboBox(FXCollections.observableArrayList(Currency.getAbbreviationToFullName().keySet()));
        currencySelector.getSelectionModel().select("USD");
        final Currency[] tmpCurrency = {new Currency("USD")};
        currencySelector.setOnAction(event -> {
            //System.out.println(currencySelector.getValue().toString());
            tmpCurrency[0] = new Currency(currencySelector.getValue().toString());
        });
        requestLoanPage.add(currencySelector, 2,1);

        Text replyMessage = new Text();
        requestLoanPage.add(replyMessage, 0, 3, 2 ,1);

        Button submitBtn = new Button();
        submitBtn.setText("Request loan");
        submitBtn.setFocusTraversable(false);
        final Float[] amount = new Float[1];
        submitBtn.setOnAction(event -> {
            try {
                amount[0] = Float.parseFloat(strAmount.getText());
                if (amount[0] >= 0){
                    if (currentCustomer.addNewLoan(amount[0], tmpCurrency[0])){
                        replyMessage.setFill(Color.GREEN);
                        replyMessage.setText(amount[0].toString() + " " + tmpCurrency[0].toString() + " loan successfully granted!");

                        strAmount.setText("");
                        //System.out.println(amount[0]);
                        //System.out.println(tmpCurrency[0].toString());
                    }else{
                        replyMessage.setFill(Color.FIREBRICK);
                        replyMessage.setText("Not enough collateral.");
                    }
                }else{
                    replyMessage.setFill(Color.FIREBRICK);
                    replyMessage.setText("Only positive amounts requests permitted.");
                }
            }catch (NumberFormatException e){
                //System.out.println("Typed command was not a number. Please type again:");
                replyMessage.setFill(Color.FIREBRICK);
                replyMessage.setText("Please insert only numbers in the amount field.");
            }
        });
        requestLoanPage.add(submitBtn, 1 , 2);

        Button closeButton = new Button("Close");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        requestLoanPage.add(closeButton, 1, 4);
    }


    private void setupLoansPage(){
        this.loansPage = new GridPane();
        setupBasicCustomerAlignments(loansPage);

        Text loansPageTitle = new Text("Please tap one of the two options:");
        loansPage.add(loansPageTitle, 0, 0, 2 , 1);

        Button requestNewLoanBtn = new Button();
        requestNewLoanBtn.setText("Request new loan");
        requestNewLoanBtn.setOnAction(event -> {
            //currentCustomer.addNewSavingsAccount();
            setupRequestLoanPage();
            primaryStage.getScene().setRoot(requestLoanPage);
        });
        requestNewLoanBtn.setFocusTraversable(false);
        loansPage.add(requestNewLoanBtn, 0 , 1);

        Button checkMyLoansBtn = new Button();
        checkMyLoansBtn.setText("Check my loans");
        checkMyLoansBtn.setOnAction(event -> {
            setupCheckLoansPage();
            primaryStage.getScene().setRoot(checkLoansPage);
        });
        checkMyLoansBtn.setFocusTraversable(false);
        loansPage.add(checkMyLoansBtn, 2, 1);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        loansPage.add(closeButton, 1, 2);
    }








    private void setupCustomerHomePage(){
        this.customerHomePage  = new GridPane();
        setupBasicCustomerAlignments(customerHomePage);

        Text customerHomePageTitle = new Text("Welcome " + currentCustomer.getPerson().toString() + ".");
        customerHomePage.add(customerHomePageTitle, 0, 0, 2 , 1);

        Button openNewAccountBtn = new Button();
        openNewAccountBtn.setText("Open new bank account");
        openNewAccountBtn.setOnAction(event -> {
            primaryStage.getScene().setRoot(createNewAccountPage);
        });
        openNewAccountBtn.setFocusTraversable(false);
        customerHomePage.add(openNewAccountBtn, 0 , 1);

        Button loansBtn = new Button();
        loansBtn.setText("Loans");
        loansBtn.setOnAction(event -> {
            primaryStage.getScene().setRoot(loansPage);
        });
        loansBtn.setFocusTraversable(false);
        customerHomePage.add(loansBtn, 1 , 1);

        Button transactionsBtn = new Button();
        transactionsBtn.setText("Transactions");
        transactionsBtn.setOnAction(event -> {
            primaryStage.getScene().setRoot(transactionsPage);
        });
        transactionsBtn.setFocusTraversable(false);
        customerHomePage.add(transactionsBtn, 1 , 2);

        Button myAccountsBtn = new Button();
        myAccountsBtn.setText("Check my accounts");
        myAccountsBtn.setOnAction(event -> {
            setupMyAccountsPage();
            primaryStage.getScene().setRoot(myAccountsPage);
        });
        myAccountsBtn.setFocusTraversable(false);
        customerHomePage.add(myAccountsBtn, 0 , 2);

        Button logoutBtn = new Button();
        logoutBtn.setText("Log out");
        logoutBtn.setOnAction(event -> {
            primaryStage.getScene().setRoot(welcomePage);
            currentCustomer = null;
        });
        logoutBtn.setFocusTraversable(false);
        customerHomePage.add(logoutBtn, 1 , 3);
    }

    private void setupCreateNewAccountPage(){
        this.createNewAccountPage = new GridPane();
        setupBasicCustomerAlignments(createNewAccountPage);

        Text createNewAccountPageTitle = new Text("Please tap the type of account you would like to open:");
        createNewAccountPage.add(createNewAccountPageTitle, 0, 0, 2 , 1);

        Button openNewSavingsAccountBtn = new Button();
        openNewSavingsAccountBtn.setText("Open new savings account");
        openNewSavingsAccountBtn.setOnAction(event -> {
            currentCustomer.addNewSavingsAccount();
            setupCreateNewSavingsAccountPage();
            primaryStage.getScene().setRoot(createNewSavingsAccountPage);
        });
        openNewSavingsAccountBtn.setFocusTraversable(false);
        createNewAccountPage.add(openNewSavingsAccountBtn, 2 , 1);

        Button openNewCheckingAccountBtn = new Button();
        openNewCheckingAccountBtn.setText("Open new checking account");
        openNewCheckingAccountBtn.setOnAction(event -> {
            currentCustomer.addNewCheckingAccount();
            setupCreateNewCheckingAccountPage();
            primaryStage.getScene().setRoot(createNewCheckingAccountPage);
        });
        openNewCheckingAccountBtn.setFocusTraversable(false);
        createNewAccountPage.add(openNewCheckingAccountBtn, 0, 1);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        createNewAccountPage.add(closeButton, 1, 2);
    }

    private void setupCreateNewSavingsAccountPage(){
        this.createNewSavingsAccountPage = new GridPane();
        setupBasicCustomerAlignments(createNewSavingsAccountPage);

        Text createNewSavingsAccountPageTitle = new Text("Your new savings account has been created successfully. You can see its details below:");
        setupNewAccountPage(createNewSavingsAccountPageTitle, createNewSavingsAccountPage);


        SavingsAccount tmpSA = currentCustomer.getLastSavingsAccount();
        Text accountNumber = new Text(Integer.toString(tmpSA.getAccountNumber()));
        createNewSavingsAccountPage.add(accountNumber, 1, 1);
        Text routingNumber = new Text(Integer.toString(tmpSA.getRoutingNumber()));
        createNewSavingsAccountPage.add(routingNumber, 1, 2);
    }

    private void setupCreateNewCheckingAccountPage(){
        this.createNewCheckingAccountPage = new GridPane();
        setupBasicCustomerAlignments(createNewCheckingAccountPage);

        Text createNewCheckingAccountPageTitle = new Text("Your new checking account has been created successfully. You can see its details below:");
        setupNewAccountPage(createNewCheckingAccountPageTitle, createNewCheckingAccountPage);


        CheckingAccount tmpCA = currentCustomer.getLastCheckingAccount();
        Text accountNumber = new Text(Integer.toString(tmpCA.getAccountNumber()));
        createNewCheckingAccountPage.add(accountNumber, 1, 1);
        Text routingNumber = new Text(Integer.toString(tmpCA.getRoutingNumber()));
        createNewCheckingAccountPage.add(routingNumber, 1, 2);
    }

    private void setupNewAccountPage(Text createNewAccountPageTitle, GridPane createNewAccountPage) {
        createNewAccountPage.add(createNewAccountPageTitle, 0, 0, 2 , 1);

        Label accountNumberLbl = new Label("Account Number:");
        createNewAccountPage.add(accountNumberLbl, 0, 1);

        Label routingNumberLbl = new Label("Routing Number:");
        createNewAccountPage.add(routingNumberLbl, 0, 2);

        Button closeButton = new Button("Close");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(customerHomePage);
        });
        createNewAccountPage.add(closeButton, 1, 3);
    }

    private void setupBasicCustomerAlignments(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(80);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    public GridPane getCustomerHomePage() {
        setupLoginPage();
        return loginPage;
    }







    public GridPane getBankerHomePage() {
        return bankerHomePage;
    }

    public void setupBankManagerGUI(){
        this.bankerHomePage = new GridPane();
        this.dailyReportPage = new GridPane();
        this.recentsReportPage = new GridPane();

        setupBankerHomePage();
    }

    private void setupBasicBankerAlignments(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void setupBankerHomePage(){
        setupBasicBankerAlignments(bankerHomePage);

        Text bankerHomePageTitle = new Text("Welcome " + bankManagerAccount.getPerson().toString() + ".");
        bankerHomePage.add(bankerHomePageTitle, 0, 0, 2 , 1);

        Button getDailyReportBtn = new Button();
        getDailyReportBtn.setText("Get daily report");
        getDailyReportBtn.setOnAction(event -> {
            setupDailyReportPage();
            primaryStage.getScene().setRoot(dailyReportPage);
        });
        getDailyReportBtn.setFocusTraversable(false);
        bankerHomePage.add(getDailyReportBtn, 0 , 1);

        Button getUnreadReportsBtn = new Button();
        getUnreadReportsBtn.setText("Get unread reports");
        getUnreadReportsBtn.setOnAction(event -> {
            setupRecentsReportPage();
            primaryStage.getScene().setRoot(recentsReportPage);
        });
        getUnreadReportsBtn.setFocusTraversable(false);
        bankerHomePage.add(getUnreadReportsBtn, 1 , 1);

        Button closeButton = new Button("Back");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(welcomePage);
        });
        bankerHomePage.add(closeButton, 2, 1, 1, 1);
    }

    private void setupReportsListView(Text reportPageTitle, GridPane reportPage, ArrayList<Transaction> transactionsReports) {
        reportPage.add(reportPageTitle, 0,0);


        ListView<Transaction> listView = new ListView<>();
        listView.getItems().addAll(transactionsReports);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        reportPage.add(listView, 0, 1, 2, 1);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            primaryStage.getScene().setRoot(bankerHomePage);
        });
        reportPage.add(closeButton, 1, 2, 1, 1);
    }

    private void setupDailyReportPage(){
        setupBasicBankerAlignments(dailyReportPage);

        Text reportPageTitle = new Text("Daily Reports:");
        setupReportsListView(reportPageTitle, dailyReportPage, bankManagerAccount.getDailyReport(getAllTransactions()));
    }

    private void setupRecentsReportPage(){
        setupBasicBankerAlignments(recentsReportPage);

        Text reportPageTitle = new Text("Unread Reports:");
        setupReportsListView(reportPageTitle, recentsReportPage, bankManagerAccount.getUnreadReports(getAllTransactions()));
    }






    public void registerNewCustomer(Person newCustomer){
        customerAccounts.add(new CustomerAccount(newCustomer));
    }

    public CustomerAccount loginCustomer(Person personToLogIn){
        for (CustomerAccount customerAccount:
             customerAccounts) {
            if (customerAccount.getPerson().toString().equals(personToLogIn.toString())){
                return customerAccount;
            }
        }
        return null;
    }

    public BankManagerAccount loginManager(Person personToLogIn){
        if (bankManagerAccount.getPerson().toString().equals(personToLogIn.toString())){
            return bankManagerAccount;
        }
        return null;
    }

    public ArrayList<Transaction> getAllTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        for (CustomerAccount customerAccount :
                customerAccounts) {
            transactions.addAll(customerAccount.getTransactions());
        }
        return transactions;
    }

    public boolean findTransfersReceiverAcc(Float amount, Currency currency, int senderAccN, int senderRoutN, int receiverAccountN, int receiverRoutingN){
        for (CustomerAccount customerAcc :
                customerAccounts) {
            if (customerAcc.depositTransferAmount(amount, currency, senderAccN, senderRoutN, receiverAccountN, receiverRoutingN)){
                return true;
            }
        }
        return false;
    }
}
