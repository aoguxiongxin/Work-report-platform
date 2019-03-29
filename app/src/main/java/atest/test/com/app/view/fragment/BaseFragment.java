package atest.test.com.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import atest.test.com.app.constant.UserInfo;
import atest.test.com.app.constant.UserInfoManager;
import atest.test.com.app.model.bean.RefreshBean;
import atest.test.com.app.utils.RxBus;
import atest.test.com.app.presenter.IPresenter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Shadow on 2017/11/7.
 */

public abstract class BaseFragment extends Fragment {
    protected UserInfo userInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUserInfo();
        initRefresh();
    }

    protected void initUserInfo() {
        userInfo = UserInfoManager.getInstance(getActivity()).getUserInfo();
    }

    private void initRefresh() {
        CompositeSubscription subscription = new CompositeSubscription();
        Observable<RefreshBean> observable = RxBus.getInstance().toObservable(RefreshBean.class);
        Subscription id = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefreshBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(RefreshBean bean) {
                        onRefresh();
                    }
                });
        //保存订阅者
        subscription.add(id);
    }

    protected void onRefresh() {
        userInfo = UserInfoManager.getInstance(getActivity()).refreshUserInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresebter() != null) {
            getPresebter().onDestroy();
        }
    }

    protected abstract IPresenter getPresebter();

}
