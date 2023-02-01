package org.zywx.wbpalmstar.plugin.uexdevice;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.LocationManager;
import android.provider.Settings;
import android.text.TextUtils;

import org.zywx.wbpalmstar.base.BUtility;
import org.zywx.wbpalmstar.engine.EBrowserView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeviceUtils {

    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isGPSEnable(Context context) {
        try {
            LocationManager locationManager = (LocationManager)context.
                    getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isBluetoothEnable() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter.isEnabled();
    }

    /**
     * 生成文件输出目录的文件路径
     *
     */
    public static String generateOutputPhotoFilePath(String prefix, EBrowserView mBrwView) {
        // 直接已最终目录作为文件名
        String folderPath = BUtility.getWidgetOneRootPath() + "/apps/" + mBrwView.getRootWidget().m_appId + "/photo";// 获得文件夹路径
        checkFolderPath(folderPath);// 如果不存在，则创建所有的父文件夹
        if (TextUtils.isEmpty(prefix)) {
            prefix = "device";
        }
        // 生成带时间戳的目录
        String fileName = getSimpleDateFormatFileName(prefix, ".jpg");
        String outputFilePath = folderPath + "/" + fileName;// 获得新的存放目录
        return outputFilePath;
    }

    /**
     * 检查文件夹路径是否存在，不存在则创建
     *
     * @param folderPath
     * @return 返回创建结果
     */
    private static boolean checkFolderPath(String folderPath) {

        File file = new File(folderPath);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 得到以日期命名的文件名
     *
     * @param prefix 前缀
     * @param postfix 后缀
     * @return 文件名
     */
    public static String getSimpleDateFormatFileName(String prefix, String postfix) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String fileName = prefix + simpleDateFormat.format(date) + postfix;

        return fileName;
    }
}
