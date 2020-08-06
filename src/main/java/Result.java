import com.google.gson.*;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

class Result {
    private static String ENDPOINT_URL = "https://jsonmock.hackerrank.com/api/articles?page=";

    /*
     * Complete the 'topArticles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER limit as parameter.
     */
    public static List<String> topArticles(int limit) throws IOException {
        List<Article> articles = getArticles();
        List<String> articleNames = articles.stream()
                .filter(Result::isValidArticle)
                .map(Result::setName)
                .map(Result::makeNullSafe)
                .sorted(Comparator.comparing(Article::getNumComments).thenComparing(Article::getName).reversed())
                .map(Article::getName)
                .limit(limit)
                .collect(Collectors.toList());
        return articleNames;
    }

    private static Article makeNullSafe(Article article) {
        if (article.getNumComments() == null) {
            article.setNumComments(0L);
        }
        return article;
    }

    private static Article setName(Article article) {
        if (article.getTitle() != null) {
            article.setName(article.getTitle());
        } else {
            article.setName(article.getStoryTitle());
        }
        return article;
    }

    private static boolean isValidArticle(Article article) {
        return article.getStoryTitle() != null || article.getTitle() != null;
    }

    private static List<Article> getArticles() throws IOException {
        Gson gson = getGsonBuilder().create();
        List<Article> articles = new ArrayList<>();
        long pages = 2;
        long currentPage = 1;
        while (currentPage <= pages) {
            ArticlePage articlePage =
                    gson.fromJson(getArticlePage(currentPage), ArticlePage.class);
            articles.addAll(articlePage.getData());
            pages = articlePage.getTotalPages();
            currentPage += 1;
        }
        return articles;
    }

    private static String getArticlePage(long page) throws IOException {
        HttpGet httpGet = new HttpGet(ENDPOINT_URL + page);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }

    private static GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json,
                                    Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
            }
        });
        return builder;
    }

}

