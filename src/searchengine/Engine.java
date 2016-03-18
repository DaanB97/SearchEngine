/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Daan
 */
public class Engine {

    private String word;
    private String file;
    private ObservableList<File> files;

    public Engine(String word, String file) {
        this.files = FXCollections.observableArrayList();
        this.word = word;
        this.file = file;
    }

    public ObservableList<File> getList() {
        return files;
    }

    public void ClearList() {
        files.clear();
    }

    public void GetFiles(String path, String word) {
        File f = new File(path);
        File[] fl = f.listFiles();
        if (fl != null) {
            for (File fs : fl) {
                if (fs != null) {
                    if (fs.isDirectory()) {
                        if (fs.getName().contains(word)) {
                            files.add(fs);
                        }
                        GetFiles(fs.getPath(), word);
                    } else if (fs.getName().contains(word)) {
                        files.add(fs);
                    }
                }
            }
        }
    }
}
