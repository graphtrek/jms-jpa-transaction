package co.grtk.controller;

import co.grtk.entity.DepositDecreaseEntity;
import co.grtk.service.DepositDecreaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("deposit")
@RequiredArgsConstructor
public class DepositDecreaseController {

    private final DepositDecreaseService depositDecreaseService;

    @Transactional
    @PostMapping("/transactional/create")
    public ResponseEntity<DepositDecreaseEntity> create(@RequestBody DepositDecreaseEntity depositDecreaseModel) {
        try {
            DepositDecreaseEntity depositDecreaseEntity = depositDecreaseService.create(depositDecreaseModel);
            return new ResponseEntity<>(depositDecreaseEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("CREATE ERROR", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/transactional/update")
    public ResponseEntity<DepositDecreaseEntity> update(@RequestBody DepositDecreaseEntity depositDecreaseModel) {
        try {
            DepositDecreaseEntity depositDecreaseEntity = depositDecreaseService.update(depositDecreaseModel);
            return new ResponseEntity<>(depositDecreaseEntity, HttpStatus.OK);
        } catch (Exception e) {
            log.error("UPDATE ERROR", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
