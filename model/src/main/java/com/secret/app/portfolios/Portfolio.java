package com.secret.app.portfolios;

import com.secret.app.positions.Position;
import com.secret.app.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Portfolio {
    private User owner;
    private String name;
    private List<Position> positions;
}
