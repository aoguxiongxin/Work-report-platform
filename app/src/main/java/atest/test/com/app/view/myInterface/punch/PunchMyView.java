package atest.test.com.app.view.myInterface.punch;


import atest.test.com.app.model.bean.punchBean.PunchMyBean;
import atest.test.com.app.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/6
 * 作用 :
 */

public interface PunchMyView extends IView {
    void onSelectPunchLogSucceed(PunchMyBean punchMyBean);

    void onSelectPunchLogFiald();
}
