package com.jbangit.xwebview.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bigbyto on 01/12/2017.
 * 读取assets
 */

public class AssetsUtils {
    public static String readString(Context context,String filePath){
        byte[] data = read(context,filePath);

        return new String(data);
    }

    public static byte[] read(Context context, String filePath) {
        AssetManager assets = context.getAssets();
        InputStream is = null;
        ByteArrayOutputStream bos = null;

        try {
            is = assets.open(filePath);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer,0,len);
            }

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new byte[0];
    }
}