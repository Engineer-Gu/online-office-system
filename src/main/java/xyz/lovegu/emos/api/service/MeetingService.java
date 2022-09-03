package xyz.lovegu.emos.api.service;

import xyz.lovegu.emos.api.common.util.PageUtils;
import xyz.lovegu.emos.api.db.dataobject.TbMeetingDO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 老顾
 * @title: MeetingService
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/28 10:39
 */
public interface MeetingService {

    PageUtils searchOfflineMeetingByPage(HashMap param);

    public int insert(TbMeetingDO meeting);

    public ArrayList<HashMap> searchOfflineMeetingInWeek(HashMap param);

    public HashMap searchMeetingInfo(short status, long id);

    public int deleteMeetingApplication(HashMap param);

    public PageUtils searchOnlineMeetingByPage(HashMap param);

    public Long searchRoomIdByUUID(String uuid);

    public ArrayList<HashMap> searchOnlineMeetingMembers(HashMap param);

    public boolean searchCanCheckinMeeting(HashMap param);

    public int updateMeetingPresent(HashMap param);
}
