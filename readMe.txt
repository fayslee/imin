将此项目集成到客户项目中方法：
1）复制本项目下的IminStraElectronicSDK_V1.2.jar到自己的项目中libs
2)将本项目中的jniLibs的全部内容复制到自己项目中的jniLibs中
3）将MainActivity.java的部分代码
除  super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
 外的代码都复制到自己需要显示的Activity中


4）将项目中的index.html复制到自己项目中的assets中。




***********************************************************************************************

1、用户的Activity implements ElectronicCallback 实现接口的方法
然后mElectronic = new Electronic.Builder().setDevicePath("/dev/ttyS4").setReceiveCallback(this).builder();
进行注册
注册后在数据有变化时会通过  electronicStatus(String info, String key) 进行回调



2、 java 中的 mWebView.loadUrl("javascript:electronicStatus('"+info+"','"+key+"')");
与JS中的 function electronicStatus(str, key) {}对应
3、
java中
mWebView.addJavascriptInterface(this, "JsActivity");
@JavascriptInterface
    public void turnZero(){
        mElectronic.turnZero();
    }
与 JS中的JsActivity.turnZero() 对应
4、java中的
mWebView.addJavascriptInterface(this, "JsActivity");
@JavascriptInterface
    public void removePeel(){
        mElectronic.removePeel();
    }

    与JS中的JsActivity.removePeel()对应　



