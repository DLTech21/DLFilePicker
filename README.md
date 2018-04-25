# DLFilePicker
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/dltech21/maven/DLFilePicker/images/download.svg) ](https://bintray.com/dltech21/maven/DLFilePicker/_latestVersion)


![](https://dltech21.github.io/assets/img/dlfilepicker.gif)

##Quick to use

1. add reference

```groovy
compile 'io.github.dltech21:DLFilePicker:1.0.0'
```

2. get file

```java
//目前支持txt doc pdf ppt xls wps docx pptx xlsx类型的文档
DLFilePicker.getInstance().setFileTypes(new String[]{".pdf"});
DLFilePicker.getInstance()
        .getFile(DemoActivity.this, new DLFilePickerListener() {
            @Override
            public void onSuccess(List<FileItem> files) {
            }
        });
```
