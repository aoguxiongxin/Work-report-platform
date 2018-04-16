package atest.test.com.app.presenter;

import android.content.Context;

import atest.test.com.app.app.MyApplication;
import atest.test.com.app.view.IView;

/**
 * Created by Shadow on 2017/10/13.
 */

public abstract class IPresenter<T extends IView> {

    public T view;

    public IPresenter(T view) {
        this.view = view;
        init();
    }

    public abstract void init();

    public Context getContext() {
        if (view != null && view.context() != null) {
            return view.context();
        }
        return MyApplication.getContext();
    }

    public void onDestroy() {
        if (view != null) {
            view = null;
        }
    }
}
