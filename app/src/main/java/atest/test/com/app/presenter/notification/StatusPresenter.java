package atest.test.com.app.presenter.notification;


import atest.test.com.app.model.bean.notificationBean.StatusBean;
import atest.test.com.app.model.notificationModel.StatusModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.notification.StatusView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/10.
 */

public class StatusPresenter extends IPresenter<StatusView> {

    private StatusModel statusModel;

    public StatusPresenter(StatusView view) {
        super(view);
    }


    @Override
    public void init() {
        statusModel = new StatusModel();
    }

    public void getStatus(String userId,String mid, String position){

        statusModel.getStatus(userId,mid,position)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StatusBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusBean statusBean) {

                        if(statusBean.getCode()==200&&view!=null){
                            view.getStatusSucced(statusBean);
                        }else {
                            view.getStatusFailed("获取已阅状态失败");
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
