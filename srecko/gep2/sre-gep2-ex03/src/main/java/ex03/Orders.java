package ex03;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Orders {
    private  List<Order> ordersList;

    public Orders() {
        ordersList = new LinkedList<>();
    }

    public  void addOrder(Order aOrder) {
        this.ordersList.add(aOrder);
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder("\n" + this.ordersList.size() + "\n");
        for (Order item : this.ordersList) {
            toReturn.append(item.getProduct()).append(", ");
            toReturn.append(item.getPrice()).append(", ");
            toReturn.append(item.getWeight()).append(", ");
            toReturn.append(item.getCountry());
            toReturn.append("\n");
        }
        return toReturn.toString();
    }

    public Double sumOrdersWeight(){
        return ordersList.stream()
                .map(Order::getWeight)
                .reduce(0.0, Double::sum);
    }

    public String ordersCountry(){
        //noinspection OptionalGetWithoutIsPresent
        return ordersList.stream()
                .collect(Collectors.groupingBy(Order::getCountry, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
}