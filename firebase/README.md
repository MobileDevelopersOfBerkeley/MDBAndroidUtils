# androidFirebaseUtils
Utils and starter code to help with firebase dev on android

API REFERENCE: https://firebase.google.com/docs/reference/android/packages

Example Usage of FirebaseUtils:
```java
List<Table1> result1 = new ArrayList<Table1>();
List<Table1> result2 = new ArrayList<Table2>();
List<Table1> result3 = new ArrayList<Table3>();

private void fetchAndShowDataAsync() {
      Query q1 = QueryUtils.ref.child("Table1").orderByChild("Column1").equalTo("SomeValue");
      Query q2 =  QueryUtils.ref.child("Table2").orderByChild("Column2").equalTo("SomeValue");
      Query q3 = QueryUtils.ref.child("Table3").orderByChild("Column3").equalTo("SomeValue");
      Callable<Void> callable3 = QueryUtils.chainQuery(q3, Table3.class, result3, showCallback);
      Callable<Void> callable2 = QueryUtils.chainQuery(q2, Table2.class, result2, callable3);
      Callable<Void> callable1 = QueryUtils.chainQuery(q1, Table1.class, result1, callable2);
      try {
          callable1.call(); // does queries in order or q1, q2, then q3
      } catch (Exception e) {
          // handle error
      }
}

private Callable<Void> showCallback = new Callable<Void>() {
      @Override
      public Void call() throws Exception {
          // do things to display data from Queries in activity
      }
};
```
