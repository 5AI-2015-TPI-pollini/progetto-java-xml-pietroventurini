package Weather;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

public class WeatherXPathHandler {
private final Document xml;
    private static final XPathFactory xpathFactory = XPathFactory.newInstance();
    private Forecast forecast;
    
    private static final String CITY_NAME = "/current/city/@name";
    private static final String CURRENT_TEMPERATURE = "/current/temperature/@value";
    private static final String MIN_TEMPERATURE = "/current/temperature/@min";
    private static final String MAX_TEMPERATURE = "/current/temperature/@max";
    private static final String CLOUDS = "/current/clouds/@name";
    
    
    public WeatherXPathHandler(Document retrivedResult) {
        xml = retrivedResult;
    }
    
    public Forecast extractFromDoc() {
        try {
            XPath xpath = xpathFactory.newXPath();
            // Prepare the queries
            XPathExpression nameEx = xpath.compile(CITY_NAME);
            XPathExpression currentEx = xpath.compile(CURRENT_TEMPERATURE);
            XPathExpression minEx = xpath.compile(MIN_TEMPERATURE);
            XPathExpression maxEx = xpath.compile(MAX_TEMPERATURE);
            XPathExpression cloudsEx = xpath.compile(CLOUDS);
            // Execute the xpath query
            String name = (String) nameEx.evaluate(xml, XPathConstants.STRING);
            double current = (Double) currentEx.evaluate(xml, XPathConstants.NUMBER);
            double min = (Double) minEx.evaluate(xml, XPathConstants.NUMBER);
            double max = (Double) maxEx.evaluate(xml, XPathConstants.NUMBER);
            String clouds = (String) cloudsEx.evaluate(xml, XPathConstants.STRING);
            
            forecast = new Forecast(name, current, min, max, clouds);
            
        } catch (XPathExpressionException ex) {
            System.out.println("extractFromDoc has reported an error");
        }
        return forecast;
    }
}

