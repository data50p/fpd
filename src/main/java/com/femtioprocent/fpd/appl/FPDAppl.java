package com.femtioprocent.fpd.appl;

import com.femtioprocent.fpd.sundry.S;

public class FPDAppl extends Appl {

    @Override
    public void main() {
        System.err.println("main() in FPDAppl: " + Appl.flags + ' ' + Appl.argl);
    }

    public static void main(String[] args) {
        decodeArgs(args);
        main(new FPDAppl());
    }
}
