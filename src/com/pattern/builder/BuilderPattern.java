package com.pattern.builder;

class HtmlElement {
    public String name, text;
}

public class BuilderPattern {
    public static void main(String[] args) {
        String hello = "hello"; // this is immutable object
        System.out.println("<p>" + hello + "</p>");

        String[] words = {"Hello", "World"};

//        System.out.println(
//                "<ul>\n" + "<li>" + words[0]
//        );
        // String builder is used to build a string

        StringBuilder sb = new StringBuilder(); // Piece wise construction
        sb.append("<ul>\n");
        for (String word: words) {
            sb.append(String.format("  <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        System.out.println(sb);
    }
}
