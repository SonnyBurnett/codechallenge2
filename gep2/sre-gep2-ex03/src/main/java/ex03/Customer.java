package ex03;

public class Customer {
    private String name;
    private Orders ordersList;
    private Packages packagesList;

    @SuppressWarnings("unused")
    public Customer() {
        setName("None");
        this.ordersList = new Orders();
        this.packagesList = new Packages();
    }

    public Customer(String name) {
        setName(name);
        this.ordersList = new Orders();
        this.packagesList = new Packages();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orders getOrdersList() {
        return this.ordersList;
    }

    public void setOrdersList(Orders ordersList) {
        this.ordersList = ordersList;
    }

    public Packages getPackagesList() {
        return this.packagesList;
    }

    public void setPackagesList(Packages packagesList) {
        this.packagesList = packagesList;
    }

    public void addOrderToCustomer(Order aOrder) {
        this.ordersList.addOrder(aOrder);
    }

    public void addPackageToCustomer(Package aPackage) {
        this.packagesList.addPackage(aPackage);
    }

    public String toString() {
        return (this.name + this.ordersList.toString() + this.packagesList.toString());
    }

    public String generatePrintingLine() {
        return this.name + ", " + this.packagesList.toPrint();
    }
}