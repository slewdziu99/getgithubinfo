package pl.adam.challenge.getgithubinfo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.adam.challenge.getgithubinfo.client.services.GitHubClient;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class RepoServiceImpl implements RepoService {

    private final GitHubClient gitHubClient;

    public RepoServiceImpl(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Override
    public JSONArray getMyRepoDescription() throws JSONException {
        String gitHubData= null;
        try {
            gitHubData =  gitHubClient.getRepositoriesForAuthenticatedUser().getBody();
        } catch ( NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error(String.format("Can not process request. Rason: %s ", e.getCause()),e);
            throw new RuntimeException(e);
        }

        return prepareJsonArrayResponse(new JSONArray(gitHubData));
    }

    @Override
    public JSONArray getUserRepoDescription(String userName) throws JSONException {
        String gitHubData= null;
        try {
            gitHubData =  gitHubClient.getRepoForUser(userName).getBody();
        } catch ( NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error(String.format("Can not process request. Rason: %s ", e.getCause()),e);
            throw new RuntimeException(e);
        }


        return prepareJsonArrayResponse(new JSONArray(gitHubData));
    }

    private JSONArray prepareJsonArrayResponse(JSONArray gitHubData) throws JSONException{
        JSONArray result = new JSONArray();
        for (int i=0;i<gitHubData.length();i++){
            JSONObject json = new JSONObject(gitHubData.get(i).toString());

            JSONObject jsonToInsertInArray = new JSONObject();
            String owner = json.getJSONObject("owner").get("login").toString();
            String repoName = json.get("name").toString();
            jsonToInsertInArray.put("Repository Name",repoName);
            jsonToInsertInArray.put("Repository URL",json.get("html_url"));
            jsonToInsertInArray.put("Owner Login", owner);

            JSONObject brunchSha = new JSONObject();
            String brunches = null;
            try {
                brunches = gitHubClient.getBrunchesForRepo(owner,repoName).getBody();
            } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
                log.error(String.format("Enable to get brunch data for owner: %s", owner));
                throw new RuntimeException(e);
            }
            JSONArray jsonBrunches = new JSONArray(brunches);

            for (int j=0;j<jsonBrunches.length();j++){
                brunchSha.put(
                        jsonBrunches.getJSONObject(j).get("name").toString(),
                        jsonBrunches.getJSONObject(j).getJSONObject("commit").get("sha").toString());
                jsonToInsertInArray.put("brunches",brunchSha);
            }

            result.put(jsonToInsertInArray);

        }
        return result;
    }
}
