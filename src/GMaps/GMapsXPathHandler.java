package GMaps;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * This class is used to analyze the Document received from
 * Geocode (GMaps APIs) using XPath queries.
 * 
 * @author Pietro Venturini
 */

public class GMapsXPathHandler {
        
    private Document xml;
    private static final XPathFactory xpathFactory = XPathFactory.newInstance();
    private Location[] locations;
    
    private static final String FORMATTED_ADDRESS = "/GeocodeResponse/result/formatted_address/text()";
    private static final String LATITUDE = "/GeocodeResponse/result/geometry/location/lat/text()";
    private static final String LONGITUDE = "/GeocodeResponse/result/geometry/location/lng/text()";
    
    public GMapsXPathHandler(Document retrivedResult) {
        xml = retrivedResult;
    }
    
    public Location[] extractFromDoc() {
        try {
            XPath xpath = xpathFactory.newXPath();
            // Prepare the queries
            XPathExpression formattedLocation = xpath.compile(FORMATTED_ADDRESS);
            XPathExpression lat = xpath.compile(LATITUDE);
            XPathExpression lng = xpath.compile(LONGITUDE);
            // Execute the xpath query
            NodeList formattedLocations = (NodeList) formattedLocation.evaluate(xml, XPathConstants.NODESET);
            NodeList lats = (NodeList) lat.evaluate(xml, XPathConstants.NODESET);
            NodeList lngs= (NodeList) lng.evaluate(xml, XPathConstants.NODESET);
            
            locations = new Location[formattedLocations.getLength()];
            for (int i = 0; i < locations.length; i++) {
                
                locations[i] = new Location(formattedLocations.item(i).getNodeValue(), Double.parseDouble(lats.item(i).getNodeValue()), Double.parseDouble(lngs.item(i).getNodeValue()));
            }
        } catch (XPathExpressionException ex) {
            System.out.println("extractFromDoc has reported an error");
        }
        
        return locations;
    }
}
