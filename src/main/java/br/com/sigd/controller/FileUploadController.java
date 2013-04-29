package br.com.sigd.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;


@Named("fileUploadController")
@SessionScoped
public class FileUploadController implements Serializable {

    public void processFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        System.out.println("Uploaded: " + event.getFile().getFileName());



        FileOutputStream outPutStream = new FileOutputStream("C:\\temp\\" + event.getFile().getFileName());
        outPutStream.write(event.getFile().getContents());

        outPutStream.flush();
        outPutStream.close();

    }
}
