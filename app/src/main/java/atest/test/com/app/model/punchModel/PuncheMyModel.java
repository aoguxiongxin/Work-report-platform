package atest.test.com.app.model.punchModel;

import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.punchBean.PunchMyBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/6
 * 作用 :
 */

public class PuncheMyModel {
    public Observable<PunchMyBean> getMyPuncheLog(String userId) {
        return RetrofitManager.getInstance().create(MyApi.class).getMyPuncheLog(userId);
    }
}
