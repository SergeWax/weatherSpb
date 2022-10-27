import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {

    private static Document spbPage() throws IOException {
        String url = "https://pogoda.spb.ru/";
        Document newDoc = Jsoup.parse(new URL(url), 3000 );
        return newDoc;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(spbPage());
    }

}
