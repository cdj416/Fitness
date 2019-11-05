package com.hongyuan.fitness.util.huanxin;
import android.os.Environment;
import android.util.Log;

import com.hongyuan.fitness.base.MyApplication;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.DefaultUser;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.MyMessage;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.Notify;
import com.hongyuan.fitness.util.TimeUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.push.EMPushConfig;
import com.hyphenate.push.EMPushHelper;
import com.hyphenate.push.EMPushType;
import com.hyphenate.push.PushListener;
import com.hyphenate.util.EMLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cn.jiguang.imui.commons.models.IMessage;

public class HuanXinUtils {

    private static HuanXinUtils huanXinUtils;

    private String nickname;//当前会话自己的昵称
    private String avatar;//当前会话自己的头像
    private String receivernickname;//当前会话对方昵称
    private String receiveravatar;//当前会话对方头像

    public static final int SUCCESS_CODE = 0X01;//成功
    public static final int ERROR_CODE = 0X02;//失败

    private HuanXinUtils(){}
    public static HuanXinUtils getInstance(){
        if(huanXinUtils == null){
            huanXinUtils = new HuanXinUtils();
        }
        return huanXinUtils;
    }

    /*
    * 设置当前会话自己和接收方基本信息
    * */
    public void setBaseData(String nickname,String avatar,String receivernickname,String receiveravatar){
        this.nickname = nickname;
        this.avatar = avatar;
        this.receivernickname = receivernickname;
        this.receiveravatar = receiveravatar;
    }

    /*
    * 发送消息状态监听回调
    * */
    public interface CallBackStatus{
        void sendStatus(int code);
    }

    /*
    * 消息监听回调
    * */
    public interface CallBackMsgListener{
        void callBackMsg(List<MyMessage> myMessages);
    }

    /*
    * 环信注册账号
    * */
    public void registerdHuanXin(String userName){
        new Thread(() -> {
            try {
                EMClient.getInstance().createAccount(userName, userName);//同步方法
                loginHuanXin(userName);
            } catch (HyphenateException e) {
                e.printStackTrace();
                if(e.getErrorCode() == 203){
                    loginHuanXin(userName);
                }
            }
        }).start();
    }

    /*
    * 环信登录账号
    * */
    private void loginHuanXin(String userName){
        EMClient.getInstance().login(userName,userName,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                //Log.e("cdj","=====================登录成功======");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                //Log.e("cdj","=====================登录失败======"+code+"================"+message);
            }
        });
    }

    /*
    * 获取回话消息列表集合
    * */
    public List<ChatDataBeans> getMessageList(){
        List<ChatDataBeans> chatList = new ArrayList<>();

        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        // 获取所有键值对对象的集合
        Set<Map.Entry<String, EMConversation>> set = conversations.entrySet();
        for (Map.Entry<String, EMConversation> me : set) {
            // 根据键值对对象获取键和值
            String key = me.getKey();
            EMConversation value = me.getValue();

            try {
                ChatDataBeans dataBeans = new ChatDataBeans();
                if(value.getLatestMessageFromOthers() != null && BaseUtil.isValue(value.getLatestMessageFromOthers().getStringAttribute("nickname"))){
                    dataBeans.setUsername(value.getLatestMessageFromOthers().getUserName());
                    dataBeans.setMsgId(value.getLatestMessageFromOthers().getMsgId());
                    dataBeans.setNickname(value.getLatestMessageFromOthers().getStringAttribute("nickname"));
                    dataBeans.setAvatar(value.getLatestMessageFromOthers().getStringAttribute("avatar"));
                    dataBeans.setLastTime(TimeUtil.getStringByFormat(value.getLatestMessageFromOthers().getMsgTime(),TimeUtil.dateFormatXieMDHM));
                    if(value.getUnreadMsgCount() > 0){
                        dataBeans.setUnread(true);
                    }
                    if(value.getLatestMessageFromOthers().getType() == EMMessage.Type.TXT){
                        if(value.getLatestMessageFromOthers().getBody() instanceof EMTextMessageBody){
                            EMTextMessageBody LatestTextMessage = (EMTextMessageBody) value.getLatestMessageFromOthers().getBody();
                            dataBeans.setMessage(LatestTextMessage.getMessage());
                        }
                    }else if(value.getLatestMessageFromOthers().getType() == EMMessage.Type.VOICE){
                        dataBeans.setMessage("[语音]");
                    }
                }else{
                    dataBeans.setUsername(value.getLastMessage().getUserName());
                    dataBeans.setMsgId(value.getLastMessage().getMsgId());
                    dataBeans.setNickname(value.getLastMessage().getStringAttribute("receivernickname"));
                    dataBeans.setAvatar(value.getLastMessage().getStringAttribute("receiveravatar"));
                    dataBeans.setLastTime(TimeUtil.getStringByFormat(value.getLastMessage().getMsgTime(),TimeUtil.dateFormatXieMDHM));
                    if(value.getUnreadMsgCount() > 0){
                        dataBeans.setUnread(true);
                    }

                    if(value.getLastMessage().getType() == EMMessage.Type.TXT){
                        if(value.getLastMessage().getBody() instanceof EMTextMessageBody){
                            EMTextMessageBody LastTextMessage = (EMTextMessageBody) value.getLastMessage().getBody();
                            dataBeans.setMessage(LastTextMessage.getMessage());
                        }
                    }else if(value.getLastMessage().getType() == EMMessage.Type.VOICE){
                        dataBeans.setMessage("[语音]");
                    }
                }
                chatList.add(dataBeans);
            }catch (HyphenateException e){
                e.printStackTrace();
            }

        }

        return chatList;
    }

    /*
    * 从db中获取相应的二十条数据
    * */
    public List<MyMessage> getChatMessages(String username,String lastMsgId,int pageSize){
        List<MyMessage> myMessages = new ArrayList<>();
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
        //SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
        //获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，APP中无需再次把获取到的messages添加到会话中
        conversation.loadMoreMsgFromDB(lastMsgId, pageSize);
        //获取此会话的所有消息
        List<EMMessage> messages = conversation.getAllMessages();
        for(EMMessage bean:messages){
            if(myMessages.size() > 0){
                myMessages.add(getProcessMsg(bean,myMessages.get(myMessages.size() - 1)));
            }else{
                myMessages.add(getProcessMsg(bean,null));
            }
        }

        return myMessages;
    }

    /*
    * 消息处理
    * */
    private MyMessage getProcessMsg(EMMessage bean,MyMessage previousBean){
        try {
            if(bean.getType() == EMMessage.Type.TXT){
                EMTextMessageBody txtBody = (EMTextMessageBody) bean.getBody();
                if(TokenSingleBean.getInstance().getM_mobile().equals(bean.getFrom())){
                    MyMessage sendTxtMsg = new MyMessage(txtBody.getMessage(), IMessage.MessageType.SEND_TEXT.ordinal());
                    sendTxtMsg.setUserInfo(new DefaultUser("1", "", TokenSingleBean.getInstance().getHeadUrl()));
                    sendTxtMsg.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);

                    sendTxtMsg.setTimes(bean.getMsgTime());
                    if(previousBean != null && TimeUtil.getOffectMinutes(bean.getMsgTime(),previousBean.getTimes()) > 1){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            sendTxtMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            sendTxtMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }else if(previousBean == null){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            sendTxtMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            sendTxtMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }
                    return sendTxtMsg;
                }else{
                    MyMessage receiveTxtMsg = new MyMessage(txtBody.getMessage(), IMessage.MessageType.RECEIVE_TEXT.ordinal());
                    receiveTxtMsg.setUserInfo(new DefaultUser("0","", bean.getStringAttribute("avatar")));
                    receiveTxtMsg.setMessageStatus(IMessage.MessageStatus.RECEIVE_SUCCEED);

                    receiveTxtMsg.setTimes(bean.getMsgTime());
                    if(previousBean != null && TimeUtil.getOffectMinutes(bean.getMsgTime(),previousBean.getTimes()) > 1){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            receiveTxtMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            receiveTxtMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }else if(previousBean == null){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            receiveTxtMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            receiveTxtMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }
                    return receiveTxtMsg;
                }
            }else if(bean.getType() == EMMessage.Type.VOICE){
                EMVoiceMessageBody voiceBody = (EMVoiceMessageBody)bean.getBody();
                if(TokenSingleBean.getInstance().getM_mobile().equals(bean.getFrom())){
                    MyMessage sendVoiceMsg = new MyMessage("", IMessage.MessageType.SEND_VOICE.ordinal());
                    sendVoiceMsg.setUserInfo(new DefaultUser("1", "", TokenSingleBean.getInstance().getHeadUrl()));
                    sendVoiceMsg.setMediaFilePath(voiceBody.getLocalUrl());
                    sendVoiceMsg.setDuration(voiceBody.getLength());
                    sendVoiceMsg.setMessageStatus(IMessage.MessageStatus.SEND_SUCCEED);

                    sendVoiceMsg.setTimes(bean.getMsgTime());
                    if(previousBean != null && TimeUtil.getOffectMinutes(bean.getMsgTime(),previousBean.getTimes()) > 1){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            sendVoiceMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            sendVoiceMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }else if(previousBean == null){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            sendVoiceMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            sendVoiceMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }
                    return sendVoiceMsg;
                }else{
                    MyMessage receiveVoiceMsg = new MyMessage("", IMessage.MessageType.RECEIVE_VOICE.ordinal());
                    receiveVoiceMsg.setUserInfo(new DefaultUser("0", "", bean.getStringAttribute("avatar")));
                    receiveVoiceMsg.setMediaFilePath(voiceBody.getLocalUrl());
                    receiveVoiceMsg.setDuration(voiceBody.getLength());
                    receiveVoiceMsg.setMessageStatus(IMessage.MessageStatus.RECEIVE_SUCCEED);

                    receiveVoiceMsg.setTimes(bean.getMsgTime());
                    if(previousBean != null && TimeUtil.getOffectMinutes(bean.getMsgTime(),previousBean.getTimes()) > 1){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            receiveVoiceMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            receiveVoiceMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }else if(previousBean == null){
                        if(TimeUtil.isToday(bean.getMsgTime())){
                            receiveVoiceMsg.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }else{
                            receiveVoiceMsg.setTimeString(new SimpleDateFormat(TimeUtil.dateFormatXieYMDHM, Locale.getDefault()).format(new Date(bean.getMsgTime())));
                        }
                    }
                    return receiveVoiceMsg;
                }

            }
        }catch (HyphenateException e){
            e.printStackTrace();
        }
        return null;
    }


    /*
    * 发送(单聊)文本消息
    * */
    public void sendTxtMessage(String content,String toChatUsername,CallBackStatus backStatus){
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id
        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
        // 增加自己特定的属性
        message.setAttribute("nickname", nickname);
        message.setAttribute("avatar", avatar);
        message.setAttribute("receivernickname", receivernickname);
        message.setAttribute("receiveravatar", receiveravatar);
        //设置发送状态回调
        message.setMessageStatusCallback(new EMCallBack(){
            @Override
            public void onSuccess() {
                Log.e("cdj","===============发送成功=====");
                backStatus.sendStatus(SUCCESS_CODE);
            }

            @Override
            public void onError(int code, String error) {
                Log.e("cdj","===============code====="+code+"======error====="+error);
                backStatus.sendStatus(ERROR_CODE);
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("cdj","===============发送中=====");
            }
        });

        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /*
    * 发送单聊语音消息
    * */
    public void sendVoic(String filePath,int length,String toChatUsername,CallBackStatus backStatus){
        //filePath为语音文件路径，length为录音时间(秒)
        EMMessage message = EMMessage.createVoiceSendMessage(filePath, length, toChatUsername);
        // 增加自己特定的属性
        message.setAttribute("nickname", nickname);
        message.setAttribute("avatar", avatar);
        message.setAttribute("receivernickname", receivernickname);
        message.setAttribute("receiveravatar", receiveravatar);
        //设置发送状态回调
        message.setMessageStatusCallback(new EMCallBack(){
            @Override
            public void onSuccess() {
                Log.e("cdj","===============发送成功=====");
                backStatus.sendStatus(SUCCESS_CODE);
            }

            @Override
            public void onError(int code, String error) {
                Log.e("cdj","===============code====="+code+"======error====="+error);
                backStatus.sendStatus(ERROR_CODE);
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("cdj","===============发送中=====");
            }
        });
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /*
    * 消息接收监听
    * */
    public void setMsgListener(CallBackMsgListener msgListener){
        EMMessageListener huanXinmsgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                List<MyMessage> myMessages = new ArrayList<>();
                //收到消息
                for(EMMessage bean:messages){
                    if(myMessages.size() > 0){
                        myMessages.add(getProcessMsg(bean,myMessages.get(myMessages.size() - 1)));
                    }else{
                        myMessages.add(getProcessMsg(bean,null));
                    }
                }
                msgListener.callBackMsg(myMessages);
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }
            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(huanXinmsgListener);
    }

    /*
    *推送接口配置
    * */
    public void setPush(){
        EMPushConfig.Builder builder = new EMPushConfig.Builder(MyApplication.getInstance());
        builder.enableVivoPush() // 推送证书相关信息配置在AndroidManifest.xml中
                //.enableMeiZuPush(String appId, String appKey)
                .enableMiPush("2882303761518138900", "5531813884900")
                .enableOppoPush("30176454", "7a38c8ff71c6472f98d6f4ceaa0ffecf")
                .enableHWPush(); //开发者需要调用该方法来开启华为推送
                //.enableFCM(String senderId); //开发者需要调用该方法来开启FCM推送

        EMOptions options = new EMOptions();
        options.setPushConfig(builder.build());
        setPushMsgListener();
        //设置聊天消息通知
        Notify notify = new Notify(MyApplication.getInstance());
        setMsgListener(myMessages -> notify.setNotification("消息","您有一条新消息！"));

    }

    /*
    * 推送消息监听
    * */
    public void setPushMsgListener(){
        EMPushHelper.getInstance().setPushListener(new PushListener() {
            @Override
            public void onError(EMPushType pushType, long errorCode) {
                Log.e("cdj", "=============Push client occur a error=====: " + pushType + " - " + errorCode);
                // TODO: 开发者会在这个回调中收到使用推送的相关错误信息，各推送类型的error code开发者可以自己去各推送平台官网查询错误原因。
            }

            @Override
            public boolean isSupportPush(EMPushType pushType, EMPushConfig pushConfig) {
                Log.e("cdj", "=============Push success=====: " + pushType);
                return super.isSupportPush(pushType, pushConfig);
                // TODO：开发者可以复写该方法控制设备是否支持某推送的判断。
            }
        });
    }
}

