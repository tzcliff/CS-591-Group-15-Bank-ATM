package bank.View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import bank.*;

public class StockTransactionView extends JPanel{

    private JComboBox transTypeDd;
    private JLabel transTypeLabel;
    private JComboBox<Stock> stockc;
    private JLabel stockl;

    private JLabel amountLabel;
    private JFormattedTextField amountTextField;
    private JButton saveButton;
    private JLabel msgLabel;

    private DecimalFormatSymbols dfs;
    private DecimalFormat dFormat;

    public StockTransactionView()
    {
        super(new BorderLayout());

        dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.'); //separator for the decimals
        dfs.setGroupingSeparator(','); //separator for the thousands
        dFormat = new DecimalFormat ("#0.##", dfs);


        transTypeLabel = new JLabel("Stock Transaction Type", JLabel.TRAILING);
        transTypeDd = new JComboBox(StockTransactionType.values());
        transTypeLabel.setLabelFor(transTypeDd);

        stockl = new JLabel("Stock", JLabel.TRAILING);
        stockc = new JComboBox<Stock>();
        stockl.setLabelFor(stockc);
//        for (var acc : LoggedUser.getProfile().getCheckingAccounts()) {
//            accNameDd.addItem(acc);
//        }
//        for (var acc : LoggedUser.getProfile().getSavingsAccounts()) {
//            accNameDd.addItem(acc);
//        }

        for (int i = 0; i< StockMarket.getStocks().size(); i++) {
            stockc.addItem(StockMarket.getStocks().get(i));
        }

        amountLabel = new JLabel("Amount", JLabel.TRAILING);
        amountTextField = new JFormattedTextField(dFormat);
        amountLabel.setLabelFor(amountTextField);

        saveButton = new JButton("Save");
        msgLabel = new JLabel();

        JPanel jp = new JPanel(new SpringLayout());

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jp.add(stockl);
        jp.add(stockc);
        jp.add(transTypeLabel);
        jp.add(transTypeDd);
        jp.add(amountLabel);
        jp.add(amountTextField);

        SpringUtilities.makeCompactGrid(jp, 3, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad

        this.add(jp, gbc);
        this.add(saveButton,gbc);
        this.add(msgLabel, gbc);
    }

    public JComboBox getTransTypeDd() {
        return transTypeDd;
    }

    public void setTransTypeDd(JComboBox transTypeDd) {
        this.transTypeDd = transTypeDd;
    }

    public JComboBox<Stock> getStockc() {
        return stockc;
    }

    public JLabel getStockl() {
        return stockl;
    }

    public JFormattedTextField getAmountTextField() {
        return amountTextField;
    }

    public void setAmountTextField(JFormattedTextField amountTextField) {
        this.amountTextField = amountTextField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JLabel getMsgLabel() {
        return msgLabel;
    }

    public void setMsgLabel(String msgLabel) {
        this.msgLabel.setText(msgLabel);
    }


}
