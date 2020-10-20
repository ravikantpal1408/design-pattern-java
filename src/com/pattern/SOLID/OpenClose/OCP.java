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

interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color ; // return true/false based on specification
    }
}


class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(p->specification.isSatisfied(p));
    }
}


class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size; // return true-false based on specification
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class AndSpecification<T> implements Specification<T> {

    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return  first.isSatisfied(item) && second.isSatisfied(item);
    }
}

// here in this scenario we are violating the principal of open-close .
// Now we will fix this using specification pattern
public class OCP {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old)");
        pf.filterByColor(products, Color.GREEN).forEach(p-> System.out.println(" - " + p.name + " is green "));


        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new)");
        bf.filter(products, new ColorSpecification(Color.GREEN)).forEach(
                p->System.out.println(" - " + p.name + " is green ")
        );


        System.out.println("Large blue items:");
        bf.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
        )).forEach(p -> System.out.println(" - " + p.name + " is large and blue"));
    }
}
