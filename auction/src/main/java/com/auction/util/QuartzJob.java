package com.auction.util;

import com.auction.dao.AuctionDAO;
import com.auction.entity.Auction;
import com.auction.entity.AuctionStatus;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.sql.Timestamp;
import java.util.Iterator;

/**
 * Created by Stepan
 * on 11-Dec-17
 */

@Component
public class QuartzJob implements Job {

    @Autowired
    private AuctionDAO auctionDAO;

    private EmailNotification emailNotification;

    public void setEmailNotification() {
        this.emailNotification = new EmailNotification();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        Auction auction;
        long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        Iterator<Auction> iterator = auctionDAO.fintNotInOpen().iterator(); //Get list of not closed auctions
        while (iterator.hasNext()) {
            auction = iterator.next();
            AuctionStatus auctionStatus = auction.getStatus();
            if (auctionStatus == AuctionStatus.PLANNED && (auction.getStartDate().getTime() <= currentTime)) {
                //If current time < opening time
                auction.setStatus(AuctionStatus.OPEN);
                //Change status to Open
                auctionDAO.update(auction);
            }
            if (auctionStatus == AuctionStatus.OPEN && (auction.getEndDate().getTime() <= currentTime)) {
                //If opening time < closing time
                auction.setStatus(AuctionStatus.CLOSED);
                auctionDAO.update(auction);
                //Change status to Closed
                //After that send email-notifications to users
                if(!auction.getParticipants().isEmpty())
                    emailNotification.sendEmail(auction);
            }
        }
    }
}
