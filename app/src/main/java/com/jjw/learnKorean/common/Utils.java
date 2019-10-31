package com.jjw.learnKorean.common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.TypedValue;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

	public Utils() {
	}

	public static String getVersion() {
		PackageInfo pInfo;
		try {
			pInfo = WsApplication.getContext().getPackageManager().getPackageInfo(WsApplication.getContext().getPackageName(), 0);
			return pInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	//내 현재 전화번호 얻기, 필요하면 permission 추가하면됨
	/*public static String getMyPhoneNumber(Context activity, boolean useHyphen) {

		try {
			TelephonyManager systemService = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

			if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				return "";
			}
			String PhoneNumber = systemService.getLine1Number();

			PhoneNumber = PhoneNumber.substring(PhoneNumber.length() - 10, PhoneNumber.length());
			PhoneNumber = "0" + PhoneNumber;

			if (useHyphen) {
				return PhoneNumberUtils.formatNumber(PhoneNumber);
			} else {
				return PhoneNumber;
			}
		} catch (Exception e) {
			return "";
		}
	}*/
	
	public static String getUrl2HtmlCode(String addr)	{
		StringBuilder html = new StringBuilder();
		try {
			URL url = new URL(addr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			if(conn != null) {
				conn.setConnectTimeout(10000);
				conn.setUseCaches(false);
				int res = conn.getResponseCode();
				if(res == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					while(true) {
						String line = br.readLine();
						if(line == null) break;
						html.append(line + '\n');
					}
					br.close();
				}else{
				}
				conn.disconnect();
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return html.toString();
	}
	
	@SuppressLint("NewApi") 
	public static Bitmap rotatePhoto(Uri url) {
		ExifInterface exif;
		String currentPhotoPath = "";
		Bitmap rotatePhoto = null;
		try {
			currentPhotoPath = url.getPath();
			exif = new ExifInterface(currentPhotoPath);
			int exifOrientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			int exifDegree = exifOrientationToDegrees(exifOrientation);
			if (exifDegree != 0) {
				Bitmap bitmap = getBitmap(currentPhotoPath);
				rotatePhoto = rotate(bitmap, exifDegree);
				saveBitmap(rotatePhoto, currentPhotoPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rotatePhoto;

	}
	
	public static Bitmap rotate(Bitmap image, int degrees) {
		if (degrees != 0 && image != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) image.getWidth(),(float) image.getHeight());

			try {
				Bitmap b = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), m, true);

				if (image != b) {
					image.recycle();
					image = b;
				}

				image = b;
			} catch (OutOfMemoryError ex) {
				ex.printStackTrace();
			}
		}
		return image;
	}
	
	@SuppressLint("NewApi") 
	public static Bitmap getBitmap(String currentPhotoPath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inInputShareable = true;
		options.inDither = false;
		options.inTempStorage = new byte[32 * 1024];
		options.inPurgeable = true;
		options.inJustDecodeBounds = false;

		File f = new File(currentPhotoPath);

		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Bitmap bm = null;

		try {
			if (fs != null)
				bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, options);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bm;
	}
	
	// 실제File to Bitmap
	public static void saveBitmap(Bitmap bitmap, String currentPhotoPath) {
		File file = new File(currentPhotoPath);
		OutputStream out = null;
		
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		bitmap.compress(CompressFormat.JPEG, 95, out);
		
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressLint("NewApi") 
	public static File createImageFile() throws IOException {

		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

//		mCurrentPhotoPath = image.getAbsolutePath();

		return image;
	}
	
	/**
	 * 이미지 회전 각도를 알아옴.
	 * @param filepath
	 * @return 각도
	 */
	public synchronized static int getPhotoOrientationDegree(String filepath) 
	{
	 int degree = 0;
	 ExifInterface exif = null;
	 
	 try 
	 {
	  exif = new ExifInterface(filepath);
	 } 
	 catch (IOException e) 
	 {
		 e.printStackTrace();
	 }
	 
	 if (exif != null) 
	 {
	  int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
	  
	  if (orientation != -1) 
	  {
	   switch(orientation) 
	   {
	    case ExifInterface.ORIENTATION_ROTATE_90:
	     degree = 90;
	     break;
	     
	    case ExifInterface.ORIENTATION_ROTATE_180:
	     degree = 180;
	     break;
	     
	    case ExifInterface.ORIENTATION_ROTATE_270:
	     degree = 270;
	     break;
	   }

	  }
	 }
	 return degree;
	}
	
	public static int exifOrientationToDegrees(int exifOrientation) {
		if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
			return 90;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
			return 180;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
			return 270;
		}
		return 0;
	}
	
	public static Bitmap decodeFile(int minImageSize, URL url) {
		Bitmap b = null;
		InputStream is;
		try {

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true); // url로 input받는 flag 허용
			connection.connect(); // 연결
			is = connection.getInputStream();

			BitmapFactory.Options options = new BitmapFactory.Options();

			int scale = 1;
			if (options.outHeight > minImageSize
					|| options.outWidth > minImageSize) {
				scale = (int) Math.pow(2,(int) Math.round(Math.log(minImageSize
								/ (double) Math.max(options.outHeight,options.outWidth)) / Math.log(0.5)));
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			b = BitmapFactory.decodeStream(is, null, o2);

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * Map 형식으로 Key와 Value를 셋팅한다.
	 * 
	 * @param key
	 *            : 서버에서 사용할 변수명
	 * @param value
	 *            : 변수명에 해당하는 실제 값
	 * @return
	 */
	public static String setValue(String key, String value) {
		return "Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n" + value;
	}

	/**
	 * 업로드할 파일에 대한 메타 데이터를 설정한다.
	 * 
	 * @param key
	 *            : 서버에서 사용할 파일 변수명
	 * @param fileName
	 *            : 서버에서 저장될 파일명
	 * @return
	 */
	public static String setFile(String key, String fileName) {
		return "Content-Disposition: form-data; name=\"" + key + "\";filename=\"" + fileName + "\"\r\n";
	}
	
	public static String setComma_won(String junsu) {
		  int inValues = Integer.parseInt(junsu);
		  DecimalFormat Commas = new DecimalFormat("#,###");
		  String result_int = Commas.format(inValues);
		  return result_int;
	}

	public static int getColor(Context context, int id) {
		final int version = Build.VERSION.SDK_INT;
		if (version >= Build.VERSION_CODES.M) {
			return ContextCompat.getColor(context, id);
		} else {
			return context.getResources().getColor(id);
		}
	}

	public static Drawable getDrawable(Context context, int id) {
		final int version = Build.VERSION.SDK_INT;
		if (version >= Build.VERSION_CODES.LOLLIPOP) {
			return ContextCompat.getDrawable(context,id);
		} else {
			return context.getResources().getDrawable(id);
		}
	}

	/**
	 * 앱이 포그라운드인지 판단 후 리턴
	 *
	 * @param context
	 *            :
	 * @return true:포그라운드
	 */

	public static boolean isAppForeground(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
		if ("com.wts.tirebids".equals(info.get(0).processName)
				&& info.get(0).importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
			return true;
		}
		return false;
	}

	/**
	 * 앱이 실행중인지 판단 후 리턴
	 *
	 * @param context
	 *            :
	 * @return true:실행중인경우
	 */

	public static boolean isAppRunning(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> info = activityManager.getRunningAppProcesses();
		for (int i = 0; i < info.size(); i++) {
			if ("com.wts.tirebids".equals(info.get(i).processName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 시간을 입력 받아 오전 오후 구분 후 리턴
	 *
	 * @param time : 11:01:00 형식으로
	 * @return : 오전 11:01
	 */
	public static String setTime_AMPM(String time) {
		String regTime[] = time .split(":");
		String stime;
		if(12 > Integer.parseInt(regTime[0])){
			stime = "오전 " + String.valueOf(Integer.parseInt(regTime[0])) + ":" + regTime[1];
		}
		else {
			if (12 == Integer.parseInt(regTime[0])) {
				stime = "오후 " + String.valueOf(Integer.parseInt(regTime[0])) + ":" + regTime[1];
			}
			else {
				stime = "오후 " +  String.valueOf(Integer.parseInt(regTime[0]) - 12) + ":" + regTime[1];
			}
		}
		return  stime;
	}


	/**
	 * Dimension dp를 가져올 경 디바이스 사이즈별에 따라 변경 됨
	 * dp 그 수치 그대로 그리기 위해 변환
	 *
	 * @param context, dimenId : getResources().getDimension(...)
	 * @return : int
	 */
	public static int getPixelValue(Context context, float dimenId) {
		Resources resources = context.getResources();
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP,
				dimenId,
				resources.getDisplayMetrics()
		);
	}
}
