package atest.test.com.app.presenter.notification;

import atest.test.com.app.model.bean.notificationBean.LPLBean;
import atest.test.com.app.model.notificationModel.GetLPLModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.notification.GetLPLView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/6.
 */

public class GetLPLPresenter extends IPresenter<GetLPLView> {

    private static final String TAG = "TAG";
    private GetLPLModel getLPLModel;

    public GetLPLPresenter(GetLPLView view) {
        super(view);
    }

    @Override
    public void init() {

        getLPLModel = new GetLPLModel();
    }

    public void getLPL(String userId) {
        getLPLModel.getLPL(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LPLBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LPLBean bean) {
                        if (view != null && bean.getCode() == 200) {
                            view.getDataSuccessed(bean);
                        } else {
                            view.getDataFailed("获取下级列表失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                             view.getDataFailed("请检查网络");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
