package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "CMS管理页面接口", description = "CMS页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

    /**
     * 页面查询
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation("分页查询列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")})
    public QueryResponseResult findPageList(int page, int size, QueryPageRequest queryPageRequest);
}
