package com.transfer.app.controller;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.service.impl.TransferServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class TransferController {

    private final TransferServiceImpl transferService;

    @Autowired
    public TransferController(TransferServiceImpl transferService) {
        this.transferService = transferService;
    }

    @PostMapping(value = "/transfer")
    @ApiOperation("Метод предназначен для перевода денег.")
    public String transferMoney(TransferMoneyRequest transferMoneyRequest) {
        return transferService.transferMoney(transferMoneyRequest);
    }
}
