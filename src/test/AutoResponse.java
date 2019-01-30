package test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import static test.Test.i;

/**
 *
 * @author MAC_03
 */
public class AutoResponse {

    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // task to run goes here
                //System.out.println("Hello !!!");
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.1.121/blzc/public/api/chatbot/activity");
// Request parameters and other properties.
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("email", "mail@mail.com"));
                params.add(new BasicNameValuePair("cookie_id", "68e8f57b5ee922c7e0fe270fb53ad562f90b71cf"));
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*
 * Execute the HTTP Request
                 */
                try {
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity respEntity = response.getEntity();

                    if (respEntity != null) {
                        // EntityUtils to get the response content
                        String content = EntityUtils.toString(respEntity);
                        System.out.println("getResponse : " + content + " i : " + i++);
                    }
                } catch (ClientProtocolException e) {
                    // writing exception to log
                    e.printStackTrace();
                } catch (IOException e) {
                    // writing exception to log
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 5 * 1000;
        // schedules the task to be run in an interval 
        timer.scheduleAtFixedRate(task, delay,
                intevalPeriod);
    }
}
