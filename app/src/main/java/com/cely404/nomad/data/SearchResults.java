package com.cely404.nomad.data;
import android.os.AsyncTask;
import android.util.Log;

import com.cely404.nomad.SearchQueryVideoList;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AsyncTask<String,Void,List<SearchResult>>{

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    private static String queryTerm;
    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;
    /**
     * Initialize a YouTube object to search for videos on YouTube. Then Populate
     * a list which will contain
     */
    @Override
    protected List<SearchResult> doInBackground(String...queryTerm) {
                List<SearchResult> searchResult = new ArrayList<>();
                try {
                    // This object is used to make YouTube Data API requests. The last
                    // argument is required, but since we don't need anything
                    // initialized when the HttpRequest is initialized, we override
                    // the interface and provide a no-op function.
                    String appName = "Nomad";//Resources.getSystem().getString(R.string.app_name);
                    youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                        public void initialize(HttpRequest request) throws IOException {
                        }
                    }).setApplicationName(appName).build();
                    // Define the API request for retrieving search results.
                    YouTube.Search.List search = youtube.search().list("id,snippet");

                    // Set your developer key from the Google Developers Console for
                    // non-authenticated requests. See:
                    // https://console.developers.google.com/
                    String apiKey = "AIzaSyBdruUR-hcHXgMbZUH7aMziDDO_jV5lfag";
                    search.setKey(apiKey);
                    search.setQ(queryTerm[0]);

                    // Restrict the search results to only include videos. See:
                    // https://developers.google.com/youtube/v3/docs/search/list#type
                    search.setType("video");

                    // To increase efficiency, only retrieve the fields that the
                    // application uses.
                    search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
                    search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);


                    // Call the API and print results.
                    SearchListResponse searchResponse = search.execute();
                    searchResult = searchResponse.getItems();

                    return searchResult;

                } catch (GoogleJsonResponseException e) {
                    Log.d("SearchResults","There was a service error: " + e.getDetails().getCode() + " : "
                            + e.getDetails().getMessage());
                } catch (IOException e) {
                    Log.d("SearchResults", "There was an IO error: " + e.getCause() + " : " + e.getMessage());
                } catch (Throwable t) {
                    Log.d("SearchResults", t.getLocalizedMessage());
                }
                return searchResult;
            }

    @Override
    protected void onPostExecute(List<SearchResult> searchResults) {
        SearchQueryVideoList.list = searchResults;
    }
}