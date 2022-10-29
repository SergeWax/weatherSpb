import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        Document page = spbPage();
        //css query language
        Element tableWth = page.select("table[class=wt]").first();
        Elements names = tableWth.select("tr[class=wth]");
        Elements values = tableWth.select("tr[valign=top]");

        for (Element name : names) {
            String date = name.select("th[id=dt]").text();
            System.out.println(date);
            //Регулярные выражения необходимы для того что бы выбрать из строки необходимую часть текста

        }
    }
}