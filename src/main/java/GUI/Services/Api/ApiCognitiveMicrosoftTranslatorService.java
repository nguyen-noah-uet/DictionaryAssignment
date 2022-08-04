package GUI.Services.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;


public class ApiCognitiveMicrosoftTranslatorService {
    private final String endpoint = "https://api.cognitive.microsofttranslator.com";
    private final String contentType = "application/json";
    private final String subscriptionKey = "2b8038e93534424a82adf6885e219900";
    private final String subscriptionRegion = "southeastasia";
    private final String charset = "UTF-8";
    private String route = "";
    private String srcLanguage;
    private String desLanguage;
    OkHttpClient httpClient;

    public ApiCognitiveMicrosoftTranslatorService() {
        httpClient = new OkHttpClient();
    }

    public String translate(String text, String from, String to) {
        route = String.format("/translate?api-version=3.0&from=%s&to=%s", from, to);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.format("[{\"Text\": \"%s\"}]", text));
        Request request = new Request.Builder()
                .url(endpoint + route)
                .addHeader("Content-type", contentType)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", subscriptionRegion)
                .post(requestBody)
                .build();
        TranslateResult[] results = new TranslateResult[1];
        try {
            Response response = httpClient.newCall(request).execute();
            ObjectMapper om = new ObjectMapper();
            results = om.readValue(response.body().string(), TranslateResult[].class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return results[0].getTranslations().get(0).getText();
    }
}
