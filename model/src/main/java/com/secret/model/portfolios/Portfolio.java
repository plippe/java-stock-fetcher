package com.secret.model.portfolios;

import com.secret.model.positions.Position;
import com.secret.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class Portfolio {
    private final User owner;
    private final String name;
    private final List<Position> positions;
}
