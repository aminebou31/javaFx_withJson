//Alex Boban 200477655
package com.example.w22comp1011gctest2student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Customer {
    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public List<Product> getProducts() {
        return products;
    }
    public int id;
    public String firstName;
    public String lastName;
    public String phone;
    public List<Product> products=new ArrayList<>();
    public String priceString;
    public Double totalPurchases(){
        List<Double> total = new ArrayList<>();
        for (Product p: this.products) {
            total.add(p.salePrice);
        }
        Double result = total.stream().collect(Collectors.summingDouble(Double::doubleValue));
        return result;
    }
    public Double totalSaved(){
        List<Double> total = new ArrayList<>();
        for (Product p: this.products) {
            total.add(p.regularPrice-p.salePrice);
        }
        Double result = total.stream().collect(Collectors.summingDouble(Double::doubleValue));
        return result;
    }
    public boolean customerGoodSavings(){
        if(totalSaved()>=5) {
            return true;
        }
        return false;
    }
    public String getPriceString() {
        return this.totalPurchases()+" $";
    }
}
