package com.hongyuan.fitness.ui.out_door.wallk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.hongyuan.fitness.base.MyApplication;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.today.step.lib.ISportStepInterface;
import com.today.step.lib.SportStepJsonUtils;
import com.today.step.lib.TodayStepService;

import java.util.HashMap;
import java.util.Map;

public class TodayStepUtils {


    private static TodayStepUtils stepUtils = null;
    private TodayStepUtils(){
        setTodyStep();
    }

    public static TodayStepUtils getInstance(){
        if(stepUtils == null){
            stepUtils = new TodayStepUtils();
        }
        return stepUtils;
    }

    //步行里面需要的数据
    public interface ReturnWalkStepData{
        void stepData(int mStepSum,String allDistion,String allCalorie);
    }
    //跑步里面需要的数据
    public interface ReturnRunData{
        void stepData(String allDistion,String allCalorie);
    }
    private ReturnWalkStepData walkStepData;
    private ReturnRunData RunData;

    public void setWalkStepData(ReturnWalkStepData walkStepData){
        this.walkStepData = walkStepData;
        //每次进入页面先回调一次
        walkStepData.stepData(mStepSum, SportStepJsonUtils.getDistanceByStep(mStepSum),SportStepJsonUtils.getCalorieByStep(mStepSum));
    }
    public void setRunData(ReturnRunData RunData){
        this.RunData = RunData;
        //初始化跑的步数
        initializeRun();
    }

    private Handler mDelayHandler = new Handler(new TodayStepCounterCall());
    private static final int REFRESH_STEP_WHAT = 0;
    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL_REFRESH = 3000;
    private ISportStepInterface iSportStepInterface;
    private int mStepSum;


    //记录跑步每分钟移动的步数数据
    private Map<Integer,Integer> runStep = new HashMap<>();

    /*
     * 计步功能
     * */
    private void setTodyStep(){
        //开启计步Service，同时绑定Activity进行aidl通信
        Intent intent = new Intent(MyApplication.getInstance(), TodayStepService.class);
        MyApplication.getInstance().startService(intent);
        MyApplication.getInstance().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //Activity和Service通过aidl进行通信
                iSportStepInterface = ISportStepInterface.Stub.asInterface(service);
                try {
                    mStepSum = iSportStepInterface.getCurrentTimeSportStep();
                    if(walkStepData != null){
                        walkStepData.stepData(mStepSum, SportStepJsonUtils.getDistanceByStep(mStepSum),SportStepJsonUtils.getCalorieByStep(mStepSum));
                    }
                    if(RunData != null){
                        RunData.stepData(SportStepJsonUtils.getDistanceByStep(getNowRunStep()),SportStepJsonUtils.getCalorieByStep(getNowRunStep()));
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    class TodayStepCounterCall implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_STEP_WHAT: {
                    //每隔500毫秒获取一次计步数据刷新UI
                    if (null != iSportStepInterface) {
                        int step = 0;
                        try {
                            step = iSportStepInterface.getCurrentTimeSportStep();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        if (mStepSum != step) {
                            mStepSum = step;
                            runEndStepNum = mStepSum;
                            if(walkStepData != null){
                                walkStepData.stepData(mStepSum, SportStepJsonUtils.getDistanceByStep(mStepSum),SportStepJsonUtils.getCalorieByStep(mStepSum));
                            }
                            if(RunData != null){
                                RunData.stepData(SportStepJsonUtils.getDistanceByStep(getNowRunStep()),SportStepJsonUtils.getCalorieByStep(getNowRunStep()));
                            }
                        }

                    }
                    mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

                    break;
                }
            }
            return false;
        }
    }

    /*
    * 跑步需要的数据
    * */
    private int runStartStepNum;
    private int runEndStepNum;
    private int runStepNum;

    private void initializeRun(){
        runStartStepNum = mStepSum;
        runEndStepNum = mStepSum;
        runStepNum = runEndStepNum - runStartStepNum;

        runStep.clear();
        runStep.put(0,runStartStepNum);
    }

    /*
    * 获取当前跑的步数
    * */
    private int getNowRunStep(){
        runStepNum = runEndStepNum - runStartStepNum;
        return runStepNum;
    }

    /*
    * 跑步过程中每隔30秒来记录一次
    * */
    public void RunningIn(int passedTime){
        runStep.put(passedTime,mStepSum);
    }

    /*
    * 判断是否停止运动
    * */
    public boolean isRunning(int passedTime){
        if(passedTime == 0) return true;
        if((runStep.get(passedTime) - runStep.get((passedTime - 30))) > 0){
            return false;
        }else{
            return true;
        }
    }

    /*
    * 获取当前的数据
    * */
    public String getSpeeds(int secends){
        if(secends != 0){
            return BigDecimalUtils.mul(BigDecimalUtils.div(SportStepJsonUtils.getDistanceByStep(getNowRunStep()),String.valueOf(secends),10),"60",2);
        }else{
            return "0.00";
        }
    }
}
