package com.jo0oy.api.menu.controller;

import com.jo0oy.api.global.api.Api;
import com.jo0oy.api.menu.business.MenuBusiness;
import com.jo0oy.api.menu.dto.request.MenuRequest;
import com.jo0oy.api.menu.dto.response.MenuResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/menus")
@RestController
public class MenuApiController {

    private final MenuBusiness menuBusiness;

    @PostMapping("")
    public ResponseEntity<Api<Void>> registerMenu(
        @Valid @RequestBody MenuRequest request
    ) {
        menuBusiness.register(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                Api.OK()
            );
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Api<MenuResponse>> updateMenu(
        @PathVariable("menuId") String menuId,
        @Valid @RequestBody MenuRequest request
    ) {
        var data = menuBusiness.update(menuId, request);

        return ResponseEntity
            .ok()
            .body(
                Api.OK(data)
            );
    }


    @DeleteMapping("/{menuId}")
    public ResponseEntity<Api<Void>> deleteMenu(
        @PathVariable("menuId") String menuId
    ) {
        menuBusiness.delete(menuId);

        return ResponseEntity
            .ok()
            .body(
                Api.OK()
            );
    }
}
