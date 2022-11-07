package pl.adam.challenge.getgithubinfo.services;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public interface RepoService {

    JSONArray getMyRepoDescription() throws JSONException;

    JSONArray getUserRepoDescription(String userName) throws JSONException;

}
