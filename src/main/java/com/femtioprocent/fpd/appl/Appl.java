package com.femtioprocent.fpd.appl;

import com.femtioprocent.fpd.sundry.S;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Base class for applications. Decode the argument and put all flags in a Map and the rest arguments in a list.
 */
public class Appl {

    public static HashMap<String, String> flags = new HashMap<String, String>();
    public static List argl = new ArrayList();
    public static String[][] help;

    public static void decodeArgs(String[] args, boolean dump, String[][] help) {
        flags.putAll(S.flagAsMap(args));
        argl = S.argAsList(args);
	Appl.help = help;
	
        if (dump) {
            System.err.println("" + flags + ' ' + argl);
        }

        if (flags.get("help") != null && help != null) {
	    helpOnStdout();
            System.exit(0);
        }
    }

    private static void helpOnStdout() {
	int max = 0;
	for (int i = 0; i < help.length; i++) {
	    if (help[i][0].length() > max) {
		max = help[i][0].length() + help[i][1].length() + 1;
	    }
	}
	for (int i = 0; i < help.length; i++) {
	    String h = help[i][0];
	    String a = help[i][1];
	    String hs;
	    if (a == null || a.length() == 0) {
		hs = S.padRight(h, max, ' ');
	    } else {
		hs = S.padRight(h + '=' + a, max, ' ');
	    }
	    System.err.println(hs + ' ' + help[i][2]);
	}
    }

    public static void decodeArgs(String[] args) {
        decodeArgs(args, false, null);
    }

    public static void decodeArgs(String[] args, boolean dump) {
        decodeArgs(args, dump, null);
    }

    public void main() {
    }

    public static void main(Appl appl) {
        appl.main();
    }

    public static void main(String[] args) {
        decodeArgs(args, true, new String[][]{
            new String[]{"help", "", "Show this help text"}
        });
        main(new Appl());
    }
}
