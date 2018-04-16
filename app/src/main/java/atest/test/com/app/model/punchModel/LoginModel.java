package atest.test.com.app.model.punchModel;

import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.punchBean.LoginBean;
import atest.test.com.app.model.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by 自信 on 2017/11/1.
 */

public class LoginModel {
    public Observable<LoginBean> Login(String phone, String username, int typeId, String address, String remarks) {
        return RetrofitManager.getInstance().create(MyApi.class).Login(phone, username, typeId, address, remarks);
    }
}
