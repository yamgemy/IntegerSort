package com.example.yamgemy.integersort.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

public class SharePrefWorker {

    private static class PREF_KEYS{
        public static final String KEY_MAIN_SHAREDPREF = "main";
        public static final String KEY_QUERIEDNUMSTACK = "qns";
    }
    /**************************************************************/
    private static SharePrefWorker prefWorker;
    private static SharedPreferences settings;
    private static Context activityCtx;

    public static SharePrefWorker getInstance(Context activityCtx){
        if (prefWorker==null){
            prefWorker = new SharePrefWorker();
            prefWorker.activityCtx = activityCtx;
            settings = prefWorker.getMAINSharedPreferences();
        }
        return prefWorker;
    }

    private SharedPreferences getMAINSharedPreferences() {
        return activityCtx.getApplicationContext().getSharedPreferences(PREF_KEYS.KEY_MAIN_SHAREDPREF, 0);
        //getSharedPreferences need only appear once across the whole app. Keys under which are already unique
    }

    public static int QTYPE_MANUAL = 54;
    public static int QTYPE_RANDOM = 23;

    public void saveQueriedStack(Stack<Integer> queriedStack, ArrayList<Integer> max2nums, int query_type){
        Gson g = new Gson();
        //g.toJson(queriedStack, new TypeToken<Stack<Integer>>(){}.getType());

        List<List<Stack<Integer>>> savedQueryList = null;

        if (!settings.contains(PREF_KEYS.KEY_QUERIEDNUMSTACK)){
            savedQueryList = new ArrayList<>();
        }else{
            String savedQueryListJson = settings.getString(PREF_KEYS.KEY_QUERIEDNUMSTACK,null);
            if (savedQueryListJson!=null){
                Type t = new TypeToken<ArrayList<ArrayList<Stack<Integer>>>>(){}.getType();
                savedQueryList = g.fromJson(savedQueryListJson, t);

                List<Stack<Integer>> newElement = new ArrayList<>();
                //0
                newElement.add(queriedStack);

                //1
                Stack<Integer> resultstack = new Stack<Integer>();
                resultstack.add(max2nums.get(0));
                resultstack.add(max2nums.get(1));
                newElement.add(resultstack);

                //2
                Stack<Integer> qtype_time = new Stack<Integer>();
                qtype_time.add(query_type);

                //3. time
                /***
                Calendar c = Calendar.getInstance();
                DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm a");
                String time = dateFormat.format(c.getTime());
                Log.d("meh",time);
                boolean isPm = false;
                if (String.valueOf(time.charAt(time.length()-1)).equalsIgnoreCase("p")){
                    isPm = true;
                }
                String subtime = time.substring(0, time.length()-2);
                Log.d("meh",time);
                time.replace(":","").trim();
                int timeInt=0;
                if (isPm){
                    timeInt = 0 - Integer.parseInt(time);
                }
                qtype_time.add(timeInt);

                //3

**/
                newElement.add(qtype_time);
                savedQueryList.add(0, newElement);
            }
        }

        String updatedJsonStr = g.toJson(savedQueryList);
        settings.edit().putString(PREF_KEYS.KEY_QUERIEDNUMSTACK, updatedJsonStr).apply();
    }

    public ArrayList<ArrayList<Stack<Integer>>> getSavedQueryList(){
        String savedQueryListJson = settings.getString(PREF_KEYS.KEY_QUERIEDNUMSTACK,null);
        Gson g = new Gson();
        Type t = new TypeToken<ArrayList<ArrayList<Stack<Integer>>>>(){}.getType();
        return g.fromJson(savedQueryListJson, t);
    }

    public void clearSavedQuery(){
        Gson g = new Gson();
        ArrayList<ArrayList<Stack<Integer>>> dummy = new ArrayList<>();
        Type t = new TypeToken<ArrayList<ArrayList<Stack<Integer>>>>(){}.getType();
        String dummyStr = g.toJson(dummy, t);
        settings.edit().putString(PREF_KEYS.KEY_QUERIEDNUMSTACK, dummyStr).apply();
    }
}
