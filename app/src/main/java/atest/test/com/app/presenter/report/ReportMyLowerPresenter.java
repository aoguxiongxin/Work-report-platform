package atest.test.com.app.presenter.report;
import atest.test.com.app.model.bean.ReportBean.ReportMyLowerBean;
import atest.test.com.app.model.reportModel.ReportMyLowerModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.report.ReportMyLowerLogView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public class ReportMyLowerPresenter extends IPresenter<ReportMyLowerLogView> {

    private ReportMyLowerModel model;

    public ReportMyLowerPresenter(ReportMyLowerLogView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new ReportMyLowerModel();
    }

    public void getMylowerReportLog(String userId) {
        Observable<ReportMyLowerBean> observable = model.getMylowerReportLog(userId);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReportMyLowerBean>() {
                    @Override
                    public void accept(ReportMyLowerBean reportMyLowerBean) throws Exception {
                        if (reportMyLowerBean.getCode() == 200) {
                            if (view != null) {
                                view.onSelectLoverLogSucceed(reportMyLowerBean);
                            }
                        } else if (reportMyLowerBean.getCode() == 500) {
                            if (view != null) {
                                view.onSelectLoverLogFaild();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onSelectLoverLogFaild();
                        }
                    }
                });
    }
}
