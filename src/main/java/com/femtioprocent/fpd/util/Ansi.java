package com.femtioprocent.fpd.util;

import java.util.Stack;

/**
 * Write escape sequences for colored output.
 *
 * A stack keep track of pushed color state, look in main at end for examples.
 * <p/>
 * Default is to have color off. The environment variable CARMEN_MAVENPLUGIN_OPTION=color
 * will turn on color.
 *
 * @author larsno
 */
public class Ansi {
    static boolean on = !false;
    private static Stack<String> stack = new Stack<String>();

    static {
        stack.push("\033[00m"); // NOTE! do not call this.push(s) (fails if on is false as default)
        if (true /*MavenUtil.isOptionDefined("color")*/) {
            on = true;
        }
    }

    public static String inverse(String s) {
        return on ? "\u001b[7m" + s + "\u001b[0m" : "";
    }

    public static String inverse() {
        return on ? "\u001b[7m" : "";
    }

    public static String normal() {
        return on ? "\u001b[0m" : "";
    }

    public static String green(String s) {
        return on ? "\033[01;32m" + s + "\033[00m" : s;
    }

    public static String blue(String s) {
        return on ? "\033[01;34m" + s + "\033[00m" : s;
    }

    public static String red(String s) {
        return on ? "\033[01;31m" + s + "\033[00m" : s;
    }

    public static String magenta(String s) {
        return on ? "\033[01;35m" + s + "\033[00m" : s;
    }

    public static String magentaBlack(String s) {
        return on ? "\033[01;40;35m" + s + "\033[00m" : s;
    }

    public static String yellowBlack(String s) {
        return on ? "\033[01;40;33m" + s + "\033[00m" : s;
    }

    public static String cyan(String s) {
        return on ? "\033[01;36m" + s + "\033[00m" : s;
    }

    public static String white(String s) {
        return on ? "\033[01;37m" + s + "\033[00m" : s;
    }

    public static String yellow(String s) {
        return on ? "\033[01;33m" + s + "\033[00m" : s;
    }

    public static String greenBG(String s) {
        return on ? "\033[01;42m" + s + "\033[00m": s;
    }

    public static String cyanBG(String s) {
        return on ? "\033[01;46m" + s + "\033[00m": s;
    }

    public static String greenP(String s) {
        return on ? push(green()) + s : s;
    }

    public static String blueP(String s) {
        return on ? push(blue()) + s : s;
    }

    public static String redP(String s) {
        return on ? push(red()) + s : s;
    }

    public static String magentaP(String s) {
        return on ? push(magenta()) + s : s;
    }

    public static String cyanP(String s) {
        return on ? push(cyan()) + s : s;
    }

    public static String whiteP(String s) {
        return on ? push(white()) + s : s;
    }

    public static String yellowP(String s) {
        return on ? push(yellow()) + s : s;
    }

    public static String green() {
        return on ? "\033[01;32m" : "";
    }

    public static String yellow() {
        return on ? "\033[01;33m" : "";
    }

    public static String white() {
        return on ? "\033[01;37m" : "";
    }

    public static String blue() {
        return on ? "\033[01;34m" : "";
    }

    public static String red() {
        return on ? "\033[01;31m" : "";
    }

    public static String magenta() {
        return on ? "\033[01;35m" : "";
    }

    public static String cyan() {
        return on ? "\033[01;36m" : "";
    }

    public static String greenBG() {
        return on ? "\033[01;42m" : "";
    }

    public static String yellowBG() {
        return on ? "\033[01;43m" : "";
    }

    public static String whiteBG() {
        return on ? "\033[01;47m" : "";
    }

    public static String blueBG() {
        return on ? "\033[01;44m" : "";
    }

    public static String redBG() {
        return on ? "\033[01;41m" : "";
    }

    public static String magentaBG() {
        return on ? "\033[01;45m" : "";
    }

    public static String cyanBG() {
        return on ? "\033[01;46m" : "";
    }

    public static String plain() {
        return on ? "\033[00m" : "";
    }

    /**
     * Push the string on a stack if color is on
     *
     * Used for color management, see main method for examples
     *
     * @param s
     * @return
     */
    public static String push(String s) {
        if (on) {
            stack.push(s);
        }
        return s;
    }

    /**
     * Pop the string from a stack if color is on
     *
     * @return
     */
    public static String pop() {
        if (on) {
            if (stack.size() > 1) {
                String s = stack.pop();
            }
            return stack.peek();
        }
        return "";
    }

    /**
     * Pop the string from a stack and append s if color is on
     *
     * @param s
     * @return
     */
    public static String pop(String s) {
        if (on) {
            String _ = stack.pop();
            return stack.peek() + s;
        }
        return s;
    }

    /**
     * top of stack with no side effect
     *
     * @return
     */
    public static String peek() {
        if (on) {
            return stack.peek();
        }
        return "";
    }

    public static void main(String[] args) {
        Ansi.on = true;//args.length > 0;

        System.out.println("000");

        System.out.println(
                "svart" +
                Ansi.push(red()) +
                "red" +
                Ansi.pop() +
                "svart");

        System.out.println("svart" +
                Ansi.push(red()) + "red" +
                Ansi.push(green()) + "green" +
                Ansi.pop() + "still red" +
                Ansi.pop() + "svart");

        System.out.println("svart" +
                Ansi.push(red()) + "red" +
                Ansi.push(green()) + "green" +
                Ansi.blue("blå") +
                "svart" +
                Ansi.pop() + "still red" +
                Ansi.pop() + "svart");

        System.out.println(
                "svart" +
                Ansi.redP("RED") +
                "red" +
                Ansi.greenP("GREEN") +
                "green" +
                Ansi.pop("RED") +
                "still red" +
                Ansi.pop("SVART") +
                "svart");
    }
}
