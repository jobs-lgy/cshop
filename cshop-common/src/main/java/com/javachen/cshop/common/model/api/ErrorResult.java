package com.javachen.cshop.common.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求失败
 * <p>Title: ErrorResult</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2019/1/23 15:07
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorResult extends AbstractBaseResult {
    private int code;
    private String title;

    /**
     * 调试信息
     */
    private String detail;
}
