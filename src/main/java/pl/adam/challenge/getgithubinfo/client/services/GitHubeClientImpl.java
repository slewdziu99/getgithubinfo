package pl.adam.challenge.getgithubinfo.client.services;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import pl.adam.challenge.getgithubinfo.config.AppConfig;

import javax.net.ssl.SSLContext;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GitHubeClientImpl implements GitHubClient {

    private final AppConfig config;

    public GitHubeClientImpl(AppConfig config) {
        this.config = config;
    }


    @Override
    public ResponseEntity<String> getRepositoriesForAuthenticatedUser() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        /*curl \
        -H "Accept: application/vnd.github+json" \
        -H "Authorization: Bearer <YOUR-TOKEN>" \
        https://api.github.com/user/repos*/
        return runRestTemplate(buildCallUrl("user","repos"), null);
    }

    @Override
    public ResponseEntity<String> getBrunchesForRepo(String owner ,String repoName) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        /*curl \
        -H "Accept: application/vnd.github+json" \
        -H "Authorization: Bearer <YOUR-TOKEN>" \
        https://api.github.com/repos/OWNER/REPO/branches*/
        return runRestTemplate(buildCallUrl("repos",owner,repoName,"branches"),null);
    }

    @Override
    public ResponseEntity<String> getRepoForUser(String username) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        /*curl \
        -H "Accept: application/vnd.github+json" \
        -H "Authorization: Bearer <YOUR-TOKEN>" \
        https://api.github.com/users/USERNAME/repos*/
        Map<String,String> params = new HashMap<>();
        params.put("type","all");
        return runRestTemplate(buildCallUrl("users",username,"repos"),params);
    }


    private ResponseEntity<String> runRestTemplate(String targetUrl, Map<String , String> params) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s" , config.getLogintoken()));
        headers.add("Accept","application/vnd.github+json");
        HttpEntity<String> request = new HttpEntity<>(headers);


        log.debug(String.format("Sending rest request to %s ", targetUrl));

        // restTemplate need to migrate to WebClient from webflux
        if (params == null) {
            return restTemplate.exchange(targetUrl, HttpMethod.GET, request, String.class);
        } else {
            return restTemplate.exchange(targetUrl, HttpMethod.GET, request,String.class, params);
        }


    }

    private String buildCallUrl(String... urlElements){
        StringBuffer sb = new StringBuffer();
        sb.append(config.getApiurl());
        for (int i=0; i<urlElements.length;i++) {
            sb.append("/").append(urlElements[i]);
        }
        return sb.toString();
    }

}