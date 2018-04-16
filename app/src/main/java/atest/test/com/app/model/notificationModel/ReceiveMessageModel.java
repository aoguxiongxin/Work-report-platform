package atest.test.com.app.model.notificationModel;
import atest.test.com.app.constant.NotificationApi;
import atest.test.com.app.model.bean.notificationBean.ChangeStatusBean;
import atest.test.com.app.model.bean.notificationBean.ReceiveMessageBean;
import atest.test.com.app.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class ReceiveMessageModel {

    public Observable<ReceiveMessageBean> getData(String userId){

        return RetrofitManager.getInstance().create(NotificationApi.class).queryReceiveMessageLog(userId);

    }
    public Observable<ChangeStatusBean> changeStatus(String userId, int messageId){

        return RetrofitManager.getInstance().create(NotificationApi.class).changeMessageStatus(userId,messageId);

    }



}
