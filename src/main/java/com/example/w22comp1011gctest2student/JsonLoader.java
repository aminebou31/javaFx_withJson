//Alex Boban 200477655
package com.example.w22comp1011gctest2student;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonLoader {
    public List<Customer> loadData(){
        List<Customer> customers = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("customers.json"));
            JsonObject jsonObject = new Gson().fromJson(reader, new TypeToken<JsonObject>() {}.getType());
            JsonArray listOfElemens = jsonObject.get("Customers").getAsJsonArray();
            listOfElemens.forEach((jsonElement -> {
                JsonObject jsonObj = jsonElement.getAsJsonObject();
                Customer customer = new Customer();
                customer.id = jsonObj.get("id").getAsInt();
                customer.firstName=jsonObj.get("firstName").getAsString();
                customer.lastName=jsonObj.get("lastName").getAsString();
                customer.phone=jsonObj.get("phoneNumber").getAsString();
                customer.priceString= String.valueOf(customer.totalPurchases());
                JsonArray listOfProducts = jsonObj.get("purchases").getAsJsonArray();
                listOfProducts.forEach((elementObject)->{
                    JsonObject objectProduct = elementObject.getAsJsonObject();
                    Product product = new Product();
                    product.id=objectProduct.get("id").getAsInt();
                    product.sku=objectProduct.get("sku").getAsString();
                    product.name=objectProduct.get("name").getAsString();
                    product.salePrice=objectProduct.get("salePrice").getAsDouble();
                    product.regularPrice=objectProduct.get("regularPrice").getAsDouble();
                    product.image=objectProduct.get("image").getAsString();
                    customer.products.add(product);
                });
                customers.add(customer);
            }));
            System.out.println(customers.size());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customers;
    }
}
