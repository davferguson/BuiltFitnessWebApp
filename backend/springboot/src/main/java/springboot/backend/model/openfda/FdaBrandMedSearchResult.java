package springboot.backend.model.openfda;

import java.util.List;

public class FdaBrandMedSearchResult {
    private String brandName;
    private String genericName;
    private MedActiveIngredients[] activeIngredients;
    private String dosageForm;
    private String route;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public MedActiveIngredients[] getActiveIngredients() {
        return activeIngredients;
    }

    public void setActiveIngredients(MedActiveIngredients[] activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
