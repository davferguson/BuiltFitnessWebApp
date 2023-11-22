package springboot.backend.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springboot.backend.model.openfda.FdaBrandMedApiResult;
import springboot.backend.model.openfda.FdaBrandMedSearchResult;

@Service
public class ApiService {
    public FdaBrandMedSearchResult[] fdaMedSearch(String name){
        String url = "https://api.fda.gov/drug/drugsfda.json?search=openfda.brand_name:\"" + name + "\"&limit=10";
        RestTemplate restTemplate = new RestTemplate();
        FdaBrandMedApiResult apiResult = restTemplate.getForObject(url, FdaBrandMedApiResult.class);

        int numOfResults = 0;
        for(int i = 0; i < apiResult.getResults().length; i++){
            for(int y = 0; y < apiResult.getResults()[i].getProducts().length; y++){
                numOfResults++;
            }
        }
        FdaBrandMedSearchResult[] searchResult = new FdaBrandMedSearchResult[numOfResults];
        for(int i = 0; i < numOfResults; i++){
            searchResult[i] = new FdaBrandMedSearchResult();
        }

        int index = 0;
        for(int i = 0; i < apiResult.getResults().length; i++){
            for(int y = 0; y < apiResult.getResults()[i].getProducts().length; y++){
                searchResult[index].setBrandName(apiResult.getResults()[i].getProducts()[y].getBrandName());
                searchResult[index].setDosageForm(apiResult.getResults()[i].getProducts()[y].getDosageForm());
                searchResult[index].setRoute(apiResult.getResults()[i].getProducts()[y].getRoute());
                searchResult[index].setActiveIngredients(apiResult.getResults()[i].getProducts()[y].getMedActiveIngredients());
                index++;
            }
        }

        assert apiResult != null;
//        searchResult.setBrandName(apiResult.getResults().getProducts().getBrandName());
        System.out.println(apiResult.getResults());
        return searchResult;
    }
}
