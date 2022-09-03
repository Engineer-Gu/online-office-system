package xyz.lovegu.emos.api.db.dao;

import xyz.lovegu.emos.api.db.dataobject.TbMeetingRoomDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbMeetingRoomDao {

    public ArrayList<HashMap> searchAllMeetingRoom();
    
    public HashMap searchById(int id);
    
    public ArrayList<String> searchFreeMeetingRoom(HashMap param);

    public ArrayList<HashMap> searchMeetingRoomByPage(HashMap param);

    public long searchMeetingRoomCount(HashMap param);

    public int insert(TbMeetingRoomDO meetingRoom);

    public int update(TbMeetingRoomDO meetingRoom);

    public boolean searchCanDelete(Integer[] ids);

    public int deleteMeetingRoomByIds(Integer[] ids);
}
