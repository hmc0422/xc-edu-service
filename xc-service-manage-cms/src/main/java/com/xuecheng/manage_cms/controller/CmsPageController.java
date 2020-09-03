package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private PageService pageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findPageList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        return pageService.findPageList(page, size, queryPageRequest);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult addCmsPageInfo(@RequestBody CmsPage cmsPage) {
        return pageService.addCmsPageInfo(cmsPage);
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPage findCmsPageInfoById(@PathVariable("id") String id) {
        return pageService.findCmsPageInfoById(id);
    }

    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult updateCmsPageInfo(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return pageService.updateCmsPageInfo(id, cmsPage);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delCmsPageInfo(@PathVariable("id") String id) {
        return pageService.delCmsPageInfo(id);
    }
}
