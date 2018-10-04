package com.apap.tutorial4.service;


import com.apap.tutorial4.model.DealerModel;

import java.util.List;
import java.util.Optional;

public interface DealerService {
    Optional<DealerModel> getDealerDetailById(Long id);

    List<DealerModel> getAllDealer();
    void addDealer(DealerModel dealer);
    void deleteDealer(DealerModel dealer);
    void updateDealer(Long dealerId, DealerModel newDealer);
}
