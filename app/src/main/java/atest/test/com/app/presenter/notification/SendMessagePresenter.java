package atest.test.com.app.presenter.notification;


import atest.test.com.app.model.bean.notificationBean.SendResultBean;
import atest.test.com.app.model.notificationModel.SendMessageModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.notification.SendMessageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shadow on 2017/11/6.
 */

public class SendMessagePresenter extends IPresenter<SendMessageView> {

    private static final String TAG = "TAG";
    private SendMessageModel sendNotionModel;

    public SendMessagePresenter(SendMessageView view) {
        super(view);
    }

    @Override
    public void init() {
        sendNotionModel = new SendMessageModel();
    }

    public void sendMessage(String userId, String content, String position) {
        sendNotionModel.getData(userId, content, position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendResultBean bean) {
                        if (view != null && bean.getCode() == 200) {
                            view.sendSuccessed(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if(view!=null){
                            view.sendFailed("未知错误,请稍候再试");
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
