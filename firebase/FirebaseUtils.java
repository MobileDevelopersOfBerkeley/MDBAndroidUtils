import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.telecom.Call;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.mobiledevsberkeley.dealonconcept.objects.Deal;
import org.mobiledevsberkeley.dealonconcept.objects.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by krishnan on 4/19/2016.
 */
public class FirebaseUtils {
    public static final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public static FirebaseUser curr = FirebaseUser.getInstance().getCurrentUser();

    public static Callable<Void> chainQuery(final Query q, final Class objectClass, final List storeResults, final Callable<Void> callback) {
        return new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                q.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot result: dataSnapshot.getChildren()) {
                            storeResults.add(result.getValue(objectClass));
                        }
                        try {
                            if(callback != null) callback.call();
                        } catch (Exception e) {
                            Utils.logStackTrace(e);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Utils.logStackTrace(databaseError.toException());
                    }
                });
            }
        };
    }
}
