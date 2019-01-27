import java.util.ArrayList;
import java.util.List;

public class Page {

    private int index;
    private List<String> data;
    private List<String> header;
    private String bottomLine = "N(ext page, P(revious page, F(irst page, L(ast page, eX(it";

    public Page(int index, List<String> data, List<String> header) {
        this.index = index;
        this.data = data;
        this.header = header;
    }

    public void printPage() {
        List<String> page = new ArrayList<String>();
        page.addAll(header);
        page.addAll(data);
        page.add(bottomLine);

        for (String line : page) {
            System.out.println(line);
        }
    }
}
