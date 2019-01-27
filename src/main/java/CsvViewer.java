import java.util.List;
import java.util.Scanner;

public class CsvViewer {

    private Catalog catalog;
    private int currentPage = 0;
    private int defaultPageSize = 3;

    public CsvViewer() {
        catalog = new Catalog();
    }

    public Catalog initializeCatalog(String[] args, List<String> csvFileData) {
        if (args.length == 1) {
            catalog.createCatalog(csvFileData, defaultPageSize);
        } else {
            String userPageSize = args[1];
            catalog.createCatalog(csvFileData, Integer.valueOf(userPageSize));
        }
        return catalog;
    }

    public void run() {
        Page firstPage = catalog.getPages().get(currentPage);
        firstPage.printPage();

        int lastPageIndex = catalog.getPages().size() - 1;
        Page lastPage = catalog.getPages().get(lastPageIndex);

        boolean stop = true;
        while (stop) {
            Scanner scanner = new Scanner(System.in);
            String userAction = scanner.next();
            if ("N".equals(userAction)) {
                if (currentPage == lastPageIndex) {
                    System.out.println("You are already on the last page.");
                    lastPage.printPage();
                } else {
                    currentPage++;
                    Page nextPage = catalog.getPages().get(currentPage);
                    nextPage.printPage();
                }
            } else if ("P".equals(userAction)) {
                if (currentPage == 0) {
                    System.out.println("You are already on the first page.");
                    firstPage.printPage();
                } else {
                    currentPage--;
                    Page previousPage = catalog.getPages().get(currentPage);
                    previousPage.printPage();
                }
            } else if ("F".equals(userAction)) {
                currentPage = 0;
                firstPage.printPage();
            } else if ("L".equals(userAction)) {
                currentPage = lastPageIndex;
                lastPage.printPage();
            } else if ("X".equals(userAction)) {
                stop = false;
            }
        }
    }
}
