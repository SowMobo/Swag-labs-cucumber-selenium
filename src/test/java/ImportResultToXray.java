import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class ImportResultToXray {
    String clientID = "AB0D99BEB1984A68817FABE0C63C8584";
    String clientSecret = "56274142b99ab7281aeafa963645bc0a306665c8f497b1017d75acbf4287377e";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InterruptedException {
        ImportResultToXray apiConnector = new ImportResultToXray();
//        System.out.println("token = " + generator.getToken());
        apiConnector.ImportToXray();
    }

    public String getToken() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]
                {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

        HttpPost request = new HttpPost("https://xray.cloud.getxray.app/api/v1/authenticate");

        request.addHeader("Content-Type", "application/json");

        String input = "{ \"client_id\": \"" + clientID + "\",\"client_secret\": \"" + clientSecret + "\"}";
        request.setEntity(new StringEntity(input));
        String result = "";
        try (
                CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            System.out.println(result);
            result = result.replace("\"", "");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public void ImportToXray() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InterruptedException {
        HttpURLConnection conn;
        URL url;
        String result;

        String URL = "https://xray.cloud.getxray.app/api/v1/import/execution/cucumber";
        //Call the openConnection method on the URL to create a connection object
        url = new URL(URL);
        conn = (HttpURLConnection) url.openConnection();

        //Various settings of HttpURLConnection
        //Set HTTP method to POST
        conn.setRequestMethod("POST");
        //Allow body submission of request
        conn.setDoInput(true);
        //Allow body reception of response
        conn.setDoOutput(true);
        //Specify Json format
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("Authorization", "Bearer " + getToken());

        // 2.Establish a connection
        conn.connect();
        // 3.Write to request and body
        //Get OutputStream from HttpURLConnection and write json string
        Thread.sleep(10000);
        PrintStream ps = new PrintStream(conn.getOutputStream());
        Path filePath = Path.of(System.getProperty("user.dir")+ File.separator+"target"+File.separator +"cucumber.json");
//        Path filePath = Paths.get("        /Users/modibosow/Software-Construction-Java/SeleniumProjects/Swag-labs-cucumber-selenium/target/cucumber.json");
        String content = Files.readString(filePath);
        System.out.println("mon fichier  = " + content);

        ps.print(content);
        ps.close();

        // 4.Receive a response
        //HttpStatusCode 200 is returned at the end of normal operation
        if (conn.getResponseCode() != 200) {
            //Error handling
        }

        //Get InputStream from HttpURLConnection and read
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        result = sb.toString();

        // 5.Disconnect
        conn.disconnect();

        //Return the result to the caller
        System.out.println(result);
    }
}
