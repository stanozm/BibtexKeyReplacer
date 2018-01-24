/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pb007.bibtexkeyreplacer;

import java.io.IOException;
import org.jbibtex.BibTeXDatabase;

/**
 *
 * @author stano
 */
public class KeyReplacer {
    
    
       public static void main(String[] args) throws IOException {
           validateArgs(args);
           BibTeXDatabase initialDatabase = new BibtexReader(args[0]).readBibtexDatabase();
           BibTeXDatabase processedDatabase = new BibtexProcessor(initialDatabase).replaceBibKeys();
           new BibtexWriter(args[1]).writeBibtexDatabase(processedDatabase);
    }

    private static void validateArgs(String[] args) {
        if (args.length !=2) {
            showHelp();
            System.exit(-1);
        }
        //TODO: add more detailed validation, e.g. file check
      
    }

    private static void showHelp() {
        System.out.println("The usage is java -jar BibtexKeyReplacer.jar [pathToInputBibFile] [PathToOutputBibFile]");
    }

    
}
