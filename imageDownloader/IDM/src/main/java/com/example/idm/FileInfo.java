package com.example.idm;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileInfo {
    private final SimpleStringProperty index = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty URL= new SimpleStringProperty();
    //"DOWNLOADING", DONE, "STARTING"
    private SimpleStringProperty status= new SimpleStringProperty();
    private SimpleStringProperty action= new SimpleStringProperty();
    private SimpleStringProperty file_path= new SimpleStringProperty();

    public FileInfo(String index, String name, String URL, String status,String action, String path) {
        this.index.set(index);
        this.name.set(name);
        this.URL.set(URL);
        this.status.set(status);
        this.action.set(action);
        this.file_path.set(path);
    }

    public String getName() {

        return name.get();
    }

    public SimpleStringProperty nameProperty() {

        return name;
    }

    public void setName(String name) {

        this.name.set(name);
    }

    public String getURL() {

        return URL.get();
    }

    public SimpleStringProperty URLProperty() {

        return URL;
    }

    public void setURL(String URL) {

        this.URL.set(URL);
    }

    public String getStatus() {

        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getAction() {
        return action.get();
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public void setAction(String action) {
        this.action.set(action);
    }

    public String getFile_path() {
        return file_path.get();
    }

    public SimpleStringProperty file_pathProperty() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path.set(file_path);
    }

    public String getIndex() {
        return index.get();
    }

    public SimpleStringProperty indexProperty() {
        return index;
    }

    public void setIndex(String index) {
        this.index.set(index);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "index=" + getIndex() +
                ", name=" + getName() +
                ", URL=" + getURL() +
                ", status=" + getStatus() +
                ", action=" + getAction() +
                ", file_path=" + getFile_path() +
                '}';
    }
}
