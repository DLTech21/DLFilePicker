package io.github.dltech21.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import io.github.dltech21.dlfilepicker.DLFilePicker;
import io.github.dltech21.dlfilepicker.DLFilePickerListener;
import io.github.dltech21.dlfilepicker.model.FileItem;

/**
 * Created by donal on 2018/4/25.
 */

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        AndPermission.with(this)
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        DLFilePicker.getInstance().getFile(DemoActivity.this, new DLFilePickerListener() {
                            @Override
                            public void onSuccess(List<FileItem> files) {
                                Log.e("sdf", files.size() + "");
                            }
                        });
                    }
                })
                .start();
    }
}
