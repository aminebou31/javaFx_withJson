//Alex Boban 200477655
package com.example.w22comp1011gctest2student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class TableViewController implements Initializable {
    @FXML
    private Label saleLabel;

    @FXML
    private Label msrpLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label rowsInTableLabel;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> totalPurchaseColumn;

    @FXML
    private ListView<Product> purchaseListView;

    @FXML
    private ImageView imageView;

    private ObservableList<Customer> data = FXCollections.observableArrayList();
    private ObservableList<Product> dataProduct = FXCollections.observableArrayList();
    @FXML
    private void top10Customers()
    {
        System.out.println("called method top10Customers()");
        totalRows=0;
        data= FXCollections.observableArrayList();
        JsonLoader gsonService = new JsonLoader();
        customers = gsonService.loadData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("priceString"));
        Collections.sort(customers, Comparator.comparingDouble(Customer ::totalPurchases).reversed());
        for (Customer customer :customers) {
            if (totalRows==10)
                break;
            data.add(customer);
            totalRows++;
        }
        tableView.setItems(data);
        rowsInTableLabel.setText("Rows in table: "+totalRows);
    }

    @FXML
    private void customersSavedOver5()
    {
        totalRows=0;
        System.out.println("called method customersSavedOver5()");
        data= FXCollections.observableArrayList();
        JsonLoader gsonService = new JsonLoader();
        customers = gsonService.loadData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("priceString"));
        for (Customer customer :customers) {
            if(customer.customerGoodSavings()){
                data.add(customer);
                totalRows++;
            }

        }
        tableView.setItems(data);
        rowsInTableLabel.setText("Rows in table: "+totalRows);
    }

    int totalRows=0;

    List<Customer> customers =new ArrayList<>();
    @FXML
    private void loadAllCustomers()
    {
        totalRows=0;
        System.out.println("called method loadAllCustomers");
        data= FXCollections.observableArrayList();
        JsonLoader gsonService = new JsonLoader();
        customers = gsonService.loadData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("priceString"));
        for (Customer customer :customers) {
            data.add(customer);
            totalRows++;
        }
        tableView.setItems(data);
        rowsInTableLabel.setText("Rows in table: "+totalRows);
    }
    public void customersProducts(Event event){
        Customer customer = tableView.getFocusModel().getFocusedItem();
        List<Product> products = new ArrayList<>();
        dataProduct=FXCollections.observableArrayList();
        if(customer.products.size() == 0){
            System.out.println("no products purchased");
        }else{
            products = customer.products;
        }
        for(Product product:products){
            dataProduct.add(product);
        }
        this.purchaseListView.setItems(dataProduct);
    }

    public void productsImage(Event event){
        Product p = purchaseListView.getFocusModel().getFocusedItem();
        Image image = new Image(p.getImage());
        imageView.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllCustomers();
        tableView.setOnMouseClicked(this::customersProducts);
        purchaseListView.setOnMouseClicked(this::productsImage);
    }
}
