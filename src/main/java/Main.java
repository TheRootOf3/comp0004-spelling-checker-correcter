/*
Spelling Checker and Correcter. Written in 2021 by Andrzej Szablewski for the COMP0004 coursework 1.

Besides checking spelling and suggesting possible correct words, this program also suggest a BEST correction.
User is able to choose which correction he/she wants to use or choose the best suggested correction.

Although this code has been organized using MVC concept mostly,
it also interacts with user when correcting words in the WordPredictor class.

Final class ReaderWriter is used to group commonly used IO methods in the whole program, all its methods are static.

The content of user input, files and dictionaries is loaded to custom data structure StringArray.
It is also used during word spelling checking. However, spell correcting os also performed using HashMaps.
StringArray has a sorting method in order to keep dictionaries sorted -> much faster access than using String Array contains method.

 */

public class Main {
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        ctrl.initDict(); //Dictionary initialization
        ctrl.actionSelection(); //Action selection and further operations
    }
}
