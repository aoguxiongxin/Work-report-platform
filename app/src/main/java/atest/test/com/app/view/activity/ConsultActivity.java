package atest.test.com.app.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import atest.test.com.app.R;
import atest.test.com.app.constant.BaseMessage;
import atest.test.com.app.constant.ProgressBarHorizontal;
import atest.test.com.app.constant.UserInfo;
import atest.test.com.app.constant.UserInfoManager;
import atest.test.com.app.presenter.report.ReportJobPresenter;
import atest.test.com.app.presenter.report.UploadingImgPresenter;
import atest.test.com.app.utils.CameraPhotoUtils;
import atest.test.com.app.utils.SettingUtils;
import atest.test.com.app.view.circle.CanAddViewImageView;
import atest.test.com.app.view.myInterface.report.ReportJobView;
import atest.test.com.app.view.myInterface.report.UploadingImgView;

/**
 * 日汇报中的咨询汇报页面
 */
public class ConsultActivity extends Activity implements View.OnClickListener, ReportJobView, UploadingImgView {

    private EditText content0;
    private EditText content1;
    private RadioGroup sexRadioGroup;
    private EditText content3;
    private EditText content4;
    private EditText content5;
    private EditText content6;
    private RadioButton radioWoman;
    private RadioButton radioMan;
    public ImageView uploading;

    public String mPublicPhotoPath;
    private PopupWindow mPopupWindow;
    private View popView;
    private CanAddViewImageView mView;
    private Button b1;
    private Button b2;
    private Button b3;
    private boolean canDelete;
    protected StringBuffer ImageName = new StringBuffer();
    private ReportJobPresenter presenter;
    private UploadingImgPresenter uploadingImgPresenter;
    private LinearLayout uploadingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_consult);
        initView();

        uploadingImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog();
                return true;
            }
        });
    }

    private void initView() {
        sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
        radioWoman = (RadioButton) findViewById(R.id.RadioWoman);
        radioMan = (RadioButton) findViewById(R.id.RadioMan);
        Button consultCommit = (Button) findViewById(R.id.consultCommit);
        Button consultGouBack = (Button) findViewById(R.id.consultGouBack);
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
        LinearLayout consultLayout = (LinearLayout) findViewById(R.id.consultLayout);
        uploadingImg = (LinearLayout) findViewById(R.id.uploadingImg);
        uploading = (ImageView) findViewById(R.id.conUploading);
        content0 = (EditText) findViewById(R.id.content0);
        content1 = (EditText) findViewById(R.id.content1);
        content3 = (EditText) findViewById(R.id.content3);
        content4 = (EditText) findViewById(R.id.content4);
        content5 = (EditText) findViewById(R.id.content5);
        content6 = (EditText) findViewById(R.id.content6);
        initPop();
        for (int i = 0; i < sexRadioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) sexRadioGroup.getChildAt(i);
            if (i == 0) {
                button.setChecked(true);
            } else {
                button.setChecked(false);
            }
        }
        consultCommit.setOnClickListener(this);
        consultGouBack.setOnClickListener(this);
        contentLayout.setOnClickListener(this);
        consultLayout.setOnClickListener(this);
        uploadingImg.setOnClickListener(this);

        radioWoman.setOnClickListener(this);
        radioMan.setOnClickListener(this);

        presenter = new ReportJobPresenter(this);
        uploadingImgPresenter = new UploadingImgPresenter(this);
    }

    public void initPop() {

        popView = View.inflate(this, R.layout.pop, null);

        b1 = (Button) popView.findViewById(R.id.but1);
        b2 = (Button) popView.findViewById(R.id.but2);
        b3 = (Button) popView.findViewById(R.id.but3);


        mPopupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#f6eaea")));


        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);

        //监听弹出框 消失
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                SettingUtils.setBackgroundAlpha(ConsultActivity.this, 1.0f); //为界面 设置正常透明度
            }
        });
        //取消
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

    }

    public void showPopupWindow(View view) {
        SettingUtils.setBackgroundAlpha(ConsultActivity.this, 0.5f);  // 弹出时设置半透明

        //底部弹出
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                CameraPhotoUtils.openPhotoAlbum(ConsultActivity.this);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
//                startTake();
                mPublicPhotoPath = CameraPhotoUtils.openCamera(ConsultActivity.this);
            }
        });
    }

    @SuppressLint("NewApi")
    public void addGridImage(String path) {
//        mView = new ImageView(this);

        canDelete = false;
        mView = new CanAddViewImageView(this);
        mView.setImageDrawable(getDrawable(R.mipmap.ic_launcher));


        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels / 3 - 30 - 30;
        params.height = getResources().getDimensionPixelSize(R.dimen.qqq);


        params.setGravity(Gravity.RIGHT);
        params.setMargins(30, 30, 30, 30);


        mView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mView.setLayoutParams(params);


        Bitmap mBitmap = CameraPhotoUtils.getSmallBitmap(path, params.width,
                params.height);
        mView.setImageBitmap(mBitmap);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                if (canDelete) {
                    showDialog();
                }

            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(this);

        dialog.setMessage("确定删除此图片？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploading.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tuankaung_tupian));
            }
        });
        dialog.create().show();
    }

   /* private void startTake() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断是否有相机应用
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //创建临时图片文件
            File photoFile = null;
            try {
                photoFile = CameraPhotoUtils.createPhotoFile();
                mPublicPhotoPath = photoFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //设置Action为拍照
            if (photoFile != null) {
                *//*takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                //这里加入flag
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri photoURI = FileProvider.getUriForFile(ConsultActivity.this, "applicationId.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);*//*
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProviderUtils.getUriForFile(this, photoFile));
                startActivityForResult(takePictureIntent, REQ_GALLERY);
            }
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mTargetW = mImageView.getWidth();
//        mTargetH = mImageView.getHeight();
      /*  if (resultCode != Activity.RESULT_OK || data == null) return;
        String photoPath = null;
        switch (requestCode) {
            case REQ_GALLERY: //拍照返回
                photoPath = Uri.parse(mPublicPhotoPath).getPath();
                //将照片添加到手机相册中
                CameraPhotoUtils.galleryAddPic(mPublicPhotoPath, this);
                if (!TextUtils.isEmpty(photoPath)) {
                    uploading.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                }
                File file = new File(photoPath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

                UserInfo userInfo = UserInfoManager.getInstance(this).getUserInfo();
                if (!TextUtils.isEmpty(userInfo.getId())) {
                    uploadingImgPresenter.uploadingImg(userInfo.getId(), part);
                    ProgressBarHorizontal.horizontalProgressBar(this);
                } else {
                    BaseMessage.showDialogAndFinish(this, "图片上传失败\n请在考勤打卡\n实名认证", "知道了");
                }
                break;
            //获取相册的图片
            case REQUEST_CODE_PICK_IMAGE:
                Uri uriData = data.getData();
                if (Build.VERSION.SDK_INT >= 19) {
                    photoPath = uriData.getPath();
                    photoPath = CameraPhotoUtils.getPath_above19(this, uriData);
                } else {
                    photoPath = CameraPhotoUtils.getFilePath_below19(this, uriData);
                }
                if (!TextUtils.isEmpty(photoPath)) {
                    uploading.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                }
                File file2 = new File(photoPath);
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
                MultipartBody.Part part2 = MultipartBody.Part.createFormData("image", file2.getName(), requestBody2);

                UserInfo userInfo2 = UserInfoManager.getInstance(this).getUserInfo();
                if (!TextUtils.isEmpty(userInfo2.getId())) {
                    uploadingImgPresenter.uploadingImg(userInfo2.getId(), part2);
                    ProgressBarHorizontal.horizontalProgressBar(this);
                } else {
                    BaseMessage.showDialogAndFinish(this, "图片上传失败\n请在考勤打卡\n实名认证", "知道了");
                }
                break;
        }*/
        String photoPath = CameraPhotoUtils.onActivityResult(this, mPublicPhotoPath, uploading, requestCode, resultCode, data, uploadingImgPresenter);
        addGridImage(photoPath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.consultCommit://提交
                if (submit()) {
                    commitConsult();
                }
                break;
            case R.id.consultGouBack://取消
                finish();
                break;
            case R.id.contentLayout://关闭输入框
                SettingUtils.closeInputMethod(ConsultActivity.this, v);
                break;
            case R.id.consultLayout://关闭输入框
                SettingUtils.closeInputMethod(ConsultActivity.this, v);
                break;
            case R.id.uploadingImg://弹出对话框
                showPopupWindow(v);
                break;
        }
    }

    private void commitConsult() {
        String text0 = content0.getText().toString().trim();
        String text1 = content1.getText().toString().trim();
        String text3 = content3.getText().toString().trim();
        String text4 = content4.getText().toString().trim();
        String text5 = content5.getText().toString().trim();
        String text6 = content6.getText().toString().trim();//备注输入框

        UserInfo userInfo = UserInfoManager.getInstance(this).getUserInfo();
        if (!TextUtils.isEmpty(userInfo.getId())) {
            if (radioMan.isChecked()) {
                presenter.commitZiXun(userInfo.getId(), 1, text0, text1, 1, text3, text4, text5, text6, ImageName.toString());
            } else if (radioWoman.isChecked()) {
                presenter.commitZiXun(userInfo.getId(), 1, text0, text1, 2, text3, text4, text5, text6, ImageName.toString());
            }
        } else {
            BaseMessage.showDialog(this, "请在考勤打卡\n实名认证", "知道了");
        }

    }

    private boolean submit() {
        String content0String = content0.getText().toString().trim();
        if (TextUtils.isEmpty(content0String)) {
            Toast.makeText(this, "学生姓名 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content1String = content1.getText().toString().trim();
        if (TextUtils.isEmpty(content1String)) {
            Toast.makeText(this, "学生联系电话 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content3String = content3.getText().toString().trim();
        if (TextUtils.isEmpty(content3String)) {
            Toast.makeText(this, "效果 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content4String = content4.getText().toString().trim();
        if (TextUtils.isEmpty(content4String)) {
            Toast.makeText(this, "证明人及联系电话 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String content5String = content5.getText().toString().trim();
        if (TextUtils.isEmpty(content5String)) {
            Toast.makeText(this, "接待人电话 不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onUploadingSucceed(String imgName) {
        ProgressBarHorizontal.dismessProgressBar();
        BaseMessage.showDialog(this, "图片上传成功", "完成");
        if (imgName != null) {
            if (ImageName.length() > 0) {
                ImageName.append("," + imgName);
            } else {
                ImageName.append(imgName);
            }
        }

    }

    @Override
    public void onUploadingFaild() {
        ProgressBarHorizontal.dismessProgressBar();
        BaseMessage.showDialog(this, "图片上传失败", "知道了");
    }

    @Override
    public void onCommitSucceed() {
        content0.setText("");
        content1.setText("");
        content3.setText("");
        content4.setText("");
        content5.setText("");
        content6.setText("");
        BaseMessage.showDialogAndFinish(this, "提交成功", "完成");
        if (ImageName.length() > 0)
            ImageName.delete(0, ImageName.length());
    }

    @Override
    public void onCommitFilad() {
        BaseMessage.showDialog(this, "提交失败\n请稍后再试", "知道了");
    }
}
