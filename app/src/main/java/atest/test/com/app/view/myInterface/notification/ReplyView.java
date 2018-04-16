package atest.test.com.app.view.myInterface.notification;


import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/7.
 */

public interface ReplyView extends IView {
    void replySuccessed(Boolean result);
    void replyError(String  error);
}
