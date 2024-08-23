package com.jo0oy.api.menu.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.menu.business.MenuBusiness;
import com.jo0oy.api.menu.dto.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/open-api/menus")
@RestController
public class MenuOpenApiController {

    private final MenuBusiness menuBusiness;

    @GetMapping("/{menuId}")
    public ResponseEntity<Api<MenuResponse>> getMenuById(
        @PathVariable("menuId") String menuId
    ) {
        var data = menuBusiness.getMenuById(menuId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }

    @GetMapping("")
    public ResponseEntity<Api<List<MenuResponse>>> getMenuList() {
        var data = menuBusiness.getMenuList();

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }
}
