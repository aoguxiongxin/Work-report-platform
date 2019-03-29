package atest.test.com.app.constant;



import atest.test.com.app.model.bean.MineBean.StudentBean;
import atest.test.com.app.model.bean.notificationBean.ChangeStatusBean;
import atest.test.com.app.model.bean.notificationBean.LPLBean;
import atest.test.com.app.model.bean.notificationBean.MineSendMessageBean;
import atest.test.com.app.model.bean.notificationBean.ReceiveMessageBean;
import atest.test.com.app.model.bean.notificationBean.ReplyBean;
import atest.test.com.app.model.bean.notificationBean.SendResultBean;
import atest.test.com.app.model.bean.notificationBean.StatusBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Shadow on 2017/11/2.
 */

public interface NotificationApi {

    //通知--------------------------------------

    //发送通知
    @FormUrlEncoded
    @POST("mobileMessage/submitMessage")
    Observable<SendResultBean> submitMessage(@Field("userId") String userId, @Field("content") String content, @Field("position") String position);


    //查询发送过的通知
    @FormUrlEncoded
    @POST("mobileMessage/querySendMessageLog")
    Observable<MineSendMessageBean> querySendMessageLog(@Field("userId") String userId);

    //查询收到的通知
    @FormUrlEncoded
    @POST("mobileMessage/queryReceiveMessageLog")
    Observable<ReceiveMessageBean> queryReceiveMessageLog(@Field("userId") String userId);

    //上级领导列表
    @FormUrlEncoded
    @POST("mobileMessage/getLeaderUserList")
    ResponseBody getLeaderUserList();

    //下属列表
    @FormUrlEncoded
    @POST("mobileMessage/getLowerPositionList")
    Observable<LPLBean> getLowerPositionList(@Field("userId") String userId);

    //改变信息状态
    @FormUrlEncoded
    @POST("mobileMessage/changeMessageStatus")
    Observable<ChangeStatusBean> changeMessageStatus(@Field("userId") String userId, @Field("messageId") int messageId);


    //数据统计---------------------

    //查询学院
    @GET("mobileGoodNews/getCollegeList")
    ResponseBody getCollegeList();

    //查询学生信息
    @FormUrlEncoded
    @POST("mobileGoodNews/queryGoodNews")
    Observable<StudentBean> queryGoodNews(@Field("userId") String userId);

    //判断这个通知  是否是自己的上级给自己发的

    @FormUrlEncoded
    @POST("mobileMessage/ismeMessage")
    Observable<StudentBean> queryGoodNews(@Field("userId") String userId, @Field("sendId") String sendId);

    //查询每个职务下的已阅状态

    @FormUrlEncoded
    @POST("mobileMessage/queryOneSendMessageLog")
    Observable<StatusBean> queryOneSendMessageLog(@Field("userId") String userId, @Field("mid") String mid, @Field("position") String position);

    //接收方设置回复信息

    @FormUrlEncoded
    @POST("mobileMessage/setMessageReply")
    Observable<ReplyBean> setMessageReply(@Field("userId") String userId, @Field("messageId") String mid, @Field("reply") String reply);

}
