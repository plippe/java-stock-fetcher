package com.secret.app.users;

import com.secret.app.portfolios.Portfolio;
import com.secret.app.ranking.Rank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String userId;
    private Rank rank;
    private Set<User> friends;
    private List<Portfolio> portfolios;
}
