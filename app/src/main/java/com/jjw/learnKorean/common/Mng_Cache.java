package com.jjw.learnKorean.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;


public final class Mng_Cache implements DicKey {
    private static final String TAG = "Cache";

//	public static NoticeNode SUBJECT_NODE;

    private HashMap<String, Properties> propertyCache = null;
    private HashMap<String, Object> dataCache = null;
    private HashMap<String, Bitmap> imgCache = null;
    private SharedPreferences sharedPreferences = null;
    public final static String PREFS_KEY = "TirebidsPrefsFile";

    private static Mng_Cache instance;

    public static Mng_Cache getInstance() {
        if (instance == null) {
            synchronized (Mng_Cache.class) {
                instance = new Mng_Cache();
            }
        }
        return instance;
    }

    private Mng_Cache() {
        propertyCache = new HashMap<String, Properties>();
        dataCache = new HashMap<String, Object>();
        imgCache = new HashMap<String, Bitmap>();
    }

    /**
     * 케쉬데이타초기화처리<br>
     *
     * @param context Context
     * @return
     */
    public void initialize(Context context) {
        try {

            // 폰번호 가져오는건데 일단 필요없으니 주석처리해놓음 혹시 폰번호 들고오고싶으면 permission 추가해서 아래 주석 해제하면됨
//            String PhoneNumber = Utils.getMyPhoneNumber(context, true);
//            this.putPrefString(KEY_PHONE, PhoneNumber);
//            this.putPrefString(KEY_OS, "A");

        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
    }


    /**
     * 프로퍼티취득<br>
     *
     * @param cacheKey String 캐쉬키
     * @return Properties 프로퍼티
     */
    public Properties getProperties(String cacheKey) {
        Properties prop = getInstance().propertyCache.get(cacheKey);
        if (prop == null) {
            return null;
        }
        return prop;
    }

    /**
     * 캐쉬데이타취득<br>
     *
     * @param cacheKey String 캐쉬키
     * @param propKey  String 프로퍼티키
     * @return String 캐쉬데이타
     */
    public String getProperty(String cacheKey, String propKey) {
        Properties prop = getInstance().propertyCache.get(cacheKey);

        if (prop == null) {
            return null;
        }

        String rtn = prop.getProperty(propKey);
        return rtn;
    }

    /**
     * 캐쉬데이타취득<br>
     *
     * @param cacheKey     String 캐쉬키
     * @param propKey      String 프로퍼티키
     * @param defaultValue String 디폴트값
     * @return String 캐쉬데이타
     */
    public String getProperty(String cacheKey, String propKey, String defaultValue) {
        String propValue = getInstance().getProperty(cacheKey, propKey);

        if (propValue == null) {
            return defaultValue;
        }

        return propValue;
    }

    /**
     * 데이타설정<br>
     *
     * @param cacheKey
     */
    public void setDataCache(String cacheKey, Object data) {
        getInstance().dataCache.put(cacheKey, data);
    }

    /**
     * 데이타취득<br>
     *
     * @param cacheKey
     */
    public Object getDataCache(String cacheKey) {
        return getInstance().dataCache.get(cacheKey);
    }

    /**
     * 이미지설정<br>
     *
     * @param cacheKey
     */
    public void setImgCache(String cacheKey, Bitmap data) {
        getInstance().imgCache.put(cacheKey, data);
    }

    /**
     * 데이타설정<br>
     *
     * @param cacheKey
     */
    public Bitmap getImgCache(String cacheKey) {
        return getInstance().imgCache.get(cacheKey);
    }

    public void setSharedPreferences(SharedPreferences pref) {
        getInstance().sharedPreferences = pref;
    }

    public void putPrefString(String prefKey, String prefValue) {
        try {
            Editor editor = getInstance().sharedPreferences.edit();
            editor.putString(prefKey, prefValue);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putPrefStringSet(String prefKey, Set<String> prefValue) {
        try {
            Editor editor = getInstance().sharedPreferences.edit();
            editor.putStringSet(prefKey, prefValue);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putPrefInt(String prefKey, int prefValue) {
        try {
            Editor editor = getInstance().sharedPreferences.edit();
            editor.putInt(prefKey, prefValue);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putPrefLong(String prefKey, long prefValue) {
        try {
            Editor editor = getInstance().sharedPreferences.edit();
            editor.putLong(prefKey, prefValue);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPrefString(String prefKey) {
        String returnValue = "";

        try {
            returnValue = getInstance().sharedPreferences.getString(prefKey, "");
        } catch (Exception e) {
//			e.printStackTrace();
        }
        return returnValue;
    }

    public Set<String> getPrefStringSet(String prefKey) {
        Set<String> returnValue = null;

        try {
            returnValue = getInstance().sharedPreferences.getStringSet(prefKey, null);
        } catch (Exception e) {
//			e.printStackTrace();
        }
        return returnValue;
    }

    public int getPrefInt(String prefKey) {
        int returnValue = Integer.MIN_VALUE;

        try {
            returnValue = getInstance().sharedPreferences.getInt(prefKey, Integer.MIN_VALUE);
        } catch (Exception e) {
//			e.printStackTrace();
        }
        return returnValue;
    }

    public long getPrefLong(String prefKey) {
        long returnValue = Long.MIN_VALUE;

        try {
            returnValue = getInstance().sharedPreferences.getLong(prefKey, Long.MIN_VALUE);
        } catch (Exception e) {
//			e.printStackTrace();
        }
        return returnValue;
    }

    public void putPrefBoolean(String key, boolean value) {
        try {
            Editor editor = getInstance().sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPresent(String key) {
        return getInstance().sharedPreferences.contains(key);
    }

    public boolean getPrefBoolean(String key) {
        Log.i(TAG, key);
        boolean returnValue = false;
        try {
            returnValue = getInstance().sharedPreferences.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
