package atest.test.com.app.view.myInterface.report;


import atest.test.com.app.model.bean.ReportBean.ReportMyLogBean;
import atest.test.com.app.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public interface ReprotMyLogView extends IView {
    void onSelectRucceed(ReportMyLogBean bean);

    void onSelectFilad();
}
