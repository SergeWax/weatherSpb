import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Document spbPage() throws IOException {
        String url = "https://pogoda.spb.ru/";
        Document newDoc = Jsoup.parse(new URL(url), 3000 );
        return newDoc;
    }

    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract date from string");
    }

    private static void printFomValues(Elements values, int index){
        Element valueLine = values.get(index);
        for (Element td : valueLine.select("td")){
            System.out.println(td.text() + "    ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Document page = spbPage();
        //css query language
        Element tableWth = page.select("table[class=wt]").first();
        Elements names = tableWth.select("tr[class=wth]");
        Elements values = tableWth.select("tr[valign=top]");
        int index = 0; //на каком значении мы сейчас находимся

        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text();
            String date = getDateFromString(dateString);
            System.out.println(date);
            printFomValues(values, index);
            //Регулярные выражения необходимы для того что бы выбрать из строки необходимую часть текста


        }
    }
}