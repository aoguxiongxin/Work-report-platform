package atest.test.com.app.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import atest.test.com.app.R;
import atest.test.com.app.model.bean.ReportBean.ReportMyLogBean;

/**
 * 作者: 赵虔
 * 时间: 2017/10/26
 * 类作用:
 */

public class ReportMyExAdapter extends BaseExpandableListAdapter {
    private String DefaultHeadUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508998138937&di=f6a5867026007513590c0ceeeea66fca&imgtype=0&src=http%3A%2F%2F2e.zol-img.com.cn%2Fproduct%2F64_500x2000%2F410%2Fceneo4LyDg8c.jpg";
    private Context context;
    private List<ReportMyLogBean.ObjectBean> groupList;
    private List<ReportMyLogBean.ObjectBean.ReportlogviewBean> childList;
    private String[] group;
    private ReportMyLogBean.ObjectBean.ReportlogviewBean[][] child;


    public ReportMyExAdapter(Context context, List<ReportMyLogBean.ObjectBean> groupList) {
        this.context = context;
        this.groupList = groupList;
        initData();
    }

    private void initData() {
        group = new String[groupList.size()];
        child = new ReportMyLogBean.ObjectBean.ReportlogviewBean[groupList.size()][];

        for (int i = 0; i < group.length; i++) {
            //一级列表的数据
            group[i] = groupList.get(i).getTypename();

            //得到二级列表的集合
            childList = groupList.get(i).getReportlogview();

            //创建二维数组的二级数据集合,也就是[][]  的第二个[]的数据
            ReportMyLogBean.ObjectBean.ReportlogviewBean[] childStr = new ReportMyLogBean.ObjectBean.ReportlogviewBean[childList.size()];
            for (int y = 0; y < childStr.length; y++) {
                childStr[y] = childList.get(y);
            }

            //把数据添加到二级列表
            child[i] = childStr;
        }
    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.report_my_group_item, null);
            viewHolder = new GroupViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.myGroupName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(group[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.report_my_child_item, null);
            viewHolder = new ChildViewHolder();
            viewHolder.headImg = (ImageView) convertView.findViewById(R.id.myChildHeadImg);
            viewHolder.msg = (TextView) convertView.findViewById(R.id.myChildMsg);
            viewHolder.time = (TextView) convertView.findViewById(R.id.myChildTiem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.time.setText(child[groupPosition][childPosition].getSendtime());
        //地点
        String site = (String) child[groupPosition][childPosition].getSite();
        //效果
        String result = child[groupPosition][childPosition].getResult();

        if (groupList.get(groupPosition).getTypename().equals("其它")) {
            viewHolder.msg.setText("内容 :" + (String) child[groupPosition][childPosition].getContent());
        } else {
            viewHolder.msg.setText(!TextUtils.isEmpty(site) && !"null".equals(site) ? "地点: " + site : "效果: " + result);
        }
        setHeadImg(viewHolder.headImg);
        return convertView;
    }


    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    class GroupViewHolder {
        TextView name;
    }

    class ChildViewHolder {
        ImageView headImg;
        TextView time;
        TextView msg;
    }

    public void refreshData(List<ReportMyLogBean.ObjectBean> tempList) {
        if (tempList != null && tempList.size() > 0) {
            if (groupList != null) {
                groupList.clear();
                childList.clear();
                childList = null;
                groupList.addAll(tempList);
                initData();
            } else {
                groupList = tempList;
            }
        }
        notifyDataSetChanged();
    }

    private void setHeadImg(ImageView img) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//加载圆图片
                .error(R.drawable.wode_wodedaka_jilu)//加载错误图片
                .placeholder(R.drawable.wode_wodedaka_jilu)//加载预加载图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置磁盘缓存模式
                .fallback(R.drawable.wode_wodedaka_jilu);//设置备用图片,当图片地址为空的时候显示
        Glide.with(context).setDefaultRequestOptions(options).load(R.drawable.wode_wodedaka_jilu).into(img);
    }
}
