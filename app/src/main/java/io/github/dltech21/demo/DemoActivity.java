package io.github.dltech21.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.LinkedList;
import java.util.List;

import io.github.dltech21.dlfilepicker.DLFilePicker;
import io.github.dltech21.dlfilepicker.DLFilePickerListener;
import io.github.dltech21.dlfilepicker.DLFilePickerSelectListener;
import io.github.dltech21.dlfilepicker.model.FileItem;

/**
 * Created by donal on 2018/4/25.
 */

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DLFilePicker.getInstance().selectFile(DemoActivity.this, new String[]{".pdf"}, new DLFilePickerSelectListener() {
                    @Override
                    public void onSuccess(List<FileItem> files) {
                        Toast.makeText(DemoActivity.this, files.get(0).getFilePath(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
