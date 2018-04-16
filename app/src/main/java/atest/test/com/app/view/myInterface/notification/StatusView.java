package atest.test.com.app.view.myInterface.notification;


import atest.test.com.app.model.bean.notificationBean.StatusBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/10.
 */

public interface StatusView extends IView {

    void getStatusSucced(StatusBean bean);
    void getStatusFailed(String error);
}
