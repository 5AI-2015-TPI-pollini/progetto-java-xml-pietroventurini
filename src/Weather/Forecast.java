package Weather;

public class Forecast {
    
    private String name;
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
