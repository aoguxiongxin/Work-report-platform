package atest.test.com.app.view.myInterface.report;


import atest.test.com.app.view.IView;

/**
 * 作者 : 赵虔
 * 时间 : 2017/11/14
 * 作用 :
 */

public interface UploadingImgView extends IView {
    void onUploadingSucceed(String imgName);

    void onUploadingFaild();
}
