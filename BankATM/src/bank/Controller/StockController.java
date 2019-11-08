package bank.Controller;

import bank.LoggedUser;
import bank.PanelData;
import bank.StockMarket;
import bank.View.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class StockController {
    private StockListView stockListView;
    private StockTransactionView stockTransactionView;
    private StockPredict stockPredictView;
    private ManagerStockView managerStockView;
    public StockController() {
        stockListView = new StockListView();
        stockTransactionView = new StockTransactionView();
        stockPredictView = new StockPredict();
        managerStockView = new ManagerStockView();
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
        PanelData.setParentPanel(stockListView);

    }

    public void stockPredictView() {
        stockPredictView.setVisible(true);
        PanelData.setParentPanel(stockPredictView);
    }

    public void bindData(){




            String col[] = {"Stock Name","Current available share ","Total share","Price"};
            DefaultTableModel tableModel = new DefaultTableModel(col, 0);




                for (int i = 0; i < StockMarket.getStocks().size(); i++)
                {
                    Object[] objs = { StockMarket.getStocks().get(i).getName(),
                            StockMarket.getStocks().get(i).getCurrentlyAvailableShares()
                            , StockMarket.getStocks().get(i).getTotalShares()
                            , StockMarket.getStocks().get(i).getCurrentPrice()

                            };
                    tableModel.addRow(objs);
                }




            JTable table = new JTable(tableModel);
            table.setFillsViewportHeight(true);



           stockListView.setTable(table);


        }



    public void initController()
    {
        stockListView.getBuyOrSell().addActionListener(l ->stockTransaction());
        stockTransactionView.getSaveButton().addActionListener(l ->sellOrBuyStock());
        stockPredictView.getSaveButton().addActionListener(l ->predict());
        managerStockView.getSaveButton().addActionListener(l ->newStock());
        stockListView.getStockPredict().addActionListener(l ->stockPredictView());
        managerStockView.getChangeStockButton().addActionListener(l ->changeStockView());

    }

    private void changeStockView() {

    }

    private void stockTransaction() {
        stockTransactionView.setVisible(true);
        PanelData.setParentPanel(stockTransactionView);
    }

    private void sellOrBuyStock() {

    }

    private void predict() {
        //stockPredictView.setMsgLabel();
    }

    private void newStock() {

    }
}
