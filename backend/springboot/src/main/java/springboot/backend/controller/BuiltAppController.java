package springboot.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springboot.backend.cloudfront.CloudFrontService;
import springboot.backend.model.openfda.FdaBrandMedSearchResult;
import springboot.backend.service.ApiService;

@RestController
@CrossOrigin
public class BuiltAppController {
    private ApiService apiService;
    private CloudFrontService cloudFrontService;

    public BuiltAppController(ApiService apiService, CloudFrontService cloudFrontService) {
        this.apiService = apiService;
        this.cloudFrontService = cloudFrontService;
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
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @RequestMapping(value = "/signed_url/{url}", method = RequestMethod.GET)
    public String GenerateSignedUrl(@PathVariable String url){
        return cloudFrontService.createSignedUrl(url);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @RequestMapping(value = "/list_bucket", method = RequestMethod.GET)
    public String ListBucketObjects(){
        return cloudFrontService.createSignedUrl("?list-type=2");
    }
}
