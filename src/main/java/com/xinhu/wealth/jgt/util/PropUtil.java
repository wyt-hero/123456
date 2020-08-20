package com.xinhu.wealth.jgt.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class PropUtil {

    public static String getProperty(String fileName, String name) {
        Properties properties = new Properties();
        try {
            URL url = PropUtil.class.getClassLoader().getResource(fileName);
            String path = java.net.URLDecoder.decode(url.getFile(), "utf-8");
            String enc = "utf-8";
            MyUnicodeInputStream uin = new MyUnicodeInputStream(new FileInputStream(path), enc);
            enc = uin.getEncoding();
            InputStreamReader in;
            if (enc == null) {
                in = new InputStreamReader(uin, "utf-8");
            } else {
                in = new InputStreamReader(uin, enc);
            }
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(name);
    }

    public static void setProperty(String fileName, String key, String value) {
        try {
            URL url = PropUtil.class.getClassLoader().getResource(fileName);
            String path = java.net.URLDecoder.decode(url.getFile(), "utf-8");
            String enc = "utf-8";
            MyUnicodeInputStream uin = new MyUnicodeInputStream(new FileInputStream(path), enc);
            enc = uin.getEncoding(); // check and skip possible BOM bytes

            StringBuffer stringBuffer = new StringBuffer();
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path), enc);
            BufferedReader bfreader = new BufferedReader(reader);
            String line;
            int c = 0;
            while ((line = bfreader.readLine()) != null) {
                if (line.startsWith(key + "=")) {
                    stringBuffer.append(key + "=" + value + "\n");
                    c++;
                } else {
                    stringBuffer.append(line + "\n");
                }
            }
            if (c == 0) {
                stringBuffer.append(key + "=" + value + "\n");
            }
            OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(path, false), enc);
            fos.write((stringBuffer.toString() + "\n"));
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
