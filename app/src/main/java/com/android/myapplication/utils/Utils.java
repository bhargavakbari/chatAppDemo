package com.android.myapplication.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.android.myapplication.BuildConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static final String TAG = "Utils";
    private static final String FILE_NAME = "android_logs_temp.txt";
    public static final String DATE_FORMAT = "MMM dd yyyy HH:mm:ss";

    public static String getMillisToHumanRedableTime2(long milliSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
        Date resultdate = new Date(milliSecond);
        return sdf.format(resultdate);
    }


    public static Date getDateFromString(String strDate, String inputDateFormat) {
        try {
            java.text.DateFormat format = new SimpleDateFormat(inputDateFormat, Locale.ENGLISH);
            return format.parse(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeLogsToFile(String log) {

        if (BuildConfig.DEBUG) {
            File externalStorageDir = Environment.getExternalStorageDirectory();
            File myFile = new File(externalStorageDir, FILE_NAME);
            if (!myFile.exists()) {
                try {
                    myFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream fostream = new FileOutputStream(myFile, true);
                OutputStreamWriter oswriter = new OutputStreamWriter(fostream);
                BufferedWriter bwriter = new BufferedWriter(oswriter);
                bwriter.write(getMillisToHumanRedableTime(System.currentTimeMillis()) + "\t" + log);
                bwriter.newLine();
                bwriter.newLine();
                bwriter.close();
                oswriter.close();
                fostream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readLogsFromFile(Context context) {
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), FILE_NAME);
        String line = "";
        try {
            FileReader fis = new FileReader(file);
            BufferedReader bufRead = new BufferedReader(fis, 100);
            try {
                line = bufRead.readLine();
                Log.v(TAG, "Line read = " + line);
                while (line != null) {
                    Log.v(TAG, "Line read = " + line);
                    line = line + bufRead.readLine();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.v(TAG, "IOException found in reading line from file.");
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Log.v(TAG, "File not found for reading.");
        }
        return line;
    }

    public static String getMillisToHumanRedableTime(long milliSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(milliSecond);
        return sdf.format(resultdate);
    }
}
