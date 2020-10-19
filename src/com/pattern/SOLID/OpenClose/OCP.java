package com.pattern.SOLID.OpenClose;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED,
    GREEN,
    BLUE
}


enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter {

    // filter by color
    public Stream<Product> filterByColor (List<Product> products, Color color) {
        return products.stream().filter(p-> p.color == color);
    }

    // filter by size
    public Stream<Product> filterBySize (List<Product> products, Size size) {
        return products.stream().filter(p-> p.size == size);
    }

    // filter both by color and size
    public Stream<Product> filterByColorAndSize (List<Product> products, Size size, Color color) {
        return products.stream().filter(p-> p.size == size && p.color == color);
    }
}

public class OCP {
}
