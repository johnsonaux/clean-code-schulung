import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {

    private String filePath;

    public CsvReader() {
    }

    public List<String> getCsvFileData() throws FileNotFoundException {
        List<String> csvData = new ArrayList<String>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            csvData.add(scanner.nextLine());
        }
        return csvData;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        System.out.println("The currently used file is [" + filePath + "]");
    }
}
