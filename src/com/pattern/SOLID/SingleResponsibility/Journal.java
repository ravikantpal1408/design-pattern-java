package com.pattern.SOLID.SingleResponsibility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// Single Responsibility - Separation of Concern
class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String txt) {
        entries.add("" + (++count) + " : " + txt);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    public void save(String filename) throws FileNotFoundException {

    }

    public void load(String filename) {}

    public void load(URL url) {}
}

class Persistence {
    public void saveToFile(Journal journal, String filename, boolean overwrite ) throws FileNotFoundException{
        if(overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)){
                out.println(toString());
            }
        }

    }
}

class Demo {
    public static void main(String[] args) throws Exception {
        Journal j = new Journal();
        j.addEntry("I Cried today !!");
        j.addEntry("I ate a bug !!");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "/Volumes/ravi128/PROJECTS/DesignPattern/src/com/pattern/SOLID/journal.txt";
        p.saveToFile(j, filename, true);

        Runtime.getRuntime().exec(new String[]{"/Volumes/ravi128/PROJECTS/DesignPattern/src/com/pattern/SOLID ", "-c", "ls > "+ filename});
    }
}
