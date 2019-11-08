package bank.View;

import javax.swing.*;
import java.awt.*;


    public class StockListView extends JPanel {

        private static final long serialVersionUID = 1L;

        private JLabel headerLabel;
        private JTable table;
        private JButton buyOrSell;
        private JButton stockPredict;



        public JButton getStockPredict() {
            return stockPredict;
        }

        public JButton getBuyOrSell() {
            return buyOrSell;
        }

        public JTable getTable() {
            return table;
        }

        public void setTable(JTable table) {
            this.table = table;

            JScrollPane scrollPane = new JScrollPane(table);
            Dimension d = table.getPreferredSize();
            scrollPane.setPreferredSize(
                    new Dimension(d.width,table.getRowHeight()*6+1));
            add(scrollPane, BorderLayout.CENTER);
        }

        public StockListView()
        {
            super(new BorderLayout());
            buyOrSell = new JButton("Buy/Sell Stock");
            stockPredict = new JButton("Predict");
            JPanel topPan = new JPanel(new BorderLayout());
            headerLabel = new JLabel("List of the stocks");
            topPan.add(headerLabel, BorderLayout.NORTH);
            topPan.add(Box.createVerticalStrut(10));
            topPan.add(buyOrSell, BorderLayout.WEST);
            topPan.add(stockPredict, BorderLayout.EAST);

            add(topPan, BorderLayout.NORTH);
        }



    }

