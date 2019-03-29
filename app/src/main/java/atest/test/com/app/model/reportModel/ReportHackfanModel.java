package atest.test.com.app.model.reportModel;

import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.ReportBean.ReportHackfanMyBean;
import atest.test.com.app.model.bean.ReportBean.ReportHackfanMyLowarBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public class ReportHackfanModel {
    public Observable<ReportHackfanMyBean> hackfanMyReport(String userId,
                                                           String startDate,
                                                           String endDate,
                                                           int ReportTypeId) {
        return RetrofitManager.getInstance().create(MyApi.class).hackfanMyReport(userId, startDate, endDate, ReportTypeId);
    }

    public Observable<ReportHackfanMyLowarBean> hackfanLowarReport(String userId,
                                                                   String startDate,
                                                                   String endDate,
                                                                   int ReportTypeId) {
        return RetrofitManager.getInstance().create(MyApi.class).hackfanLowarReport(userId, startDate, endDate, ReportTypeId);
    }
}
