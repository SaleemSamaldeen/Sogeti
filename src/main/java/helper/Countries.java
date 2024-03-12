package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Countries and specific links
 */
public enum Countries {

    BELGIUM("https://www.sogeti.be/"),

    FINLAND("https://www.sogeti.fi/"),

    FRANCE("https://www.fr.sogeti.com/"),

    GERMANY("https://www.sogeti.de/"),

    IRELAND("https://www.sogeti.ie/"),

    LUXEMBOURG("https://www.sogeti.lu/"),

    NETHERLANDS("https://www.sogeti.nl/"),

    NORWAY("https://www.sogeti.no/"),

    SPAIN("https://www.sogeti.es/"),

    SWEDEN("https://www.sogeti.se/"),

    UK("https://www.uk.sogeti.com/"),

    USA("https://www.us.sogeti.com/");

    private String url;

    Countries(String url) {
        this.url = url;
    }

    public String getPageUrl() {
        return url;
    }

    public static List<String> countryNames() {
        List<String> names = new ArrayList<>();
        Arrays.stream(Countries.values()).forEach(countries -> {
            names.add(countries.name());
        });
        return names;
    }
}
