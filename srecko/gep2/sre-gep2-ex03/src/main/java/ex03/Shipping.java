package ex03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Shipping implements ReadFileInterface, WriteFileInterface {
    private String fileName;
    private static Customers customers;

    public Shipping(){
        customers = new Customers();
    }

    public void shippingInfo() {
        shippingInfo("003-experts-inputs.csv",
                "output.csv",
                "gep2/sre-gep2-ex03/src/main/resources/");
    }

    public void shippingInfo(String inPutFile, String outPutFile, String path) {
        try {
            load(path + inPutFile);
            printShippingLabels();
            write(path + outPutFile);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Generating shipping information failed: " + exception.toString());
        }
    }

    public void printShippingLabels() {
        customers.generateCustomersPackage();
    }

    public void load(String fileName) {
        setFileName(fileName);
        try {
            readFile();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Reading failed: " + exception.toString());
        }
    }

    public void write(String fileName) {
        setFileName(fileName);
        try {
            createFile();
            writeFile("CustomerId, Name, Shipper, Duration, ShippingCost\n");
            writeFile(customers.generatePrintingLines());
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Loading failed: " + exception.toString());
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void readFile() throws IOException {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String rawValueLine = scanner.nextLine();
                String[] values = rawValueLine.split(",\\s*");

                customers.addCustomer(values[0], values[1]);
                Order order = new Order(values[2],
                        Double.parseDouble(values[3]),
                        Double.parseDouble(values[4]),
                        values[5]);
                customers.addCustomerOrder(values[0], order);
            }
            scanner.close();
        } catch (Exception error) {
            throw new IOException("Read error from " + fileName);
        }
    }

    public void writeFile(String outPut) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);

            for (int i = 0; i < outPut.length(); i++)
                fileWriter.write(outPut.charAt(i));

            fileWriter.close();

        } catch (Exception error) {
            throw new IOException("Write error to " + fileName);
        }
    }

    public void createFile() throws IOException {
        try {
            new FileWriter(fileName);
        } catch (Exception error) {
            throw new IOException("Error creating " + fileName);
        }
    }
}