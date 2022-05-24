package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    FileWriter writeToFile;

    public AuditService(){
        try {
            String fileName = "src/csvFiles/Audit.csv";

            this.writeToFile = new FileWriter(fileName, true);
            File file = new File(fileName);

            if (file.length() == 0){
                writeToFile.append("ActionName");
                writeToFile.append(",");
                writeToFile.append("TimeStamp");
                writeToFile.append("\n");
            }
        }
        catch (Exception exception){
            System.out.printf("\n\t\tException: " + exception.getMessage() + "\n");
        }
    }

    public void writeActionToFile(String actionName) throws IOException {
        if (writeToFile != null){
            writeToFile.append(actionName);
            writeToFile.append(",");

            LocalDateTime dateAndTime = LocalDateTime.now();
            writeToFile.append(dateAndTime.toString());

            writeToFile.append("\n");
            writeToFile.flush();
        }
    }
}
