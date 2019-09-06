package com.hongyuan.fitness.base;

/**
 * Created by suxq on 2019/3/21
 */

public interface RetrofitListener<T> {

    /**
     * 请求成功时回调
     *
     * @param data 网络请求的数据对象
     */
    void onSuccess(T data);

    /**
     * 请求成功时回调(当不同接口使用相同对象时，需要实现该方法来区分)
     *
     * @param data 网络请求的数据对象
     */
    void onSuccess(int code,T data);

    /**
     * 请求失败时回调，网络问题造成的连接失败以及服务端返回的code不在200-300内，比如404
     * 都会触发该回调
     *
     * @param description 失败的描述
     */
    void onError(int error_code,String description);

    /*
    * 当为空数据时返回
    * */
    //void onEmpty();

    /*
    * 关闭刷新功能
    * */
    void closeRefresh();

}
