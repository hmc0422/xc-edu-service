package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
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

    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    @ApiOperation("添加页面")
    public CmsPageResult addCmsPageInfo(CmsPage cmsPage);

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    @ApiOperation("通过id查找页面")
    public CmsPage findCmsPageInfoById(String id);

    /**
     * 修改页面
     *
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改页面")
    public CmsPageResult updateCmsPageInfo(String id, CmsPage cmsPage);

    /**
     * 根据ID删除页面
     *
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除页面")
    public ResponseResult delCmsPageInfo(String id);

}
