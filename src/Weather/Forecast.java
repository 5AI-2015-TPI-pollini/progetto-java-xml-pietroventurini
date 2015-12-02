package Weather;

/**
 * This is the Forecast Class,
 * It indicates: name of the location, current temperature, min/MAX, description
 * 
 * @author Pietro Venturini
 */

public class Forecast {
    
    private final String name;
    private double temperature, min, max;
    private String clouds;

    public Forecast(String name, double temperature, double min, double max, String clouds) {
        this.name = name;
        this.temperature = temperature;
        this.min = min;
        this.max = max;
        this.clouds = clouds;
    }

    public String getName() {
        return name;
    }

    // '%.1f" shows just 1 decimal
    public String getTemperature() {
        return String.format("%.1f", temperature) + "°C";
    }

    public String getMin() {
        return String.format("%.1f", min) + "°C";
    }

    public String getMax() {
        return String.format("%.1f", max) + "°C";
    }

    public String getClouds() {
        return clouds;
    }

    @Override
    public String toString() {
        return "Forecast {" 
                + "Name = " + name 
                + ", Temperature = " + temperature + "°C"
                + ", Min = " + min + "°C" 
                + ", Max = " + max + "°C"
                + ", Clouds = " + clouds 
                + "}" ;
    }
    
    
}
