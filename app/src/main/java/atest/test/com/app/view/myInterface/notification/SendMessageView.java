package atest.test.com.app.view.myInterface.notification;


import atest.test.com.app.model.bean.notificationBean.SendResultBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface SendMessageView extends IView {

    void sendSuccessed(SendResultBean bean);

    void sendFailed(String error);
}
