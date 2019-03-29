package atest.test.com.app.model.reportModel;

import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.ReportBean.ReportMyLogBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLogModel {
    public Observable<ReportMyLogBean> getMyReportLog(String userId) {
        return RetrofitManager.getInstance().create(MyApi.class).getMyReportLog(userId);
    }
}
