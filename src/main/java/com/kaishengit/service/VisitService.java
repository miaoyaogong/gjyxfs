package com.kaishengit.service;

import com.kaishengit.mapper.VisitMapper;
import com.kaishengit.pojo.Visit;
import com.kaishengit.util.AddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
@Transactional
public class VisitService {

    private Logger logger = LoggerFactory.getLogger(VisitService.class);

    @Inject
    private VisitMapper visitMapper;

    /**
     * 保存新客户
     * @param request
     */
    public void saveNewVisit(final HttpServletRequest request) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                String ip = AddressUtils.getRemortIP(request);
                String ipAddress = "";
                AddressUtils au = new AddressUtils();
                try {
                    ipAddress = au.getAddresses("ip="+ip, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Visit visit = new Visit();
                visit.setIp(ip);
                visit.setIpAddress(ipAddress);
                visit.setCreatetime(dateString);
                visitMapper.save(visit);
                logger.info("-添加了新访问者-ip:{}, 地址:{}", visit.getIp(), visit.getIpAddress());
            }
        });
        t.start();

    }

}
