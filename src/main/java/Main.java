/*
Spelling Checker and Correcter. Written in 2021 by Andrzej Szablewski for the COMP0004 coursework 1.
 */

public class Main {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        ctrl.initDict(); //Dictionary initialization
        ctrl.actionSelection(); //Action selection and further operations
    }
}
