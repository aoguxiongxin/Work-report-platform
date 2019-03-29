package atest.test.com.app.model.notificationModel;


import atest.test.com.app.constant.NotificationApi;
import atest.test.com.app.model.bean.notificationBean.StatusBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class StatusModel {
    public Observable<StatusBean> getStatus(String userId, String mid, String position) {
        return RetrofitManager.getInstance().create(NotificationApi.class).queryOneSendMessageLog(userId,mid, position);
    }
}
