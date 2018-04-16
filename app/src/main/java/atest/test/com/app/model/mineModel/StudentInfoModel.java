package atest.test.com.app.model.mineModel;

import atest.test.com.app.constant.NotificationApi;
import atest.test.com.app.model.bean.MineBean.StudentBean;
import atest.test.com.app.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/7.
 */

public class StudentInfoModel {
    public Observable<StudentBean> getData(String userId){
        return RetrofitManager.getInstance().create(NotificationApi.class).queryGoodNews(userId);
    }
}
