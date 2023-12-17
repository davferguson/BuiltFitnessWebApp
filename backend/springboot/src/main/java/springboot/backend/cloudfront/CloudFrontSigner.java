package springboot.backend.cloudfront;
import software.amazon.awssdk.services.cloudfront.model.CannedSignerRequest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import software.amazon.awssdk.services.cloudfront.CloudFrontUtilities;
import software.amazon.awssdk.services.cloudfront.url.SignedUrl;

public class CloudFrontSigner {
    private CloudFrontUtilities cloudFrontUtilities = CloudFrontUtilities.create();

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

    public String createSignedUrl(int expiration, String resourceUrl) {
        String privateKeyFileName = "X:\\workspace\\BuiltFitnessWebsite\\keys\\private_key.der";
        Path privateKeyPath = Paths.get(privateKeyFileName);
        String keyPairId = "K2BGUGZ1NTWWB4";
        String resUrl = "https://d1ts8jzzdee93e.cloudfront.net/fish.mp4";

        CannedSignerRequest cannedRequest = null;
        try {
            cannedRequest = createCannedRequest(expiration, resourceUrl, keyPairId, privateKeyPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SignedUrl signedUrl = cloudFrontUtilities.getSignedUrlWithCannedPolicy(cannedRequest);
        return signedUrl.url();
    }
}
