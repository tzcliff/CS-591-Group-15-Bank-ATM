package bank;

import java.util.ArrayList;

public class StockMarket {
    ArrayList<Stock> stocks;
    int numsOfStocks;

    StockMarket() {
        stocks = new ArrayList<>();
        numsOfStocks = 0;
    }

    public void addStock(String name, int totalShares, float pricePerShare) {
        stocks.add(new Stock(pricePerShare, totalShares, totalShares, name, "", ""));
    }

    public boolean deleteStock(String name) {
        int temp = stockExists(name);
        if (temp != -1){
            stocks.remove(temp);
            return true;
        }
        return false;

    }

    private int stockExists(String name){
        for (int i = 0; i < stocks.size(); i ++){
            if (stocks.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean changeStockPrice(String name, Float newPrice) {
        int temp = stockExists(name);
        if (temp != -1){
            stocks.get(temp).setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean changeStockCurrentShare(String name, int newShare) {
        int temp = stockExists(name);
        if (temp != -1){
            stocks.get(temp).setCurrentlyAvailableShares(newShare);
            return true;
        }
        return false;
    }

}
