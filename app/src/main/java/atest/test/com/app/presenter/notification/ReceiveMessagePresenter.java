package atest.test.com.app.presenter.notification;


import atest.test.com.app.model.bean.notificationBean.ChangeStatusBean;
import atest.test.com.app.model.bean.notificationBean.ReceiveMessageBean;
import atest.test.com.app.model.notificationModel.ReceiveMessageModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.notification.ReceiveMessageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/7.
 */

public class ReceiveMessagePresenter extends IPresenter<ReceiveMessageView> {

    private ReceiveMessageModel receiveMessageModel;

    public ReceiveMessagePresenter(ReceiveMessageView view) {
        super(view);
    }

    @Override
    public void init() {
        receiveMessageModel = new ReceiveMessageModel();
    }

    public void receiveMessage(String userId) {
        receiveMessageModel.getData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReceiveMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReceiveMessageBean receiveMessageBean) {

                        if (view != null && receiveMessageBean.getCode() == 200) {
                            view.getMessageSuccessed(receiveMessageBean);
                        } else {
                            view.getMessageFailed("获取收到的通知失败");
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


    public void changeStatus(String userId, int messageId) {

        receiveMessageModel.changeStatus(userId, messageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeStatusBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangeStatusBean changeStatusBean) {

                        if (view != null) {
                            view.changeStatus(changeStatusBean.getCode());
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
