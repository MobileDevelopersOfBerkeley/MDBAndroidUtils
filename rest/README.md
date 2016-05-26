# androidRestUtils
This is starter code that will help you get started on calling REST API (or cloud code) functions from your android app.

<strong>Note: </strong> use DoPostMultiPart only if you want to upload files, otherwise use DoPostJSON

```java
  public static void someCloudFunction(Map<String, Object> args, Activity context) {
    try {
      JSONObject params = new JSONObject();
          
      // indicate the name of the cloud function
      params.put("action", "someCloudFunction");
                
      // pass in arguements
      for(String key : args) {
        params.put(key, args[key]);
      }
                
      // call "someCloudFunction" and get the result from it
      JSONObject response = new DoPostJSON().execute(params).get();
          
      // ...
    } catch (Exception e) {
      // ...    
    }
  }
```