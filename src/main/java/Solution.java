import java.io.*;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        /*
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int limit = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> result = Result.topArticles(limit);

        bufferedWriter.write(
                String.join("\n", result)
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();*/
        Result.topArticles(2);
    }
}
