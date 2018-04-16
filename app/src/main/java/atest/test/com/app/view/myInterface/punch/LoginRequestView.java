package atest.test.com.app.view.myInterface.punch;


import atest.test.com.app.model.bean.punchBean.LoginBean;
import atest.test.com.app.view.IView;

/**
 * Created by 自信 on 2017/11/1.
 */

public interface LoginRequestView extends IView {
    void onLoginSucceed(LoginBean loginBean);

    void onLoginFaild(String result);
}
