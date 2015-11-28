package GMaps;

import Weather.Forecast;
import java.awt.Point;

public class Location {


    private String address;
    private double lat, lng;
    private Forecast forecast;

    public Location(String name) {
        this.address = name;
    }
    
    public Location(String name, double latitude, double longitude) {
        this.address = name;
        lat = latitude;
        lng = longitude;
    }

    public String getName() {
        return address;
    }

    public void setName(String name) {
        this.address = name;
    }

    public Point.Double getCoordinates() {
        return new Point.Double(lat, lng);
    }
    
    public void setCoordinates(Point.Double coordinates) {
        lat = coordinates.getX();
        lng = coordinates.getY();
    }
    
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
    
    public Forecast getForecast() {
        return forecast;
    }
         
    
    public String toString() {
        return "Address: " + address + "\nLatitude: " + lat + "\nLongitude: " + lng;
    }
}
