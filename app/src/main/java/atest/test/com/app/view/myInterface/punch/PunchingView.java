package atest.test.com.app.view.myInterface.punch;


import atest.test.com.app.view.IView;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public interface PunchingView extends IView {
    void onPunchSucceed();

    void onPunchFaild();
}
