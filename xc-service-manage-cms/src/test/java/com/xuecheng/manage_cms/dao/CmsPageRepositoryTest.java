package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


}
