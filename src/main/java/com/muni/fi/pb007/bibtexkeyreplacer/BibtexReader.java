/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pb007.bibtexkeyreplacer;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXParser;
import org.jbibtex.CharacterFilterReader;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

/**
 *
 * @author stano
 */
public class BibtexReader {

    Reader reader;

    public BibtexReader(String filePath) {
        try {
            reader = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
           // reader = new FileReader(filePath);
        } catch (IOException ex) {
            System.out.println("File not found.");
        }
    }

    public BibTeXDatabase readBibtexDatabase() {
        BibTeXDatabase parsedDatabase = null;
        try {
            CharacterFilterReader filterReader = new CharacterFilterReader(reader); 
            BibTeXParser parser = new BibTeXParser();
           // parsedDatabase = parser.parseFully(filterReader);
           parsedDatabase = parser.parse(reader);
        } catch (ParseException ex) {
            Logger.getLogger(BibtexReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TokenMgrException ex) {
            Logger.getLogger(BibtexReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDatabase;
    }

}
