package springboot.backend.model.openfda;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FdaBrandMedApiResult {
    @JsonProperty("results")
    private FdaMedResults[] results;

    public FdaMedResults[] getResults() {
        return results;
    }

    public void setResults(FdaMedResults[] results) {
        this.results = results;
    }
}
