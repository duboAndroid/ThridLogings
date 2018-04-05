package shareSDK.listenerShar;

import android.text.TextUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import shareSDK.ShareSDKActivity;

public class StephenThirdPartyTool {

    public StephenThirdPartyTool(ShareSDKActivity baseActivity) {
        ShareSDK.initSDK(baseActivity);
    }

    public void startShareSdkThirdLogin(String thirdPlatName,final ShareSdkThirdLoginCallBack shareThirdLoginCallBack){
        System.out.print("thirdPlatName ========="+thirdPlatName);
        if(!TextUtils.isEmpty(thirdPlatName)){
            Platform platform = ShareSDK.getPlatform(thirdPlatName);
            if(null != platform){
                if(platform.isAuthValid())platform.removeAccount(true);
                platform.SSOSetting(false);//true不使用SSO授权,false使用SSO授权
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onComplete(Platform plat, int action,HashMap<String, Object> res) {
                        if(action == Platform.ACTION_USER_INFOR) {
                            if(null != shareThirdLoginCallBack)shareThirdLoginCallBack.onLoginComplete(plat, action, res);
                        }else{
                            if(null != shareThirdLoginCallBack)shareThirdLoginCallBack.onNotLogin("未能成功获取到你的用户信息,无法登录!");
                        }
                    }

                    public void onError(Platform plat, int action, Throwable t) {
                        if(null != shareThirdLoginCallBack)shareThirdLoginCallBack.onLoginError(plat, action, t);
                    }

                    public void onCancel(Platform plat, int action) {
                        if(null != shareThirdLoginCallBack)shareThirdLoginCallBack.onLoginCancel(plat, action);
                    }
                });
                //platform.authorize();
                platform.showUser(null);
            }else{
                if(null != shareThirdLoginCallBack)shareThirdLoginCallBack.onNotLogin("登录平台实例获取失败,无法登录!");
            }
        }else{
            if(null != shareThirdLoginCallBack)shareThirdLoginCallBack.onNotLogin("登录平台名称为空,无法登录!");
        }
    }

    ////////////////////////////////////////////interface///////////////////////////////////////////
    public interface ShareSdkThirdLoginCallBack{
        void onNotLogin(String failMsg);
        void onLoginComplete(Platform plat, int action, HashMap<String, Object> res);
        void onLoginError(Platform plat, int action, Throwable t);
        void onLoginCancel(Platform plat, int action);
    }
}
