package atest.test.com.app.presenter.mine;

import atest.test.com.app.model.bean.VersionBean;
import atest.test.com.app.model.mineModel.CheckVersionModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.CheckVersionView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/15.
 */

public class CheckVersionPresenter extends IPresenter<CheckVersionView> {

    private CheckVersionModel checkVersionModel;

    public CheckVersionPresenter(CheckVersionView view) {
        super(view);
    }

    @Override
    public void init() {
        checkVersionModel = new CheckVersionModel();
    }

    public void checkVersion(final int version) {
        checkVersionModel.checkVersion(version)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        //
                        if (view != null) {
                            if (versionBean.getCode() == 200) {
                                view.Upgrade(versionBean);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
