package ex03;

import java.util.LinkedList;
import java.util.List;

public class Packages {
    private List<Package> packagesList;

    public Packages() {
        packagesList = new LinkedList<>();
    }

    public  void addPackage (Package aPackage){
        packagesList.add(aPackage);
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder("\n" + packagesList.size() + "\n");
        for (Package aPackage : packagesList) {
            toReturn.append(aPackage.getShipper()).append(", ");
            toReturn.append(aPackage.getDuration().toString()).append(", ");
            toReturn.append(aPackage.getShippingCost().toString()).append(", ");
            toReturn.append(aPackage.getTotalWeight().toString());
            toReturn.append("\n");
        }
        return toReturn.toString();
    }

    public String toPrint(){
        StringBuilder toReturn = new StringBuilder();
        for (Package aPackage : packagesList) {
            toReturn.append(aPackage.getShipper()).append(", ");
            toReturn.append(aPackage.getDuration().toString()).append(", ");
            toReturn.append(aPackage.getShippingCost().toString());
        }
        return toReturn.toString();
    }

    @SuppressWarnings("unused")
    public Integer size() {
        return packagesList.size();
    }
}