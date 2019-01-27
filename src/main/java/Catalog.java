import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Page> pages;
    private List<String> header;
    private List<Integer> maxWordLengths;
    private int amountOfCategories;
    private int amountOfPages = 0;


    public Catalog() {
        maxWordLengths = new ArrayList<Integer>();
    }

    public List<Page> getPages() {
        return this.pages;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public void createCatalog(List<String> data, int pageSize) {
        List<String[]> allLines = new ArrayList<String[]>();
        for (String line : data) {
            String[] words = splitIntoWords(line);
            allLines.add(words);
        }

        getMaxWordLengthOfEachCategory(allLines);
        List<String> catalog = formatList(allLines);

        // Create and set header
        List<String> newHeader = createHeader(catalog);
        setHeader(newHeader);

        // Remove header data from catalog
        catalog.remove(0);

        createPages(pageSize, catalog);
    }

    private List<String> createHeader(List<String> catalog) {
        String headlineSeparator = "";
        for (int i = 0; i < amountOfCategories; i++) {
            for (int j = 0; j < maxWordLengths.get(i); j++) {
                headlineSeparator = headlineSeparator + "-";
            }
            headlineSeparator = headlineSeparator + "+";
        }

        List<String> header = new ArrayList<String>();
        header.add(catalog.get(0));
        header.add(headlineSeparator);
        return header;
    }

    private void createPages(int pageSize, List<String> catalog) {
        pages = new ArrayList<Page>();
        while (catalog.size() > 0) {
            List<String> pageData = new ArrayList<String>();
            for (int i = 0; i < pageSize; i++) {
                if (catalog.size() > i) {
                    pageData.add(catalog.get(i));
                }
            }
            amountOfPages = amountOfPages + 1;
            Page page = new Page(amountOfPages, pageData, header);
            pages.add(page);
            catalog.removeAll(pageData);
        }
    }

    private List<String> formatList(List<String[]> allLines) {
        List<String> formattedData = new ArrayList<String>();
        for (String[] line : allLines) {
            String formattedLine = "";
            for (int i = 0; i < amountOfCategories; i++) {
                formattedLine = formattedLine + String.format("%-" + maxWordLengths.get(i) + "s", line[i]) + "|";
            }
            formattedData.add(formattedLine);
        }
        return formattedData;
    }

    private void getMaxWordLengthOfEachCategory(List<String[]> allLines) {
        amountOfCategories = allLines.get(0).length;
        Integer categorySizes[] = new Integer[amountOfCategories];
        for (int i = 0; i < amountOfCategories; i++) {
            categorySizes[i] = 0;
        }
        for (String[] line : allLines) {
            for (int i = 0; i < amountOfCategories; i++) {
                int wordLength = line[i].length();
                if (wordLength > categorySizes[i]) {
                    categorySizes[i] = wordLength;
                }
            }
        }
        for (int i = 0; i < amountOfCategories; i++) {
            maxWordLengths.add(categorySizes[i]);
        }

    }

    private String[] splitIntoWords(String line) {
        String[] words = line.split(";");
        return words;
    }
}
