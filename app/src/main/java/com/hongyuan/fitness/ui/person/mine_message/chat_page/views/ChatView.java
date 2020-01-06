package com.hongyuan.fitness.ui.person.mine_message.chat_page.views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.DefaultUser;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.MyMessage;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.audioconverter.AndroidAudioConverter;
import com.hongyuan.fitness.util.audioconverter.AudioFormat;
import com.hongyuan.fitness.util.audioconverter.IConvertCallback;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.CustomMenuEventListener;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.listener.RecordVoiceListener;
import cn.jiguang.imui.chatinput.menu.Menu;
import cn.jiguang.imui.chatinput.menu.MenuManager;
import cn.jiguang.imui.chatinput.menu.view.MenuFeature;
import cn.jiguang.imui.chatinput.menu.view.MenuItem;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.chatinput.model.VideoItem;
import cn.jiguang.imui.chatinput.record.RecordVoiceButton;
import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ViewHolderController;
import pub.devrel.easypermissions.EasyPermissions;

public class ChatView extends RelativeLayout implements SensorEventListener {

    private final static String TAG = "ChatPage";
    private final int RC_RECORD_VOICE = 0x0001;
    private final int RC_CAMERA = 0x0002;
    private final int RC_PHOTO = 0x0003;

    private CustomActivity mActivity;

    private MessageList mMsgList;
    private ChatInputView mChatInput;
    private SmartRefreshLayout refreshLayout;
    private RecordVoiceButton mRecordVoiceBtn;
    private ImageButton mSelectAlbumIb;

    private HeadsetDetectReceiver mReceiver;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;

    //消息列表数据
    private MsgListAdapter<MyMessage> mAdapter;
    private List<MyMessage> mUseData;//当前使用数据

    //会话名
    private String username;
    //最后一条消息的msgid
    private String lastMsgId;

    public ChatView(Context context) {
        super(context);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
    }

    public ChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
    }

    public ChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(context instanceof CustomActivity){
            mActivity = (CustomActivity) context;
        }
    }

    public void setDataParms(String username,String lastMsgId){
        this.username = username;
        this.lastMsgId = lastMsgId;
    }

    public void initModule() {
        mReceiver = new HeadsetDetectReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        mActivity.registerReceiver(mReceiver, intentFilter);
        //注册电源监听
        registerProximitySensorListener();

        mMsgList = findViewById(R.id.msgList);
        mChatInput = findViewById(R.id.chatInput);
        refreshLayout = findViewById(R.id.chatRefresh);
        mRecordVoiceBtn = mChatInput.getRecordVoiceButton();
        mSelectAlbumIb = mChatInput.getSelectAlbumBtn();

        //初始高度为固定，避免第一次打开录音表情，会撑满全屏问题。
        mChatInput.setMenuContainerHeight(691);

        //菜单管理
        MenuManager menuManager = mChatInput.getMenuManager();
        //添加自定义菜单
        //menuManager.addCustomMenu("MY_CUSTOM",R.layout.menu_text_item,R.layout.menu_text_feature);
        //菜单设置
        menuManager.setMenu(Menu.newBuilder().
                customize(true).
                setRight(Menu.TAG_SEND).
                setBottom(Menu.TAG_VOICE,Menu.TAG_EMOJI/*Menu.TAG_GALLERY,Menu.TAG_CAMERA*/).
                build());
        menuManager.setCustomMenuClickListener(new CustomMenuEventListener() {
            @Override
            public boolean onMenuItemClick(String tag, MenuItem menuItem) {
                //Menu feature will not be show shown if return false；
                return true;
            }

            @Override
            public void onMenuFeatureVisibilityChanged(int visibility, String tag, MenuFeature menuFeature) {
                if(visibility == View.VISIBLE){
                    // Menu feature is visible.
                }else {
                    // Menu feature is gone.
                }
            }
        });

        //设置语音发送事件
        mRecordVoiceBtn.setRecordVoiceListener(new RecordVoiceListener() {
            @Override
            public void onStartRecord() {
                // set voice file path, after recording, audio file will save here
                String path = Environment.getExternalStorageDirectory().getPath() + "/voice";
                File destDir = new File(path);
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                mRecordVoiceBtn.setVoiceFilePath(destDir.getPath(), DateFormat.format("yyyy-MM-dd-hhmmss", Calendar.getInstance(Locale.CHINA)) + "");
            }

            @Override
            public void onFinishRecord(File voiceFile, int duration) {

                //先去转换音频格式
                audioConverter(voiceFile,duration);
            }

            @Override
            public void onCancelRecord() {

            }

            /**
             * In preview record voice layout, fires when click cancel button
             * Add since chatinput v0.7.3
             */
            @Override
            public void onPreviewCancel() {

            }

            /**
             * In preview record voice layout, fires when click send button
             * Add since chatinput v0.7.3
             */
            @Override
            public void onPreviewSend() {

            }
        });

        setOnRefresh();
        //设置输入法监听事件
        setInputClick();

        //设置初始显示值
        setShowData();
    }

    /*
    * 音频转换
    * */
    private void audioConverter(File oldFile,int duration){
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                MyMessage message = new MyMessage(null, IMessage.MessageType.SEND_VOICE.ordinal());
                message.setUserInfo(new DefaultUser("1", "", TokenSingleBean.getInstance().getHeadUrl()));
                message.setMediaFilePath(convertedFile.getPath());
                message.setDuration(duration);
                message.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
                if(mUseData.size() == 0 || TimeUtil.getOffectMinutes(System.currentTimeMillis(),mUseData.get(mUseData.size() -1).getTimes()) > 1){
                    message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                }
                mAdapter.addToStart(message, true);

                //发送消息
                HuanXinUtils.getInstance().sendVoic(convertedFile.getPath(),duration, username, code -> {
                    if(code == HuanXinUtils.SUCCESS_CODE){
                        mAdapter.getMessageList().get(0).setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                        mAdapter.notifyItemChanged(0);
                    }
                });
            }
            @Override
            public void onFailure(Exception error) {

            }
        };

        //开始转换
        AndroidAudioConverter.with(mActivity)
                .setFile(oldFile)
                .setFormat(AudioFormat.WAV)
                .setCallback(callback)
                .convert();
    }

    /*
     * 初始化加载控件各个设置
     * */
    private void setOnRefresh(){
        //关闭滚动到底部自动加载
        refreshLayout.setEnableAutoLoadMore(false);
        //设置主题颜色
        refreshLayout.setPrimaryColors(0xFFF2F2F2);
        //初始刷新动画
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
        //初始化加载动画
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        //开启上拉加载更多
        refreshLayout.setEnableLoadMore(true);
        //是否开启刷新功能
        refreshLayout.setEnableRefresh(true);
        refreshLayout.finishLoadMoreWithNoMoreData();
        refreshLayout.setOnRefreshListener(onRefresh());
    }

    /*
     * 下拉加载更多事件
     * */
    private OnRefreshListener onRefresh() {
        return refreshLayout -> {
            refreshLayout.finishRefreshWithNoMoreData();

        };
    }

    /*
    * 设置初始显示值
    * */
    public void setShowData() {
        //加载当前会话的所有消息的数据
        if(BaseUtil.isValue(lastMsgId)){
            mUseData = HuanXinUtils.getInstance().getChatMessages(username,lastMsgId,100);
        }else{
            mUseData = new ArrayList<>();
        }


        MsgListAdapter.HoldersConfig holdersConfig = new MsgListAdapter.HoldersConfig();
        mAdapter = new MsgListAdapter<>("0", holdersConfig, getImageLoader());
        mAdapter.addToEndChronologically(mUseData);
        mMsgList.setAdapter(mAdapter);
        mAdapter.getLayoutManager().scrollToPosition(0);

        //设置消息监听
        HuanXinUtils.getInstance().setMsgListener(myMessages -> {
            for(MyMessage bean:myMessages){
                mAdapter.addToStart(bean,true);
            }

        });

    }

    /*
    * 设置触摸事件
    * */
    public void setOnTouchListener(OnTouchListener listener) {
        mMsgList.setOnTouchListener(listener);
    }


    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public ChatInputView getChatInputView() {
        return mChatInput;
    }


    /**
     * (这他妈是多余的，反而还有问题)
     * reset MessageList's height, so that switch to SoftInput or Menu
     * wouldn't cause MessageList scroll
     * @param isTouchMsgList if touch MessageList, reset MessageList's height.
     */
    /*public void setMsgListHeight(boolean isTouchMsgList) {
        if (!isTouchMsgList) {
            ViewGroup.LayoutParams layoutParams = mMsgList.getLayoutParams();
            int height = mChatInput.getSoftKeyboardHeight();
            if (height > 0) {
                Log.e("cdj", "flse======= = " + height);
                layoutParams.height = mChatInput.getSoftKeyboardHeight();
                mMsgList.setLayoutParams(layoutParams);
            }
        } else {
            ViewGroup.LayoutParams layoutParams = mMsgList.getLayoutParams();
            layoutParams.height = mMsgListMaxHeight;
            Log.e("cdj", "set MessageList height, height = " + layoutParams.height);
            mMsgList.setLayoutParams(layoutParams);
        }
    }*/

    /**
     * Store all image messages' path, pass it to {@link},
     * so that click image message can browser all images.
     */
    private ArrayList<String> mPathList = new ArrayList<>();
    private ArrayList<String> mMsgIdList = new ArrayList<>();

    /*
     * 设置item图标点击事件
     * */
    private void setInputClick(){
        mChatInput.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(CharSequence input) {
                if (input.length() == 0) {
                    return false;
                }
                MyMessage message = new MyMessage(input.toString(), IMessage.MessageType.SEND_TEXT.ordinal());
                message.setUserInfo(new DefaultUser("1", "", TokenSingleBean.getInstance().getHeadUrl()));
                if(mUseData.size() == 0 || TimeUtil.getOffectMinutes(System.currentTimeMillis(),mUseData.get(mUseData.size() -1).getTimes()) > 1){
                    message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                }
                message.setMessageStatus(IMessage.MessageStatus.SEND_GOING);
                mAdapter.addToStart(message, true);
                //发送消息
                HuanXinUtils.getInstance().sendTxtMessage(input.toString(), username, code -> {
                    if(code == HuanXinUtils.SUCCESS_CODE){
                        mAdapter.getMessageList().get(0).setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);
                        mAdapter.notifyItemChanged(0);
                    }
                });
                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                // should reset messageList height
                //setMsgListHeight(true);
                MyMessage message;
                for (FileItem item : list) {
                    if (item.getType() == FileItem.Type.Image) {
                        message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE.ordinal());
                        mPathList.add(item.getFilePath());
                        mMsgIdList.add(message.getMsgId());
                    } else if (item.getType() == FileItem.Type.Video) {
                        message = new MyMessage(null, IMessage.MessageType.SEND_VIDEO.ordinal());
                        message.setDuration(((VideoItem) item).getDuration());

                    } else {
                        throw new RuntimeException("Invalid FileItem type. Must be Type.Image or Type.Video");
                    }

                    message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
                    message.setMediaFilePath(item.getFilePath());
                    message.setUserInfo(new DefaultUser("1", "Ironman", "R.drawable.ironman"));

                    final MyMessage fMsg = message;
                    mActivity.runOnUiThread(() -> mAdapter.addToStart(fMsg, true));
                }
            }

            @Override
            public boolean switchToMicrophoneMode() {
                scrollToBottom();
                String[] perms = new String[]{
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if (!EasyPermissions.hasPermissions(getContext(), perms)) {
                    EasyPermissions.requestPermissions(mActivity,
                            getContext().getResources().getString(R.string.rationale_record_voice),
                            RC_RECORD_VOICE, perms);
                }
                return true;
            }

            @Override
            public boolean switchToGalleryMode() {
                scrollToBottom();
                String[] perms = new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };

                if (!EasyPermissions.hasPermissions(getContext(), perms)) {
                    EasyPermissions.requestPermissions(mActivity,
                            getContext().getResources().getString(R.string.rationale_photo),
                            RC_PHOTO, perms);
                }
                // If you call updateData, select photo view will try to update data(Last update over 30 seconds.)
                mChatInput.getSelectPhotoView().updateData();
                return true;
            }

            @Override
            public boolean switchToCameraMode() {
                scrollToBottom();
                String[] perms = new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                };

                if (!EasyPermissions.hasPermissions(getContext(), perms)) {
                    EasyPermissions.requestPermissions(mActivity,
                            getContext().getResources().getString(R.string.rationale_camera),
                            RC_CAMERA, perms);
                    return false;
                } else {
                    File rootDir = getContext().getFilesDir();
                    String fileDir = rootDir.getAbsolutePath() + "/photo";
                    mChatInput.setCameraCaptureFile(fileDir, new SimpleDateFormat("yyyy-MM-dd-hhmmss",
                            Locale.getDefault()).format(new Date()));
                }
                return true;
            }

            @Override
            public boolean switchToEmojiMode() {
                scrollToBottom();
                return true;
            }
        });
    }

    public void scrollToBottom() {
        //setMsgListHeight(false);
        new Handler().postDelayed(() -> mMsgList.smoothScrollToPosition(0), 200);
    }

    /*
     * 图片加载器
     * */
    public ImageLoader getImageLoader(){
        return new ImageLoader() {
            final float density = getResources().getDisplayMetrics().density;
            final float MIN_WIDTH = 60 * density;
            final float MAX_WIDTH = 200 * density;
            final float MIN_HEIGHT = 60 * density;
            final float MAX_HEIGHT = 200 * density;
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
                // You can use other image load libraries.
                if (string.contains("R.drawable")) {
                    Integer resId = getResources().getIdentifier(string.replace("R.drawable.", ""),
                            "drawable", getContext().getPackageName());

                    avatarImageView.setImageResource(resId);
                } else {
                    Glide.with(getContext())
                            .load(string)
                            .apply(new RequestOptions().placeholder(R.drawable.aurora_headicon_default))
                            .into(avatarImageView);
                }
            }

            /**
             * Load image message
             * @param imageView Image message's ImageView.
             * @param string A file path, or a uri or url.
             */
            @Override
            public void loadImage(final ImageView imageView, String string) {
                // You can use other image load libraries.
                Glide.with(getContext().getApplicationContext())
                        .asBitmap()
                        .load(string)
                        .apply(new RequestOptions().fitCenter().placeholder(R.drawable.aurora_picture_not_found))
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                int imageWidth = resource.getWidth();
                                int imageHeight = resource.getHeight();
                                // 裁剪 bitmap
                                float width, height;
                                if (imageWidth > imageHeight) {
                                    if (imageWidth > MAX_WIDTH) {
                                        float temp = MAX_WIDTH / imageWidth * imageHeight;
                                        height = temp > MIN_HEIGHT ? temp : MIN_HEIGHT;
                                        width = MAX_WIDTH;
                                    } else if (imageWidth < MIN_WIDTH) {
                                        float temp = MIN_WIDTH / imageWidth * imageHeight;
                                        height = temp < MAX_HEIGHT ? temp : MAX_HEIGHT;
                                        width = MIN_WIDTH;
                                    } else {
                                        float ratio = imageWidth / imageHeight;
                                        if (ratio > 3) {
                                            ratio = 3;
                                        }
                                        height = imageHeight * ratio;
                                        width = imageWidth;
                                    }
                                } else {
                                    if (imageHeight > MAX_HEIGHT) {
                                        float temp = MAX_HEIGHT / imageHeight * imageWidth;
                                        width = temp > MIN_WIDTH ? temp : MIN_WIDTH;
                                        height = MAX_HEIGHT;
                                    } else if (imageHeight < MIN_HEIGHT) {
                                        float temp = MIN_HEIGHT / imageHeight * imageWidth;
                                        width = temp < MAX_WIDTH ? temp : MAX_WIDTH;
                                        height = MIN_HEIGHT;
                                    } else {
                                        float ratio = imageHeight / imageWidth;
                                        if (ratio > 3) {
                                            ratio = 3;
                                        }
                                        width = imageWidth * ratio;
                                        height = imageHeight;
                                    }
                                }
                                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.width = (int) width;
                                params.height = (int) height;
                                imageView.setLayoutParams(params);
                                Matrix matrix = new Matrix();
                                float scaleWidth = width / imageWidth;
                                float scaleHeight = height / imageHeight;
                                matrix.postScale(scaleWidth, scaleHeight);
                                imageView.setImageBitmap(Bitmap.createBitmap(resource, 0, 0, imageWidth, imageHeight, matrix, true));
                            }
                        });
            }

            /**
             * Load video message
             * @param imageCover Video message's image cover
             * @param uri Local path or url.
             */
            @Override
            public void loadVideo(ImageView imageCover, String uri) {
                long interval = 5000 * 1000;
                Glide.with(getContext())
                        .asBitmap()
                        .load(uri)
                        // Resize image view by change override size.
                        .apply(new RequestOptions().frame(interval).override(200, 400))
                        .into(imageCover);
            }
        };
    }

    private void setScreenOn() {
        if (mWakeLock != null) {
            mWakeLock.setReferenceCounted(false);
            mWakeLock.release();
            mWakeLock = null;
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    private void registerProximitySensorListener() {
        try {
            mPowerManager = (PowerManager) mActivity.getSystemService(mActivity.POWER_SERVICE);
            mWakeLock = mPowerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, TAG);
            mSensorManager = (SensorManager) mActivity.getSystemService(mActivity.SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    private void setScreenOff() {
        if (mWakeLock == null) {
            mWakeLock = mPowerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, TAG);
        }
        mWakeLock.acquire();
    }

    private class HeadsetDetectReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                if (intent.hasExtra("state")) {
                    int state = intent.getIntExtra("state", 0);
                    mAdapter.setAudioPlayByEarPhone(state);
                }
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        AudioManager audioManager = (AudioManager) mActivity.getSystemService(mActivity.AUDIO_SERVICE);
        try {
            if (audioManager.isBluetoothA2dpOn() || audioManager.isWiredHeadsetOn()) {
                return;
            }
            if (mAdapter.getMediaPlayer().isPlaying()) {
                float distance = event.values[0];
                if (distance >= mSensor.getMaximumRange()) {
                    mAdapter.setAudioPlayByEarPhone(0);
                    setScreenOn();
                } else {
                    mAdapter.setAudioPlayByEarPhone(2);
                    ViewHolderController.getInstance().replayVoice();
                    setScreenOff();
                }
            } else {
                if (mWakeLock != null && mWakeLock.isHeld()) {
                    mWakeLock.release();
                    mWakeLock = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /*
    * 注销广播
    * */
    public void onDestory(){
        mActivity.unregisterReceiver(mReceiver);
        mSensorManager.unregisterListener(this);
    }
}
