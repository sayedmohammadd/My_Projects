package WeatherApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherApi {

    private static final String API_BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22"; // Replace with the actual API URL

    private static String makeHttpGetRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    }

    private static String getWeatherData(String date) {
        try {
            String apiUrl = API_BASE_URL + "?date=" + date;
            String response = makeHttpGetRequest(apiUrl);
            // Parse the JSON response and extract the temperature
            // Implement this part according to your actual API response structure.
            // For example:
             JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
             JsonElement temperature1 = jsonObject.get("weather");
             Object obj=temperature1;
             String temperature=(String)obj;
             return temperature;
            //return response; // Replace with actual temperatur1e value
           
        } catch (IOException e) {
            System.out.println("Failed to fetch weather data for date " + date);
            e.printStackTrace();
            return null;
        }
    }

    private static String getWindSpeedData(String date) {
        // Similar implementation as getWeatherData
        // Implement according to your actual API response structure.
        return "Wind Speed"; // Replace with actual wind speed value
    }

    private static String getPressureData(String date) {
        // Similar implementation as getWeatherData
        // Implement according to your actual API response structure.
        return "Pressure"; // Replace with actual pressure value
    }

    public static void main(String[] args)throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Get weather");
                System.out.println("2. Get Wind Speed");
                System.out.println("3. Get Pressure");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                String choice = br.readLine();
                switch(choice)
                {
                case "0":
                	 System.out.println("Exiting the program.");
                   break;
                	
                case "1":
                	 System.out.print("Enter the date (YYYY-MM-DD): ");
                     String date = br.readLine();
                     String temp = getWeatherData(date);
                     if (temp != null) {
                         System.out.println("Temperature on " + date + ": " + temp + "°C");
                     }
                     break;
                case "2":
                	 System.out.print("Enter the date (YYYY-MM-DD): ");
                   String date1 = br.readLine();
                   String windSpeed = getWindSpeedData(date1);
                   if (windSpeed != null) {
                       System.out.println("Wind Speed on " + date1 + ": " + windSpeed + " m/s");
                   }
                   break;
                case "3":
                	 System.out.print("Enter the date (YYYY-MM-DD): ");
                     String date2 = br.readLine();
                     String pressure = getPressureData(date2);
                     if (pressure != null) {
                         System.out.println("Pressure on " + date2 + ": " + pressure + " hPa");
                     }
                     break;
               
                default:
                	 System.out.println("Invalid choice. Please try again.");
                	break;
                	
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}