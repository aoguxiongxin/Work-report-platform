package atest.test.com.app.model.mineModel;

import atest.test.com.app.constant.CheckVersionApi;
import atest.test.com.app.model.bean.VersionBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;

/**
 * Created by Shadow on 2017/11/7.
 */

public class CheckVersionModel {
    public Observable<VersionBean> checkVersion(int version) {
        return RetrofitManager.getInstance().create(CheckVersionApi.class).checkVersion(version);
    }
}
