package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    public QueryResponseResult findPageList(int page, int size, QueryPageRequest queryPageRequest) {
        if (null == queryPageRequest) {
            queryPageRequest = new QueryPageRequest();
        }
        // 自定义查询条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotBlank(queryPageRequest.getSiteId())) { // 站点ID
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotBlank(queryPageRequest.getTemplateId())) { // 模板id
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotBlank(queryPageRequest.getPageAliase())) { // 别名
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
            exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        // 分页参数
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example, pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsPages.getContent()); // 数据列表
        queryResult.setTotal(cmsPages.getTotalElements()); // 数据总记录数
        QueryResponseResult responseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return responseResult;
    }


    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult addCmsPageInfo(CmsPage cmsPage) {
        if (null == cmsPage) {
            throw new CustomException(CommonCode.FAIL);
        }
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (null != cmsPage1) {
            // 抛出异常：页面已经存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        // 页面主键由spring data自动生成
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    /**
     * 根据ID查询页面
     *
     * @param id
     * @return
     */
    public CmsPage findCmsPageInfoById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        Optional<CmsPage> opt = cmsPageRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    /**
     * 修改页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult updateCmsPageInfo(String id, CmsPage cmsPage) {
        CmsPage one = this.findCmsPageInfoById(id);
        if (null != one) {
            one.setSiteId(cmsPage.getSiteId());
            one.setPageName(cmsPage.getPageName());
            one.setPageAliase(cmsPage.getPageAliase());
            one.setPageWebPath(cmsPage.getPageWebPath());
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            one.setTemplateId(cmsPage.getTemplateId());
            CmsPage save = cmsPageRepository.save(one);
            return new CmsPageResult(CommonCode.SUCCESS, save);
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    /**
     * 根据ID删除页面
     *
     * @param id
     * @return
     */
    public ResponseResult delCmsPageInfo(String id) {
        CmsPage one = this.findCmsPageInfoById(id);
        if (null != one) {
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
