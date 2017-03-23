package com.example.asus.currencyconvertor;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;


/**
 * Created by annguyen on 10/6/16.
 */
public class CommonUtils<T> {
    public static String getDeviceRegionCode(Context ctx) {
        return ctx.getResources().getConfiguration().locale.getCountry();
    }

    public static String parseNumber(long number) {
        return NumberFormat.getNumberInstance().format(number);
    }

    public static boolean isJSONArrayString(String input) {
        return "[".equalsIgnoreCase(Character.toString(input.charAt(0)))
                && "]".equalsIgnoreCase(Character.toString(input.charAt(input.length() - 1)));
    }

    public static boolean isJSONObjectString(String input) {
        return "{".equalsIgnoreCase(Character.toString(input.charAt(0)))
                && "}".equalsIgnoreCase(Character.toString(input.charAt(input.length() - 1)));
    }

    /*public static String parseDuration(String duration) {
        String result = "";
        PeriodFormatter format = ISOPeriodFormat.standard();
        Period _duration = format.parsePeriod(duration);
        int hours = _duration.getHours();
        int minutes = _duration.getMinutes();
        int seconds = _duration.getSeconds();
        ArrayList<String> temp = new ArrayList<>();
        if (hours > 0) {
            if (hours < 10)
                temp.add("0" + hours);
            else
                temp.add("" + hours);
        }
        temp.add(minutes < 10 ? "0" + minutes : "" + minutes);
        temp.add(seconds < 10 ? "0" + seconds : "" + seconds);
        result = TextUtils.join(":", temp);
        return result;
    }
*/
    public boolean isNetworkConnecting() {
        boolean isConnected = false;
//        ConnectivityManager cm = (ConnectivityManager) Context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return isConnected;
    }

    public static int getDisplayHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static float getDpFromPx(Context context, int px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

//    public static void openListVideoFragment(int layoutReplace) {
//        Global.fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
//                .replace(layoutReplace, new ListVideoSegment(), Constants.FRAGMENT_LIST_VIDEO_TAG)
//                .addToBackStack(null).commit();
//
//    }

   /* public static VideoModel initVideoModel(Video videoDTO) {
        return new VideoModel(videoDTO.getVideoChannelId(), videoDTO.getVideoThumbURI(), videoDTO.getVideoChannelTitle(), videoDTO.getVideoDuration(), videoDTO.getVideoId(), videoDTO.getVideoName(), videoDTO.getVideoViewCount());
    }

    public static boolean isVideoFavourited(String videoId) {
        Video video = Global.database.getVideoById(videoId);
        return false;
    }*/

//    public static void removeFragmentByTag(String tag) {
//        Global.fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
//                                                 .remove(Global.fragmentManager.findFragmentByTag(tag)).commit();
//    }

//    public static void addFragment(int mainLayout, Fragment fragment, String tag) {
//        Global.fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_to_left, R.anim.left_to_right)
//                                                 .replace(mainLayout, fragment, tag).addToBackStack(null).commit();
//    }

    public static String getRegionCode(Context ctx, String lang) {
        return ctx.getResources().getString(ctx.getResources().getIdentifier(lang + "_region_code", "string", ctx.getPackageName()));
    }

    public static int getResourceByName(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

  /*  public static void setupGoogleAnalyticTracker(String screenName) {
        Global.mTracker.setScreenName("Image~" + screenName);
        Global.mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }*/

    public static void setTimeOut(final Runnable runnable, final int timer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(timer);
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static void setTimeoutRunUI(final Runnable runnable, final int timer, final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(timer);
                    activity.runOnUiThread(runnable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Load image from cache or from online
     *
     * @param context    Context of fragment or activity
     * @param uri        Image URI
     * @param targetView ImageView that image will be populated
     * @param isFit      Image is fit to view or not
     */
   /* public static void loadImage(final Context context, final String uri, final ImageView targetView, boolean isFit) {
        if (uri.equals(Constants.BLANK))
            return;

        RequestCreator requestCreator = Picasso.with(context).load(uri);
        if (isFit)
            requestCreator = requestCreator.fit();
        requestCreator.networkPolicy(NetworkPolicy.OFFLINE).into(targetView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context).load(uri).networkPolicy(NetworkPolicy.NO_CACHE).into(targetView);
            }
        });
    }*/

    /**
     * Load image from cache or from online and fit to view
     *
     * @param// context    Context of fragment or activity
     * @param// uri        Image URI
     * @param// targetView ImageView that image will be populated
     */
    /*public static void loadImage(final Context context, final String uri, final ImageView targetView) {
        if (uri.equals(Constants.BLANK))
            return;

        RequestCreator requestCreator = Picasso.with(context).load(uri);
        requestCreator.networkPolicy(NetworkPolicy.OFFLINE).fit().into(targetView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context).load(uri).networkPolicy(NetworkPolicy.NO_CACHE).fit().into(targetView);
            }
        });
    }*/

    public static <T> ArrayList<T> castListToArrayList(List<T> data) {
        ArrayList<T> response = new ArrayList<>();
        for (T item : data) {
            response.add(item);
        }
        return response;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static JSONObject readJSONObjectFromAssets(String path, String fileName, Context context) {
        try {
            InputStream inputStream = context.getAssets().open(path + fileName);
            int size = inputStream.available();
            byte[] content = new byte[size];
            inputStream.read(content);
            return new JSONObject(new String(content));
        } catch (IOException e) {
            e.printStackTrace();
            ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONArray radJSONArrayFromAssets(String fileName, Context context) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] content = new byte[size];
            inputStream.read(content);
            return new JSONArray(new String(content));
        } catch (IOException e) {
            e.printStackTrace();
            ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static String readJSONFileFromAssets(String fileName, Context context) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] content = new byte[size];
            inputStream.read(content);
            return new String(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Constants.BLANK;
    }*/

    public static ArrayList<String> convertJSONArrayStringToArrayListString(JSONArray jsonArray) {
        ArrayList<String> result = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getEmojiByCountryCode(String countryCode) {
        int firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }

     public static  boolean isWifiAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService
                (CONNECTIVITY_SERVICE));
        boolean isWifiConnected = false;
        Network[] networks = new Network[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            networks = connectivityManager.getAllNetworks();
        }
        if (networks == null) {
            isWifiConnected = false;
        } else {
            for (Network network : networks) {
                NetworkInfo info = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    info = connectivityManager.getNetworkInfo(network);
                }
                if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
                    if (info.isAvailable() && info.isConnected()) {
                        isWifiConnected = true;
                        break;
                    }
                }
            }
        }
        return isWifiConnected;
    }

    public  static boolean  checkInternet(Context context){
        boolean internet =false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        if(is3g ==true)
            internet = true;
        else if(is3g ==false && isWifi ==false)
            internet =false;
        else if(isWifi ==true)
            internet = isWifiAvailable(context);

        return  internet;
    }
}
