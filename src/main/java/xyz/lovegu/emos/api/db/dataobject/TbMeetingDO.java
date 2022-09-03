package xyz.lovegu.emos.api.db.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_meeting
 *
 * @author
 */
@Data
public class TbMeetingDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String uuid;

    private String title;

    private Integer creatorId;

    private String date;

    private String place;

    private String start;

    private String end;

    private Short type;

    private Object members;

    private String desc;

    private String instanceId;

    private String present;

    private String unpresent;

    private Short status;

    private Date createTime;
}