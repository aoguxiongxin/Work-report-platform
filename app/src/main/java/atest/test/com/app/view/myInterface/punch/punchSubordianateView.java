package atest.test.com.app.view.myInterface.punch;


import atest.test.com.app.model.bean.punchBean.PuncheLowerBean;
import atest.test.com.app.view.IView;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public interface punchSubordianateView extends IView {
    void onSelectSucceed(PuncheLowerBean puncheLowerBean);

    void onSelectFaild();
}
