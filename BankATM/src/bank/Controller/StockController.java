package bank.Controller;

import bank.*;
import bank.View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StockController {
    private StockListView stockListView;
    private StockBuyView stockBuyView;
    private StockSellView stockSellView;
    private StockPeek stockPeekView;
    private ManagerStockView managerStockView;
    private changeStockView changeStockView;
    public StockController() {
        stockListView = new StockListView();
        //stockTransactionView = new StockTransactionView();
       // stockPredictView = new StockPredict();
        managerStockView = new ManagerStockView();
        //changeStockView = new changeStockView();
        initController();
    }
    public void StockList()
    {
       stockListView.setVisible(true);
        PanelData.setParentPanel(stockListView);
        bindData();
    }

    public void newStockView()
    {
        managerStockView.setVisible(true);
        PanelData.setParentPanel(managerStockView);

    }

    public void stockPredictView() {
        stockPeekView = new StockPeek();
        stockPeekView.getSaveButton().addActionListener(l ->predict());
        stockPeekView.setVisible(true);
        PanelData.setParentPanel(stockPeekView);
    }

    public void bindData(){




            String col[] = {"Stock Name","Current available share ","Total share","Price"};
            DefaultTableModel tableModel = new DefaultTableModel(col, 0);




                for (int i = 0; i < Data.getStockMarket().getStocks().size(); i++)
                {
                    Object[] objs = { Data.getStockMarket().getStocks().get(i).getName(),
                            Data.getStockMarket().getStocks().get(i).getCurrentlyAvailableShares()
                            , Data.getStockMarket().getStocks().get(i).getTotalShares()
                            , Data.getStockMarket().getStocks().get(i).getCurrentPrice()

                            };
                    tableModel.addRow(objs);
                }




            JTable table = new JTable(tableModel);
            table.setFillsViewportHeight(true);



           stockListView.setTable(table);


        }



    public void initController()
    {
        stockListView.getBuy().addActionListener(l ->stockTransactionBuy());
        stockListView.getSell().addActionListener(l ->stockTransactionSell());
        //stockTransactionView.getSaveButton().addActionListener(l ->sellOrBuyStock());
       // stockPredictView.getSaveButton().addActionListener(l ->predict());
        managerStockView.getSaveButton().addActionListener(l ->newStock());
        stockListView.getStockPeekProfit().addActionListener(l ->stockPredictView());
        managerStockView.getChangeStockButton().addActionListener(l ->changeStockView());
        //changeStockView.getSaveButton().addActionListener(l ->changeStock());

    }

    private void changeStock () {
        String name =(String) changeStockView.getStockComboBox().getSelectedItem();
        int totalShare = Integer.parseInt(changeStockView.getTotalShareTextField().getText());
        int current = Integer.parseInt(changeStockView.getCurrentTextField().getText());
        float price = Float.parseFloat(changeStockView.getPriceTextField().getText());
        if(Data.getStockMarket().changeStockTotalShare(name, totalShare) && Data.getStockMarket().changeStockCurrentShare(name, current) && Data.getStockMarket().changeStockPrice(name, price )){
            changeStockView.setMsgLabel("Successfully changed the attributes of the selected stock");
        }

    }

    private void changeStockView() {
        changeStockView = new changeStockView();
        changeStockView.getSaveButton().addActionListener(l ->changeStock());
        changeStockView.setVisible(true);
        PanelData.setParentPanel(changeStockView);
    }

    private void stockTransactionBuy() {
        stockBuyView = new StockBuyView();
        stockBuyView.getSaveButton().addActionListener(l ->BuyStock());
        stockBuyView.setVisible(true);
        PanelData.setParentPanel(stockBuyView);
    }

    private void stockTransactionSell() {
        stockSellView = new StockSellView();
        stockSellView.getSaveButton().addActionListener(l ->SellStock());
        stockSellView.setVisible(true);
        PanelData.setParentPanel( stockSellView);
    }

    private void BuyStock() {
        String name = (String) stockBuyView.getStockc().getSelectedItem();
        int amount = Integer.parseInt(stockBuyView.getAmountTextField().getText());

        int newCurrent = Data.getStockMarket().getStockByName(name).getCurrentlyAvailableShares() - amount;

        if (newCurrent >= 0) {
            LoggedUser.getProfile().getSecurityAccount().addNewSharesToStock(Data.getStockMarket().getStockByName(name), amount);
            Data.getStockMarket().changeStockCurrentShare(name, newCurrent);
            stockBuyView.setMsgLabel("Successfully buy stock!");
        }
        else {
            stockBuyView.setMsgLabel("The amount of share you wanna buy is beyond the total share! ");
        }


    }

    private void SellStock() {
        String name = (String) stockSellView.getStockc().getSelectedItem();
        int amount = Integer.parseInt(stockSellView.getAmountTextField().getText());

        if (amount <= LoggedUser.getProfile().getSecurityAccount().getBoughtStockByName(name).getAmountOfStocks()) {
                Float profit = LoggedUser.getProfile().getSecurityAccount().sellSharesOfStock(LoggedUser.getProfile().getSecurityAccount().getBoughtStockByName(name).getStock(), amount);
                stockSellView.setMsgLabel("The profit you made is: " + profit);

        } else {
            stockSellView.setMsgLabel("The amount of share you wanna sell is beyond the share you have! ");
        }

    }

    private void predict() {
        //stockPredictView.setMsgLabel();
    }

    private void newStock() {
        String name = managerStockView.getNameTextField().getText();
        int totalShare = Integer.parseInt(managerStockView.getTotalShareTextField().getText());
        int current = Integer.parseInt(managerStockView.getCurrentAvailableShareTextField().getText());
        float price = Float.parseFloat(managerStockView.getPriceTextField().getText());
        Data.getStockMarket().addStock(name, totalShare, current, price);
        managerStockView.setMsgLabel("Successfully create a new stock");

    }
}
