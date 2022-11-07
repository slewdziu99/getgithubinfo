package pl.adam.challenge.getgithubinfo.controllers;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adam.challenge.getgithubinfo.services.RepoService;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@RestController
public class RepoController {

   private final RepoService repoService;

   public RepoController(RepoService repoService) {
      this.repoService = repoService;
   }

   @PostMapping(value = "getMyRepoInfo", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getMyRepoInfo() throws JSONException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
      JSONArray repoResp = repoService.getMyRepoDescription();

      return new ResponseEntity<>(repoResp.toString(), HttpStatus.OK);
   }

   @PostMapping(value = "getUserRepoInfo", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> getUserRepoInfo(@RequestParam(name="user") String username) throws JSONException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
      JSONArray repoResp = repoService.getUserRepoDescription(username);

      return new ResponseEntity<>(repoResp.toString(), HttpStatus.OK);

   }

}
