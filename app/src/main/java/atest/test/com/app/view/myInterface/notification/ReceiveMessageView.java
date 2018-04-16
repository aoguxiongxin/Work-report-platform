package atest.test.com.app.view.myInterface.notification;

import atest.test.com.app.model.bean.notificationBean.ReceiveMessageBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/7.
 */

public interface ReceiveMessageView extends IView {
    void getMessageSuccessed(ReceiveMessageBean bean);

    void getMessageFailed(String error);

    void changeStatus(int code);
}
