import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by krishnan on 3/9/2016.
 */
public class DoPostMultiPart extends AsyncTask<JSONObject, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        JSONObject args = params[0];
        try {
            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

            // args to rest function
            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("fullname", args.getString("fullname"))
                    .addFormDataPart("email", args.getString("email"))
                    .addFormDataPart("password", args.getString("password"))
                    .addFormDataPart("confpassword",  args.getString("confpassword"))
                    .addFormDataPart("pic", "profile.png", RequestBody.create(MEDIA_TYPE_PNG, (File) args.get("pic")))
                    .build();

            // setup connection to rest api function
            Request request = new Request.Builder()
                    .url("<SOME URL OF REST API GOES HERE>/" + args.getString("action"))
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (Exception e) {
            Utils.logStackTrace(e);
            return null;
        }
    }
}
