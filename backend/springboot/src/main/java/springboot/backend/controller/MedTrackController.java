package springboot.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.backend.model.openfda.FdaBrandMedSearchResult;
import springboot.backend.service.ApiService;

@RestController
@CrossOrigin
public class MedTrackController {
    private ApiService apiService;

    public MedTrackController(ApiService apiService) {
        this.apiService = apiService;
    }

    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    @ResponseBody
    @RequestMapping(value = "/med_search/{name}", method = RequestMethod.GET)
    public FdaBrandMedSearchResult[] minutesReadFromISBN(@PathVariable String name){
        return apiService.fdaMedSearch(name);
    }
}
