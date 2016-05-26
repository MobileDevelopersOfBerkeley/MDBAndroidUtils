import android.os.AsyncTask;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by krishnan on 3/9/2016.
 */
public class DoPostJSON  extends AsyncTask<JSONObject, Void, JSONObject>
{

    // setup connection to rest api function
    private HttpURLConnection createConn(String action) throws Exception
    {
        URL url = new URL("<SOME URL OF REST API GOES HERE>/" + action);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        return conn;
    }

    // read return value from rest api function
    private JSONObject getResponse(HttpURLConnection conn) throws Exception
    {
        String response = "";
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK)
        {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = br.readLine()) != null)
            {
                response += line;
            }
        }
        else
        {
            response = "";
        }
        return new JSONObject(response);
    }

    // pass in arguements to rest api function
    private void writeArgsToConn(JSONObject args, HttpURLConnection conn) throws Exception
    {
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(args.toString());
        writer.flush();
        writer.close();
        os.close();
    }

    @Override
    protected JSONObject doInBackground(JSONObject... params)
    {
        JSONObject args = params[0];
        try
        {
            String action = args.getString("action"); // an action is the name,
                                                    // of the rest api function you want to call
            HttpURLConnection conn = createConn(action);
            writeArgsToConn(args, conn);
            return getResponse(conn);
        }
        catch (Exception e)
        {
            Utils.logStackTrace(e);
            return null;
        }
    }
}
