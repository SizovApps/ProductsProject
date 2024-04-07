package com.company;

import java.util.HashSet;
import java.util.Set;

class Product {

    private final String name;
    private final Set<Product> consistsFromProducts;
    private final Set<Product> requiredInProducts;

    public Product(String name) {
        this.name = name;
        this.consistsFromProducts = new HashSet<>();
        this.requiredInProducts = new HashSet<>();
    }

    public boolean addProduct(Product new_product) {
        if (canAddProduct(new_product, this)) {
            new_product.requiredInProducts.add(this);
            consistsFromProducts.add(new_product);
            return true;
        }
        return false;
    }

    private boolean canAddProduct(Product new_product, Product current_product) {
        if (current_product == new_product) {
            return false;
        }
        boolean canAdd = true;
        for (Product requiredInProduct: current_product.requiredInProducts) {
            canAdd &= canAddProduct(new_product, requiredInProduct);
        }
        return canAdd;
    }

    @Override
    public String toString() {
        return "Product: " + name;
    }
}


public class Main {

    public static void main(String[] args) {
        Product p1 = new Product("Тесто");
        Product p2 = new Product("Мука");
        Product p3 = new Product("Яйца");
        Product p4 = new Product("Вода");
        Product p5 = new Product("Пшеница");

        System.out.println(p1.addProduct(p2));
        System.out.println(p1.addProduct(p3));
        System.out.println(p1.addProduct(p4));
        System.out.println(p2.addProduct(p1));
        System.out.println(p2.addProduct(p5));
        System.out.println(p5.addProduct(p1));
    }
}
