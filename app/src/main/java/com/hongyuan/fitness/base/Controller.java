package com.hongyuan.fitness.base;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.hongyuan.fitness.ui.find.circle.edit_post.FileBean;
import com.hongyuan.fitness.util.GsonUtil;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Controller {
    public static final int TYPE_GET = 0x1;
    public static final int TYPE_POST = 0x2;
    /*
    * post,get键值对请求方式
    * */
    public static synchronized <T> void myRequest(String path,int requestTpey,
                                                  Map<String,String> maps,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(path);
        Log.e("cdj","=====path===="+path);
        if(maps != null){
            //组装常规参数
            Set<String> set = maps.keySet();
            for (String s : set) {
                String key = s;
                String value = maps.get(s);
                params.addBodyParameter(key, value);
                Log.e("cdj","=====key===="+key+"=====value===="+value);
            }
        }
        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(path,dataBean,listener));
        }else{
            x.http().post(params, getCallback(path,dataBean,listener));
        }

    }

    /*
    * 多文件上传
    * */
    public static synchronized <T> void myRequest(String path,int requestTpey,
                                                  List<KeyValue> files,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(path);
        if(files != null){
            MultipartBody body= new MultipartBody(files,"UTF-8");
            params.setRequestBody(body);
            params.setMultipart(true);
        }
        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(path,dataBean,listener));
        }else{
            x.http().post(params, getCallback(path,dataBean,listener));
        }

    }
    /*
    * 单文件上传
    * */
    public static synchronized <T> void myRequest(String path,int requestTpey,
                                                  Map<String,String> maps,
                                                  FileBean mFile,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(path);
        if(maps != null){
            //组装常规参数
            Set<String> set = maps.keySet();
            for (String s : set) {
                String key = s;
                String value = maps.get(s);
                params.addBodyParameter(key, value);
            }
        }
        if(mFile != null){
            params.addBodyParameter(mFile.getFileKey(), mFile.getmFile());
        }

        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(path,dataBean,listener));
        }else{
            x.http().post(params, getCallback(path,dataBean,listener));
        }

    }


    /*
    * callback
    * */
    private static <T> Callback.CommonCallback<String> getCallback(String mPath,final Class<T> dataBean,
                                                                   final RetrofitListener<T> listener){
        return new Callback.CommonCallback<String>() {
            BaseBean baseBean;
            @Override
            public void onSuccess(String result) {
                Log.e("cdj","=====接口====="+mPath+"====="+result);
                try {
                    JSONObject object = new JSONObject(result);
                    baseBean = GsonUtil.getGson().fromJson(result, new TypeToken<BaseBean>(){}.getType());
                    if((!object.get("data").toString().equals("{}") && !object.get("data").toString().equals("[]")) && "1".equals(baseBean.getStatus().getSucceed())){
                        T data = GsonUtil.getGson().fromJson(result,dataBean);
                        //请求成功并解析成功反馈结果
                        if(listener != null){
                            listener.onSuccess(data);
                        }
                    }else if((object.get("data").toString().equals("{}") || object.get("data").toString().equals("[]")) && !"1".equals(baseBean.getStatus().getSucceed())){
                        listener.onError(baseBean.getStatus().getError_code(),baseBean.getStatus().getError_desc());
                    }else if((object.get("data").toString().equals("{}") || object.get("data").toString().equals("[]")) && "1".equals(baseBean.getStatus().getSucceed())){
                        listener.onSuccess(1,null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("cdj","====解析错误===="+mPath+"========"+e.getMessage());
                    //解析出错
                    if(listener != null){
                        listener.onError(baseBean.getStatus().getError_code(),"解析异常！");
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("cdj","====请求错误===="+ex.getMessage()+ex.toString());
                //请求错误反馈信息
                if(listener != null){
                    listener.onError(baseBean.getStatus().getError_code(),ex.getMessage());
                }
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("cdj","====请求取消===="+cex.getMessage());
                //取消请求反馈信息
                if(listener != null){
                    listener.onError(1,cex.getMessage());
                }
            }
            @Override
            public void onFinished() {
                //关闭刷新
                if(listener != null){
                    listener.closeRefresh();
                }
            }
        };
    }

    /*
     * post,get键值对请求方式(改请求方式是在不同接口，使用的同一个对象时，用来做区分)
     * */
    public static synchronized <T> void myRequest(int code,String path,int requestTpey,
                                                  Map<String,String> maps,
                                                  final Class<T> dataBean,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(path);
        Log.e("cdj","=============="+path);
        if(maps != null){
            //组装常规参数
            Set<String> set = maps.keySet();
            for (String s : set) {
                String key = s;
                String value = maps.get(s);
                params.addBodyParameter(key, value);
                Log.e("cdj","=====key===="+key+"=====value===="+value);
            }
        }
        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(code,dataBean,listener));
        }else{
            x.http().post(params, getCallback(code,dataBean,listener));
        }
    }

    /*
    * callback
    * */
    private static <T> Callback.CommonCallback<String> getCallback(int code,final Class<T> dataBean,
                                                                   final RetrofitListener<T> listener){
        return new Callback.CommonCallback<String>() {
            BaseBean baseBean;
            @Override
            public void onSuccess(String result) {
                try {
                    Log.e("cdj","====="+result);
                    baseBean = GsonUtil.getGson().fromJson(result, new TypeToken<BaseBean>(){}.getType());
                    if("1".equals(baseBean.getStatus().getSucceed())){
                        T data = GsonUtil.getGson().fromJson(result,dataBean);
                        //请求成功并解析成功反馈结果
                        if(listener != null){
                            listener.onSuccess(code,data);
                        }
                    }else{
                        listener.onError(baseBean.getStatus().getError_code(),baseBean.getStatus().getError_desc());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("cdj","====解析错误===="+e.getMessage());
                    //解析出错
                    if(listener != null){
                        listener.onError(baseBean.getStatus().getError_code(),"解析异常！");
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("cdj","====请求错误===="+ex.getMessage()+ex.toString());
                //请求错误反馈信息
                if(listener != null){
                    listener.onError(baseBean.getStatus().getError_code(),ex.getMessage());
                }
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("cdj","====请求取消===="+cex.getMessage());
                //取消请求反馈信息
                if(listener != null){
                    listener.onError(baseBean.getStatus().getError_code(),cex.getMessage());
                }
            }
            @Override
            public void onFinished() {
                //关闭刷新
                if(listener != null){
                    listener.closeRefresh();
                }
            }
        };
    }


    /*
     * 不做解析的操作（等到下一步再去解析）
     * */
    public static synchronized <T> void myRequest(int code,String path,int requestTpey,
                                                  Map<String,String> maps,
                                                  final RetrofitListener<T> listener){
        RequestParams params = new RequestParams(path);
        //Log.e("cdj","====接口地址==="+path);
        if(maps != null){
            //组装常规参数
            Set<String> set = maps.keySet();
            for (String s : set) {
                String key = s;
                String value = maps.get(s);
                params.addBodyParameter(key, value);
                //Log.e("cdj","=====key===="+key+"=====value===="+value);
            }
        }
        if(requestTpey == TYPE_GET){
            x.http().get(params, getCallback(code,listener));
        }else{
            x.http().post(params, getCallback(code,listener));
        }

    }

    private static Callback.CommonCallback<String> getCallback(int code,final RetrofitListener listener){
        return new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listener.onSuccess(code,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("cdj","====请求错误===="+ex.getMessage()+ex.toString());
                //请求错误反馈信息
                if(listener != null){
                    listener.onError(600,ex.getMessage());
                }
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("cdj","====请求取消===="+cex.getMessage());
                //取消请求反馈信息
                if(listener != null){
                    listener.onError(600,cex.getMessage());
                }
            }
            @Override
            public void onFinished() {
                //关闭刷新
                if(listener != null){
                    listener.closeRefresh();
                }
            }
        };
    }

}
