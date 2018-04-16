package atest.test.com.app.view.myInterface.mine;

import atest.test.com.app.model.bean.notificationBean.MineSendMessageBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface MineMessage extends IView {
    void getMineMessageSucced(MineSendMessageBean bean);
    void getMineMessageFailed(String error);
}
