package atest.test.com.app.view.myInterface.report;


import atest.test.com.app.model.bean.ReportBean.ReportHackfanMyBean;
import atest.test.com.app.model.bean.ReportBean.ReportHackfanMyLowarBean;
import atest.test.com.app.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/8
 * 作用 :
 */

public interface ReportHackfanView extends IView {
    void onReportMyHackfanSucceed(ReportHackfanMyBean bean);

    void onReportLowarHackfanSucceed(ReportHackfanMyLowarBean bean);

    void onReportMyHackfanFaild();

    void onReportLowarHackfanFaild();
}
