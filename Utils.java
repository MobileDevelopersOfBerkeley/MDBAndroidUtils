public class Utils {

    public static String logTag = "TAG";
    private static SharedPreferences sharedPref;

    private static SharedPreferences.Editor editor;

    public static void logStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        Log.e(logTag, sw.toString());
    }

    public static void createPopup(String s, String title, Context context) {
        AlertDialog.Builder popup = new AlertDialog.Builder(context);
        popup.setTitle(title);
        popup.setMessage(s);
        popup.setPositiveButton("OK", null);
        popup.show();
    }

    public static void createPopup(String s, String title, Context context, DialogInterface.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder popup = new AlertDialog.Builder(context);
        popup.setTitle(title);
        popup.setMessage(s);
        popup.setPositiveButton("OK", onClickListener);
        popup.setOnCancelListener(onCancelListener);
        popup.show();
    }

    public static void savePref(String pref, String val, Activity context) {
        if (sharedPref == null)
            sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        if (editor == null)
            editor = sharedPref.edit();
        editor.putString(pref, val);
        editor.commit();
    }

    public static String readPref(String pref, String def, Activity context) {
        if (sharedPref == null)
            sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(pref, def);
    }

    public static void clearPrefs(Activity context) {
        savePref("user", "", context);
        savePref("pass", "", context);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getElapsedTime(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        long elapsedSeconds = different / secondsInMilli;
        if(elapsedDays > 0) return elapsedDays + " days left";
        if(elapsedDays < 0) return -1*elapsedDays + " days ago";
        if(elapsedHours> 0) return elapsedHours + " hours left";
        if(elapsedHours < 0) return -1*elapsedHours + " hours ago";
        if(elapsedMinutes > 0) return elapsedMinutes + " minutes left";
        if(elapsedMinutes < 0) return -1*elapsedMinutes + " minutes ago";
        if(elapsedSeconds > 0) return elapsedSeconds + " seconds left";
        if(elapsedSeconds < 0) return -1*elapsedSeconds + " seconds ago";
        return "???";
    }
}
