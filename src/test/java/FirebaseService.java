import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import config.TestConfig;

import java.io.FileInputStream;
import java.io.IOException;



public class FirebaseService {

    public static void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream(TestConfig.FirebaseSetup.adminSdk);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserFromFirebase(String email) {
        FirebaseService.initialize();
        try {
            UserRecord user = FirebaseAuth.getInstance().getUserByEmail(email);
            String userUUID = user.getUid();
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference userDocRef = db.collection("users").document(userUUID);
            System.out.println(userUUID);
            ApiFuture<WriteResult> writeResult = userDocRef.delete();
            writeResult.get();
            ApiFuture<DocumentSnapshot> future = userDocRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                throw new RuntimeException("Firestore document still exists after deletion.");
            }
            FirebaseAuth.getInstance().deleteUser(userUUID);
            System.out.println("User and Firestore document deleted successfully.");
            Thread.sleep(2000);

        } catch (Exception e) {
            System.err.println("Error deleting user or Firestore document: " + e.getMessage());
        }
    }
}