package atest.test.com.app.model.punchModel;

import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.punchBean.PuncheLowerBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class PunchSubordinateModel {
    public Observable<PuncheLowerBean> getMyLowerPuncheLog(String userId) {
        return RetrofitManager.getInstance().create(MyApi.class).getMyLowerPuncheLog(userId);
    }
}
