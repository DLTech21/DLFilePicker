package io.github.dltech21.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.LinkedList;
import java.util.List;

import io.github.dltech21.dlfilepicker.DLFilePicker;
import io.github.dltech21.dlfilepicker.DLFilePickerListener;
import io.github.dltech21.dlfilepicker.model.FileItem;

/**
 * Created by donal on 2018/4/25.
 */

public class DemoActivity extends Activity {
    private RecyclerView rvFile;
    private FileAdapter fileAdapter;
    private List<FileItem> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        AndPermission.with(this)
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        DLFilePicker.getInstance().getFile(DemoActivity.this, new DLFilePickerListener() {
                            @Override
                            public void onSuccess(List<FileItem> files) {
                                mData.addAll(files);
                                fileAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                })
                .start();
    }

    public void initView() {
        rvFile = (RecyclerView) findViewById(R.id.rv_photo);
        rvFile.setLayoutManager(new LinearLayoutManager(this));
        mData = new LinkedList<>();
        fileAdapter = new FileAdapter(this, mData);
        rvFile.setAdapter(fileAdapter);
    }
}
