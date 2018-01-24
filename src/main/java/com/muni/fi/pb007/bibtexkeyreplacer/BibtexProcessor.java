/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pb007.bibtexkeyreplacer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.KeyValue;
import org.jbibtex.Value;

/**
 *
 * @author stano
 */
public class BibtexProcessor {

    private BibTeXDatabase oldDatabase; 
    private BibTeXDatabase newDatabase = new BibTeXDatabase();
    private Set<String> usedKeys = new HashSet<>();;

    public BibtexProcessor(BibTeXDatabase database) {
        this.oldDatabase = database;
        
       
    }

    public BibTeXDatabase replaceBibKeys() {
        Map<Key, BibTeXEntry> entryMap = oldDatabase.getEntries();
        Collection<BibTeXEntry> entries = entryMap.values();
        System.out.println("Total Entries:"+entries.size());
        for (BibTeXEntry entry : entries) {
            System.out.println("Processing entry #"+usedKeys.size());
            replaceBibKey(entry);
        }
        return newDatabase;
    }

    private void replaceBibKey(BibTeXEntry oldEntry) {
        Key newKey = generateNewBibKeyFromEntry(oldEntry);

        BibTeXEntry entryWithReplacedKey = new BibTeXEntry(oldEntry.getType(), newKey);
        entryWithReplacedKey.addAllFields(oldEntry.getFields());

        newDatabase.addObject(entryWithReplacedKey);
    }

    private Key generateNewBibKeyFromEntry(BibTeXEntry entry) {
        String newKeyString = generateUniqueBibKeyString(entry);
        return new Key(newKeyString);
    }

    private String generateAuthorsString(BibTeXEntry entry) {
        String rawAuthors = entry.getField(BibTeXEntry.KEY_AUTHOR).toUserString();
        String[] individualAuthors = rawAuthors.split(" and ");

        int count = 0;
        StringBuilder finalAuthorString = new StringBuilder();
        for (String authorString : individualAuthors) {
            if (count == 2) {
                break;
            }
            String parsedName = parseAuthorName(authorString);
            finalAuthorString.append(parsedName + "-");
            count++;
        }
        
        return finalAuthorString.toString().replace(' ', '_');

    }

    private String parseAuthorName(String authorString) {
        String[] name = authorString.split(",");
        if (name.length == 2) {
            return name[0];
        } else {
            String[] nameWithSpace = authorString.split(" ");
            return nameWithSpace.length == 2 ? nameWithSpace[1] : nameWithSpace[0];

        }
    }

    private String getYearString(BibTeXEntry entry) {
                return entry.getField(BibTeXEntry.KEY_YEAR).toUserString();
    }

    private String generateUniqueBibKeyString(BibTeXEntry entry) {
       String authors = generateAuthorsString(entry);
       String year = getYearString(entry);
       
       String candidateKey = authors + year;
       int counter = 1;
       while (!isUnique(candidateKey)) {
           candidateKey = replaceOrAddSuffix(candidateKey, "_"+counter, "_"+(counter+1));
           System.out.println("ck"+candidateKey);
           counter++;
       }
       usedKeys.add(candidateKey);
       return candidateKey;
       
    }

    private boolean isUnique(String candidateKey) {
        return !usedKeys.contains(candidateKey);
    }
    
    public String replaceOrAddSuffix (String target, String suffix, String replacement) {
    if (!target.endsWith(suffix)) {
        return target+replacement;
    }
    String prefix = target.substring(0, target.length() - suffix.length());
    return prefix + replacement;
}

}
