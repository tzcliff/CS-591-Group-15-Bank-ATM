package bank.Controller;

import bank.PanelData;
import bank.View.TransactionListView;

public class TransactionController {
    private TransactionListView transactionListView;

    public TransactionController() {
        transactionListView = new TransactionListView();
    }

    public void transactionList() {
        transactionListView.setVisible(true);
        PanelData.setParentPanel(transactionListView);
        bindData();
    }

    private void bindData() {

    }
}
