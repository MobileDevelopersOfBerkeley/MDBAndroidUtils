# androidFirebaseUtils
Utils and starter code to help with firebase dev on android

# THIS IS OLD CODE NOT UPDATED TO NEW FIREBASE SDK CHANGES DO NOT USE

<strong> API REFERENCE: </strong> https://www.firebase.com/docs/android/api/

```java
Firebase ref = new Firebase("https://<name of firebase app>.firebaseio.com/");

// EXAMPLE LOGIN
ref.authWithPassword("some email goes here", "some password goes here", new Firebase.AuthResultHandler() {
      @Override
      public void onAuthenticated(AuthData authData) {
          // do something with authData or go to some new intent
      }

      @Override
      public void onAuthenticationError(FirebaseError firebaseError) {
          // handle error here
      }
});

// EXAMPLE MULTI RESULT QUERY
ref.child("someTableNameGoesHere").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
          for(DataSnapshot child : dataSnapshot.getChildren()) {
              // handle data here
          }
      }

      @Override
      public void onCancelled(FirebaseError firebaseError) {
          // handle error here
      }
});

// EXAMPLE SINGLE RESULT QUERY
ref.child("someTableName").orderByChild("someColumnName").equalTo("someValue").addValueEventListenerForSingleResult(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
          dataSnapshot.getValue(); // handle value here
      }

      @Override
      public void onCancelled(FirebaseError firebaseError) {
          // handle error here
      }
});

```
