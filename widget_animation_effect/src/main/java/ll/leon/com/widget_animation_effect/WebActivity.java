package ll.leon.com.widget_animation_effect;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends AppCompatActivity {

    public static final String URL = "http://api.01yuelao.com/two/web/wpayauth.html";
    private BridgeWebView wv;

    private SeekBar sb;

    public static void start(Context context) {
        Intent starter = new Intent(context, WebActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        wv = findViewById(R.id.wv1);
        sb = findViewById(R.id.sb);


        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                sb.setProgress(newProgress);
                sb.setVisibility(newProgress == 100 ? View.INVISIBLE : View.VISIBLE);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                result.confirm();
                if (BuildConfig.DEBUG)
                    Log.d("onJsPrompt : ", url + "  " + message + " defaultValue  " + defaultValue + "  result " + result.toString());
                return true;
                // return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

//        wv.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                addPictureClick();
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (BuildConfig.DEBUG) Log.d("WebActivity", url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });

        wv.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(WebActivity.this, data, Toast.LENGTH_SHORT).show();
                if (BuildConfig.DEBUG) Log.d("WebActivity", "接受 JS send data" + data);
                if (function != null) {
                    function.onCallBack("Native Receive data");
                }

            }
        });
        wv.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (BuildConfig.DEBUG) Log.d("WebActivity", "submitFromWeb :" + data);
                Toast.makeText(WebActivity.this, data, Toast.LENGTH_SHORT).show();
                if (function != null) {

                    function.onCallBack("submitFromWeb Received in Native");//回调到Js
                }

            }
        });

        wv.registerHandler("functionOpen",  new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(WebActivity.this, data+"网页在打开你的文件预览", Toast.LENGTH_SHORT).show();
                pickFile();
            }

        });

        wv.loadUrl("file:///android_asset/demo.html");
        wv.send("Hello from Java!");
    }


    /**
     *
     */
    private void addPictureClick() {

        wv.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "    objs[i].onclick=function()  " + "   " + " {  "
                + "        window.JavascriptCallNativeInterface.openImage(this.src);  "
                + "    }  " + "}" + "})()");

    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1: //注入接口
                wv.removeJavascriptInterface("WebViewJavascriptBridge");
                wv.loadUrl("https://hao.360.cn/?360safe");
                wv.addJavascriptInterface(new JsCallJava() {
                                              @JavascriptInterface
                                              @Override
                                              public void openImage(String src) {
                                                  if (BuildConfig.DEBUG) Log.d("WebActivity", src);
                                              }
                                          }
                        , "JavascriptCallNativeInterface");

                break;
            case R.id.bt2:
                wv.loadUrl(URL);
                wv.addJavascriptInterface(new JsCallJava2() {
                    @JavascriptInterface
                    @Override
                    public void callHandler(String handlerName, String src) {
                        Toast.makeText(WebActivity.this, "price" + src, Toast.LENGTH_SHORT).show();
                    }
                }, "WebViewJavascriptBridge");
                break;
            case R.id.bt3:
                wv.loadUrl("file:///android_asset/demo.html");
                break;
            case R.id.bt4:
                wv.send("Hello from Java!", new CallBackFunction() {
                    @Override
                    public void onCallBack(String data) {
                        Toast.makeText(WebActivity.this, "JS 回调" +data, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }

    }
    private int CODE=0;
    public void pickFile() {
        Intent picture = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CODE) {

            if (intent == null || intent.getData() ==null) {
                return;
            }
            //文件操作
            Uri selectedImage = intent.getData();
            Toast.makeText(this, "selectedImage:" + selectedImage, Toast.LENGTH_SHORT).show();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage,
                    filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Log.i("WebActivity",picturePath);
            Toast.makeText(this,picturePath,Toast.LENGTH_LONG).show();
        }
    }

    public interface JsCallJava {
        public void openImage(String src);
    }

    public interface JsCallJava2 {
        public void callHandler(String handlerName, String src);
    }
}
