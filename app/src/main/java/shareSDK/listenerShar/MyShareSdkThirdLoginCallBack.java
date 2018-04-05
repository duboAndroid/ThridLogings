package shareSDK.listenerShar;


import android.widget.Toast;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import shareSDK.ShareSDKActivity;

/**
 * Created by Administrator on 2016-12-22.
 */
public abstract class MyShareSdkThirdLoginCallBack implements StephenThirdPartyTool.ShareSdkThirdLoginCallBack {
    private ShareSDKActivity baseActivity;

    public MyShareSdkThirdLoginCallBack(ShareSDKActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onNotLogin(String failMsg) {
        Toast.makeText(baseActivity,failMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(Platform plat, int action, Throwable t) {
        Toast.makeText(baseActivity,"第三方登录失败,请重试!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginCancel(Platform plat, int action) {
        if(QQ.NAME.equals(plat.getName())){
            Toast.makeText(baseActivity,"QQ登录已取消!",Toast.LENGTH_SHORT).show();
        }else if(Wechat.NAME.equals(plat.getName())){
            Toast.makeText(baseActivity,"微信登录已取消!",Toast.LENGTH_SHORT).show();
        }else if(SinaWeibo.NAME.equals(plat.getName())){
            Toast.makeText(baseActivity,"微博登录已取消!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(baseActivity,"第三方登录已取消!",Toast.LENGTH_SHORT).show();
        }
    }
}
