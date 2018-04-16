package atest.test.com.app.view.myInterface.report;


import atest.test.com.app.model.bean.ReportBean.ReportMyLowerBean;
import atest.test.com.app.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/7
 * 作用 :
 */

public interface ReportMyLowerLogView extends IView {
    void onSelectLoverLogSucceed(ReportMyLowerBean reportMyLowerBean);

    void onSelectLoverLogFaild();
}
