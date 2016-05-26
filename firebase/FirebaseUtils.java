import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by krishnan on 5/16/2016.
 */
public class FirebaseUtils {
    public static final Firebase ref = new Firebase("https://<name of firebase app>.firebaseio.com/");

    public static void setImageViewByFirebaseImageUrlAsync(final ImageView imageView, String url) {
        ref.child(url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String binStr = dataSnapshot.getValue(String.class);
                if(binStr.contains("jpeg")) binStr = binStr.replace("data:image/jpeg;base64,","");
                else if (binStr.contains("png")) binStr = binStr.replace("data:image/png;base64,","");
                byte[] decodedString = Base64.decode(binStr, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // handle error here
            }
        });
    }
}
