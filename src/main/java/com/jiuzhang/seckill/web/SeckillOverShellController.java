package com.jiuzhang.seckill.web;

import com.jiuzhang.seckill.services.SeckillActivityService;
import com.jiuzhang.seckill.services.SeckillOverSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeckillOverShellController {

    @Autowired
    private SeckillOverSellService seckillOverShellService;

    @Autowired
    private SeckillActivityService seckillActivityService;
    /**
     * 使用 lua 脚本处理抢购请求
     * @param seckillActivityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/seckill/{seckillActivityId}")
    public String seckillCommodity(@PathVariable long seckillActivityId) {
        boolean stockValidateResult =
                seckillActivityService.seckillStockValidator(seckillActivityId);
        return stockValidateResult ? "恭喜你秒杀成功" : "商品已经售完，下次再来";
    }
    /*
     *
     * processing flash sale request
     *
     * @param seckillActivityId
     * @return
     *
     * */
    //@ResponseBody
    //@RequestMapping("/seckill/{seckillActivityId}")
    public String seckill(@PathVariable long seckillActivityId) {
        return seckillOverShellService.processSeckill(seckillActivityId);
    }
}
