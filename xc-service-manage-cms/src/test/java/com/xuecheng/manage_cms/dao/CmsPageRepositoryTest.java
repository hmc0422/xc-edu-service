package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll() {
        List<CmsPage> cmsPageList = cmsPageRepository.findAll();
        System.out.println(cmsPageList.toString());
    }

    /**
     * 分页查询
     */
    @Test
    public void testFindPage() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<CmsPage> cmsPageList = cmsPageRepository.findAll(pageable);
        System.out.println(cmsPageList);
    }

    /**
     * 根据id修改
     */
    @Test
    public void testUpdateById() {
        Optional<CmsPage> cmsPageOpt = cmsPageRepository.findById("5ada939168db524a909d30a8");
        if (cmsPageOpt.isPresent()) {
            CmsPage cmsPage = cmsPageOpt.get();
            cmsPage.setPageAliase("课程预览页面");
            CmsPage cmsPage1 = cmsPageRepository.save(cmsPage);
            System.out.println(cmsPage1);
        }
    }

    /**
     * 自定义查询条件：精确匹配
     */
    @Test
    public void testFindByExemple() {
        // 条件值对象
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        // 条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        // 分页条件
        Pageable pageable = PageRequest.of(0, 10);
        Page<CmsPage> pages = cmsPageRepository.findAll(example, pageable);
        System.out.println(pages.getContent());
    }

    /**
     * 自定义查询条件：模糊匹配
     */
    @Test
    public void testFindByExemple2() {
        // 条件值对象
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageAliase("分类");
        // 条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        // exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        // 分页条件
        Pageable pageable = PageRequest.of(0, 10);
        Page<CmsPage> pages = cmsPageRepository.findAll(example, pageable);
        System.out.println(pages.getContent());
    }


}
