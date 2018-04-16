package atest.test.com.app.view.myInterface;


import atest.test.com.app.model.bean.VersionBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/15.
 */

public interface CheckVersionView extends IView {
    void Upgrade(VersionBean bean);
}
