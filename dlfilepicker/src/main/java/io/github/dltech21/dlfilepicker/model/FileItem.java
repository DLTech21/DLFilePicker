package io.github.dltech21.dlfilepicker.model;

import java.io.File;
import java.text.NumberFormat;

/**
 * Created by donal on 2018/4/25.
 */

public class FileItem {
    private String mFilePath;
    private String mSize;
    private String mDate;

    public FileItem(String mFilePath, String mSize, String mDate) {
        this.mFilePath = mFilePath;
        this.mSize = mSize;
        this.mDate = mDate;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public String getFileName() {
        int lastSep = mFilePath.lastIndexOf(File.separator);
        return lastSep == -1 ? mFilePath : mFilePath.substring(lastSep + 1);
    }

    public String getDate() {
        return mDate;
    }

    public String getFileSize() {
        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        //保留小数点后两位
        ddf1.setMaximumFractionDigits(2);
        long size = Long.valueOf(mSize);
        String sizeDisplay;
        if (size > 1048576.0) {
            double result = size / 1048576.0;
            sizeDisplay = ddf1.format(result) + "M";
        } else if (size > 1024) {
            double result = size / 1024;
            sizeDisplay = ddf1.format(result) + "K";

        } else {
            sizeDisplay = ddf1.format(size) + "B";
        }
        return sizeDisplay;
    }
}
