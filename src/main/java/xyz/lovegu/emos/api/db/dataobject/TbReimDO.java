package xyz.lovegu.emos.api.db.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TbReimDO {
    private Integer id;
    private Integer userId;
    private String content;
    private BigDecimal amount;
    private BigDecimal anleihen;
    private BigDecimal balance;
    private Byte typeId;
    private Byte status;
    private String instanceId;
    private Date createTime;
}
