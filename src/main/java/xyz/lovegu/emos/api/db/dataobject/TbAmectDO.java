package xyz.lovegu.emos.api.db.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 86153
 */
@Data
public class TbAmectDO {
    private Integer id;
    private String uuid;
    private Integer userId;
    private BigDecimal amount;
    private Byte typeId;
    private String reason;
    private String prepayId;
    private Byte status;
    private Date createTime;
}
