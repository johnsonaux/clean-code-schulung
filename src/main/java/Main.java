import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        csvReader.setFilePath(args[0]);
        List<String> csvFileData = null;
        try {
            csvFileData = csvReader.getCsvFileData();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        }
        CsvViewer viewer = new CsvViewer();
        viewer.initializeCatalog(args, csvFileData);
        viewer.run();
    }
}
