# BibtexKeyReplacer
Tool for replacing citation keys in the bib file.

The new key has following format: lastname1-(lastname2)-year. 

The generated keys are unique for given bib file. In case of match for given key a "_X" suffix is appended.

## Usage
java -jar BibtexKeyReplacer.jar pathToInputBibFile pathToOutputBibFile

It requires jbibtex library on classpath or in the lib folder 
