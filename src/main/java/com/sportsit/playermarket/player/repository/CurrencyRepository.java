package com.sportsit.playermarket.player.repository;

import com.sportsit.playermarket.player.model.Currency;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface CurrencyRepository extends Repository<Currency, Long>, JpaSpecificationExecutor<Currency> {

}
