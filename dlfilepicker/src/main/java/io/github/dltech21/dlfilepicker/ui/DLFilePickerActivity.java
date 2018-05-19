package io.github.dltech21.dlfilepicker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.LinkedList;
import java.util.List;

import io.github.dltech21.dlfilepicker.DLFilePicker;
import io.github.dltech21.dlfilepicker.DLFilePickerListener;
import io.github.dltech21.dlfilepicker.R;
import io.github.dltech21.dlfilepicker.model.FileItem;

/**
 * Created by donal on 2018/4/26.
 */

public class DLFilePickerActivity extends AppCompatActivity {
    private RecyclerView rvFile;
    private FileAdapter fileAdapter;
    private List<FileItem> mData;
    private String[] mFileFilter;

    public static void open(Context mContext, String[] fileFilter) {
        mContext.startActivity(new Intent(mContext, DLFilePickerActivity.class).putExtra("filefilter", fileFilter));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlfilepicker);
        mFileFilter = getIntent().getStringArrayExtra("filefilter");
        initView();
        AndPermission.with(this)
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        getFile();
                    }
                })
                .start();
    }

    private void initView() {
        initToolbar();
        rvFile = (RecyclerView) findViewById(R.id.rv_photo);
        rvFile.setLayoutManager(new LinearLayoutManager(this));
        mData = new LinkedList<>();
        fileAdapter = new FileAdapter(this, mData);
        rvFile.setAdapter(fileAdapter);
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getFile() {
        //目前支持txt doc pdf ppt xls wps docx pptx xlsx类型的文档
        DLFilePicker.getInstance().setFileTypes(mFileFilter);
        DLFilePicker.getInstance()
                .getFile(this, new DLFilePickerListener() {
                    @Override
                    public void onSuccess(List<FileItem> files) {
                        mData.addAll(files);
                        fileAdapter.notifyDataSetChanged();
                    }
                });
    }
}
