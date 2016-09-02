# zbar
zbar的扫描二维码,个人觉得扫描速度很快的库

1、如何使用它？
先在 build.gradle(Project:XXXX) 的 repositories 添加:

    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
然后在 build.gradle(Module:app) 的 dependencies 添加:

    dependencies {
            compile 'com.github.xuanu:zbar:0.0.1'
    }

2、代码中使用
    去扫描页面：```startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 99);```
    取扫描结果：```String tempResult = data.getStringExtra(CaptureActivity.RESULT_DATA);```
   
