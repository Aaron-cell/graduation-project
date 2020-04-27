package com.gp.resources_manager.resources.supplier.controller;

import com.gp.api.resources.supplier.SupplierControllerApi;
import com.gp.framework.domain.resource.supplier.Supplier;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码农界的小学生
 * @description:供应商控制层
 * @title: SupplierController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 2:50
 */
@RestController
@RequestMapping("/resources/supplier")
public class SupplierController implements SupplierControllerApi {
    @Autowired
    private SupplierService supplierService;

    @Override
    @PostMapping("/addsupplier")
    public ResponseResult AddSupplier(@RequestBody Supplier supplier) {
        return supplierService.AddSupplier(supplier);
    }

    @Override
    @GetMapping("/getsupplier/list/{page}/{size}")
    public QueryResponseResult getSupplierList(@PathVariable("page") int page,
                                               @PathVariable("size") int size) {
        if(page <= 0){
            page = 1;
        }
        if (size <= 0){
            size = 10;
        }
        return supplierService.getSupplierList(page,size);
    }

    @Override
    @PutMapping("/editsupplier")
    public ResponseResult alterSupplier(@RequestBody Supplier supplier) {
        return supplierService.alterSupplier(supplier);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseResult deleteSupplier(@RequestParam("ids") String ids) {
        //ids 规则： id1,id2,id3
        return supplierService.deleteSupplier(ids);
    }

}
