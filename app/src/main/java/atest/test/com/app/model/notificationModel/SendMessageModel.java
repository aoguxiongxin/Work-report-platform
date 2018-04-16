package atest.test.com.app.model.notificationModel;

import atest.test.com.app.constant.NotificationApi;
import atest.test.com.app.model.bean.notificationBean.MineSendMessageBean;
import atest.test.com.app.model.bean.notificationBean.SendResultBean;
import atest.test.com.app.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class SendMessageModel {

    public Observable<SendResultBean> getData(String userId, String content, String position){

        return RetrofitManager.getInstance().create(NotificationApi.class).submitMessage(userId,content,position);

    }
    public Observable<MineSendMessageBean> getMineMessage(String userId){

        return RetrofitManager.getInstance().create(NotificationApi.class).querySendMessageLog(userId);

    }


}
