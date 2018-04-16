package atest.test.com.app.model.reportModel;
import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.ReportBean.ReportMyLowerBean;
import atest.test.com.app.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLowerModel {
    public Observable<ReportMyLowerBean> getMylowerReportLog(String userId) {
        return RetrofitManager.getInstance().create(MyApi.class).getMylowerReportLog(userId);
    }
}
