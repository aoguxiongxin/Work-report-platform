package atest.test.com.app.presenter.punch;


import atest.test.com.app.model.bean.punchBean.PunchMyBean;
import atest.test.com.app.model.punchModel.PuncheMyModel;
import atest.test.com.app.presenter.IPresenter;
import atest.test.com.app.view.myInterface.punch.PunchMyView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/6
 * 作用 :
 */

public class PunchMyPresenter extends IPresenter<PunchMyView> {

    private PuncheMyModel model;

    public PunchMyPresenter(PunchMyView view) {
        super(view);
    }

    public void getMyPuncheLog(String userId) {
        Observable<PunchMyBean> puncheLog = model.getMyPuncheLog(userId);
        puncheLog.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PunchMyBean>() {
                    @Override
                    public void accept(PunchMyBean punchMyBean) throws Exception {
                        if (punchMyBean.getCode() == 200) {
                            if (view != null) {
                                view.onSelectPunchLogSucceed(punchMyBean);
                            }
                        } else if (punchMyBean.getCode() == 500) {
                            if (view != null) {
                                view.onSelectPunchLogFiald();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view != null) {
                            view.onSelectPunchLogFiald();
                        }
                    }
                });
    }

    @Override
    public void init() {
        model = new PuncheMyModel();
    }
}
