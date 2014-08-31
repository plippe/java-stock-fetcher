package com.secret.app.portfolios;

import com.secret.app.positions.Position;
import com.secret.app.users.User;
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
