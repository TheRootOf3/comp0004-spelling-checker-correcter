# Spelling checker and correcter
This program is supposed to check and correct text given by the user. Beside suggesting correction,
program also predicts the best correction and show it to the user. It has been implemented mostly using custom `StringArray` data structure.

## QuickStart
This project uses Java JDK 15 and JUnit 5.4 for unit tests.\
In order to run it, run the `main` method from `Main.java` file.
1. Choose your dictionary source
    - Built-in 
    - From a file (if so, provide a path to your dictionary file)
2. Choose action you want to perform
    - Only check text from a terminal
    - Only check text from a file (if so, provide a path to your dictionary file)
    - Check and correct text from a terminal
    - Check and correct text from a file (if so, provide a path to your dictionary file)
3. Provide your input text
4. If you decided to also correct, follow instructions from the screen. 
5. If you decided to check and correct text from a file, provide a path to save corrected text

## Overview

### Input language
This code was design to work primarily with English dictionary and English input.
However, it has been modified, so the user can provide its own dictionary and check text written in a different language. 
 
### Dictionary
In order to give accurate results of checking a word, program contains two built-in English dictionaries.
- UNIX words dictionary
- bigger, ~400k words english dict from https://github.com/dwyl/english-words

If user wants to use its own dictionary (perhaps in a different language) he is able to load it from a file.
Dictionary format is fixed: Each line contains only one word, however, the dictionary does not necessarily need to be sorted.
Sorting is implemented as a method of StringArray data structure and can be called on it anytime.  

### Spelling checking
Spelling checking is done by providing program a dictionary and input. Every word from the input is checked and after checking
the list of wrong words is displayed to the user.

### Spelling correcting
There are two spelling suggestion methods used in this program.

#### User's decision
For every wrong word, the algorithm tries to find its closest possible correction. Assuming wrong spelling 
may happen due to the misslick program is performing 4 operations on a wrong word:
- Checking whether any letter is missing
- Checking whether there is an extra letter
- Checking whether there is any letter replaced with a wrong one
- Checking whether two letters have been swapped
After creating all possible combinations, the algorithm checks for them in a dictionary.
Correct suggestions are presented to the user and choosing the proper one is the user's decision.  

#### Best match
The best match method works only for english words and is based on the word frequency analysis.
For this feature I used data from SymSpell GitHub repo: https://github.com/wolfgarbe/SymSpell

## Code Structure
Although this code has been organized using MVC concept mostly, 
it also interacts with a user when correcting words in the WordPredictor class.

Final class `ReaderWriter` is used to group commonly used IO methods in the whole program, all its methods are static.

The content of user input, files and dictionaries is loaded to custom data structure `StringArray`.
It is also used during word spelling checking. However, spell correcting os also performed using `HashMap`s.
`StringArray` has a sorting method in order to keep dictionaries sorted `->` much faster access than using `StringArray` `contains` method.

## About author
This is my first Java project.
Written in 2021 by Andrzej Szablewski for the UCL CS COMP0004 coursework 1.
