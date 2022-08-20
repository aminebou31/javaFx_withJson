//Alex Boban 200477655
package com.example.w22comp1011gctest2student;

public class Product {
  public int getId() {
    return id;
  }


  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public Double getRegularPrice() {
    return regularPrice;
  }

  public Double getSalePrice() {
    return salePrice;
  }

  public String getImage() {
    return image;
  }

  public String sku;
  public String name;

  @Override
  public String toString() {
    return name+"-$"+salePrice;
  }
  public int id;
  public Double regularPrice;
  public Double salePrice;
  public String image;

  }
