package xyz.lovegu.emos.api.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import xyz.lovegu.emos.api.common.util.PageUtils;
import xyz.lovegu.emos.api.db.dao.TbMeetingDao;
import xyz.lovegu.emos.api.db.dataobject.TbMeetingDO;
import xyz.lovegu.emos.api.exception.EmosException;
import xyz.lovegu.emos.api.service.MeetingService;
import xyz.lovegu.emos.api.task.MeetingWorkflowTask;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 老顾
 * @title: MeetingServiceImpl
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/28 10:39
 */
@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final TbMeetingDao meetingDao;

    private final MeetingWorkflowTask meetingWorkflowTask;

    private static HashMap map;

    private final RedisTemplate redisTemplate;


    @Override
    public PageUtils searchOfflineMeetingByPage(HashMap param) {
        ArrayList<HashMap> list = meetingDao.searchOfflineMeetingByPage(param);
        long count = meetingDao.searchOfflineMeetingCount(param);
        int start = MapUtil.getInt(param,"start");
        int length = MapUtil.getInt(param,"length");
        for (HashMap map : list) {
            String meeting = (String) map.get("meeting");
            if (meeting!=null && meeting.length()>0){
                map.replace("meeting", JSONUtil.parseArray(meeting));
            }
        }
        return new PageUtils(list, count, start, length);
    }

    @Override
    public int insert(TbMeetingDO meeting) {
        int rows = meetingDao.insert(meeting);
        if (rows != 1){
            throw new EmosException("会议添加失败");
        }
        meetingWorkflowTask.startMeetingWorkflow(meeting.getUuid(), meeting.getCreatorId(),meeting.getTitle(),
                meeting.getDate(),meeting.getStart()+":00","线下会议");
        return rows;
    }

    @Override
    public ArrayList<HashMap> searchOfflineMeetingInWeek(HashMap param) {
        ArrayList<HashMap> list = meetingDao.searchOfflineMeetingInWeek(param);
        return list;
    }

    @Override
    public HashMap searchMeetingInfo(short status,long id) {
        //判断正在进行的会议
        if (status == 4){
            map = meetingDao.searchCurrentMeetingInfo(id);
        }else {
            map = meetingDao.searchMeetingInfo(id);
        }
        return map;
    }

    @Override
    public int deleteMeetingApplication(HashMap param) {
        Long id = MapUtil.getLong(param,"id");
        String uuid = MapUtil.getStr(param, "uuid");
        String instanceId = MapUtil.getStr(param, "instanceId");
        //查询会议详情，一会要判断师父距离会议开始不足20分钟
        HashMap meeting = meetingDao.searchMeetingById(param);
        String date = MapUtil.getStr(meeting, "date");
        String start = MapUtil.getStr(meeting, "start");
        Integer status = MapUtil.getInt(meeting, "status");
        boolean isCreator = Boolean.parseBoolean(MapUtil.getStr(meeting, "isCreator"));
        DateTime dateTime = DateUtil.parse(date + " " + start);
        DateTime now = DateUtil.date();
        //距离会议开始不足20分钟，不能删除会议
        if (now.isAfterOrEquals(dateTime.offset(DateField.MINUTE,20))){
            throw new EmosException("距离会议开始不足20分钟，不能删除会议");
        }
        //只能申请人删除会议
        if (!isCreator) {
            throw new EmosException("只能申请人删除该会议");
        }
        //待审批和未开始的会议可以删除
        if (status == 1 || status == 3){
            int rows = meetingDao.deleteMeetingApplication(param);
            if(rows == 1){
                String reason = MapUtil.getStr(param, "reason");
                meetingWorkflowTask.deleteMeetingApplication(uuid,instanceId,reason);
            }
            return rows;
        }else {
            throw new EmosException("只能删除待审批和未开始的会议");
        }
    }
    @Override
    public PageUtils searchOnlineMeetingByPage(HashMap param) {
        ArrayList<HashMap> list = meetingDao.searchOnlineMeetingByPage(param);
        long count = meetingDao.searchOnlineMeetingCount(param);
        int start = (Integer) param.get("start");
        int length = (Integer) param.get("length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    public Long searchRoomIdByUUID(String uuid) {
        if(redisTemplate.hasKey(uuid)){
            Object temp=redisTemplate.opsForValue().get(uuid);
            long roomId=Long.parseLong(temp.toString());
            return roomId;
        }
        return null;
    }

    @Override
    public ArrayList<HashMap> searchOnlineMeetingMembers(HashMap param) {
        ArrayList<HashMap> list=meetingDao.searchOnlineMeetingMembers(param);
        return list;
    }

    @Override
    public boolean searchCanCheckinMeeting(HashMap param) {
        long count=meetingDao.searchCanCheckinMeeting(param);
        return count==1?true:false;
    }

    @Override
    public int updateMeetingPresent(HashMap param) {
        int rows=meetingDao.updateMeetingPresent(param);
        return rows;
    }
}
