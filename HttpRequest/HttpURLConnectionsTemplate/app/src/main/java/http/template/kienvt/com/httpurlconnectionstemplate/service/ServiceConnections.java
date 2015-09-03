package http.template.kienvt.com.httpurlconnectionstemplate.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mrkien on 9/3/15.
 */
public class ServiceConnections implements ServiceConstant {
    public static String getBaseUrl() {
        if (isDevelopmentMode) {
            return PREFIX_URL_DEVELOPMENT;
        }
        return PREFIX_URL_PRODUCTION;
    }

    public static String sendGetRequest(String urlString) {
        String retVal = "";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            retVal = convertInputStreamToString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return retVal;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
            /* Close Stream */
        if (null != inputStream) {
            inputStream.close();
        }
        return result;
    }
}
