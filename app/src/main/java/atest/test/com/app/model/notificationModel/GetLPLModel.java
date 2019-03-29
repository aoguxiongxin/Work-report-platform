package atest.test.com.app.model.notificationModel;


import atest.test.com.app.constant.NotificationApi;
import atest.test.com.app.model.bean.notificationBean.LPLBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/6.
 */

public class GetLPLModel {
    public Observable<LPLBean> getLPL(String userId) {
        return RetrofitManager.getInstance().create(NotificationApi.class).getLowerPositionList(userId);
    }
}
