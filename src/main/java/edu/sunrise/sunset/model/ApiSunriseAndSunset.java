
package edu.sunrise.sunset.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiSunriseAndSunset {
    @SerializedName("results")
    @Expose
    private Results results;
    @SerializedName("status")
    @Expose
    private String status;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApiSunriseAndSunset{" +
                "results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}


