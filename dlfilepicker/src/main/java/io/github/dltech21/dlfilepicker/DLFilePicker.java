package io.github.dltech21.dlfilepicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.github.dltech21.dlfilepicker.model.FileItem;
import io.github.dltech21.dlfilepicker.ui.DLFilePickerActivity;

/**
 * Created by donal on 2018/4/25.
 */

public class DLFilePicker {

    private volatile static DLFilePicker mInstance;

    private String[] fileTypes;

    private MyHandler myHandler;

    private DLFilePickerListener listener;

    public DLFilePicker() {
        myHandler = new MyHandler();
    }

    public static DLFilePicker getInstance() {
        if (mInstance == null) {
            synchronized (DLFilePicker.class) {
                if (mInstance == null) {
                    mInstance = new DLFilePicker();
                }
            }
        }
        return mInstance;
    }

    public void setFileTypes(String[] mFileTypes) {
        this.fileTypes = mFileTypes;
    }

    public void getFile(Context context, DLFilePickerListener listener) {
        this.listener = listener;
        getDocuments(context);
    }

    private void getDocuments(final Context mContext) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                ContentResolver contentResolver = mContext.getContentResolver();
                String[] projection = new String[]{MediaStore.Files.FileColumns.DATA,
                        MediaStore.Files.FileColumns.TITLE, MediaStore.Files.FileColumns.SIZE,
                        MediaStore.Files.FileColumns.DATE_MODIFIED};

                //分别对应 txt doc pdf ppt xls wps docx pptx xlsx 类型的文档
                String selection = MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? "
                        + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";

                String[] selectionArgs = new String[]{"text/plain", "application/msword", "application/pdf",
                        "application/vnd.ms-powerpoint", "application/vnd.ms-excel", "application/vnd.ms-works",
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                        "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};

                Cursor cursor = contentResolver.query(MediaStore.Files.getContentUri("external"), projection,
                        selection, selectionArgs, MediaStore.Files.FileColumns.DATE_MODIFIED + " desc");

                if (cursor != null) {
                    List<FileItem> files = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                        String size = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE));
                        String date = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED));
                        if (scannerFile(filePath)) {
                            FileItem fileItem = new FileItem(filePath, size, date);
                            files.add(fileItem);
                        }

                    }
                    cursor.close();
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = files;
                    myHandler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 0;
                    myHandler.sendMessage(msg);
                }
            }
        }).start();
    }

    private boolean scannerFile(String path) {
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            if (fileTypes != null) {
                for (String type : fileTypes) {
                    if (path.contains(type)) {
                        return true;
                    }
                }
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public DLFilePickerListener getListener() {
        return listener;
    }

    private static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (getInstance().getListener() != null) {
                        List<FileItem> files = (List<FileItem>) msg.obj;
                        getInstance().getListener().onSuccess(files);
                    }
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }

    public void selectFile(Context mContext, String[] fileFilter) {
        DLFilePickerActivity.open(mContext, fileFilter);
    }
}
