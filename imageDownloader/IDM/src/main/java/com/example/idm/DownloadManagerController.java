package com.example.idm;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.File;

public class DownloadManagerController {
    @FXML
    private TextField urlText;

    @FXML
    private TableView <FileInfo> tableView;
    public int index = 0;
    public void downloadButton(ActionEvent e){
        String url = urlText.getText().trim();
        this.index = index+1;
        String URL [] = url.split("/");
        String filename = URL[URL.length-1];
        String status = "STARTING";
        String action = "OPEN";
        String path = "src/main/"+filename;
        FileInfo file = new FileInfo(this.index+"",filename,url,status,action,path);
        DownloadThread thread = new DownloadThread(file,this);
        this.tableView.getItems().setAll(file);
        thread.start();

    }

    public void updateUI(FileInfo file) {
        System.out.println("here"+file);
        FileInfo fileInfo = this.tableView.getItems().get(Integer.parseInt(file.getIndex())-1);
        fileInfo.setStatus(file.getStatus());
        this.tableView.refresh();
    }

    @FXML
    public void initialize() throws Exception{

        TableColumn<FileInfo, String> indexColumn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(0);
        indexColumn.setCellValueFactory(p -> p.getValue().indexProperty());

        // Set the CellValueFactory for other columns as needed
        TableColumn<FileInfo, String> filenameColumn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(1);
        filenameColumn.setCellValueFactory(p -> p.getValue().nameProperty());

        TableColumn<FileInfo, String> urlColumn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(2);
        urlColumn.setCellValueFactory(p -> p.getValue().URLProperty());

        TableColumn<FileInfo, String> statusColumn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(3);
        statusColumn.setCellValueFactory(p -> p.getValue().statusProperty());

        TableColumn<FileInfo, String> actionColumn = (TableColumn<FileInfo, String>) this.tableView.getColumns().get(4);
        actionColumn.setCellValueFactory(p -> p.getValue().actionProperty());
    }




}
