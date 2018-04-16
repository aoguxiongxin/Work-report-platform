package atest.test.com.app.model.punchModel;

import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.punchBean.PunchingBean;
import atest.test.com.app.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public class PunchModel {
    public Observable<PunchingBean> checkIn(String userId, int typeId, String address, String remarks) {
        return RetrofitManager.getInstance().create(MyApi.class).checkIn(typeId, address, userId,remarks);
    }
}
