package com.br.yurisimao.domain;

import com.br.yurisimao.primarydb.entity.PrimaryEntity;
import com.br.yurisimao.exception.LineUnexpectedException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import static com.br.yurisimao.constants.Constants.REGEX_PIPE;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PrimaryDomain {

    private Long position;
    private String uuid;
    private String name;

    public static PrimaryDomain toDomain(@NotNull final String line) throws LineUnexpectedException {

        final String[] array = line.split(REGEX_PIPE);

        if (array.length < 3) {
            throw new LineUnexpectedException("Incorrect Content! " + System.lineSeparator() + "Line: " + line);
        }

        final PrimaryDomain domain = new PrimaryDomain();
        domain.setPosition(Long.parseLong(array[0]));
        domain.setUuid(array[1]);
        domain.setName(array[2]);

        return domain;

    }

    public static PrimaryDomain toDomain(@NotNull final PrimaryEntity entity) {

        final PrimaryDomain domain = new PrimaryDomain();
        domain.setPosition(entity.getPosition());
        domain.setUuid(entity.getUuid());
        domain.setName(entity.getName());

        return domain;

    }
}


