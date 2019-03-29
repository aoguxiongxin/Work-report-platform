package atest.test.com.app.model.notificationModel;


import atest.test.com.app.constant.NotificationApi;
import atest.test.com.app.model.bean.notificationBean.ReplyBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class ReplyModel {
    public Observable<ReplyBean> reply(String userId, String mid, String reply) {
        return RetrofitManager.getInstance().create(NotificationApi.class).setMessageReply(userId,mid,reply);
    }
}
