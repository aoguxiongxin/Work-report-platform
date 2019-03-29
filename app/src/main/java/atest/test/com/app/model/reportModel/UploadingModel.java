package atest.test.com.app.model.reportModel;


import atest.test.com.app.constant.MyApi;
import atest.test.com.app.model.bean.ReportBean.ImageBean;
import atest.test.com.app.utils.RetrofitManager;
import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/14
 * 作用 :
 */

public class UploadingModel {
    public Observable<ImageBean> uploading(String userId, MultipartBody.Part file) {
        return RetrofitManager.getInstance().create(MyApi.class).uploadingImg(userId, file);
    }
}
