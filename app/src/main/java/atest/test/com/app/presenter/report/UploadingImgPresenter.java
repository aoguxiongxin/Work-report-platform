package atest.test.com.app.presenter.report;

import atest.test.com.app.model.bean.ReportBean.ImageBean;
import atest.test.com.app.model.reportModel.UploadingModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.report.UploadingImgView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/14
 * 作用 :
 */

public class UploadingImgPresenter extends IPresenter<UploadingImgView> {


    private UploadingModel model;

    public UploadingImgPresenter(UploadingImgView view) {
        super(view);
    }

    @Override
    public void init() {
        model = new UploadingModel();
    }

    public void uploadingImg(String userId, MultipartBody.Part file) {
        Observable<ImageBean> observable = model.uploading(userId, file);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ImageBean imageBean) {
                        if (imageBean != null) {
                            if (imageBean.getCode() == 200) {
                                if (view != null) {
                                    String name = imageBean.getObject();
                                    view.onUploadingSucceed(name);
                                }
                            } else {
                                if (view != null) {
                                    view.onUploadingFaild();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
