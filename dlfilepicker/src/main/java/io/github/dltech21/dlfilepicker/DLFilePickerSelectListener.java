package io.github.dltech21.dlfilepicker;

import java.util.List;

import io.github.dltech21.dlfilepicker.model.FileItem;

/**
 * Created by donal on 2018/5/19.
 */

public interface DLFilePickerSelectListener {
    void onSuccess(List<FileItem> files);
}
