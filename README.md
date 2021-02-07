# Spelling checker and correcter

## Overview
This program is supposed to check and correct text given by the user. Beside suggesting correction,
program also predicts the best correction and show it to the user.

### Dictionary
In order to give accurate results of checking a word, program contains two built-in dictionaries.
If user wants to use its own dictionary (perhaps in a different language) he is able to load it from a file.
Dictionary format is fixed: Each line contains only one word, however, the dictionary does not necessarily need to be sorted.
Sorting is implemented as a method of StringArray data structure and can be called on it anytime.  

### Spelling checking
Spelling checking is done by providing program a dictionary  
 