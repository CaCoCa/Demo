package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Music 实体说明")
public class Music {
    @Schema(description = "名称")
    private String name;
    @Schema(description = "播放时间")
    private BigDecimal size;
    @Schema(description = "作者")
    private String author;
    @Schema(description = "所属公司")
    private String company;
}
