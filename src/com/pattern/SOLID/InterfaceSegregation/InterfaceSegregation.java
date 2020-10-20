package com.pattern.SOLID.InterfaceSegregation;

// Separate interface for specific purpose and need

class Document {

}

interface Machine {
    void print(Document d);
    void fax(Document d) throws Exception;
    void scan(Document d) throws Exception;
}

// ok if you need a multifunction
class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}

class OldFashionedPrinter implements Machine {

    @Override
    public void print(Document d) {
        // yep
    }

    @Override
    public void fax(Document d) throws Exception {
        // Do not required
        throw new Exception();
    }

    @Override
    public void scan(Document d) throws Exception {
        // Not required
        throw  new Exception();
    }
}

interface Printer {
    void Print(Document d) throws Exception;
}

interface IScanner {
    void Scan(Document d) throws Exception;
}

class JustAPrinter implements Printer, IScanner {

    @Override
    public void Print(Document d) throws Exception {

    }

    @Override
    public void Scan(Document d) throws Exception {

    }
}

class MultiFunctionDevice implements Printer, IScanner {

    @Override
    public void Print(Document d) throws Exception {

    }

    @Override
    public void Scan(Document d) throws Exception {

    }
}
class MultiFunctionMachine extends MultiFunctionDevice {
    private Printer printer;
    private IScanner scanner;

    public MultiFunctionMachine(Printer printer, IScanner scanner)
    {
        this.printer = printer;
        this.scanner = scanner;
    }

    public void Print(Document d) throws Exception
    {
        printer.Print(d);
    }

    public void Scan(Document d) throws Exception
    {
        scanner.Scan(d);
    }
}


public class InterfaceSegregation {

}
