package springboot.backend.cloudfront;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cloudfront.model.CannedSignerRequest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import software.amazon.awssdk.services.cloudfront.CloudFrontUtilities;
import software.amazon.awssdk.services.cloudfront.url.SignedUrl;

@Service
public class CloudFrontService {
    private CloudFrontUtilities cloudFrontUtilities = CloudFrontUtilities.create();
    private String keyPairId = "K2BGUGZ1NTWWB4";
    private Path privateKeyPath = Paths.get("X:\\workspace\\BuiltFitnessWebsite\\keys\\private_key.der");
    private String distDomain = "https://d1ts8jzzdee93e.cloudfront.net";
    private int defaultUrlExpiration = 20;

    private CannedSignerRequest createCannedRequest(int expiration, String resourceUrl, String keyPairId, Path privateKeyPath) throws Exception {
        Instant expirationDate = Instant.now().plus(expiration, ChronoUnit.SECONDS);
        CannedSignerRequest cannedRequest = CannedSignerRequest.builder()
                .resourceUrl(resourceUrl)
                .privateKey(privateKeyPath)
                .keyPairId(keyPairId)
                .expirationDate(expirationDate)
                .build();
        return cannedRequest;
    }

    public String createSignedUrl(int expiration, String objectUrl) {
        CannedSignerRequest cannedRequest = null;
        try {
            String resourceUrl = distDomain + "/" + objectUrl;
            cannedRequest = createCannedRequest(expiration, resourceUrl, keyPairId, privateKeyPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SignedUrl signedUrl = cloudFrontUtilities.getSignedUrlWithCannedPolicy(cannedRequest);
        return signedUrl.url();
    }
    public String createSignedUrl(String objectUrl) {
        CannedSignerRequest cannedRequest = null;
        try {
            String resourceUrl = distDomain + "/" + objectUrl;
            cannedRequest = createCannedRequest(defaultUrlExpiration, resourceUrl, keyPairId, privateKeyPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SignedUrl signedUrl = cloudFrontUtilities.getSignedUrlWithCannedPolicy(cannedRequest);
        return signedUrl.url();
    }
}
