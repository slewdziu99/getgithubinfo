package pl.adam.challenge.getgithubinfo.client.services;


import org.springframework.http.ResponseEntity;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public interface GitHubClient {

    ResponseEntity<String> getRepositoriesForAuthenticatedUser() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    ResponseEntity<String> getBrunchesForRepo(String owner, String repoName) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

    ResponseEntity<String> getRepoForUser(String username) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException;


}
