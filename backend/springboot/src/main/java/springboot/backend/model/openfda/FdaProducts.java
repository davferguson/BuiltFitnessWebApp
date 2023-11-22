package springboot.backend.model.openfda;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FdaProducts {
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("dosage_form")
    private String dosageForm;

    @JsonProperty("route")
    private String route;

    @JsonProperty("active_ingredients")
    private MedActiveIngredients[] medActiveIngredients;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public MedActiveIngredients[] getMedActiveIngredients() {
        return medActiveIngredients;
    }

    public void setMedActiveIngredients(MedActiveIngredients[] medActiveIngredients) {
        this.medActiveIngredients = medActiveIngredients;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }
}
