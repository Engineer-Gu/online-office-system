package xyz.lovegu.emos.api.service;

import xyz.lovegu.emos.api.common.util.PageUtils;
import xyz.lovegu.emos.api.db.dataobject.TbMeetingRoomDO;

import java.util.ArrayList;
import java.util.HashMap;

public interface MeetingRoomService {

    public ArrayList<HashMap> searchAllMeetingRoom();

    public HashMap searchById(int id);

    public ArrayList<String> searchFreeMeetingRoom(HashMap param);

    public PageUtils searchMeetingRoomByPage(HashMap param);

    public int insert(TbMeetingRoomDO meetingRoom);

    public int update(TbMeetingRoomDO meetingRoom);

    public int deleteMeetingRoomByIds(Integer[] ids);
}
