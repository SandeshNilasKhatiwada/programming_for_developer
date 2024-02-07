package com.example.idm;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadThread extends Thread{
    private FileInfo file;
    DownloadManagerController controller;

    public DownloadThread(FileInfo file, DownloadManagerController controller){
        this.file = file;
        System.out.println(file.toString());
        this.controller = controller;
    }

    @Override
    public void run(){
        this.file.setStatus("DOWNLOADING");
        this.controller.updateUI(this.file);

        //download logic
        try {
           Files.copy(new URL(this.file.getURL()).openStream(),Paths.get(this.file.getFile_path()));
           this.file.setStatus("DONE");
        } catch (IOException e) {
            this.file.setStatus("FAILED");
            System.out.println("Downloading fail");
            e.printStackTrace();
        }
        this.controller.updateUI(this.file);
    }

}
