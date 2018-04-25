# DLFilePicker

![](https://dltech21.github.io/assets/img/dlfilepicker.gif)

##Quick to use
1. add reference

```
compile 'io.github.dltech21:DLFilePicker:1.0.0'
```

2. get file

```
//目前支持txt doc pdf ppt xls wps docx pptx xlsx类型的文档
DLFilePicker.getInstance().setFileTypes(new String[]{"pdf"});
DLFilePicker.getInstance()
        .getFile(DemoActivity.this, new DLFilePickerListener() {
            @Override
            public void onSuccess(List<FileItem> files) {
            }
        });
```
