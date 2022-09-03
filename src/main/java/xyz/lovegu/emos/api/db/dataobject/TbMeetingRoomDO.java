package xyz.lovegu.emos.api.db.dataobject;

import lombok.Data;

@Data
public class TbMeetingRoomDO {
    private Integer id;
    private String name;
    private Short max;
    private String desc;
    private Byte status;
}
