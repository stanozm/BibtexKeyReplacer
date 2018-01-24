/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pb007.bibtexkeyreplacer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXFormatter;

/**
 *
 * @author stano
 */
public class BibtexWriter {
    
    private Writer writer;

    public BibtexWriter(String filePath) throws IOException {
        writer = new FileWriter(filePath);
    }
      
    public void writeBibtexDatabase(BibTeXDatabase database) throws IOException {
       BibTeXFormatter bibtexFormatter = new BibTeXFormatter();
       bibtexFormatter.format(database, writer);
       writer.close();
    }
    
}
