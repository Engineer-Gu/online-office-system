package xyz.lovegu.emos.api.db.dataobject;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TbAmectTypeDO {
    private Integer id;
    private String type;
    private BigDecimal money;
    private Boolean systemic;
}
