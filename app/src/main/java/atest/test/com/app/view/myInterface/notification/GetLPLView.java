package atest.test.com.app.view.myInterface.notification;


import atest.test.com.app.model.bean.notificationBean.LPLBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface GetLPLView extends IView {
    void getDataSuccessed(LPLBean bean);

    void getDataFailed(String error);
}
