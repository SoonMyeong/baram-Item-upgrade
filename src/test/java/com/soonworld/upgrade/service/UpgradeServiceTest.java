package com.soonworld.upgrade.service;

import com.soonworld.upgrade.service.vo.UpgradeItem;
import org.hamcrest.CoreMatchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
public class UpgradeServiceTest {

    @MockBean
    private UpgradeItemService upgradeItemService;
    private UpgradeItem item;

    @Test
    public void upgradeItemCase1() throws Exception{
        item = UpgradeItem.builder()
                .currentItemLevel(1)
                .upgradeItemUsed(false)
                .upgradeDoubleItemUsed(false)
                .resultItemAddLevel(1)
                .upgradeResult(true)
                .build();

        //given
        given(upgradeItemService.upgradeItem(item)).willReturn(item);
        //when
        UpgradeItem result  = upgradeItemService.upgradeItem(item);
        //then
        assertThat(result.getResultItemAddLevel(), CoreMatchers.is(1));
    }

}
