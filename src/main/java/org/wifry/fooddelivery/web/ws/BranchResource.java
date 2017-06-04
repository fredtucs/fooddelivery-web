package org.wifry.fooddelivery.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wifry.fooddelivery.model.Branch;
import org.wifry.fooddelivery.services.admin.BranchService;
import org.wifry.fooddelivery.web.ws.model.BranchModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.MediaType;
import org.wifry.fooddelivery.web.ws.model.OrderMessageModel;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_BRANCHES)
@Scope("request")
public class BranchResource {

    @Autowired
    private BranchService branchService;

    private static final Logger LOG = LoggerFactory.getLogger(BranchResource.class);

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<BranchModel> getBranches() {
        List<Branch> branches = branchService.list();
        List<BranchModel> branchModels = new ArrayList<>();
        branches.stream().forEach((branch) -> {
            try {
                branchModels.add(new BranchModel(branch.getBranchId(), branch.getName(), branch.getBranchAddress()));
            } catch (Exception e) {
                LOG.error(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        return branchModels;
    }

    @RequestMapping(value = "message", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderMessageModel getMessage() {
        OrderMessageModel messageModel = new OrderMessageModel();
        messageModel.setOrderMessage("Thank You. YourOrder has been received!");
        messageModel.setOrderId(15L);
        messageModel.setOrderTime(new Date());
        messageModel.setTotalPrice(38.0);
        return messageModel;
    }

}
