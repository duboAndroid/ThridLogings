package shareSDK;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dubo.thridlogings.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import shareSDK.listenerShar.MyShareSdkThirdLoginCallBack;
import shareSDK.listenerShar.StephenThirdPartyTool;

import static android.content.ContentValues.TAG;


/**
 * Created by dubo on 2018/3/26.
 */
public class ShareSDKActivity extends Activity{
    private StephenThirdPartyTool stephenThirdPartyTool;
    private String thirdOpenId,thirdUserName, thirdHeadUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_sdk);
        stephenThirdPartyTool = new StephenThirdPartyTool(this);
        findViewById(R.id.qq_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"QQ登录请求中,请稍候...");
                stephenThirdPartyTool.startShareSdkThirdLogin(QQ.NAME, new MyShareSdkThirdLoginCallBack(ShareSDKActivity.this) {
                    @Override
                    public void onLoginComplete(Platform plat, int action, HashMap<String, Object> res) {
                        thirdOpenId = String.valueOf(plat.getId());
                        thirdUserName = plat.getDb().getUserName();
                        thirdHeadUrl = plat.getDb().getUserIcon();
                    }
                });
            }
        });

        findViewById(R.id.we_chat_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"微信登录请求中,请稍候...");
                stephenThirdPartyTool.startShareSdkThirdLogin(Wechat.NAME, new MyShareSdkThirdLoginCallBack(ShareSDKActivity.this) {
                    @Override
                    public void onLoginComplete(Platform plat, int action, HashMap<String, Object> res) {
                        thirdOpenId = String.valueOf(plat.getId());
                        thirdUserName = plat.getDb().getUserName();
                        thirdHeadUrl = plat.getDb().getUserIcon();
                    }
                });
            }
        });

        findViewById(R.id.webo_chat_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"微博登录请求中,请稍候...");
                stephenThirdPartyTool.startShareSdkThirdLogin(SinaWeibo.NAME, new MyShareSdkThirdLoginCallBack(ShareSDKActivity.this) {
                    @Override
                    public void onLoginComplete(Platform plat, int action, HashMap<String, Object> res) {
                        thirdOpenId = String.valueOf(plat.getId());
                        thirdUserName = plat.getDb().getUserName();
                        thirdHeadUrl = plat.getDb().getUserIcon();
                    }
                });
            }
        });
    }

    //解析用户信息保存
    /*private boolean parseUserInfoSave(String loginJson){
        BeanResponseLoginBase loginBase= (BeanResponseLoginBase) JsonUtil.fromJson(loginJson,BeanResponseLoginBase.class);
        if(null != loginBase && null != loginBase.getData()){
            (new StephenUserInfoTool(this)).saveLoginUserInfo(userNameE.getText().toString(),passWordE.getText().toString(),loginBase.getData().getUid(),loginBase.getData().getTokenid());//把帐号保存起来后面都要用
            return true;
        }//end of if
        return false;
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        /*String weiXinFlag = ((MyApplication)getApplication()).getWeiXinFlag();
        if(!TextUtils.isEmpty(weiXinFlag)){
            ((MyApplication) getApplication()).setWeiXinFlag(null);
        }else{
            //mainHandler.sendEmptyMessage(VickyConfig.msg_closeLoading);//将loading关闭
        }*/
    }
}
