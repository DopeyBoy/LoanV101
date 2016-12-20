package com.llkj.creditchecking.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaderFactory;
import com.bumptech.glide.load.model.LazyHeaders;
import com.llkj.creditchecking.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.CookieStore;
import java.util.List;

import okhttp3.Cookie;
import com.llkj.creditchecking.widget.GlideCircleTransform;
import com.llkj.creditchecking.widget.GlideRoundTransform;

/**
 * 加载图片工具类
 * Created by Administrator on 2016/11/1.
 */

public class ImageLoaderUtils {

    public static String result="";
    /**
     *  平角图
     * @param context 上下文
     * @param url 路径
     * @param icon 视图对象
     */
    public static void setLoginImg(Context context, final String url, ImageView icon){

        result="";
        final String murl = url+ "?t="+Math.random()*100;

        GlideUrl glideUrl = new GlideUrl(murl, new LazyHeaders.Builder()

                .addHeader("Cookie", new LazyHeaderFactory() {
                    @Override
                    public String buildHeader() {
                        String expensiveAuthHeader = getCookie(murl);
                        return expensiveAuthHeader;
                    }
                })
                .build());
        Glide.with(context).load(glideUrl)
               // .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存
                .error(R.drawable.ic_default_color) //加载失败的icon
                .centerCrop()
                .centerCrop()
                .skipMemoryCache(true)//跳过缓存
                .crossFade() //渐入
                .into(icon); //添加控件

    }

    private synchronized static String getCookie(String murl) {

        CookieJarImpl cookieJarImpl =  OkGo.getInstance().getCookieJar();
        CookieStore cookieStore = cookieJarImpl.getCookieStore();
        List<Cookie> CookieStores = cookieStore.getAllCookie();
        StringBuilder cookieBuilder = new StringBuilder();

        for (Cookie cookie : CookieStores) {

            LogUtils.e(cookie.toString()) ;
            cookieBuilder.append(cookie.toString());
            result = cookie.toString();
        }
        LogUtils.e("Cookie  "+result);
        return result;
    }

    public static void setImage(Context context, String url, ImageView icon){
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存
                .error(R.drawable.ic_default_color) //加载失败的icon
                .centerCrop()
                .skipMemoryCache(true)//跳过缓存
                .crossFade() //渐入
                .into(icon); //添加控件

    }

    /**
     *  圆形图
     * @param context
     * @param url
     * @param icon
     */
    public static void setCircleImage(Context context, String url, ImageView icon){

        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(context))
                .error(R.drawable.ic_default_color)
                .fitCenter()
                .crossFade()
                .into(icon);
    }

    /**
     *  圆角图
     * @param context
     * @param url
     * @param icon
     */
    public static void setTransFormImage(Context context, String url, ImageView icon){

        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(context))
                .error(R.drawable.ic_default_color)
                .centerCrop()
                .crossFade()
                .into(icon);
    }
}
