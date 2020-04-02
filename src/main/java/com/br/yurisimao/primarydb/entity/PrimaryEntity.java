package com.br.yurisimao.primarydb.entity;

import javax.persistence.*;

import com.br.yurisimao.domain.PrimaryDomain;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "PRIMARY_DATABASE_TEST")
public class PrimaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long position;
    private String uuid;
    private String name;

    public static PrimaryEntity toEntity(@NotNull final PrimaryDomain domain) {

        final PrimaryEntity entity = new PrimaryEntity();
        entity.setPosition(domain.getPosition());
        entity.setUuid(domain.getUuid());
        entity.setName(domain.getName());

        return entity;

    }

}




