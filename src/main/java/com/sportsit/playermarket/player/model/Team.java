package com.sportsit.playermarket.player.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "public",name = "team")
public class Team {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "team_id_seq", sequenceName = "team_id_seq", allocationSize = 1)
    @Column(name = "id", insertable = true, updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq")
    private Long teamId;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;

    @Column(name = "team_transfer_fees")
    @NotNull
    private BigDecimal teamTransferFee;

    @Column(name = "team_commission")
    @NotNull
    private BigDecimal teamCommission;

    @Column(name = "contract_fee")
    @NotNull
    private BigDecimal contractFee;

}
