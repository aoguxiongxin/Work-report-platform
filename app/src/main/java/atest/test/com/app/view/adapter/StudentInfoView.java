package atest.test.com.app.view.adapter;


import atest.test.com.app.model.bean.MineBean.StudentBean;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/11/6.
 */

public interface StudentInfoView extends IView {

    void getStudentInfoSucced(StudentBean bean);

    void getStudentInfoFailed(String error);
}
